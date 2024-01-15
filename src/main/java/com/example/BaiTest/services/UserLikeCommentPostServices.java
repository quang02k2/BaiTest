package com.example.BaiTest.services;

import com.example.BaiTest.model.CommentPost;
import com.example.BaiTest.model.User;
import com.example.BaiTest.model.UserLikeCommentPost;
import com.example.BaiTest.repository.CommentPostRepo;
import com.example.BaiTest.repository.UserLikeCommentPostRepo;
import com.example.BaiTest.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserLikeCommentPostServices implements IUserLikeCommentPostService {
    @Autowired
    private UserLikeCommentPostRepo userLikeCommentPostRepoo;
    @Autowired
    private CommentPostRepo commentPostRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public ResponseEntity<?> addUserLikeCommentPost(UserLikeCommentPost userLikeCommentPost) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        CommentPost commentPost = commentPostRepo.findById(userLikeCommentPost.getCommentPost().getId()).orElse(null);
        User user = userRepo.findById(userLikeCommentPost.getUser().getId()).orElse(null);
        if(commentPost !=  null){
            UserLikeCommentPost userlikeCommentpost = UserLikeCommentPost.builder()
                    .user(user)
                    .commentPost(commentPost)
                    .createAt(timestamp)
                    .build();
            userLikeCommentPostRepoo.save(userlikeCommentpost);
            return new ResponseEntity<>("liked", HttpStatus.OK);
        }
        return new ResponseEntity<>("Cann't like", HttpStatus.BAD_REQUEST);
    }
}
