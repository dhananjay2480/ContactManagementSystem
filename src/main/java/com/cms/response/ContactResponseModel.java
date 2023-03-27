package com.cms.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactResponseModel {

    private int id;

    private String createdBy;

    private String updatedBy;

    private String firstName;

    private String LastName;

    private String email;

    private String phoneNumber;
}
