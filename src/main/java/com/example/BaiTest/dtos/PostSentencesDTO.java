package com.example.BaiTest.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostSentencesDTO {
    private String imagePost;

    private String imageTile;

    private String content;

    private int sortNumber;

}
