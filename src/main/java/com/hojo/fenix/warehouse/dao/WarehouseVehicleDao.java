package com.hojo.fenix.warehouse.dao;

import com.hojo.fenix.warehouse.utils.dao.WarehouseCRUD;
import com.hojo.fenix.warehouse.domain.entities.VehicleEntity;
import com.hojo.fenix.warehouse.utils.dao.WarehouseSearch;

public interface WarehouseVehicleDao extends WarehouseCRUD<VehicleEntity,Long>, WarehouseSearch<VehicleEntity> {



}
