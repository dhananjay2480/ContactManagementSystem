package com.cms.serviceImpl;

import com.cms.constant.AppConstants;
import com.cms.entity.User;
import com.cms.exception.UserNotFountException;
import com.cms.repository.UserRepository;
import com.cms.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CustomUserDetainServiceImpl implements CustomUserDetailService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user=userRepository.findByUserName(username);

        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),new ArrayList<>());
    }
}
