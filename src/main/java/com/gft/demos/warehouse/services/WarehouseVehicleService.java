package com.gft.demos.warehouse.services;

import com.gft.demos.warehouse.domain.cdm.ContainerList;
import com.gft.demos.warehouse.domain.entities.VehicleEntity;
import com.gft.demos.warehouse.domain.entities.VehicleInspection;
import com.gft.demos.warehouse.domain.requests.VehicleInspectionRequest;
import org.springframework.web.multipart.MultipartFile;

public interface WarehouseVehicleService {

    VehicleEntity save(VehicleEntity vehicleEntity);

    VehicleEntity get(Long id);

    VehicleEntity updateVehicleImage(Long id, MultipartFile file);

    ContainerList<VehicleEntity> search(String filter, Integer limit, Long offset);

    VehicleEntity saveInspection(VehicleInspectionRequest request);

    VehicleEntity updateInspection(VehicleInspectionRequest request);

    VehicleInspection getInspectionDetail(Long vehicleId, Long inspectionId);

    void deleteVehicleInspection(Long vehicleId, Long inspectionId);

}
