package com.gothaxcity.idealworldcupreboot.post.service;

import com.gothaxcity.idealworldcupreboot.post.dto.PostCreateRequest;
import com.gothaxcity.idealworldcupreboot.post.dto.PostDto;

import java.io.IOException;
import java.util.List;

public interface PostService {
    public PostDto createPost(PostCreateRequest postCreateRequest) throws IOException;
    public List<PostDto> postFindAll();
    public PostDto postFindById(Long id);
    public void postDelete(Long id);
}
