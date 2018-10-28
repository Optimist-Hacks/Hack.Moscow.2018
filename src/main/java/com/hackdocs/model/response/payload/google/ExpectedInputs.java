package com.hackdocs.model.response.payload.google;

import com.hackdocs.model.response.payload.google.expectedInputs.InputPrompt;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class ExpectedInputs {

    List<InputPrompt> inputPrompts = Collections.singletonList(new InputPrompt());
}
