package com.hackdocs.model.request.queryResult;

import com.hackdocs.model.request.queryResult.outputContext.Parameters;
import lombok.Data;

@Data
public class OutputContext {
    String name;
    Integer lifespanCount;
    Parameters parameters;
}
