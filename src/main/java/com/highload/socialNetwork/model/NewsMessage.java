package com.highload.socialNetwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class NewsMessage {
        private String from;
        private String to;
        private String content;
}
