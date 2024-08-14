package com.gothaxcity.idealworldcupreboot.post.api;

import com.gothaxcity.idealworldcupreboot.post.dto.PostCreateRequest;
import com.gothaxcity.idealworldcupreboot.post.dto.PostDto;
import com.gothaxcity.idealworldcupreboot.post.service.PostService;
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

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
        PostDto findPost = postService.postFindById(id);
        if (findPost == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(findPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.postDelete(id);
        return ResponseEntity.noContent().build();
    }


}
