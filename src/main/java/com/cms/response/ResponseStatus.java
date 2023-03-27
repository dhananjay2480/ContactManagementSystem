package com.cms.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseStatus {
    private  int statusCode;

    public ResponseStatus(int statusCode) {
        this.statusCode=statusCode;
    }

    public ResponseStatus() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
