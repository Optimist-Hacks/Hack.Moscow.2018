package com.hackdocs.pdf;

public enum FieldModels {

    NAME_FIELD(new String[]{"Name"}),
    SURNAME_FIELD(new String[]{"Surname"}),
    DATA_FIELD(new String[]{"Date"}),
    MALE_FIELD(new String[]{"Male"}),
    FAVORITE_PORN_FIELD(new String[]{"Favorite porn genre"});

    private final String[] keyWords;
    FieldModels(String[] keyWords) { this.keyWords = keyWords; }
    public String[] getValues() { return keyWords;}
}
