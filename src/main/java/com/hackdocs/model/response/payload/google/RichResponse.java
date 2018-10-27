package com.hackdocs.model.response.payload.google;

import com.hackdocs.model.response.payload.google.richResponse.Item;
import lombok.Data;

import java.util.List;

@Data
public class RichResponse {

    public List<Item> items = null;

    public RichResponse(List<Item> items) {
        this.items = items;
    }

}