package com.gft.demos.warehouse.controllers;

import com.gft.demos.warehouse.api.WarehouseGarage;
import com.gft.demos.warehouse.utils.aop.WarehouseLogger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@WarehouseLogger
public class WarehouseGarageController implements WarehouseGarage {

    @Override
    public void getVehicles() {
        System.out.println("Funciona la interfaz");
    }

    @Override
    public void getVechicleById() {

    }

    @Override
    public void addVehicle() {

    }

    @Override
    public void updateVehicle() {

    }

    @Override
    public void getVehicleInspections() {

    }

    @Override
    public void addVehicleInspection() {

    }

    @Override
    public void updateVehicleInspection() {

    }

    @Override
    public void removeVehicleInspection() {

    }
}
