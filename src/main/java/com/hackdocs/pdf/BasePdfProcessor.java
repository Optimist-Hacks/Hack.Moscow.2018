package com.hackdocs.pdf;

import java.util.HashMap;

public interface BasePdfProcessor {

    HashMap<Integer, FieldModels> process(String path);
    void writeDocument(HashMap<Integer, String> values);

}
