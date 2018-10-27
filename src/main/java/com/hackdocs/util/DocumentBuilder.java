package com.hackdocs.util;

import com.hackdocs.model.businessModels.Document;

public interface DocumentBuilder {

    static Document getHotelDocument() {
        Document hotelDoc = new Document();

        return hotelDoc;
    }
}
