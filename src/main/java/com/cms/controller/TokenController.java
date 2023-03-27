package com.cms.controller;

import com.cms.constant.AppConstants;
import com.cms.exception.UserNotFountException;
import com.cms.repository.UserRepository;
import com.cms.request.TokenRequestModel;
import com.cms.response.BaseApiResponse;
import com.cms.response.ResponseBuilder;
import com.cms.response.TokenResponseModel;
import com.cms.service.CustomUserDetailService;
import com.cms.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.soap.Addressing;
import java.util.Objects;

@RestController
public class TokenController {
    @Autowired
    private CustomUserDetailService service;

    @Autowired
    private JwtUtils utils;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/token")
    public ResponseEntity<BaseApiResponse> generateToken(@RequestBody TokenRequestModel requestModel) throws Exception {
        System.out.println(requestModel.getUserName()+requestModel.getPassword());
        if (userRepository.findByUserName(requestModel.getUserName())==null)
        {
            throw new UserNotFountException(AppConstants.ErrorTypes.USER_NOT_FOUND_ERROR_TYPE
                    ,AppConstants.ErrorCodes.USER_NOT_FOUND_ERROR_CODE
                    ,AppConstants.ErrorMessages.USER_NOT_FOUND_ERROR_MESSAGE);
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestModel.getUserName(),requestModel.getPassword()));
        }
        catch (UsernameNotFoundException exception)
        {
            exception.printStackTrace();
            throw new UserNotFountException(AppConstants.ErrorTypes.INVALID_INPUT_ERROR_TYPE
            ,AppConstants.ErrorCodes.INVALID_INPUT_ERROR_CODE,
                    AppConstants.ErrorMessages.INVALID_INPUT_ERROR_MESSAGE);
        }
        UserDetails details=service.loadUserByUsername(requestModel.getUserName());
        String token=utils.generateToken(details);
        BaseApiResponse response= ResponseBuilder.getSuccessResponse(token);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
