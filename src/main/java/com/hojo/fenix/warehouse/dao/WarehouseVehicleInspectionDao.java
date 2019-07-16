package com.hojo.fenix.warehouse.dao;

import com.hojo.fenix.warehouse.domain.entities.VehicleInspection;
import com.hojo.fenix.warehouse.utils.dao.WarehouseCRUD;

import java.util.List;

public interface WarehouseVehicleInspectionDao extends WarehouseCRUD<VehicleInspection,Long> {

    VehicleInspection getDetail(Long vehicleId, Long  inspectionId);

    List<VehicleInspection> getAll(Long vechicleId);

}
