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
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "Type", required = true, example = "IMPOLITE")
    private String type;

    @ApiModelProperty(value = "Note", required = true, example = "Bài viết chứa từ thô tục")
    private String note;
}
