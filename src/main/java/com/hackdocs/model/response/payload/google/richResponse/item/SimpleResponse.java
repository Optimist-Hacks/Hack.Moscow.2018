package com.hackdocs.model.response.payload.google.richResponse.item;

import lombok.Data;

@Data
public class SimpleResponse {

    public String textToSpeech = "http://images.math.cnrs.fr/IMG/png/section8-image.png";

    public SimpleResponse(String textToSpeech) {
//        this.textToSpeech = textToSpeech;
    }

}
