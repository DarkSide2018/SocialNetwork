package com.highload.socialNetwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class NewsMessage {

        private Long id;
        private String type;
        private String message;
        private String from;
        private String to;
        private String content;
        private LocalDateTime createdAt;
}
