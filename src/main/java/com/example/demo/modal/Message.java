package com.example.demo.modal;

public class Message {
    private final Integer type;
    private final String content;

    public Message(Integer type, String content) {
        this.type = type;
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
