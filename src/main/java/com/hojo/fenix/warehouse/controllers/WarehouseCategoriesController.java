package com.hojo.fenix.warehouse.controllers;

import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.entities.*;
import com.hojo.fenix.warehouse.domain.requests.CategoryUpdateSubCategoriesRequest;
import com.hojo.fenix.warehouse.services.WarehouseCategoriesServices;

import com.hojo.fenix.warehouse.utils.aop.WarehouseLogger;
import com.hojo.fenix.warehouse.utils.exceptions.WarehouseExceptionResponse;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * @author hojo
 */
@RestController
@WarehouseLogger
@RequestMapping("/categories")
@Api(consumes = "application/json", produces = "application/jsons")
public class WarehouseCategoriesController {

    private final WarehouseCategoriesServices warehouseCategoriesServices;

    public WarehouseCategoriesController(final WarehouseCategoriesServices warehouseCategoriesServices) {
        this.warehouseCategoriesServices = warehouseCategoriesServices;
    }

    @PostMapping()
    @ApiOperation(value = "Method to create a list of categories", response = ContainerList.class, nickname = "createCategories", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category added successfully", response = ContainerList.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerList<FoodCategoryEntity> createCategories(@RequestBody @Valid final List<FoodCategoryEntity> categories) {
        return warehouseCategoriesServices.createCategories(categories);
    }

    @PutMapping()
    @ApiOperation(value = "Method to update a category entity", response = ContainerList.class, nickname = "updateCategory", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category updated successfully", response = ContainerList.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerList<FoodCategoryEntity> updateCategory(@RequestBody @Valid final FoodCategoryEntity foodCategoryEntity) {
        return warehouseCategoriesServices.updateCategories(Collections.singletonList(foodCategoryEntity));
    }

    @PatchMapping("/update-subcategories")
    @ApiOperation(value = "Method to update the subcategory relation", response = ContainerList.class, nickname = "updateSubcategoriesToCategory", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category updated successfully", response = ContainerList.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public FoodCategoryEntity updateSubcategoriesToCategory(@RequestBody @Valid CategoryUpdateSubCategoriesRequest request) {
        return warehouseCategoriesServices.updateSubCategoriesToCategory(request);
    }

    @GetMapping()
    @ApiOperation(value = "Category search method", response = ContainerList.class, nickname = "getCategories", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve category successfully", response = ContainerList.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerList<FoodCategoryEntity> getCategories(
            @ApiParam(name = "filter", example = "filter=name~food,description~meat")
            @RequestParam(value = "filter", required = false) final String filter) {
        return warehouseCategoriesServices.getCategories(filter);
    }

    @PostMapping("/subcategories")
    @ApiOperation(value = "SubCategory create method", response = ContainerList.class, nickname = "createSubCategoryEntity", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SubCategory created successfully", response = ContainerList.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerList<FoodSubCategoryEntity> createSubCategoryEntity(@RequestBody @Valid FoodSubCategoryEntity foodSubCategoryEntity) {
        return warehouseCategoriesServices.createSubCategories(Collections.singletonList(foodSubCategoryEntity));
    }

    @GetMapping("/subcategories")
    @ApiOperation(value = "SubCategory search method", response = ContainerList.class, nickname = "getSubCategories", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve subcategory successfully", response = ContainerList.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerList<FoodSubCategoryEntity> getSubCategories(
            @ApiParam(name = "filter", example = "filter=name~food,description~meat")
            @RequestParam(value = "filter", required = false) final String filter) {
        return warehouseCategoriesServices.searchSubCategories(filter);
    }




}
