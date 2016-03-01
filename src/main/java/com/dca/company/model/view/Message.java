package com.dca.company.model.view;

import lombok.Getter;

/**
 * Created by denis on 01/03/16.
 */
public class Message {

    @Getter
    private final String status;

    public Message(String status) {
        this.status = status;
    }

}
