package com.gft.demos.warehouse.services.impl;

import com.gft.demos.warehouse.dao.WarehouseVehicleDao;
import com.gft.demos.warehouse.domain.cdm.ContainerList;
import com.gft.demos.warehouse.domain.entities.VehicleEntity;
import com.gft.demos.warehouse.services.WarehouseVehicleService;
import org.springframework.stereotype.Service;

@Service
public class WarehouseVehicleServiceImpl implements WarehouseVehicleService {

    private final WarehouseVehicleDao warehouseVehicleDao;
    pr

    public WarehouseVehicleServiceImpl(final WarehouseVehicleDao warehouseVehicleDao) {
        this.warehouseVehicleDao = warehouseVehicleDao;
    }

    @Override
    public VehicleEntity create(final VehicleEntity vehicleEntity) {
        return null;
    }

    @Override
    public VehicleEntity get(final Long id) {
        return null;
    }

    @Override
    public ContainerList<VehicleEntity> searchVehicles(final String filter, final Integer limit, final Long offset) {
        return null;
    }
}
