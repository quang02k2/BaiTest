package com.example.BaiTest.services;

import com.example.BaiTest.dtos.PostDTO;
import com.example.BaiTest.model.Post;
import com.example.BaiTest.model.PostSentence;
import com.example.BaiTest.model.User;
import com.example.BaiTest.repository.PostRepo;
import com.example.BaiTest.repository.PostSentenceRepo;
import com.example.BaiTest.repository.UserRepo;
import com.example.BaiTest.responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;

@Service
public class PostService implements iPostService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private PostSentenceRepo postSentenceRepo;


    @Override
    public ResponseEntity<PostResponse> addPost(PostDTO post) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int userID = post.getUser().getId();
        User user = userRepo.findById(userID).orElse(null);
        if (user != null) {
            Set<PostSentence> postSentences = post.getSentenceSet();

            Post newPost = Post.builder()
                    .description(post.getDescription())
                    .imagePost(post.getImagePost())
                    .user(user)
                    .createAt(timestamp)
                    .likeCount(0)
                    .commentCount(0)
                    .build();
            postRepo.save(newPost);

            postSentences.forEach(x -> {
                x.setPost(newPost);
                postSentenceRepo.save(x);
            });
            return new ResponseEntity<>(new PostResponse("Thêm bài viết thành công", newPost), HttpStatus.OK);
        }

        return new ResponseEntity<>(new PostResponse("Thêm bài viết thất bại"), HttpStatus.BAD_REQUEST);
    }

    @Override
    public boolean deletePost(int postId) {
        return false;
    }
}
