package com.gothaxcity.idealworldcupreboot.service.impl;

import com.gothaxcity.idealworldcupreboot.domain.Post;
import com.gothaxcity.idealworldcupreboot.domain.PostImage;
import com.gothaxcity.idealworldcupreboot.dto.request.PostCreateRequest;
import com.gothaxcity.idealworldcupreboot.dto.response.PostDto;
import com.gothaxcity.idealworldcupreboot.repository.PostImageRepository;
import com.gothaxcity.idealworldcupreboot.repository.PostRepository;
import com.gothaxcity.idealworldcupreboot.service.PostService;
import com.gothaxcity.idealworldcupreboot.utils.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;
    private final FileStore fileStore;

    @Override
    @Transactional
    public PostDto createPost(PostCreateRequest request) throws IOException {

        List<PostImage> savedPostImages = fileStore.storeFiles(request.getFiles());
        Post post = new Post(request.getTitle(), request.getContent());

        if (!savedPostImages.isEmpty()) {
            for (PostImage savedPostImage : savedPostImages) {
                savedPostImage.setPost(post);
                postImageRepository.save(savedPostImage);
            }
        }
        Post savedPost = postRepository.save(post);
        return new PostDto(savedPost);
    }

    @Override
    public List<PostDto> postFindAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostDto::new)
                .collect(Collectors.toList());
    }

}
