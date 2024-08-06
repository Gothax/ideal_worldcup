package com.gothaxcity.idealworldcupreboot.service.impl;

import com.gothaxcity.idealworldcupreboot.domain.Post;
import com.gothaxcity.idealworldcupreboot.domain.PostImage;
import com.gothaxcity.idealworldcupreboot.dto.PostCreateRequest;
import com.gothaxcity.idealworldcupreboot.dto.PostCreateResponse;
import com.gothaxcity.idealworldcupreboot.repository.PostImageRepository;
import com.gothaxcity.idealworldcupreboot.repository.PostRepository;
import com.gothaxcity.idealworldcupreboot.service.PostService;
import com.gothaxcity.idealworldcupreboot.utils.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;
    private final FileStore fileStore;

    @Transactional
    public PostCreateResponse createPost(PostCreateRequest request) throws IOException {

        List<PostImage> savedPostImages = fileStore.storeFiles(request.getFiles());
        Post post = new Post(request.getTitle(), request.getContent());

        if (!savedPostImages.isEmpty()) {
            for (PostImage savedPostImage : savedPostImages) {
                savedPostImage.setPost(post);
                postImageRepository.save(savedPostImage);
            }
        }
        Post savedPost = postRepository.save(post);
        return new PostCreateResponse(savedPost, savedPostImages);
    }

}
