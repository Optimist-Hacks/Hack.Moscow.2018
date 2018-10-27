package com.hackdocs.model.response;

import com.hackdocs.model.response.payload.Google;
import com.hackdocs.model.response.payload.Message;
import com.hackdocs.model.response.payload.Telegram;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class Payload {

    public Google google;

    public Telegram telegram;

    public List<Message> messages = Collections.singletonList(new Message("telegram"));

//    public String messages = "[\n" +
//            "  {\n" +
//            "    \"imageUrl\": \"http://images.math.cnrs.fr/IMG/png/section8-image.png\",\n" +
//            "    \"platform\": \"google\",\n" +
//            "    \"type\": 3\n" +
//            "  }\n" +
//            "]";



    public Payload(Google google) {
        this.google = google;
    }

    public Payload(Telegram telegram) {
        this.telegram = telegram;
    }

}
