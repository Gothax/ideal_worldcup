package ideal.worldcup.controller;

import ideal.worldcup.domain.Post;
import ideal.worldcup.dto.AddPostRequest;
import ideal.worldcup.service.WorldcupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorldcupApiController {

    private final WorldcupService worldcupService;

    @PostMapping("/api/posts")
    public ResponseEntity<Post> addPost(@RequestBody AddPostRequest request) {
        Post savedPost = worldcupService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedPost);
    }
}
