package com.gothaxcity.idealworldcupreboot.service;

import com.gothaxcity.idealworldcupreboot.dto.PostCreateRequest;
import com.gothaxcity.idealworldcupreboot.dto.PostCreateResponse;

import java.io.IOException;

public interface PostService {
    public PostCreateResponse createPost(PostCreateRequest postCreateRequest) throws IOException;

}
