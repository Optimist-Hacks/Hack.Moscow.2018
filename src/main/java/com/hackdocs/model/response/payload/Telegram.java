package com.hackdocs.model.response.payload;

import lombok.Data;

@Data
public class Telegram {

    public String text;

    public Telegram(String text) {
        this.text = text;
    }

}
