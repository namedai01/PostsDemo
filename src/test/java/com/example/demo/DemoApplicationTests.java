package com.example.demo;

import com.example.demo.repository.PostsRepository;
import com.example.demo.service.PostsService;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@DataJpaTest
//@ActiveProfiles("test")
//@TestPropertySource(properties = {
//        "spring.jpa.hibernate.ddl-auto=create-drop"
//})
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {DemoApplication.class})
@AutoConfigureMockMvc
@Log4j2
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class DemoApplicationTests {

    @TestConfiguration
    public static class PostsServiceTestConfiguration{
        /*
        Create a Bean TodoService in Context
         */
        @Bean
        PostsService postsService(){
            return new PostsService();
        }
    }

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    // Test all components is not null
    @Test
    public void allComponentAreNotNull() {
        Assertions.assertThat(postsService).isNotNull();
        Assertions.assertThat(postsRepository).isNotNull();
    }

    // Test repository
    @Test
    @Sql("/createPosts.sql")
    public void testPostRepository() {
        Assertions.assertThat(postsRepository.findAll()).hasSize(8);
        Assertions.assertThat(postsRepository.findById(0L).get().getTitle()).isEqualTo("Hội Lim");
        Assertions.assertThat(postsRepository.findById(1L).get().getDescription()).isEqualTo("Nhắc đến Ninh Bình chắc chắn sẽ không thể bỏ qua Tam Cốc Bích Động – một trong những cảnh đẹp nhất Việt Nam cũng như Ninh Bình");
        Assertions.assertThat(postsRepository.findById(2L).get().getLikeCount()).isEqualTo(1);
        Assertions.assertThat(postsRepository.findById(3L).get().getLikeCount()).isEqualTo(100);
        Assertions.assertThat(postsRepository.findById(4L).get().getTitle()).isEqualTo("Thác Bản Giốc");
    }

    @After
    public void clean() {
        postsRepository.deleteAll();
    }

}
