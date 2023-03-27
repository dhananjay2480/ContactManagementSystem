package com.cms.constant;

public interface AppConstants {

    interface StatusCodes {
        int SUCCESS = 1;
        int FAILURE = 0;
    }

    interface ErrorCodes {
        String USER_NOT_FOUND_ERROR_CODE = "101";
        String INVALID_INPUT_ERROR_CODE="102";
        String UNAUTHORISED_USER_ERROR_CODE="103";
        String CONTACT_NOT_FOUND_ERROR_CODE="104";
    }

    interface ErrorTypes {
        String USER_NOT_FOUND_ERROR_TYPE= "User Not Found";
        String INVALID_INPUT_ERROR_TYPE="Invalid Input";
        String UNAUTHORISED_USER_ERROR_TYPE="Access Denied";
        String CONTACT_NOT_FOUND_ERROR_TYPE="Contact not found";
    }

    interface ErrorMessages {
        String USER_NOT_FOUND_ERROR_MESSAGE= "User does not exist";
        String INVALID_INPUT_ERROR_MESSAGE="Please Provide valid username and password";
        String UNAUTHORISED_USER_ERROR_MESSAGE="Unauthorized access";
        String CONTACT_NOT_FOUND_ERROR_MESSAGE="Contact Doesn't Exist";
    }
}
