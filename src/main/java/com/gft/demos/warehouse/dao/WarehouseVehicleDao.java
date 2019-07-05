package com.gft.demos.warehouse.dao;

import com.gft.demos.warehouse.utils.dao.WarehouseCRUD;
import com.gft.demos.warehouse.domain.entities.VehicleEntity;
import com.gft.demos.warehouse.utils.dao.WarehouseSearch;

public interface WarehouseVehicleDao extends WarehouseCRUD<VehicleEntity,Long>, WarehouseSearch<VehicleEntity> {



}
