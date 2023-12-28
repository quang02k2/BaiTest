package com.example.BaiTest.services;

import com.example.BaiTest.dtos.UserDTO;
import com.example.BaiTest.dtos.UserLoginDTO;
import com.example.BaiTest.model.User;
import com.example.BaiTest.responses.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    User createUser(UserDTO userDTO) throws Exception;
    String login(String email, String password) throws Exception;

}
