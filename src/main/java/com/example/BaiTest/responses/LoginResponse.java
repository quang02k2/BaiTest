package com.example.BaiTest.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {


    private String avatar;

    private String email;

    private String userName;

    private String dateOfBirth;

    private String gender;

    private String address;

    private String firstName;

    private String lastName;
    private int isActive;

    @JsonProperty("token")
    private String token;
    private List<String> roles;
}
