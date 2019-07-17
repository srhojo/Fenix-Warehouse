package com.hojo.fenix.warehouse.clients.impl;

import com.hojo.fenix.warehouse.clients.VectaliaClient;
import com.hojo.fenix.warehouse.clients.VectaliaLines;
import com.hojo.fenix.warehouse.utils.rest.NameValuePair;
import com.hojo.fenix.warehouse.utils.rest.impl.RestClientExecutorV1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class VectaliaClientImpl implements VectaliaClient {


    private final RestTemplate restTemplate;

    @Value("${vectalia.alicante.api.url}")
    private String url;

    @Value("${vectalia.alicante.api.path.estimateStop}")
    private String estimatePathStop;

    public VectaliaClientImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public VectaliaLines getBusStopInfo(final Integer busStopId) {
        final String fullUrl = url + estimatePathStop;
        final List<NameValuePair> queryParams = new ArrayList<>();
        queryParams.add(NameValuePair.of("lang", "es"));
        queryParams.add(NameValuePair.of("__internal__", "1"));
        queryParams.add(NameValuePair.of("code", String.valueOf(busStopId)));

        return (VectaliaLines) RestClientExecutorV1.of(fullUrl, restTemplate)
                .setResponseType(VectaliaLines.class)
                .setParameters(queryParams)
                .execute();

    }
}
