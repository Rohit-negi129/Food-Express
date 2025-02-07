package com.project.UserService.proxy;

import com.project.UserService.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-authentication",url = "localhost:8083")
public interface UserProxy {
    @PostMapping("api/v1/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User user);

}
