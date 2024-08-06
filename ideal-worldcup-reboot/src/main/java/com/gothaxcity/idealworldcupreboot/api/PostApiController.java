package com.gothaxcity.idealworldcupreboot.api;

import com.gothaxcity.idealworldcupreboot.dto.PostCreateRequest;
import com.gothaxcity.idealworldcupreboot.dto.PostCreateResponse;
import com.gothaxcity.idealworldcupreboot.service.PostService;
import com.gothaxcity.idealworldcupreboot.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostApiController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostCreateResponse> createPost(@ModelAttribute PostCreateRequest postCreateRequest) throws IOException {
        PostCreateResponse postCreateResponse = postService.createPost(postCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(postCreateResponse);
    }

    @GetMapping
    public void postList(PostCreateRequest postCreateRequest, RedirectAttributes redirectAttributes) {

    }


}
