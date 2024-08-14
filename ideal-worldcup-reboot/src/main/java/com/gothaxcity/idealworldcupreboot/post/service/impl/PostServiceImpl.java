package com.gothaxcity.idealworldcupreboot.post.service.impl;

import com.gothaxcity.idealworldcupreboot.post.domain.Post;
import com.gothaxcity.idealworldcupreboot.post.domain.PostImage;
import com.gothaxcity.idealworldcupreboot.post.dto.PostCreateRequest;
import com.gothaxcity.idealworldcupreboot.post.dto.PostDto;
import com.gothaxcity.idealworldcupreboot.post.repository.PostImageRepository;
import com.gothaxcity.idealworldcupreboot.post.repository.PostRepository;
import com.gothaxcity.idealworldcupreboot.global.utils.FileStore;
import com.gothaxcity.idealworldcupreboot.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // TODO 페이징
    @Override
    public List<PostDto> postFindAll() {
        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .map(PostDto::new)
                .collect(Collectors.toList());
    }

    // TODO 예외처리, CONTROLLER 응답 수정
    @Override
    public PostDto postFindById(Long id) {
        Optional<Post> findPost = postRepository.findById(id);
        return findPost.map(PostDto::new).orElse(null);
    }

    @Override
    @Transactional
    public void postDelete(Long id) {
        postRepository.deleteById(id);
        // TODO FILE STORAGE 삭제까지
    }

}
