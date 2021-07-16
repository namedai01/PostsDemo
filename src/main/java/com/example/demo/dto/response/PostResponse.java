package com.example.demo.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostResponse {
    @ApiModelProperty(value = "Id", required = true, example = "123")
    private Long id;

    @ApiModelProperty(value = "Title", required = true, example = "Thánh địa Mỹ Sơn")
    private String title;

    @ApiModelProperty(value = "Description", required = true, example = "Thánh địa Mỹ Sơn là một danh lam thắng cảnh Việt Nam nổi tiếng được UNESCO công nhận.")
    private String description;

    @ApiModelProperty(value = "LikeCount", required = true, example = "12")

    private int likeCount = 0;

    public PostResponse(long l, String title1, String description1) {
        this.id = l;
        this.title = title1;
        this.description = description1;
    }
}
