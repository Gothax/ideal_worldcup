package com.gothaxcity.idealworldcupreboot.post.api;

import com.gothaxcity.idealworldcupreboot.post.dto.PostCreateRequest;
import com.gothaxcity.idealworldcupreboot.post.dto.PostDto;
import com.gothaxcity.idealworldcupreboot.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostApiController {

    private final PostService postService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PostDto> createPost(@ModelAttribute @Valid PostCreateRequest postCreateRequest,
                                              @AuthenticationPrincipal UserPrincipal currentUser) throws IOException {
        System.out.println("currentUser = " + currentUser);
        PostDto postDto = postService.createPost(postCreateRequest, currentUser);
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
