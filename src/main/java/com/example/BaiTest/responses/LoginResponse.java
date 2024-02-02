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
    private String userName;
    @JsonProperty("token")
    private String token;
    private List<String> roles;
}
