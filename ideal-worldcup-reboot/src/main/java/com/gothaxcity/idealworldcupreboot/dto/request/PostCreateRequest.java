package com.gothaxcity.idealworldcupreboot.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostCreateRequest {
    private String title;
    private String content;
    private List<MultipartFile> files;
}
