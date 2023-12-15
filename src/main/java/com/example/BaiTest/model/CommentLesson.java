package com.example.BaiTest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commentLesson")
public class CommentLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String comment;

    private int likeCount;

    private int dislikeCount;

    private Timestamp createAt;

    private Timestamp updateAt;

    private Timestamp removeAt;

    private boolean isActive;
}
