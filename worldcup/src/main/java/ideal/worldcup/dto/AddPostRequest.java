package ideal.worldcup.dto;

import ideal.worldcup.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor()
@Getter
public class AddPostRequest {
    private String title;
    private String content;

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}
