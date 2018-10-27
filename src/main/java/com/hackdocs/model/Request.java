package com.hackdocs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hackdocs.model.request.QueryResult;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {

    String responseId;
    String session;
    QueryResult queryResult;
//    OriginalDetectIntentRequest originalDetectIntentRequest;

}
