package com.example.BaiTest.dtos;

import com.example.BaiTest.model.PostSentence;
import com.example.BaiTest.model.User;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDTO {
    private int Id;

    private String description;

    private String imagePost;

    private Integer userID;

    private List<PostSentencesDTO> postSentences;

}
