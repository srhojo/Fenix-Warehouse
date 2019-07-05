package com.gft.demos.warehouse.services;

import com.gft.demos.warehouse.domain.cdm.ContainerList;
import com.gft.demos.warehouse.domain.entities.VehicleEntity;

public interface WarehouseVehicleService {

    VehicleEntity create(VehicleEntity vehicleEntity);

    VehicleEntity get(Long id);

    ContainerList<VehicleEntity> searchVehicles(String filter, Integer limit, Long offset);

}
