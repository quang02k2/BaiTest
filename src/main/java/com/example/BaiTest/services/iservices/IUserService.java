package com.example.BaiTest.services.iservices;

import com.example.BaiTest.dtos.User.UserDTO;
import com.example.BaiTest.model.User;

public interface IUserService {
    User createUser(UserDTO userDTO) throws Exception;
    String login(String email, String password) throws Exception;

}
