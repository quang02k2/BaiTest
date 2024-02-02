package com.example.BaiTest.controllers;

import com.example.BaiTest.dtos.User.UserDTO;
import com.example.BaiTest.dtos.User.UserLoginDTO;
//import com.example.BaiTest.exceptions.CustomExceptionHandler;
import com.example.BaiTest.exceptions.DataNotFoundException;
import com.example.BaiTest.exceptions.DisabledException;
import com.example.BaiTest.model.User;
import com.example.BaiTest.responses.LoginResponse;
import com.example.BaiTest.responses.RegisterResponse;
import com.example.BaiTest.responses.UserResponse;
import com.example.BaiTest.services.implement.UserService;
import com.example.BaiTest.services.iservices.IUserService;

import com.example.BaiTest.components.LocalizationUtils;
import com.example.BaiTest.utils.MessageKeys;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final LocalizationUtils localizationUtils;
    private final UserDetailsService userDetailsService;
//    @Autowired
//    private CustomExceptionHandler customExceptionHandler;

    @PostMapping("/register")
    //can we register an "admin" user ?
    public ResponseEntity<RegisterResponse> createUser(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result
    ) {
        RegisterResponse registerResponse = new RegisterResponse();

        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            registerResponse.setMessage(errorMessages.toString());
            return ResponseEntity.badRequest().body(registerResponse);
        }
        try {
            User user = userService.createUser(userDTO);
            registerResponse.setMessage("Đăng kí thành công");
            registerResponse.setUser(user);
            return ResponseEntity.ok(registerResponse);
        } catch (Exception e) {
            registerResponse.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(registerResponse);
        }
    }

    /*
    Thêm tk admin
    * */

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        try {
            var loginResponse = userService.login(userLoginDTO);
            return ResponseEntity.ok().body(loginResponse);
        } catch (DataNotFoundException e) {
            // Email không tồn tại
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AuthenticationException e) {
            // Sai mật khẩu hoặc thông tin đăng nhập không hợp lệ
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }catch (DisabledException e){
            // taif khoản bị vô hiệu hóa
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            //lỗi khác do serve
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<UserResponse> getAllUser(@RequestParam int pageNumber, int limit) {
        try {
            var result = userService.getAllUser(pageNumber,limit);
            if (result != null) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.ok().body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/total-user-count")
    public ResponseEntity<?> getTotalUserCount(){
        try {
            var result = userService.getTotalUserCount();
            return  ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error server");
        }
    }
    @GetMapping("/total-user-locked")
    public ResponseEntity<?> getTotalUserLocked(){
        try {
            var result = userService.getTotalUserLocked();
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error server");
        }
    }



}
