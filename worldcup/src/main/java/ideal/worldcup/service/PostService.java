package ideal.worldcup.service;

import ideal.worldcup.domain.Post;
import ideal.worldcup.dto.AddPostRequest;
import ideal.worldcup.repository.PostRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post save(AddPostRequest request) {
        return postRepository.save(request.toEntity());
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
