package com.example.BaiTest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String avatar;

    private String userName;

    private String password;

    private String email;

    private Timestamp dateOfBirth;

    private String gender;

    private String address;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "adviceContact", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<AdviceContact> adviceContact;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId", foreignKey = @ForeignKey(name = "fk_User_Role"), nullable = false)
    @JsonManagedReference
    private Roles role;

    @OneToMany(mappedBy = "refreshTokens", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<RefreshTokens> refreshTokens;

    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Notification> notification;

    @OneToMany(mappedBy = "learningExperience", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<LearningExperience> learningExperience;

    @OneToMany(mappedBy = "userCourse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<UserCourse> userCourse;

    @OneToMany(mappedBy = "commentLesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<CommentLesson> commentLesson;

    @OneToMany(mappedBy = "userLikeCommentLesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<UserLikeCommentLesson> userLikeCommentLesson;

    @OneToMany(mappedBy = "userLessonCheckpoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<UserLessonCheckpoint> userLessonCheckpoint;

    @OneToMany(mappedBy = "userLikePost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<UserLikePost> userLikePost;

    @OneToMany(mappedBy = "approvePost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<ApprovePost> approvePost;

    @OneToMany(mappedBy = "commentPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<CommentPost> commentPost;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Post> post;

    @OneToMany(mappedBy = "userLikeCommentPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<UserLikeCommentPost> userLikeCommentPost;

}
