package com.gothaxcity.idealworldcupreboot.service;

import com.gothaxcity.idealworldcupreboot.dto.PostCreateRequest;
import com.gothaxcity.idealworldcupreboot.dto.PostCreateResponse;

public interface PostService {
    public PostCreateResponse createPost(PostCreateRequest postCreateRequest);

}
