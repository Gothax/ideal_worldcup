package com.gothaxcity.idealworldcupreboot.dto;

import com.gothaxcity.idealworldcupreboot.domain.Post;
import com.gothaxcity.idealworldcupreboot.domain.PostImage;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostCreateResponse {

    private Long postId;
    private String title;
    private String content;
    private List<PostImageCreateResponse> images;

    public PostCreateResponse(Post savedPost, List<PostImage> savedPostImages) {
        postId = savedPost.getId();
        title = savedPost.getTitle();
        content = savedPost.getContent();
        images = savedPostImages.stream()
                .map(PostImageCreateResponse::new)
                .collect(Collectors.toList());
    }

    @Getter
    public static class PostImageCreateResponse {
        private final Long postImageId;
        private final String uploadFileName;
        private final String storeFileName;

        public PostImageCreateResponse(PostImage postImage) {
            postImageId = postImage.getId();
            uploadFileName = postImage.getUploadFileName();
            storeFileName = postImage.getStoreFileName();
        }
    }
}
