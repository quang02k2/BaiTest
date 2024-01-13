package com.example.BaiTest.services;

import com.example.BaiTest.dtos.PostDTO;
import com.example.BaiTest.dtos.PostSentencesDTO;
import com.example.BaiTest.model.Post;
import com.example.BaiTest.model.PostSentence;
import com.example.BaiTest.model.User;
import com.example.BaiTest.repository.PostRepo;
import com.example.BaiTest.repository.PostSentenceRepo;
import com.example.BaiTest.repository.UserRepo;
import com.example.BaiTest.responses.PostResponse;
import com.example.BaiTest.responses.PostUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
//        int userID = post.getUser().getId();
        // todo useRepo tra về Optional (null thi throw exception )
        User user = userRepo.findById(post.getUserID()).orElse(null);
        if (user != null) {
            List<PostSentencesDTO> postSentences = post.getPostSentences();

            Post newPost = Post.builder()
                    .description(post.getDescription())
                    .imagePost(post.getImagePost())
                    .user(user)
                    .createAt(timestamp)
                    .updateAt(timestamp)
                    .likeCount(0)
                    .commentCount(0)
                    .build();
            postRepo.save(newPost);
//            List<PostsentenceResponse> postsentenceResponseList = new ArrayList<>();
            postSentences.forEach(x -> {
                PostSentence p = new PostSentence();
                p.setImagePost(x.getImagePost());
                p.setPost(newPost);
                p.setImageTile(x.getImageTile());
                p.setSortNumber(x.getSortNumber());
                p.setContent(x.getContent());
                postSentenceRepo.save(p);

//                postsentenceResponseList.add(PostsentenceResponse.builder().build());

            });

            return new ResponseEntity<>(PostResponse.builder().imagePost(newPost.getImagePost())
                    .description(newPost.getDescription())
                    .updateAt(newPost.getUpdateAt())
                    .createAt(newPost.getCreateAt())
                    .likeCount(newPost.getLikeCount())
                    .commentCount(newPost.getCommentCount())
                    .user(PostUserResponse.builder()
                            .nameUser(user.getUsername())
                            .avatar(user.getAvatar()).build())
//                    .sentences(postsentenceResponseList)
                    .build(),HttpStatus.OK);
        }
        return new ResponseEntity<>(new PostResponse(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public boolean deletePost(int postId) {
        return false;
    }
}
