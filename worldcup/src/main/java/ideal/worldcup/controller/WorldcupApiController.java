package ideal.worldcup.controller;

import ideal.worldcup.domain.Post;
import ideal.worldcup.dto.AddPostRequest;
import ideal.worldcup.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.stream;

@RestController
@RequiredArgsConstructor
public class WorldcupApiController {

    private final PostService postService;

    @PostMapping("api/posts")
    public ResponseEntity post(@RequestBody AddPostRequest request) {
        Post savedPost = postService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedPost);
    }

    @GetMapping("api/posts")
    public ResponseEntity<List<PostResponse>> findAllPosts() {
        List<Object> posts = postService.findAll()
                .stream()
                .map(PostResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(posts);
    }
}
