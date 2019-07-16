package com.hojo.fenix.warehouse.controllers;

import com.hojo.fenix.warehouse.domain.entities.ShoppingListEntity;
import com.hojo.fenix.warehouse.domain.cdm.ContainerList;
import com.hojo.fenix.warehouse.domain.requests.ShoppingListItemsRequest;
import com.hojo.fenix.warehouse.domain.requests.ShoppingListRequest;
import com.hojo.fenix.warehouse.services.WarehouseShoppingListService;
import com.hojo.fenix.warehouse.utils.aop.WarehouseLogger;
import com.hojo.fenix.warehouse.utils.exceptions.WarehouseExceptionResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;


@Validated
@RestController
@WarehouseLogger
@RequestMapping("/shopping-list")
@Api(consumes = "application/json", produces = "application/jsons")
public class WarehouseShoppingListController {

    private final WarehouseShoppingListService warehouseShoppingListService;

    public WarehouseShoppingListController(WarehouseShoppingListService warehouseShoppingListService) {
        this.warehouseShoppingListService = warehouseShoppingListService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get shopping list by id method", response = ShoppingListEntity.class, nickname = "getShoppingListById", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieved shopping list successfully", response = ShoppingListEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ShoppingListEntity getShoppingListById(@PathVariable("id") Long id) {
        return warehouseShoppingListService.getShoppingListById(id);
    }

    @GetMapping()
    @ApiOperation(value = "Get shopping lists", response = ShoppingListEntity.class, nickname = "getShoppingLists", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieved shopping lists successfully", response = ShoppingListEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ContainerList getShoppingLists(@ApiParam(name = "filter", example = "filter=") @RequestParam(value = "filter", required = false) final String filter,
                                          @ApiParam(name = "limit", example = "2") @Valid
                                          @RequestParam(value = "limit", required = false) @Min(value = 1, message = "Limit size must not be less than one!") final Integer limit,
                                          @ApiParam(name = "offset", example = "0") @Valid
                                          @RequestParam(value = "offset", required = false) @Min(value = 0L, message = "Offset size must not be less than zero!") final Long offset) {
        return warehouseShoppingListService.getShoppingLists(filter, limit, offset);
    }


    @PostMapping
    @ApiOperation(value = "create shopping list method", response = ShoppingListEntity.class, nickname = "createShoppingList", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Created shopping list successfully", response = ShoppingListEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ShoppingListEntity createShoppingList(@RequestBody @Valid ShoppingListRequest request) {
        return warehouseShoppingListService.saveShoppingList(request);
    }


    @PatchMapping("/{id}")
    @ApiOperation(value = "Edit shopping list method", response = ShoppingListEntity.class, nickname = "editShoppingList", httpMethod = "PATCH", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Edited shopping list successfully", response = ShoppingListEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ShoppingListEntity editShoppingList(@PathVariable("id") Long id, @RequestBody @Valid ShoppingListRequest request) {
        request.setId(id);
        return warehouseShoppingListService.saveShoppingList(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleted shopping list items method", response = ShoppingListEntity.class, nickname = "deleteShoppingList", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted shopping list successfully", response = void.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public void deleteShoppingList(@PathVariable("id") Long id) {
        warehouseShoppingListService.deleteShoppingList(id);
    }

    @PatchMapping("/{id}/items")
    @ApiOperation(value = "Edit shopping list items method", response = ShoppingListEntity.class, nickname = "editShoppingListItems", httpMethod = "PATCH", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Edited items successfully", response = ShoppingListEntity.class),
            @ApiResponse(code = 500, message = "An error has occurred", response = WarehouseExceptionResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "Content-Type", dataType = "string", paramType = "header", defaultValue = "application/json")
    })
    public ShoppingListEntity editShoppingListItems(@PathVariable("id") Long shoppingListId, @RequestBody @Valid ShoppingListItemsRequest items) {
        items.setShoppingListId(shoppingListId);
        return warehouseShoppingListService.editShoppingListItems(items);
    }
}
