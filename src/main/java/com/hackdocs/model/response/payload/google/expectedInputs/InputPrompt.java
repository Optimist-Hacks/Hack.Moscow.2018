package com.hackdocs.model.response.payload.google.expectedInputs;

import com.hackdocs.model.response.payload.google.expectedInputs.inputPrompt.RichInitialPrompt;
import lombok.Data;

@Data
public class InputPrompt {

    RichInitialPrompt richInitialPrompt = new RichInitialPrompt();
}
