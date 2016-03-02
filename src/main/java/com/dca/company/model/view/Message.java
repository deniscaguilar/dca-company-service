package com.dca.company.model.view;

import lombok.Getter;

public class Message {

    @Getter
    private final String status;

    public Message(String status) {
        this.status = status;
    }

}
