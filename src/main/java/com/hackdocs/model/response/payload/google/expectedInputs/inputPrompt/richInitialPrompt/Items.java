package com.hackdocs.model.response.payload.google.expectedInputs.inputPrompt.richInitialPrompt;

import com.hackdocs.model.response.payload.google.expectedInputs.inputPrompt.richInitialPrompt.items.BasicCard;
import com.hackdocs.model.response.payload.google.richResponse.item.Item;
import lombok.Data;

@Data
public class Items extends Item {

    BasicCard basicCard = new BasicCard();
}
