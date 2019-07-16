package com.hojo.fenix.warehouse.dao.repositories;

import com.hojo.fenix.warehouse.domain.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<VehicleEntity, Long>, JpaSpecificationExecutor<VehicleEntity> {

}
