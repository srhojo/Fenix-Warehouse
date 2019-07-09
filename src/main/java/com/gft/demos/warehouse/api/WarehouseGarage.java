package com.gft.demos.warehouse.api;

import com.gft.demos.warehouse.domain.cdm.ContainerList;
import com.gft.demos.warehouse.domain.entities.VehicleEntity;
import com.gft.demos.warehouse.domain.entities.VehicleInspection;
import com.gft.demos.warehouse.domain.requests.VehicleInspectionRequest;
import com.gft.demos.warehouse.utils.exceptions.WarehouseExceptionResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RequestMapping("/garage")
@Api(consumes = "application/json", produces = "application/jsons")
public interface WarehouseGarage {

    @GetMapping("/vehicles")
    @ApiOperation(value = "Method to retrieve vehicles", response = ContainerList.class, nickname = "getVehicles", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve vehicles successfully", response = ContainerList.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    ContainerList<VehicleEntity> searchVehicles(@RequestParam(value = "filter", required = false) String filter,
                                                @ApiParam(name = "limit", example = "2") @Valid
                                                @RequestParam(value = "limit", required = false) @Min(value = 1, message = "Limit size must not be less than one!") final Integer limit,
                                                @ApiParam(name = "offset", example = "0") @Valid
                                                @RequestParam(value = "offset", required = false) @Min(value = 0L, message = "Offset size must not be less than zero!") final Long offset);


    @GetMapping("/vehicle/{id}")
    @ApiOperation(value = "Method to retrieve vehicle by its id", response = VehicleEntity.class, nickname = "getVehicleById", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve vehicle successfully", response = VehicleEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    VehicleEntity getVehicleById(@PathVariable Long id);

    @PostMapping("/vehicles/{type}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Method to create a vehicle", response = VehicleEntity.class, nickname = "addVehicle", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Vehicle created successfully", response = VehicleEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    VehicleEntity addVehicle(@RequestBody @Valid VehicleEntity request);

    @PutMapping("/vehicle/{id}")
    @ApiOperation(value = "Method to update a vehicle", response = VehicleEntity.class, nickname = "updateVehicle", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Vehicle updated successfully", response = VehicleEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    VehicleEntity updateVehicle(@PathVariable Long id, @RequestBody @Valid VehicleEntity request);

    @GetMapping("/vehicles/{vehicleId}/inspections/{inspectionId}")
    VehicleInspection getVehicleInspections(@PathVariable Long vehicleId, @PathVariable Long inspectionId);

    @PostMapping("/vehicles/{vehicleId}/inspections")
    @ResponseStatus(HttpStatus.CREATED)
    VehicleEntity addVehicleInspection(@PathVariable Long vehicleId, @RequestBody @Valid VehicleInspectionRequest request);

    @PutMapping("/vehicles/{vehicleId}/inspections/{inspectionId}")
    VehicleEntity updateVehicleInspection(@PathVariable Long vehicleId, @PathVariable Long inspectionId, @RequestBody @Valid VehicleInspectionRequest request);

    @DeleteMapping("/vehicles/{vehicleId}/inspections/{inspectionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeVehicleInspection(@PathVariable Long vehicleId, @PathVariable Long inspectionId);


}
