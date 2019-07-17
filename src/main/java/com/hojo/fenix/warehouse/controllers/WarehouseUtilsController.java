package com.hojo.fenix.warehouse.controllers;

import com.hojo.fenix.warehouse.api.WarehouseUtils;
import com.hojo.fenix.warehouse.clients.VectaliaClient;
import com.hojo.fenix.warehouse.clients.VectaliaLines;
import com.hojo.fenix.warehouse.utils.aop.WarehouseLogger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@WarehouseLogger
public class WarehouseUtilsController implements WarehouseUtils {

    private final VectaliaClient vectaliaClient;

    //TODO: Call service not client directly...
    public WarehouseUtilsController(VectaliaClient vectaliaClient) {
        this.vectaliaClient = vectaliaClient;
    }

    @Override
    public VectaliaLines getBusStopInfo(Integer id) {
        return vectaliaClient.getBusStopInfo(id);
    }
}
