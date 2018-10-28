package com.hackdocs.model.response;

import com.hackdocs.model.request.queryResult.outputContext.Parameters;
import lombok.Data;

@Data
public class FollowupEventInput {

    public String name;
    public String languageCode;

    public Parameters parameters;

}
