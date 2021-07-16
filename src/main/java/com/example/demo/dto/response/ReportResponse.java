package com.example.demo.dto.response;

import com.example.demo.ReportType;
import io.swagger.annotations.ApiModelProperty;

public class ReportResponse {
    @ApiModelProperty(value = "Id", required = true, example = "123")
    private Long id;

    @ApiModelProperty(value = "Type", required = true, example = "IMPOLITE")
    private ReportType type;

    @ApiModelProperty(value = "Note", required = true, example = "Bài viết chứa từ thô tục")
    private String note;
}
