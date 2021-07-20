package com.example.demo.dto.request;

import com.example.demo.ReportType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
public class CreateReport {
    @ApiModelProperty(value = "Type", required = true, example = "SPAM")
    private String type;

    @ApiModelProperty(value = "Note", required = true, example = "OK OK OK")
    private String note;
}
