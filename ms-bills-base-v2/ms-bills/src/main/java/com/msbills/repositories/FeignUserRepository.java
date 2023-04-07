package com.msbills.repositories;

import com.msbills.security.feign.FeignInterceptor;
import com.msbills.models.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "users-service",url = "http://localhost:8086", configuration = FeignInterceptor.class)
public interface FeignUserRepository {

    @RequestMapping(method = RequestMethod.GET,value = "/users/{username}")
    ResponseEntity<UserDTO> findByUsername(@RequestParam String username);
}
