package com.example.BaiTest.services;


import com.example.BaiTest.dtos.CommentPostDTO;
import com.example.BaiTest.model.CommentPost;
import com.example.BaiTest.model.Post;
import com.example.BaiTest.model.User;
import com.example.BaiTest.repository.CommentPostRepo;
import com.example.BaiTest.repository.PostRepo;
import com.example.BaiTest.repository.UserRepo;
import com.example.BaiTest.responses.CommentPostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class CommentPostService implements ICommentPostService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentPostRepo commentPostRepo;

    public int updateCommentCount (int postID){
        int countCommentofPost = 0 ;
        for (CommentPost cm: commentPostRepo.findAll()){
            if(cm.getPost().getId() == postID){
                countCommentofPost++;
            }
        }
        return countCommentofPost;
    }

    @Override
    public ResponseEntity<?> addCommentPost(CommentPostDTO commentPostDTO) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Post post = postRepo.findById(commentPostDTO.getPostID()).orElse(null);
        User user = userRepo.findById(commentPostDTO.getUserID()).orElse(null);
        if(post != null && user !=null) {
            CommentPost newCommentPost = CommentPost.builder()
                    .comment(commentPostDTO.getComment())
                    .dislikeCount(0)
                    .isActive(true)
                    .createAt(timestamp)
                    .post(post)
                    .user(user)
                    .updateAt(timestamp)
                    .likeCount(0)
                    .removeAt(null)
                    .build();
            commentPostRepo.save(newCommentPost);

            post.setCommentCount(updateCommentCount(post.getId()));
            postRepo.save(post);

            CommentPostResponse commentPostResponse = CommentPostResponse.builder()
                    .comment(newCommentPost.getComment())
                    .dislikeCount(newCommentPost.getDislikeCount())
                    .likeCount(newCommentPost.getLikeCount())
                    .build();
            return  new ResponseEntity<>(commentPostResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>("Cann't add comment", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> deleteCommentPost(int commentPostID) {


        return null;
    }
}
