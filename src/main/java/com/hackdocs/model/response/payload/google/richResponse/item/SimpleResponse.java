package com.hackdocs.model.response.payload.google.richResponse.item;

import lombok.Data;

@Data
public class SimpleResponse {

    public String textToSpeech;

    public SimpleResponse(String textToSpeech) {
        this.textToSpeech = textToSpeech;
    }

}
