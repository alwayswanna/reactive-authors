package agleb.databaseservice;

import agleb.databaseservice.controllers.PostController;
import agleb.databaseservice.model.Post;
import agleb.databaseservice.model.dto.PostDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class DatabaseServiceApplicationTests {

    @Autowired
    private MockMvc mock;

    PostDTO ps = new PostDTO(null, "Test title for UnitTest", "Test description for UnitTest", "Test full text for UnitTest", 40, null);

    @Test
    public void addNewTestPost() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(ps);

        this.mock.perform(post("/db_service/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnListWithPosts() throws Exception{

        MvcResult result = mock.perform(get("/db_service/posts"))
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        List<PostDTO> postDTOList = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<PostDTO>>() {
        });

        int index = postDTOList.indexOf(ps);

        this.mock.perform(get("/db_service/posts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[" + index + "].title", is(ps.getTitle())))
                .andExpect(jsonPath("$[" + index + "].description", is(ps.getDescription())))
                .andExpect(jsonPath("$[" + index + "].full_story", is(ps.getFull_story())))
                .andExpect(jsonPath("$[" + index + "].likes", is(ps.getLikes())))
                ;
    }

    @Test
    public void editAddedTestPost() throws Exception{

        MvcResult result = mock.perform(get("/db_service/posts"))
                .andExpect(status().isOk())
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        List<PostDTO> postDTOList = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<PostDTO>>() {
        });

        int index = postDTOList.indexOf(ps);

        this.mock.perform(put("/db_service/post"));
    }





}
