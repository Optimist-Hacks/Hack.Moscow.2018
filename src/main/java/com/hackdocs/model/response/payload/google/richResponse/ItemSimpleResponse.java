package com.hackdocs.model.response.payload.google.richResponse;

import com.hackdocs.model.response.payload.google.richResponse.item.Item;
import com.hackdocs.model.response.payload.google.richResponse.item.SimpleResponse;
import lombok.Data;

@Data
public class ItemSimpleResponse extends Item {

    public SimpleResponse simpleResponse;

    public ItemSimpleResponse(SimpleResponse simpleResponse) {
        this.simpleResponse = simpleResponse;
    }

}
