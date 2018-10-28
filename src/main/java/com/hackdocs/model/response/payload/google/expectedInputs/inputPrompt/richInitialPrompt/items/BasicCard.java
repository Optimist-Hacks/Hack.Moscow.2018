package com.hackdocs.model.response.payload.google.expectedInputs.inputPrompt.richInitialPrompt.items;

import com.hackdocs.model.response.payload.google.expectedInputs.inputPrompt.richInitialPrompt.items.basicCard.Image;
import lombok.Data;

@Data
public class BasicCard {

    String title = "Math & prime numbers";

    String formattedText = "42 is an even composite number. It\n    is composed of three distinct prime numbeTo count fro";
    Image image = new Image();
    String imageDisplayOptions = "CROPPED";
}
