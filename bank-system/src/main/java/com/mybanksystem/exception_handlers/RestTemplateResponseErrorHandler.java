package com.mybanksystem.exception_handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;
@Slf4j
public class RestTemplateResponseErrorHandler  implements ResponseErrorHandler{

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return new DefaultResponseErrorHandler().hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            // handle 5xx errors
            // raw http status code e.g `500`
            log.info(String.valueOf(response.getRawStatusCode()));

            // http status code e.g. `500 INTERNAL_SERVER_ERROR`
            log.info(response.getStatusCode().toString());

        } else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            // handle 4xx errors


            log.info(String.valueOf(response.getRawStatusCode()));
            // http status code e.g. `500 INTERNAL_SERVER_ERROR`
            log.info(response.getStatusCode().toString());

            // get response body
            log.info(response.getBody().toString());

            // get http headers
            HttpHeaders headers = response.getHeaders();
            log.info(headers.get("Content-Type").toString());
            log.info(headers.get("Server").toString());
        }
    }

}
