package com.hackdocs.model.response.payload.google.expectedInputs.inputPrompt;

import com.hackdocs.model.response.payload.google.expectedInputs.inputPrompt.richInitialPrompt.Items;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class RichInitialPrompt {

    List<Items> items = Collections.singletonList(new Items());
}
