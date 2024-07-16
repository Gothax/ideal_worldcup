package ideal.worldcup.service;

import ideal.worldcup.domain.Post;
import ideal.worldcup.dto.AddPostRequest;
import ideal.worldcup.repository.WorldcupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorldcupService {

    private final WorldcupRepository repository;

    public Post save(AddPostRequest request) {
        return repository.save(request.toEntity());
    }
}
