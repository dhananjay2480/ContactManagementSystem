package com.cms.controller;

import com.cms.request.ContactRequestModel;
import com.cms.response.BaseApiResponse;
import com.cms.response.ResponseBuilder;
import com.cms.service.ContactService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ContactController {
    @Autowired
    private ContactService service;

    @GetMapping(path = "/test")
    public String testing()
    {
        return "Welcome";
    }

    @PostMapping(path = "/create/contact")
    public ResponseEntity<BaseApiResponse> createContact(@RequestBody ContactRequestModel requestModel, HttpServletRequest request)
    {
        BaseApiResponse response= ResponseBuilder.getSuccessResponse(service.createContact(requestModel,request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping(path = "/get/contact")
    public ResponseEntity<BaseApiResponse> getContactById(@RequestParam @NonNull int contactId)
    {
        BaseApiResponse response= ResponseBuilder.getSuccessResponse(service.getContactById(contactId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(path = "/get/all/contact")
    public ResponseEntity<BaseApiResponse> getAllContact(@RequestBody ContactRequestModel requestModel)
    {
        BaseApiResponse response= ResponseBuilder.getSuccessResponse(service.getAllContact(requestModel));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping(path = "/delete/contact")
    public ResponseEntity<BaseApiResponse> deleteContactById(@RequestParam @NonNull int contactId)
    {
        BaseApiResponse response= ResponseBuilder.getSuccessResponse(service.deleteContactById(contactId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
