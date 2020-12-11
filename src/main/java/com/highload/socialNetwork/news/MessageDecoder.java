package com.highload.socialNetwork.news;

import com.google.gson.Gson;
import com.highload.socialNetwork.model.NewsMessage;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<NewsMessage> {

    private static Gson gson = new Gson();

    @Override
    public NewsMessage decode(String s) throws DecodeException {
        NewsMessage message = gson.fromJson(s, NewsMessage.class);
        return message;
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}
