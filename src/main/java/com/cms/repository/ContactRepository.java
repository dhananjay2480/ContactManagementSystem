package com.cms.repository;

import com.cms.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<ContactEntity ,Integer> {

    @Query(value = "select * from user_contact_details AS c WHERE  \n" +
            "(c.first_name IN (:firstName) or coalesce(:firstName,null) is null) AND \n" +
            "(c.last_name in (:lastName) or coalesce(:lastName,null) is null) AND  \n" +
            "(c.email in (:email) or coalesce(:email,null) is NULL) ORDER BY c.id desc", nativeQuery = true)
    List<ContactEntity> finAllContacts(String firstName, String lastName, String email);
}
