package com.example.BaiTest.responses;

import com.example.BaiTest.model.Post;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("post")
    private Post post;

    public PostResponse(String message) {
        this.message = message;
    }
}
