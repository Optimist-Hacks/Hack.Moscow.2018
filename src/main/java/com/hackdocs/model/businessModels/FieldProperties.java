package com.hackdocs.model.businessModels;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldProperties {

    int xCoord;
    int yCoord;
    int numbOfLines;
    int maxLength;

}
