package com.gft.demos.warehouse.api;

import com.gft.demos.warehouse.domain.entities.ProductCategoryEntity;
import com.gft.demos.warehouse.utils.exceptions.WarehouseExceptionResponse;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/garage")
@Api(consumes = "application/json", produces = "application/jsons")
public interface WarehouseGarage {

    @GetMapping("/vehicles")
    @ApiOperation(value = "Method to retrieve vehicles", response = Void.class, nickname = "getVehicles", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve vehicles successfully", response = Void.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    void getVehicles();

    void getVechicleById();

    void addVehicle();

    void updateVehicle();

    void getVehicleInspections();

    void addVehicleInspection();

    void updateVehicleInspection();

    void removeVehicleInspection();



}
