package com.hojo.fenix.warehouse.controllers;

import com.hojo.fenix.warehouse.domain.CategoryUpdateSubCategoriesRequest;
import com.hojo.fenix.warehouse.domain.ContainerEntities;
import com.hojo.fenix.warehouse.domain.entities.CategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.FoodEntity;
import com.hojo.fenix.warehouse.domain.entities.SubCategoryEntity;
import com.hojo.fenix.warehouse.services.WarehouseService;
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

    private final WarehouseService warehouseService;

    public WarehouseController(final WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping("/categories")
    @ApiOperation(value = "Method to create a list of categories", response = ContainerEntities.class, nickname = "createCategories", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Categorted successfully", response = ContainerEntities.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerEntities<CategoryEntity> createCategories(@RequestBody @Valid final List<CategoryEntity> categories) {
        return warehouseService.createCategories(categories);
    }

    @PutMapping("/categories")
    @ApiOperation(value = "Method to update a category entity", response = ContainerEntities.class, nickname = "updateCategory", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category updated successfully", response = ContainerEntities.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerEntities<CategoryEntity> updateCategory(@RequestBody @Valid final CategoryEntity categoryEntity) {
        return warehouseService.updateCategories(Collections.singletonList(categoryEntity));
    }

    @PatchMapping("/categories/update-subcategories")
    @ApiOperation(value = "Method to update the subcategory relation", response = ContainerEntities.class, nickname = "updateSubcategoriesToCategory", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category updated successfully", response = ContainerEntities.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public CategoryEntity updateSubcategoriesToCategory(@RequestBody @Valid CategoryUpdateSubCategoriesRequest request) {
        return warehouseService.updateSubCategoriesToCategory(request);
    }

    @GetMapping("/categories")
    @ApiOperation(value = "Category search method", response = ContainerEntities.class, nickname = "getCategories", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve category successfully", response = ContainerEntities.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerEntities<CategoryEntity> getCategories(
            @ApiParam(name = "filter", example = "filter=name~food,description~meat")
            @RequestParam(value = "filter", required = false) final String filter) {
        return warehouseService.getCategories(filter);
    }

    @PostMapping("/subcategories")
    @ApiOperation(value = "SubCategory create method", response = ContainerEntities.class, nickname = "createSubCategoryEntity", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SubCategory created successfully", response = ContainerEntities.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerEntities<SubCategoryEntity> createSubCategoryEntity(@RequestBody @Valid SubCategoryEntity subCategoryEntity) {
        return warehouseService.createSubCategories(Collections.singletonList(subCategoryEntity));
    }

    @GetMapping("/subcategories")
    @ApiOperation(value = "SubCategory search method", response = ContainerEntities.class, nickname = "getSubCategories", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve subcategory successfully", response = ContainerEntities.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerEntities<SubCategoryEntity> getSubCategories(
            @ApiParam(name = "filter", example = "filter=name~food,description~meat")
            @RequestParam(value = "filter", required = false) final String filter) {
        return warehouseService.searchSubCategories(filter);
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
        return warehouseService.searchFoods(filter);
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
        return warehouseService.createFood(food);
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
        return warehouseService.updateFood(food);
    }


}
