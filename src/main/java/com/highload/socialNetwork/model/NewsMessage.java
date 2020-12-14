package com.highload.socialNetwork.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class NewsMessage {
        private String type;
        private String message;
        private String from;
        private String to;
        private String content;

        public NewsMessage(String s, String value) {
                this.message = s;
                this.content = value;
        }
}
