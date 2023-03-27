package com.cms.config;

import com.cms.constant.AppConstants;
import com.cms.exception.UserNotFountException;
import com.cms.service.CustomUserDetailService;
import com.cms.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class FilterConfig extends OncePerRequestFilter {
    @Autowired
    private JwtUtils utils;
    @Autowired
    private CustomUserDetailService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //get token
        String token=request.getHeader("Authorization");
        String userName;
        if(token!=null && token.startsWith("Bearer "))
        {
            token=token.substring(7);
            userName=utils.extractUsername(token);
            UserDetails details=service.loadUserByUsername(userName);
            if (userName!=null && SecurityContextHolder.getContext().getAuthentication()==null)
            {
                UsernamePasswordAuthenticationToken passwordAuthenticationToken=new UsernamePasswordAuthenticationToken(details,null,details.getAuthorities());
                passwordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken);
            }
            else
                throw new UserNotFountException(AppConstants.ErrorTypes.UNAUTHORISED_USER_ERROR_TYPE
                        ,AppConstants.ErrorCodes.UNAUTHORISED_USER_ERROR_CODE
                        ,AppConstants.ErrorMessages.UNAUTHORISED_USER_ERROR_MESSAGE);


        }

        filterChain.doFilter(request,response);



    }
}
