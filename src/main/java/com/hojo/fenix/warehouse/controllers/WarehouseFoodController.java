package com.hojo.fenix.warehouse.controllers;

import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.entities.FoodEntity;
import com.hojo.fenix.warehouse.domain.requests.FoodRequest;
import com.hojo.fenix.warehouse.services.WarehouseFoodService;
import com.hojo.fenix.warehouse.utils.aop.WarehouseLogger;
import com.hojo.fenix.warehouse.utils.exceptions.WarehouseExceptionResponse;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RestController
@WarehouseLogger
@RequestMapping("/foods")
@Api(consumes = "application/json", produces = "application/json")
public class WarehouseFoodController {


    private final WarehouseFoodService warehouseFoodService;

    public WarehouseFoodController(WarehouseFoodService warehouseFoodService) {
        this.warehouseFoodService = warehouseFoodService;
    }

    @GetMapping()
    @ApiOperation(value = "Food search method", response = ContainerList.class, nickname = "searchFood", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve foods successfully", response = ContainerList.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerList<FoodEntity> searchFood(@RequestParam(value = "filter", required = false) String filter,
                                                @ApiParam(name = "limit", example = "2") @Valid
                                                @RequestParam(value = "limit", required = false) @Min(value = 1, message = "Limit size must not be less than one!") final Integer limit,
                                                @ApiParam(name = "offset", example = "0") @Valid
                                                @RequestParam(value = "offset", required = false) @Min(value = 0L, message = "Offset size must not be less than zero!") final Long offset) {
        return warehouseFoodService.searchFoods(filter, limit, offset);
    }

    @PostMapping()
    @ApiOperation(value = "Create new food", response = FoodEntity.class, nickname = "createFood", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Create foods successfully", response = ContainerList.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public FoodEntity createFood(@RequestBody @Valid FoodRequest food) {
        return warehouseFoodService.createFood(food);
    }

    @PutMapping("/{name}")
    @ApiOperation(value = "Update food", response = FoodEntity.class, nickname = "updateFood", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Create foods successfully", response = ContainerList.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public FoodEntity updateFood(@PathVariable("name") String name, @RequestBody @Valid FoodRequest food) {
        food.setName(name);
        return warehouseFoodService.updateFood(food);
    }

}
