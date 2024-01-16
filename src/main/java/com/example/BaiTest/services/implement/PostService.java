package com.example.BaiTest.services.implement;

import com.example.BaiTest.dtos.Post.PostDTO;
import com.example.BaiTest.dtos.Post.PostSentencesDTO;

import com.example.BaiTest.model.Post;
import com.example.BaiTest.model.PostSentence;
import com.example.BaiTest.model.User;
import com.example.BaiTest.repository.PostRepo;
import com.example.BaiTest.repository.PostSentenceRepo;
import com.example.BaiTest.repository.UserRepo;
import com.example.BaiTest.responses.PostResponse;
import com.example.BaiTest.responses.PostSentenceResponse;
import com.example.BaiTest.responses.PostUserResponse;
import com.example.BaiTest.services.iservices.iPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService implements iPostService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private PostSentenceRepo postSentenceRepo;

//    @Autowired
//    private ModelMapper modelMapper;


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
            List<PostSentenceResponse> postsentenceResponseList = new ArrayList<>();

            for(int i=0; i<postSentences.size(); i++) {
                PostSentence p = new PostSentence();
                p.setImagePost(postSentences.get(i).getImagePost());
                p.setPost(newPost);
                p.setImageTile(postSentences.get(i).getImageTile());
                p.setContent(postSentences.get(i).getContent());
                p.setSortNumber(i+1);
                postSentenceRepo.save(p);
//            postSentences.forEach(x -> {
//                PostSentence p = new PostSentence();
//                p.setImagePost(x.getImagePost());
//                p.setPost(newPost);
//                p.setImageTile(x.getImageTile());
//                p.setContent(x.getContent());
//                p.setSortNumber(autoIncrement(sortnumber));
//                sortnumber++;
//                postSentenceRepo.save(p);

                postsentenceResponseList.add(PostSentenceResponse.builder()
                        .imagePost(postSentences.get(i).getImagePost())
                        .imageTile(postSentences.get(i).getImageTile())
                        .content(postSentences.get(i).getContent())
                        .sortNumber(postSentences.get(i).getSortNumber()).build());

            };

            return new ResponseEntity<>(PostResponse.builder()
                    .imagePost(newPost.getImagePost())
                    .description(newPost.getDescription())
                    .updateAt(newPost.getUpdateAt())
                    .createAt(newPost.getCreateAt())
                    .likeCount(newPost.getLikeCount())
                    .commentCount(newPost.getCommentCount())
                    .user(PostUserResponse.builder()
                            .nameUser(user.getUsername())
                            .avatar(user.getAvatar()).build())

                    .sentences(postsentenceResponseList)

                    .build(),HttpStatus.OK);
        }
        return new ResponseEntity<>(new PostResponse(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> revisePost(PostDTO postdto) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int postID = postdto.getPostId();
        Post post = postRepo.findById(postID).orElse(null);
        if(post != null) {

            List<PostSentencesDTO> postSentencesDTO = postdto.getPostSentences();

            post.setDescription(postdto.getDescription());
            post.setImagePost(postdto.getImagePost());
            post.setUser(userRepo.findById(postdto.getUserID()).orElse(null));
            post.setUpdateAt(timestamp);
            postRepo.save(post);

            postSentenceRepo.findAll().forEach(x-> {
                if(x.getPost().getId() == postID) {
                    x.setPost(null);
                    postSentenceRepo.deleteById(x.getId());
                }
            });

            postSentencesDTO.forEach(x -> {
                PostSentence p = new PostSentence();
                p.setImagePost(x.getImagePost());
                p.setPost(post);
                p.setImageTile(x.getImageTile());
                p.setSortNumber(x.getSortNumber());
                p.setContent(x.getContent());
                postSentenceRepo.save(p);
            });

            return new ResponseEntity<>("Revised", HttpStatus.OK);
        }
        return new ResponseEntity<>("Can't revise", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> deletePost(int id) {
//        Optional<Post> post = postRepo.findById(id);
//        if(post.isPresent()){
//            for (PostSentence postSentence : postSentenceRepo.findAll()){
//                if (postSentence.getPost().getId() == id){
//                    postSentence.setPost(null);
//                    postSentenceRepo.deleteById(postSentence.getId());
//                }
//            }
//            postRepo.deleteById(id);
//            return new ResponseEntity<>("Deleted", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("xoa that bai", HttpStatus.OK);
        Optional<Post> post1 = postRepo.findById(id);
        if(post1.isPresent()){
            for (PostSentence postSentence : postSentenceRepo.findAll()){
                if (postSentence.getPost().getId() == id){
                    postSentence.setPost(null);
                    postSentenceRepo.deleteById(postSentence.getId());
                }
            }
            postRepo.deleteById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("xoa that bai", HttpStatus.BAD_REQUEST);
    }



//    public User dtoToUser(UserDTO userDto){
//        User user = this.modelMapper.map(userDto, User.class);
//        return user;
//    }
//
//
//    public UserDTO userToDto(User user) {
//        UserDTO userDto = this.modelMapper.map(user, UserDTO.class);
//        return userDto;
//
//    }

}