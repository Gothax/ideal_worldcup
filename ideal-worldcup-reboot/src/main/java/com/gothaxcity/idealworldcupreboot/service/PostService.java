package com.gothaxcity.idealworldcupreboot.service;

import com.gothaxcity.idealworldcupreboot.domain.Post;
import com.gothaxcity.idealworldcupreboot.dto.request.PostCreateRequest;
import com.gothaxcity.idealworldcupreboot.dto.response.PostDto;

import java.io.IOException;
import java.util.List;

public interface PostService {
    public PostDto createPost(PostCreateRequest postCreateRequest) throws IOException;

    public List<PostDto> postFindAll();
}
