package com.example.BaiTest.controllers;

import com.example.BaiTest.dtos.Post.ApprovePostDto;
import com.example.BaiTest.services.implement.ApprovePostServiceimpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/adminApprovePost")
//@RequestMapping("${api.prefix}/adminApprovePost")
@RequiredArgsConstructor
public class ApprovePostController {

    @Autowired
    private ApprovePostServiceimpl approvePostService;

    @PutMapping("approvePost")
    public ResponseEntity<?> approvePost(@RequestParam int approvePostID,@RequestParam int adminID ){
        ApprovePostDto approvePostDtoUpdate = this.approvePostService.addApprovePost(approvePostID, adminID);
        return new ResponseEntity<ApprovePostDto> (approvePostDtoUpdate, HttpStatus.OK);
    }

}
