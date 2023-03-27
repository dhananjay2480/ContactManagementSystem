package com.cms.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(value = {"cause","stackTrace","suppressed","localizedMessage"})
public class AppException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String errorType;

    private String errorCode;

    private String errorMessage;
}
