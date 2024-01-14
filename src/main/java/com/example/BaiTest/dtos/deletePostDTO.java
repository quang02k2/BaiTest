package com.example.BaiTest.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class deletePostDTO {
    private int userID;
    private int postID;
}
