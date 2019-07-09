package com.gft.demos.warehouse.dao.impl;

import com.gft.demos.warehouse.dao.WarehouseVehicleInspectionDao;
import com.gft.demos.warehouse.dao.repositories.VehicleInspectionRepository;
import com.gft.demos.warehouse.domain.entities.VehicleEntity;
import com.gft.demos.warehouse.domain.entities.VehicleInspection;
import com.gft.demos.warehouse.utils.exceptions.WarehouseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.List;

import static com.gft.demos.warehouse.utils.exceptions.ExceptionConstants.*;

@Repository
@Transactional
public class WarehouseVehicleInspectionDaoImpl implements WarehouseVehicleInspectionDao {

    private final VehicleInspectionRepository vehicleInspectionRepository;

    public WarehouseVehicleInspectionDaoImpl(VehicleInspectionRepository vehicleInspectionRepository) {
        this.vehicleInspectionRepository = vehicleInspectionRepository;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleInspection create(final VehicleInspection vehicleInspection) {
        if (vehicleInspection.getId() != null) {
            throw new WarehouseException(HttpStatus.METHOD_NOT_ALLOWED, ERRORS_DAO_NOT_UPDATE_CODE, ERRORS_DAO_NOT_UPDATE_MESSAGE);
        }
        return vehicleInspectionRepository.save(vehicleInspection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleInspection update(final VehicleInspection vehicleInspection) {
        this.get(vehicleInspection.getId());
        return vehicleInspectionRepository.save(vehicleInspection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleInspection get(final Long id) {
        return vehicleInspectionRepository.findById(id).orElseThrow(() -> new WarehouseException(HttpStatus.NOT_FOUND, ERRORS_DAO_ENTITY_NOT_FOUND_CODE,
                String.format(ERRORS_DAO_ENTITY_NOT_FOUND_MESSAGE, id)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final Long id) {
        this.get(id);
        vehicleInspectionRepository.deleteById(id);
    }

    @Override
    public List<VehicleInspection> getAll(final Long vehicleId) {
        return vehicleInspectionRepository.findAllByVehicleId(vehicleId);
    }
}
