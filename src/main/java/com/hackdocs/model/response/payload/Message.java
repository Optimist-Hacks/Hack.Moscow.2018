package com.hackdocs.model.response.payload;

import lombok.Data;

@Data
public class Message {

    String imageUrl = "http://images.math.cnrs.fr/IMG/png/section8-image.png";

    String platform = "telegram";

    int type = 3;

    public Message(String platform){
        this.platform = platform;
    }

//    "[\n" +
//            "  {\n" +
//            "    \"imageUrl\": \"http://images.math.cnrs.fr/IMG/png/section8-image.png\",\n" +
//            "    \"platform\": \"google\",\n" +
//            "    \"type\": 3\n" +
//            "  }\n" +
//            "]";
}
