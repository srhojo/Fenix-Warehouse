package com.hojo.fenix.warehouse.services.impl;

import com.hojo.fenix.warehouse.dao.WarehouseVehicleDao;
import com.hojo.fenix.warehouse.dao.WarehouseVehicleInspectionDao;
import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.cdm.OffsetPagination;
import com.hojo.fenix.warehouse.domain.cdm.OffsetPaginationRequest;
import com.hojo.fenix.warehouse.domain.entities.VehicleEntity;
import com.hojo.fenix.warehouse.domain.entities.VehicleInspection;
import com.hojo.fenix.warehouse.domain.mappers.VehicleInspectionMapper;
import com.hojo.fenix.warehouse.domain.requests.VehicleInspectionRequest;
import com.hojo.fenix.warehouse.services.WarehouseVehicleService;
import com.hojo.fenix.warehouse.utils.exceptions.WarehouseException;
import com.hojo.fenix.warehouse.utils.ql.QueryLanguajeComponentImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class WarehouseVehicleServiceImpl implements WarehouseVehicleService {

    private final WarehouseVehicleDao warehouseVehicleDao;
    private final WarehouseVehicleInspectionDao warehouseVehicleInspectionDao;
    private final VehicleInspectionMapper vehicleInspectionMapper;
    private final QueryLanguajeComponentImpl<VehicleEntity> qlVehicle;


    public WarehouseVehicleServiceImpl(final WarehouseVehicleDao warehouseVehicleDao, WarehouseVehicleInspectionDao warehouseVehicleInspectionDao, VehicleInspectionMapper vehicleInspectionMapper, final QueryLanguajeComponentImpl<VehicleEntity> qlVehicle) {
        this.warehouseVehicleDao = warehouseVehicleDao;
        this.warehouseVehicleInspectionDao = warehouseVehicleInspectionDao;
        this.vehicleInspectionMapper = vehicleInspectionMapper;
        this.qlVehicle = qlVehicle;
    }

    @Override
    public VehicleEntity save(@NotNull final VehicleEntity vehicleEntity) {
        return vehicleEntity.getId() != null ? warehouseVehicleDao.update(vehicleEntity) : warehouseVehicleDao.create(vehicleEntity);
    }

    @Override
    public VehicleEntity get(final Long id) {
        return warehouseVehicleDao.get(id);
    }

    @Override
    public VehicleEntity updateVehicleImage(final Long id, final MultipartFile file) {
        VehicleEntity vehicle = warehouseVehicleDao.get(id);
        try {
            vehicle.setImage(file.getBytes());
        } catch (IOException e) {
            throw new WarehouseException(e.getMessage());
        }
        return warehouseVehicleDao.update(vehicle);
    }

    @Override
    public void deleteVehicle(Long id) {
        warehouseVehicleDao.delete(id);
    }

    @Override
    public ContainerList<VehicleEntity> search(final String filter, final Integer limit, final Long offset) {
        if (offset != null && limit != null) {
            final Pageable pageable = OffsetPaginationRequest.of(limit, offset);
            final Page<VehicleEntity> vehicleEntityPage = warehouseVehicleDao.search(qlVehicle.parse(filter), pageable);
            OffsetPagination offsetPagination = new OffsetPagination(limit, offset, vehicleEntityPage.getTotalElements());
            return new ContainerList<>(vehicleEntityPage.get().collect(Collectors.toList()), offsetPagination);
        }
        return new ContainerList<>(warehouseVehicleDao.search(qlVehicle.parse(filter)));
    }


    @Override
    public VehicleEntity saveInspection(final VehicleInspectionRequest request) {
        try {
            final VehicleEntity vehicleEntity = warehouseVehicleDao.get(request.getVehicleId());
            warehouseVehicleInspectionDao.create(vehicleInspectionMapper.mapToInner(request));
            //if (vehicleEntity.getInspections() == null) vehicleEntity.setInspections(Collections.emptyList());
            //vehicleEntity.getInspections().add(inspectionSaved);
            return vehicleEntity;
        } catch (WarehouseException warehouseException) {
            throw new WarehouseException(warehouseException.getStatus(), "101", warehouseException);
        }
    }

    @Override
    public VehicleEntity updateInspection(VehicleInspectionRequest request) {
        try {
            final VehicleEntity vehicleEntity = warehouseVehicleDao.get(request.getVehicleId());

            //Me va a dejar actualizar la información dentro del array??
            vehicleEntity.getInspections().stream().filter(i -> i.getId().equals(request.getId())).forEach(i -> {
                vehicleInspectionMapper.updateToInner(i, request);
                warehouseVehicleInspectionDao.update(i);
            });

            return vehicleEntity;
        } catch (WarehouseException warehouseException) {
            throw new WarehouseException(warehouseException.getStatus(), "101", warehouseException);
        }
    }

    @Override
    public VehicleInspection getInspectionDetail(final Long vehicleId, final Long inspectionId) {
        return warehouseVehicleInspectionDao.getDetail(vehicleId, inspectionId);
        //return warehouseVehicleInspectionDao.get(inspectionId);
    }

    @Override
    public void deleteVehicleInspection(Long vehicleId, Long inspectionId) {
        final VehicleEntity vehicle = warehouseVehicleDao.get(vehicleId);
        final VehicleInspection vehicleInspection = vehicle.getInspections().stream()
                .filter(i -> i.getId().equals(inspectionId))
                .findAny()
                .orElseThrow(() -> new WarehouseException(String.format("Inspection %s not found in vehicle: %s", inspectionId, vehicleId)));
        warehouseVehicleInspectionDao.delete(vehicleInspection.getId());
    }
}
