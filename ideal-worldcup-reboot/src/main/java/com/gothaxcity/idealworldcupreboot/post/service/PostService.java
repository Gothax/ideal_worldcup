package com.gothaxcity.idealworldcupreboot.post.service;

import com.gothaxcity.idealworldcupreboot.accounts.domain.UserEntity;
import com.gothaxcity.idealworldcupreboot.accounts.dto.PrincipalUserDetails;
import com.gothaxcity.idealworldcupreboot.accounts.repository.UserRepository;
import com.gothaxcity.idealworldcupreboot.post.domain.Post;
import com.gothaxcity.idealworldcupreboot.post.domain.PostImage;
import com.gothaxcity.idealworldcupreboot.post.dto.PostCreateRequest;
import com.gothaxcity.idealworldcupreboot.post.dto.PostDto;
import com.gothaxcity.idealworldcupreboot.post.repository.PostImageRepository;
import com.gothaxcity.idealworldcupreboot.post.repository.PostRepository;
import com.gothaxcity.idealworldcupreboot.global.utils.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;
    private final FileStore fileStore;
    private final UserRepository userRepository;

    @Transactional
    public PostDto createPost(PostCreateRequest request, PrincipalUserDetails currentUser) throws IOException {

        UserEntity user = userRepository.findByEmail(currentUser.getUsername()).get();

        List<PostImage> savedPostImages = fileStore.storeFiles(request.getFiles());

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .build();

//        Post post = new Post(request.getTitle(), request.getContent());

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
    public List<PostDto> postFindAll() {
        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .map(PostDto::new)
                .collect(Collectors.toList());
    }

    // TODO 예외처리, CONTROLLER 응답 수정
    public PostDto postFindById(Long id) {
        Optional<Post> findPost = postRepository.findById(id);
        return findPost.map(PostDto::new).orElse(null);
    }

    @Transactional
    public void postDelete(Long id) {
        postRepository.deleteById(id);
        // TODO FILE STORAGE 삭제까지
    }

}
