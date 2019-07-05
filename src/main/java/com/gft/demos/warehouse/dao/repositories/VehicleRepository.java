package com.gft.demos.warehouse.dao.repositories;

import com.gft.demos.warehouse.domain.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<VehicleEntity, Long>, JpaSpecificationExecutor<VehicleEntity> {

}
