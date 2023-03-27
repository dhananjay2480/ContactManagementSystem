package com.cms.service;

import com.cms.request.ContactRequestModel;
import com.cms.response.ContactResponseModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ContactService {
    ContactResponseModel createContact(ContactRequestModel requestModel, HttpServletRequest request);

    ContactResponseModel getContactById(int contactId);

    List<ContactResponseModel> getAllContact(ContactRequestModel requestModel);

    Object deleteContactById(int contactId);
}
