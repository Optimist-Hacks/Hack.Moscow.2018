package com.hackdocs.model.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryResult {

    String queryText;
//    Map<String, String> parameters;
//    Boolean allRequiredParamsPresent;
//    String fulfillmentText;
//    List<FulfillmentMessage> fulfillmentMessages;
//    List<OutputContext> outputContexts;
//    Intent intent;
//    Integer intentDetectionConfidence;
//    DiagnosticInfo diagnosticInfo;
//    String languageCode;

}
