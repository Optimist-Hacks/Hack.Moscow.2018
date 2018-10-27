package com.hackdocs.model.response;

import com.hackdocs.model.response.payload.Google;
import lombok.Data;

@Data
public class Payload {

    public Google google;

    public Payload(Google google) {
        this.google = google;
    }

}
