package com.hackdocs.model.response.payload;

import lombok.Data;

@Data
public class Telegram {

    public String text;

    public String imageUrl = "http://images.math.cnrs.fr/IMG/png/section8-image.png";

//    public List<Message> messages = Collections.singletonList(new Message("telegram"));

//    public String messages = "[\n" +
//            "  {\n" +
//            "    \"imageUrl\": \"http://images.math.cnrs.fr/IMG/png/section8-image.png\",\n" +
//            "    \"platform\": \"telegram\",\n" +
//            "    \"type\": 3\n" +
//            "  }\n" +
//            "]";

    public Telegram(String text) {
        this.text = text;
    }

}
