package com.gothaxcity.idealworldcupreboot.service.impl;

import com.gothaxcity.idealworldcupreboot.domain.Post;
import com.gothaxcity.idealworldcupreboot.dto.PostCreateRequest;
import com.gothaxcity.idealworldcupreboot.dto.PostCreateResponse;
import com.gothaxcity.idealworldcupreboot.repository.PostRepository;
import com.gothaxcity.idealworldcupreboot.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostCreateResponse createPost(PostCreateRequest request) {
//        Post post = new Post();

    }


}
