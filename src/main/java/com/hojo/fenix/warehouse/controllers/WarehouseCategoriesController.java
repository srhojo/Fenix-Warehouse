package com.hojo.fenix.warehouse.controllers;

import com.hojo.fenix.warehouse.domain.requests.CategoryRequest;
import com.hojo.fenix.warehouse.domain.requests.SubCategoryRequest;
import com.hojo.fenix.warehouse.services.WarehouseCategoriesServices;
import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.entities.ProductCategoryEntity;
import com.hojo.fenix.warehouse.domain.entities.ProductSubCategoryEntity;
import com.hojo.fenix.warehouse.domain.requests.CategoryUpdateSubCategoriesRequest;
import com.hojo.fenix.warehouse.utils.aop.WarehouseLogger;
import com.hojo.fenix.warehouse.utils.exceptions.WarehouseExceptionResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author hojo
 */
@Validated
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
    @ApiOperation(value = "Method to create a categories", response = ProductCategoryEntity.class, nickname = "createCategory", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category added successfully", response = ProductCategoryEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ProductCategoryEntity createCategory(@RequestBody @Valid final CategoryRequest category) {
        return warehouseCategoriesServices.createCategory((category));
    }

    @PutMapping()
    @ApiOperation(value = "Method to update a category", response = ProductCategoryEntity.class, nickname = "updateCategory", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category updated successfully", response = ProductCategoryEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ProductCategoryEntity updateCategory(@RequestBody @Valid final CategoryRequest category) {
        return warehouseCategoriesServices.updateCategory(category);
    }

    @PatchMapping("/update-subcategories")
    @ApiOperation(value = "Method to update the subcategory relation", response = ProductCategoryEntity.class, nickname = "updateSubcategoriesToCategory", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category updated successfully", response = ProductCategoryEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ProductCategoryEntity updateSubcategoriesToCategory(@RequestBody @Valid CategoryUpdateSubCategoriesRequest request) {
        return warehouseCategoriesServices.updateSubCategoriesToCategory(request);
    }


    @GetMapping
    @ApiOperation(value = "Category search method", response = ContainerList.class, nickname = "getCategories", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve category successfully", response = ContainerList.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerList<ProductCategoryEntity> getCategories(
            @ApiParam(name = "filter", example = "filter=name~food,description~meat")
            @RequestParam(value = "filter", required = false) final String filter,
            @ApiParam(name = "limit", example = "2") @Valid
            @RequestParam(value = "limit", required = false) @Min(value = 1, message = "Limit size must not be less than one!") final Integer limit,
            @ApiParam(name = "offset", example = "0") @Valid
            @RequestParam(value = "offset", required = false) @Min(value = 0L, message = "Offset size must not be less than zero!") final Long offset) {
        return warehouseCategoriesServices.getCategories(filter, limit, offset);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Category delete method", nickname = "getCategories", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Delete category successfully"),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public void deleteCategory(
            @ApiParam(name = "name")
            @PathVariable(value = "name") final String name) {
        warehouseCategoriesServices.deleteCategory(name);
    }


    @PostMapping("/subcategories")
    @ApiOperation(value = "SubCategory create method", response = ProductSubCategoryEntity.class, nickname = "createSubCategoryEntity", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SubCategory created successfully", response = ProductSubCategoryEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ProductSubCategoryEntity createSubCategoryEntity(@RequestBody @Valid SubCategoryRequest subCategoryRequest) {
        return warehouseCategoriesServices.createSubCategory(subCategoryRequest);
    }


    @PutMapping("/subcategories")
    @ApiOperation(value = "Method to update a sub-category ", response = ProductSubCategoryEntity.class, nickname = "updateSubCategory", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category updated successfully", response = ProductSubCategoryEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ProductSubCategoryEntity updateSubCategory(@RequestBody @Valid final SubCategoryRequest subCategoryRequest) {
        return warehouseCategoriesServices.updateSubCategory(subCategoryRequest);
    }


    @GetMapping("/subcategories")
    @ApiOperation(value = "SubCategory search method", response = ContainerList.class, nickname = "getSubCategories", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve subcategory successfully", response = ContainerList.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerList<ProductSubCategoryEntity> getSubCategories(
            @ApiParam(name = "filter", example = "filter=name~food,description~meat")
            @RequestParam(value = "filter", required = false) final String filter,
            @ApiParam(name = "limit", example = "2") @Valid
            @RequestParam(value = "limit", required = false) @Min(value = 1, message = "Limit size must not be less than one!") final Integer limit,
            @ApiParam(name = "offset", example = "0") @Valid
            @RequestParam(value = "offset", required = false) @Min(value = 0L, message = "Offset size must not be less than zero!") final Long offset) {
        return warehouseCategoriesServices.searchSubCategories(filter, limit, offset);
    }


}
