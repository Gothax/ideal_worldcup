package ideal.worldcup.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post_image")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_image_id")
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    // 생성 메서드
    public static PostImage createPostImage(String imageUrl, Post post) {
        PostImage postImage = new PostImage();
        postImage.setImageUrl(imageUrl);
        postImage.setPost(post);
        return postImage;
    }

}