package com.hojo.fenix.warehouse.domain.entities;

import com.hojo.fenix.warehouse.domain.entities.enums.VehicleTypeEnum;

import javax.persistence.Entity;

@Entity(name = VehicleTypeEnum.Names.CAR)
public class CarEntity extends VehicleEntity {


}
