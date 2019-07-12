package com.gft.demos.warehouse.dao.repositories;

import com.gft.demos.warehouse.domain.entities.VehicleInspection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleInspectionRepository extends CrudRepository<VehicleInspection,Long> {


    Optional<VehicleInspection> findByIdAndVehicleId(Long id, long vehicleId);

    List<VehicleInspection> findAllByVehicleId(Long vehicleId);
}
