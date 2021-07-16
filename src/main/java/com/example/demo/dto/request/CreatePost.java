package com.example.demo.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePost {
    @ApiModelProperty(value = "Title", required = true, example = "Thánh địa Mỹ Sơn")
    private String title;

    @ApiModelProperty(value = "Description", required = true, example = "Thánh địa Mỹ Sơn là một danh lam thắng cảnh Việt Nam nổi tiếng được UNESCO công nhận.")
    private String description;
}
