package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "Title", required = true, example = "Thánh địa Mỹ Sơn")
    private String title;

    @ApiModelProperty(value = "Description", required = true, example = "Thánh địa Mỹ Sơn là một danh lam thắng cảnh Việt Nam nổi tiếng được UNESCO công nhận.")
    private String description;

    @ApiModelProperty(value = "LikeCount", required = true, example = "12")
    @Column(name = "like_count")
    private int likeCount = 0;

    public Post(long l, String title1, String description1) {
        this.id = l;
        this.title = title1;
        this.description = description1;
    }
}
