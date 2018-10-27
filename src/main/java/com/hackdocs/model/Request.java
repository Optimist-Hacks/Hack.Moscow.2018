package com.hackdocs.model;

import com.hackdocs.model.request.QueryResult;
import lombok.Data;

@Data
public class Request {

    String responseId;
    String session;
    QueryResult queryResult;
//    OriginalDetectIntentRequest originalDetectIntentRequest;

}
