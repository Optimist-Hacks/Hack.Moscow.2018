package com.hackdocs.model.response.payload;

import lombok.Data;

@Data
public class Telegram {

    public String text;

    public String imageUrl = "http://images.math.cnrs.fr/IMG/png/section8-image.png";

    public Telegram(String text) {
        this.text = text;
    }

}
