package com.gothaxcity.idealworldcupreboot.api;

import com.gothaxcity.idealworldcupreboot.dto.PostCreateRequest;
import com.gothaxcity.idealworldcupreboot.dto.PostCreateResponse;
import com.gothaxcity.idealworldcupreboot.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostApiController {

    private final PostServiceImpl postServiceImpl;

    @PostMapping
    public ResponseEntity<PostCreateResponse> createPost(@ModelAttribute PostCreateRequest postCreateRequest) {
        postServiceImpl.createPost(postCreateRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body()
    }

    @GetMapping
    public void postList(PostCreateRequest postCreateRequest, RedirectAttributes redirectAttributes) {

    }


}
