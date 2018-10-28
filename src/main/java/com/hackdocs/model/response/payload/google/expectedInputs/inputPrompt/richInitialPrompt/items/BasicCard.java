package com.hackdocs.model.response.payload.google.expectedInputs.inputPrompt.richInitialPrompt.items;

import com.hackdocs.model.response.payload.google.expectedInputs.inputPrompt.richInitialPrompt.items.basicCard.Button;
import com.hackdocs.model.response.payload.google.expectedInputs.inputPrompt.richInitialPrompt.items.basicCard.Image;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class BasicCard {

    String title = "Math & prime numbers";

    String formattedText = "42 is an even composite number. It\n    is composed of three distinct prime numbeTo count fro";
    Image image = new Image();

    List<Button> buttons = Collections.singletonList(new Button());

    String imageDisplayOptions = "CROPPED";
}
