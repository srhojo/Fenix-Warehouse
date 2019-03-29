package com.hojo.fenix.warehouse.controllers;

import com.hojo.fenix.warehouse.domain.CategoryUpdateSubCategoriesRequest;
import com.hojo.fenix.warehouse.domain.ContainerEntities;
import com.hojo.fenix.warehouse.domain.entities.FoodCategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodSubCategoryEntity;
import com.hojo.fenix.warehouse.services.WarehouseFoodService;
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
@RequestMapping("/warehouse")
@Api(consumes = "application/json", produces = "application/jsons")
public class WarehouseController {

    private final WarehouseFoodService warehouseFoodService;

    public WarehouseController(final WarehouseFoodService warehouseFoodService) {
        this.warehouseFoodService = warehouseFoodService;
    }

    @PostMapping("/categories")
    @ApiOperation(value = "Method to create a list of categories", response = ContainerEntities.class, nickname = "createCategories", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Categorted successfully", response = ContainerEntities.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerEntities<FoodCategoryEntity> createCategories(@RequestBody @Valid final List<FoodCategoryEntity> categories) {
        return warehouseFoodService.createCategories(categories);
    }

    @PutMapping("/categories")
    @ApiOperation(value = "Method to update a category entity", response = ContainerEntities.class, nickname = "updateCategory", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category updated successfully", response = ContainerEntities.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerEntities<FoodCategoryEntity> updateCategory(@RequestBody @Valid final FoodCategoryEntity foodCategoryEntity) {
        return warehouseFoodService.updateCategories(Collections.singletonList(foodCategoryEntity));
    }

    @PatchMapping("/categories/update-subcategories")
    @ApiOperation(value = "Method to update the subcategory relation", response = ContainerEntities.class, nickname = "updateSubcategoriesToCategory", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category updated successfully", response = ContainerEntities.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public FoodCategoryEntity updateSubcategoriesToCategory(@RequestBody @Valid CategoryUpdateSubCategoriesRequest request) {
        return warehouseFoodService.updateSubCategoriesToCategory(request);
    }

    @GetMapping("/categories")
    @ApiOperation(value = "Category search method", response = ContainerEntities.class, nickname = "getCategories", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve category successfully", response = ContainerEntities.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerEntities<FoodCategoryEntity> getCategories(
            @ApiParam(name = "filter", example = "filter=name~food,description~meat")
            @RequestParam(value = "filter", required = false) final String filter) {
        return warehouseFoodService.getCategories(filter);
    }

    @PostMapping("/subcategories")
    @ApiOperation(value = "SubCategory create method", response = ContainerEntities.class, nickname = "createSubCategoryEntity", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SubCategory created successfully", response = ContainerEntities.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerEntities<FoodSubCategoryEntity> createSubCategoryEntity(@RequestBody @Valid FoodSubCategoryEntity foodSubCategoryEntity) {
        return warehouseFoodService.createSubCategories(Collections.singletonList(foodSubCategoryEntity));
    }

    @GetMapping("/subcategories")
    @ApiOperation(value = "SubCategory search method", response = ContainerEntities.class, nickname = "getSubCategories", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve subcategory successfully", response = ContainerEntities.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerEntities<FoodSubCategoryEntity> getSubCategories(
            @ApiParam(name = "filter", example = "filter=name~food,description~meat")
            @RequestParam(value = "filter", required = false) final String filter) {
        return warehouseFoodService.searchSubCategories(filter);
    }


    @GetMapping("/foods")
    @ApiOperation(value = "Food search method", response = ContainerEntities.class, nickname = "searchFood", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve foods successfully", response = ContainerEntities.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerEntities<FoodEntity> searchFood(@RequestParam(value = "filter", required = false) String filter) {
        return warehouseFoodService.searchFoods(filter);
    }

    @PostMapping("/foods")
    @ApiOperation(value = "Create new food", response = FoodEntity.class, nickname = "createFood", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Create foods successfully", response = ContainerEntities.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public FoodEntity createFood(@RequestBody @Valid FoodEntity food) {
        return warehouseFoodService.createFood(food);
    }

    @PutMapping("/foods/{name}")
    @ApiOperation(value = "Update food", response = FoodEntity.class, nickname = "updateFood", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Create foods successfully", response = ContainerEntities.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public FoodEntity updateFood(@PathVariable("name") String name, @RequestBody @Valid FoodEntity food) {
        food.setName(name);
        return warehouseFoodService.updateFood(food);
    }


}
