package com.highload.socialNetwork.news;

import com.google.gson.Gson;
import com.highload.socialNetwork.model.NewsMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<NewsMessage> {

    private static Gson gson = new Gson();

    @Override
    public String encode(NewsMessage message) throws EncodeException {
        return gson.toJson(message);
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
