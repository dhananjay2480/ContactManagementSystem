package com.cms.response;

import com.cms.constant.AppConstants;
import com.cms.exception.AppException;

public class ResponseBuilder {
    public static BaseApiResponse getSuccessResponse(Object responseData) throws AppException {

        BaseApiResponse baseApiResponse = new BaseApiResponse();
        baseApiResponse.setResponseStatus(new ResponseStatus(AppConstants.StatusCodes.SUCCESS));
        baseApiResponse.setResponseData(responseData);
        baseApiResponse.setMessage("Success");

        return baseApiResponse;
    }

    public static BaseApiResponse getSuccessResponse() throws AppException {
        BaseApiResponse baseApiResponse = new BaseApiResponse();
        baseApiResponse.setResponseStatus(new ResponseStatus(AppConstants.StatusCodes.SUCCESS));
        baseApiResponse.setMessage("Success");
        baseApiResponse.setResponseData(null);
        return baseApiResponse;

    }
}
