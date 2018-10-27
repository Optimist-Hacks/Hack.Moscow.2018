package com.hackdocs.model.request;


import com.hackdocs.model.request.queryResult.DiagnosticInfo;
import com.hackdocs.model.request.queryResult.FulfillmentMessage;
import com.hackdocs.model.request.queryResult.Intent;
import com.hackdocs.model.request.queryResult.OutputContext;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class QueryResult {

    String queryText;
    Map<String, String> parameters;
    Boolean allRequiredParamsPresent;
    String fulfillmentText;
    List<FulfillmentMessage> fulfillmentMessages;
    List<OutputContext> outputContexts;
    Intent intent;
    Integer intentDetectionConfidence;
    DiagnosticInfo diagnosticInfo;
    String languageCode;


}
