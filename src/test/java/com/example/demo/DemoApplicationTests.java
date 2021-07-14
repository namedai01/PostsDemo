package com.example.demo;

import com.example.demo.repository.PostsRepository;
import com.example.demo.service.PostsService;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
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

//    @Before
//    public void setUp() {
//        postsRepository.save(
//                new Post(
//                        1L,
//                        "Hội Lim",
//                        "Hội Lim là một lễ hội lớn của tỉnh Bắc Ninh, chính hội được tổ chức vào ngày 13 tháng giêng hàng năm, trên địa bàn huyện Tiên Du tỉnh Bắc Ninh",
//                        6));
//        postsRepository.save(
//                new Post(
//                        2L,
//                        "Tam Cốc",
//                        "Nhắc đến Ninh Bình chắc chắn sẽ không thể bỏ qua Tam Cốc Bích Động – một trong những cảnh đẹp nhất Việt Nam cũng như Ninh Bình",
//                        1212));
//        postsRepository.save(
//                new Post(
//                        3L,
//                        "Thánh địa Mỹ Sơn",
//                        "Nhắc đến Ninh Bình chắc chắn sẽ không thể bỏ qua Tam Cốc Bích Động – một trong những cảnh đẹp nhất Việt Nam cũng như Ninh Bình",
//                        23));
//        postsRepository.save(
//                new Post(
//                        4L,
//                        "Sapa",
//                        "Trong danh sách danh lam thắng cảnh Việt Nam nổi tiếng thế giới, chắc chắn không thể thiếu Sapa – một thị trấn ở phía Tây Bắc cách không xa biên giới Trung Quốc.",
//                        10002));
//        postsRepository.save(
//                new Post(
//                        5L,
//                        "Thác Bản Giốc",
//                        "Thác Bản Giốc là thác nước nổi tiếng nhất Cao Bằng và là một trong những danh lam thắng cảnh Việt Nam ấn tượng nhất",
//                        24));
//
//    }


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
        Assertions.assertThat(postsRepository.findAll()).hasSize(5);
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
