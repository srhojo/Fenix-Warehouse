package com.gft.demos.warehouse.dao;

import com.gft.demos.warehouse.domain.entities.VehicleInspection;
import com.gft.demos.warehouse.utils.dao.WarehouseCRUD;

import java.util.List;

public interface WarehouseVehicleInspectionDao extends WarehouseCRUD<VehicleInspection,Long> {

    VehicleInspection getDetail(Long vehicleId, Long  inspectionId);

    List<VehicleInspection> getAll(Long vechicleId);

}
