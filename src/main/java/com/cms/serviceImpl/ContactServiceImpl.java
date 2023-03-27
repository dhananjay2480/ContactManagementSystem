package com.cms.serviceImpl;

import com.cms.constant.AppConstants;
import com.cms.entity.ContactEntity;
import com.cms.exception.UserNotFountException;
import com.cms.repository.ContactRepository;
import com.cms.request.ContactRequestModel;
import com.cms.response.ContactResponseModel;
import com.cms.service.ContactService;
import com.cms.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private JwtUtils utils;

    @Override
    public ContactResponseModel createContact(ContactRequestModel requestModel, HttpServletRequest request) {
        return convertToResponseModel(contactRepository.save(convertToEntityModel(requestModel,request)));
    }

    @Override
    public ContactResponseModel getContactById(int contactId) {
        Optional<ContactEntity> contactEntity=contactRepository.findById(contactId);
        if (!contactEntity.isPresent())
            throw new UserNotFountException(AppConstants.ErrorTypes.CONTACT_NOT_FOUND_ERROR_TYPE
                    ,AppConstants.ErrorCodes.CONTACT_NOT_FOUND_ERROR_CODE
                    ,AppConstants.ErrorMessages.CONTACT_NOT_FOUND_ERROR_MESSAGE);
        return convertToResponseModel(contactEntity.get());
    }

    @Override
    public List<ContactResponseModel> getAllContact(ContactRequestModel requestModel) {
        return contactRepository.finAllContacts(requestModel.getFirstName(),requestModel.getLastName(),requestModel.getEmail()).stream().map(this::convertToResponseModel).collect(Collectors.toList());
    }

    @Override
    public Object deleteContactById(int contactId) {
        contactRepository.deleteById(contactId);
        return "Success";
    }

    private ContactResponseModel convertToResponseModel(ContactEntity entity) {
        return ContactResponseModel.builder()
                .id(entity.getId()).createdBy(entity.getCreatedBy()).updatedBy(entity.getUpdatedBy())
                .firstName(entity.getFirstName()).LastName(entity.getLastName()).email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .build();
    }

    private ContactEntity convertToEntityModel(ContactRequestModel requestModel,HttpServletRequest request) {

    return ContactEntity.builder()
            .id(requestModel.getId()).createdBy(requestModel.getId()==null?utils.extractUsername(request.getHeader("Authorization").substring(7)):getContactById(requestModel.getId()).getCreatedBy())
            .updatedBy(requestModel.getId()!=null?utils.extractUsername(request.getHeader("Authorization").substring(7)):null)
            .firstName(requestModel.getFirstName()).lastName(requestModel.getLastName()).email(requestModel.getEmail())
            .phoneNumber(requestModel.getPhoneNumber())
            .build();
    }
}
