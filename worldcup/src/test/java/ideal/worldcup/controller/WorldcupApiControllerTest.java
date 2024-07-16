package ideal.worldcup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ideal.worldcup.dto.AddPostRequest;
import ideal.worldcup.repository.WorldcupRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class WorldcupApiControllerTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    WorldcupRepository worldcupRepository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public void mockMVCSetUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
        worldcupRepository.deleteAll();
    }

    @DisplayName("addPost - 글 추가")
    @Test
    public void addArticle() throws Exception{
        // given
        final String url = "/api/posts";
        final String title = "제목";
        final String content = "내용";
        final AddPostRequest userRequest = new AddPostRequest(title, content);

        final String requestBody = objectMapper.writeValueAsString(userRequest);
        // when
//        ResultActions result = mockMvc.perform(post(url))
        // then

    }

}