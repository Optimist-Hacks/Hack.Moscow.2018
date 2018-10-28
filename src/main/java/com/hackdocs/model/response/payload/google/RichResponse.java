package com.hackdocs.model.response.payload.google;

import com.hackdocs.model.response.payload.google.expectedInputs.inputPrompt.richInitialPrompt.Items;
import com.hackdocs.model.response.payload.google.richResponse.item.Item;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RichResponse extends Item {

    public List<Item> itemSimpleResponses = new ArrayList<>();

    public RichResponse(Item itemSimpleResponse) {
        this.itemSimpleResponses.add(itemSimpleResponse);
        itemSimpleResponses.add(new Items());
    }
}