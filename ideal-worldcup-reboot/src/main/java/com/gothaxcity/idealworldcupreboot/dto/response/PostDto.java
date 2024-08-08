package com.gothaxcity.idealworldcupreboot.dto.response;

import com.gothaxcity.idealworldcupreboot.domain.Post;
import com.gothaxcity.idealworldcupreboot.domain.PostImage;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Data
public class PostDto {

    private Long postId;
    private String title;
    private String content;
    private List<postImageDto> images;

    public PostDto(Post post) {
        postId = post.getId();
        title = post.getTitle();
        content = post.getContent();
        images = post.getPostImages().stream()
                .map(postImageDto::new)
                .collect(toList());
    }

    @Getter
    public static class postImageDto {
        private final Long postImageId;
        private final String uploadFileName;
        private final String storeFileName;

        public postImageDto(PostImage postImage) {
            postImageId = postImage.getId();
            uploadFileName = postImage.getUploadFileName();
            storeFileName = postImage.getStoreFileName();
        }
    }
}
