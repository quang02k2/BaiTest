package com.example.BaiTest.services;

import com.example.BaiTest.components.JwtTokenUtils;
import com.example.BaiTest.components.LocalizationUtils;
import com.example.BaiTest.dtos.UserDTO;
import com.example.BaiTest.dtos.UserLoginDTO;
import com.example.BaiTest.exceptions.DataNotFoundException;

import com.example.BaiTest.exceptions.PermissionDenyException;
import com.example.BaiTest.model.*;
import com.example.BaiTest.repository.RolesRepo;
import com.example.BaiTest.repository.UserRepo;
import com.example.BaiTest.responses.LoginResponse;
import com.example.BaiTest.utils.MessageKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.swing.text.html.Option;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{
    private final UserRepo userRepository;
    private final RolesRepo roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final LocalizationUtils localizationUtils;
    @Override
    public User createUser(UserDTO userDTO) throws Exception {
        //register user
        String email = userDTO.getEmail();
        // Kiểm tra xem email đã tồn tại hay chưa
        if(userRepository.existsByEmail(email)) {
            throw new DataIntegrityViolationException("Email already exists");
        }
        Roles userRole = roleRepository.findById(2).orElseThrow(()
                -> new IllegalStateException("Role not found with ID 1"));
        //convert from userDTO => user
        User newUser = User.builder()
                .avatar(" ")
                .email(userDTO.getEmail())
                .userName(userDTO.getUserName())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .address(userDTO.getAddress())
                .dateOfBirth(userDTO.getDateOfBirth())
                .gender(userDTO.getGender())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .role(userRole)
                .build();

        // Kiểm tra nếu có accountId, không yêu cầu password
//        if (userDTO.getFacebookAccountId() == 0 && userDTO.getGoogleAccountId() == 0) {
//            String password = userDTO.getPassword();
//            String encodedPassword = passwordEncoder.encode(password);
//            newUser.setPassword(encodedPassword);
//        }
        return userRepository.save(newUser);
    }



    @Override
    public String login(
            String email,
            String password
    ) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) {
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.WRONG_PHONE_PASSWORD));
        }
        //return optionalUser.get();//muốn trả JWT token ?
        User existingUser = optionalUser.get();
        //check password

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                email, password,
                existingUser.getAuthorities()
        );

        //authenticate with Java Spring security
        authenticationManager.authenticate(authenticationToken);
        String token = jwtTokenUtil.generateToken(existingUser);
        return token;
    }
}
