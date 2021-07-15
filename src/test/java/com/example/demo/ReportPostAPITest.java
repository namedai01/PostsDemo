package com.example.demo;

import com.example.demo.model.Post;
import com.example.demo.model.Report;
import com.example.demo.service.PostsService;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {DemoApplication.class})
@AutoConfigureMockMvc
@Log4j2
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class ReportPostAPITest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PostsService postsService;

    // when id is not exist, then return Invalid input data.
    @Test
    @Sql("/createPosts.sql")
    @Transactional
    public void test_Id_NotExist() throws Exception {
        Assert.fail();
    }

    // When Id's length is greater than 10, then return Invalid input data.
    @Test
    @Sql("/createPosts.sql")
    @Transactional
    public void test00_Id_InvalidValue() throws Exception {
        Assert.fail();
    }

    // When Id's length is empty (If id is string and length = 0), then return Invalid input data.
    @Test
    @Sql("/createPosts.sql")
    @Transactional
    public void test01_Id_InvalidValue() throws Exception {
        Assert.fail();
    }

    // When Id is not number, then return Invalid input data.
    @Test
    @Sql("/createPosts.sql")
    @Transactional
    public void test_Id_IsNotNumber() throws Exception {
        Assert.fail();
    }

    // when type's value is invalid (not SPAM / IMPOLITE), then return Invalid input data.
    @Test
    @Sql("/createPosts.sql")
    @Transactional
    public void test_Type_InvalidValue() throws Exception {
        Assert.fail();
    }

    // when type is not exist, then return Invalid input data.
    @Test
    @Sql("/createPosts.sql")
    @Transactional
    public void test_Type_NotExist() throws Exception {
        Assert.fail();
    }

    // When note's length is out of value domain ([0 - 200]), then return Invalid input data.
    @Test
    @Sql("/createPosts.sql")
    @Transactional
    public void test_Note_InvalidValue() throws Exception {
        Assert.fail();
    }

    // When report SPAM and content has NO duplicated words, then return OK
    @Test
    @Sql("/createPosts.sql")
    @Transactional
    public void test00_All_Valid() throws Exception {
        Report report = new Report(1L, "SPAM", "OK OK OK");

        mvc.perform(MockMvcRequestBuilders.post("/posts/6/report").content(String.valueOf(new Gson().toJson(report))).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }

    // When report SPAM and content has duplicated words, then return NOT OK
    @Test
    @Sql("/createPosts.sql")
    @Transactional
    public void test01_All_Valid() throws Exception {

        Assert.fail();
    }

    // When report IMPOLITE and content has rude words, then return NOT OK
    @Test
    @Sql("/createPosts.sql")
    @Transactional
    public void test02_All_Valid() throws Exception {
        Assert.fail();
    }

    // When report IMPOLITE and content has no rude words, then return OK
    @Test
    @Sql("/createPosts.sql")
    @Transactional
    public void test03_All_Valid() throws Exception {
        Assert.fail();
    }
}
