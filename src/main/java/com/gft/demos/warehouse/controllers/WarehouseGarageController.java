package com.gft.demos.warehouse.controllers;

import com.gft.demos.warehouse.api.WarehouseGarage;
import com.gft.demos.warehouse.domain.cdm.ContainerList;
import com.gft.demos.warehouse.domain.entities.VehicleEntity;
import com.gft.demos.warehouse.domain.entities.VehicleInspection;
import com.gft.demos.warehouse.domain.requests.VehicleInspectionRequest;
import com.gft.demos.warehouse.services.WarehouseVehicleService;
import com.gft.demos.warehouse.utils.aop.WarehouseLogger;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;


@Validated
@RestController
@WarehouseLogger
public class WarehouseGarageController implements WarehouseGarage {

    private final WarehouseVehicleService warehouseVehicleService;

    public WarehouseGarageController(WarehouseVehicleService warehouseVehicleService) {
        this.warehouseVehicleService = warehouseVehicleService;
    }

    @Override
    public ContainerList<VehicleEntity> searchVehicles(@RequestParam(value = "filter", required = false) final String filter,
                                                       @ApiParam(name = "limit", example = "2") @Valid
                                                       @RequestParam(value = "limit", required = false) @Min(value = 1, message = "Limit size must not be less than one!") final Integer limit,
                                                       @ApiParam(name = "offset", example = "0") @Valid
                                                       @RequestParam(value = "offset", required = false) @Min(value = 0L, message = "Offset size must not be less than zero!") final Long offset) {
        return warehouseVehicleService.search(filter, limit, offset);
    }

    @Override
    public VehicleEntity getVehicleById(final Long id) {
        return warehouseVehicleService.get(id);
    }

    @Override
    public VehicleEntity addVehicle(final VehicleEntity request) {
        return warehouseVehicleService.save(request);
    }

    @Override
    public VehicleEntity updateVehicle(final Long id, final @Valid VehicleEntity request) {
        request.setId(id);
        return warehouseVehicleService.save(request);
    }

    @Override
    public VehicleEntity updateVehicleImage(final Long id, @RequestPart(name = "image")  final MultipartFile[] files) {
        return warehouseVehicleService.updateVehicleImage(id,files[0]);
    }

    @Override
    public VehicleInspection getVehicleInspections(final Long vehicleId, final Long inspectionId) {
        return warehouseVehicleService.getInspectionDetail(vehicleId,inspectionId);

    }

    @Override
    public VehicleEntity addVehicleInspection(final Long vehicleId, final @Valid VehicleInspectionRequest request) {
        request.setVehicleId(vehicleId);
        return warehouseVehicleService.saveInspection(request);
    }

    @Override
    public VehicleEntity updateVehicleInspection(final Long vehicleId, final Long inspectionId, final @Valid VehicleInspectionRequest request) {
        request.setId(inspectionId);
        request.setVehicleId(vehicleId);
        return warehouseVehicleService.updateInspection(request);
    }

    @Override
    public void removeVehicleInspection(final Long vehicleId, final Long inspectionId) {
        warehouseVehicleService.deleteVehicleInspection(vehicleId, inspectionId);
    }


}
