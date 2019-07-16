package com.hojo.fenix.warehouse.domain.entities;

import com.hojo.fenix.warehouse.domain.entities.enums.VehicleTypeEnum;

import javax.persistence.Entity;

@Entity(name = VehicleTypeEnum.Names.MOTORCYCLE)
public class MotorcyleEntity extends VehicleEntity {
}
