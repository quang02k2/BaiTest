package com.example.BaiTest.dtos;

import com.example.BaiTest.model.PostSentence;
import com.example.BaiTest.model.User;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

    private String description;

    private String imagePost;

    private User user;

    private Set<PostSentence> sentenceSet;


}
