package com.hojo.fenix.warehouse.api;

import com.hojo.fenix.warehouse.clients.VectaliaLines;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface WarehouseUtils {

    @GetMapping("/utils/busstop/{id}")
    VectaliaLines getBusStopInfo(@PathVariable Integer id);
}
