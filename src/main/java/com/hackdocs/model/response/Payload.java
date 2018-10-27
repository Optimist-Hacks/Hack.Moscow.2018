package com.hackdocs.model.response;

import com.hackdocs.model.response.payload.Google;
import com.hackdocs.model.response.payload.Telegram;
import lombok.Data;

@Data
public class Payload {

    public Google google;

    public Telegram telegram;

    public Payload(Google google) {
        this.google = google;
    }

    public Payload(Telegram telegram) {
        this.telegram = telegram;
    }

}
