package com.gothaxcity.idealworldcupreboot.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String content;

    @OneToMany(mappedBy = "post")
    private List<PostImage> postImages = new ArrayList<>();

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addPostImage(PostImage postImage) {
        postImages.add(postImage);
        if (postImage.getPost() != this) {
            postImage.setPost(this);
        }
    }
}
