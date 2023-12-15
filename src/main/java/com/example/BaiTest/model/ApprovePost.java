package com.example.BaiTest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "approvePost")
public class ApprovePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private int adminApprovePostId;

    private int postId;

    private String statusPost;

    private Timestamp createAt;

    private Timestamp updateAt;
}
