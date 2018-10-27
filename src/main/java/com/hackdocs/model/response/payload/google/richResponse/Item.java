package com.hackdocs.model.response.payload.google.richResponse;

import com.hackdocs.model.response.payload.google.richResponse.item.SimpleResponse;
import lombok.Data;

@Data
public class Item {

    public SimpleResponse simpleResponse;

    public Item(SimpleResponse simpleResponse) {
        this.simpleResponse = simpleResponse;
    }

}
