package com.hojo.fenix.warehouse.services;

import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.entities.VehicleEntity;
import com.hojo.fenix.warehouse.domain.entities.VehicleInspection;
import com.hojo.fenix.warehouse.domain.requests.VehicleInspectionRequest;
import org.springframework.web.multipart.MultipartFile;

public interface WarehouseVehicleService {

    VehicleEntity save(VehicleEntity vehicleEntity);

    VehicleEntity get(Long id);

    VehicleEntity updateVehicleImage(Long id, MultipartFile file);

    void deleteVehicle(Long id);

    ContainerList<VehicleEntity> search(String filter, Integer limit, Long offset);

    VehicleEntity saveInspection(VehicleInspectionRequest request);

    VehicleEntity updateInspection(VehicleInspectionRequest request);

    VehicleInspection getInspectionDetail(Long vehicleId, Long inspectionId);

    void deleteVehicleInspection(Long vehicleId, Long inspectionId);

}
