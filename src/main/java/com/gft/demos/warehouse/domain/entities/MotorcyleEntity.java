package com.gft.demos.warehouse.domain.entities;

import com.gft.demos.warehouse.domain.entities.enums.VehicleTypeEnum;

import javax.persistence.Entity;

@Entity(name = VehicleTypeEnum.Names.MOTORCYCLE)
public class MotorcyleEntity extends VehicleEntity {
}
