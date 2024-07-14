package ideal.worldcup.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostImage> images = new ArrayList<>();

    // 생성 메서드
    public static Post createPost(String title, LocalDateTime createAt, List<String>imageUrls ,Member member) {
        Post post = new Post();
        post.setTitle(title);
        post.setCreateAt(LocalDateTime.now());
        post.setUpdateAt(LocalDateTime.now());
        post.setMember(member);

        for (String imageUrl : imageUrls) {
            post.addImage(PostImage.createPostImage(imageUrl, post));
        }
        
        return post;
    }

    private void addImage(PostImage postImage) {
        this.images.add(postImage);
    }

}
