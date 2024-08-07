package com.gothaxcity.idealworldcupreboot.api;

import com.gothaxcity.idealworldcupreboot.domain.Post;
import com.gothaxcity.idealworldcupreboot.dto.request.PostCreateRequest;
import com.gothaxcity.idealworldcupreboot.dto.response.PostDto;
import com.gothaxcity.idealworldcupreboot.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostApiController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@ModelAttribute PostCreateRequest postCreateRequest) throws IOException {
        PostDto postDto = postService.createPost(postCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(postDto);
    }

    @GetMapping
    public List<PostDto> postList() {
        return postService.postFindAll();
    }


}
