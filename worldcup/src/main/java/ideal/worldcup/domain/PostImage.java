package ideal.worldcup.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Entity
@Table(name = "post_image")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImage {

    @Id @GeneratedValue
    @Column(name = "post_image_id")
    private Long id;

    private String originFileName;
    private String storeFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    // 연관관계 메서드
    protected void setPost(Post post) {
        this.post = post;
    }

    // 생성 메서드
    @Builder
    public PostImage(String originFileName, String storeFileName, Post post) {
        this.originFileName = originFileName;
        this.storeFileName = storeFileName;
        this.post = post;
    }

    // 오버로딩, image file을 넘기는 방식
    public static PostImageBuilder builder(MultipartFile file, Post post) {
        String originFileName = file.getOriginalFilename();
        String storeFileName = createStoreFileName(originFileName);

        // 여기서 실제 파일 저장 로직 수행
        // saveFile(storeFileName, file);

        return new PostImageBuilder()
                .originFileName(originFileName)
                .storeFileName(storeFileName)
                .post(post);
    }

    public static String createStoreFileName(String originFileName) {
        String ext = extractExt(originFileName);
        return UUID.randomUUID().toString() + "." + ext;
    }

    private static String extractExt(String originFileName) {
        int pos = originFileName.lastIndexOf(".");
        return originFileName.substring(pos + 1);
    }
}