package com.hojo.fenix.warehouse.utils.rest.impl;

import com.hojo.fenix.warehouse.utils.rest.NameValuePair;
import com.hojo.fenix.warehouse.utils.rest.RestClientExecutor;
import com.hojo.fenix.warehouse.utils.rest.RestClientObject;
import com.hojo.fenix.warehouse.utils.rest.exceptions.RestClientExecutorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @Author Hojo
 */
public class RestClientExecutorV1 implements RestClientExecutor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //Required parameters
    private final String url;
    private final RestTemplate restTemplate;
    private final HttpMethod httpMethod;
    //Internal parameters
    private final HttpHeaders headers;
    private HttpEntity<Object> entity;
    //Optional parameters
    private Optional<Class<? extends RestClientObject>> responseType;
    private Optional<RestClientObject> request;
    private List<NameValuePair> queryParams;
    private List<NameValuePair> optionalHeaders;


    private RestClientExecutorV1(final String url, final RestTemplate restTemplate, final HttpMethod httpMethod) {
        this.url = url;
        this.restTemplate = restTemplate;
        this.httpMethod = httpMethod;
        this.request = Optional.empty();
        this.responseType = Optional.empty();
        this.optionalHeaders = new ArrayList<>();
        this.queryParams = new ArrayList<>();
        this.headers = new HttpHeaders();
    }

    public static RestClientExecutorV1 of(final String url, final RestTemplate restTemplate) {
        return new RestClientExecutorV1(url, restTemplate, HttpMethod.GET);
    }

    public static RestClientExecutorV1 of(@NotNull final String url, @NotNull final RestTemplate restTemplate, @NotNull final HttpMethod httpMethod) {
        return new RestClientExecutorV1(url, restTemplate, httpMethod);
    }

    public RestClientExecutorV1 setResponseType(@NotNull final Class<? extends RestClientObject> responseType) {
        this.responseType = Optional.of(responseType);
        return this;
    }

    public RestClientExecutorV1 setRequest(@NotNull final RestClientObject request) {
        this.request = Optional.of(request);
        return this;
    }

    public RestClientExecutorV1 setHeaders(@NotNull final List<NameValuePair> headers) {
        this.optionalHeaders.addAll(headers);
        return this;
    }

    public RestClientExecutorV1 setParameters(@NotNull final List<NameValuePair> queryParams) {
        this.queryParams.addAll(queryParams);
        return this;
    }


    @Override
    public RestClientObject execute() {
        try {
            logger.info("@@@ - Executing RestClient: - @@@");
            logger.info(String.format("@@@ - Method: %s", this.httpMethod.name()));

            //Configure Headers
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            if (!this.optionalHeaders.isEmpty()) {
                optionalHeaders.forEach(header -> headers.set(header.getName(), header.getValue()));
            }

            logger.info("@@@ - Headers:");
            headers.entrySet().forEach(e -> {
                logger.info(String.format("@@@ - - Key: %s, Value: %s", e.getKey(), e.getValue()));
            });

            //Configure request
            if (request.isPresent()) {
                entity = new HttpEntity<>(request.get(), headers);
            } else {
                entity = new HttpEntity<>(headers);
            }

            //Configure URI
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(this.url);
            queryParams.forEach(qp -> builder.queryParam(qp.getName(), qp.getValue()));

            logger.info(String.format("@@@ - Uri: %s", builder.toUriString()));

            //Configure response
            if (this.responseType.isPresent()) {
                logger.info(String.format("@@@ - Expected response: %s", this.responseType.get()));
                return restTemplate.exchange(builder.toUriString(), this.httpMethod, this.entity, this.responseType.get()).getBody();
            }
            restTemplate.exchange(builder.toUriString(), this.httpMethod, this.entity, Void.class);
            return null;

        } catch (final RestClientResponseException re) {
            throw new RestClientExecutorException(HttpStatus.valueOf(re.getRawStatusCode()), "1001", re);
        } catch (final Exception e) {
            throw new RestClientExecutorException(HttpStatus.INTERNAL_SERVER_ERROR, "1002", e.getMessage());
        }
    }
}
