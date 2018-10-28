package com.hackdocs.model.response.payload.google.expectedInputs.inputPrompt.richInitialPrompt.items.basicCard;

import com.hackdocs.model.response.payload.google.expectedInputs.inputPrompt.richInitialPrompt.items.basicCard.button.OpenUrlAction;
import lombok.Data;

@Data
public class Button {

    String title = "Download PDF";

    OpenUrlAction openUrlAction = new OpenUrlAction();
}
