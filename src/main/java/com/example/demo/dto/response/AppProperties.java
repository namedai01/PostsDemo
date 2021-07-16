package com.example.demo.dto.response;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Data
@Component
public class AppProperties {
    @Value("${spring.application.name}")
    HashSet<String> keywords;
}
