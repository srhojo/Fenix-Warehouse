package com.gft.demos.warehouse.dao.repositories;

import com.gft.demos.warehouse.domain.entities.VehicleInspection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleInspectionRepository extends CrudRepository<VehicleInspection,Long> {


    List<VehicleInspection> findAllByVehicleId(Long vehicleId);
}
