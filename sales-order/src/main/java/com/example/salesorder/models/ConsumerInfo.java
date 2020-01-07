package com.example.salesorder.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class ConsumerInfo {
        private String description;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date date;

        private String email;

        private List<String> itemNames;

    }

