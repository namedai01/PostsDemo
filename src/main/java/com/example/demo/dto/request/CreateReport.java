package com.example.demo.dto.request;

import com.example.demo.ReportType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
public class CreateReport {
    private Long id;

    private String type;

    private String note;
}
