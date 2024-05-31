package com.platform.platform.client;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public interface AbstractClient {

    default String urlBuilder(String host, String path, Map<String, String> query) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(host)
                .path(path);

        for (Map.Entry<String, String> pare : query.entrySet()) {
            uriComponentsBuilder.queryParam(pare.getKey(), pare.getValue());
        }
        return uriComponentsBuilder.toUriString();
    }

    default String urlBuilder(String host, String path) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(host)
                .path(path);
        return uriComponentsBuilder.toUriString();
    }
}
