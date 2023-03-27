package com.cms.universal;

import com.cms.constant.AppConstants;
import com.cms.exception.UserNotFountException;
import com.cms.response.BaseApiResponse;
import com.cms.response.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UniversalControllerExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFountException.class)
    public ResponseEntity<BaseApiResponse> userNotFound(UserNotFountException userNotFountException) {
        BaseApiResponse baseApiResponse = new BaseApiResponse();
        baseApiResponse.setResponseStatus(new ResponseStatus(AppConstants.StatusCodes.FAILURE));
        baseApiResponse.setResponseData(userNotFountException);
        return new ResponseEntity<>(baseApiResponse, HttpStatus.OK);
    }
}
