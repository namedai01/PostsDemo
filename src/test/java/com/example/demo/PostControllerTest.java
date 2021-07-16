package com.example.demo;


import com.example.demo.model.Post;
import com.example.demo.service.PostsService;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static java.lang.Math.max;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {DemoApplication.class})
@AutoConfigureMockMvc
@Log4j2
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class PostControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private PostsService postsService;

    // Test api http://localhost:8081/posts with Method = GET
    @Test
    @Sql("/createPosts.sql")
    @Transactional
    public void testFindAll() throws Exception {
        mvc.perform(get("/posts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(8)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[1].title", is("Tam Cốc")))
                .andExpect(jsonPath("$[2].description", is("Thánh địa Mỹ Sơn là một danh lam thắng cảnh Việt Nam nổi tiếng được UNESCO công nhận.")))
                .andExpect(jsonPath("$[3].likeCount", is(100)));
    }

    // Test api http://localhost:8081/posts/ with Method = POST
    @Test
    @Sql("/createPosts.sql")
    @Transactional
    public void testAdd() throws Exception {

        Post post = new Post(100L, "title1", "description1");

        mvc.perform(post("/posts").content(String.valueOf(new Gson().toJson(post))).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("title1")))
                .andExpect(jsonPath("$.description", is("description1")))
                .andExpect(jsonPath("$.likeCount", is(0)));
    }

    // Test api http://localhost:8081/posts/{id} with Method = PUT
    @Test
    @Transactional
    @Sql("/createPosts.sql")
    public void testEdit() throws Exception {
        Post post = postsService.getPost(0L);

        post.setTitle("Hello");

        mvc.perform(put("/posts/0").content(String.valueOf(new Gson().toJson(post))).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Hello")));
    }

    // Test api http://localhost:8081/posts/{id}/like with Method = PUT
    @Test
    @Sql("/createPosts.sql")
    @Transactional
    public void testLike() throws Exception {
        Post post = postsService.getPost(0L);

        int numLike = post.getLikeCount();

        mvc.perform(put("/posts/0/like").content(String.valueOf(new Gson().toJson(post))).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.likeCount", is(numLike + 1)));
    }

    // Test api http://localhost:8081/posts/{id}/unlike with Method = PUT
    @Test
    @Sql("/createPosts.sql")
    @Transactional
    public void testUnLike() throws Exception {
        Post post = postsService.getPost(0L);

        int numLike = post.getLikeCount();

        mvc.perform(put("/posts/0/unlike").content(String.valueOf(new Gson().toJson(post))).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.likeCount", is(max(0, numLike - 1))));
    }

    // Test api http://localhost:8081/posts/{id} with Method = GET
    @Test
    @Sql("/createPosts.sql")
    @Transactional
    public void testGetPost() throws Exception {
        mvc.perform(get("/posts/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.title", is("Hội Lim")))
                .andExpect(jsonPath("$.description", is("Hội Lim là một lễ hội lớn của tỉnh Bắc Ninh, chính hội được tổ chức vào ngày 13 tháng giêng hàng năm, trên địa bàn huyện Tiên Du tỉnh Bắc Ninh")))
                .andExpect(jsonPath("$.likeCount", is(7)));
    }
}
