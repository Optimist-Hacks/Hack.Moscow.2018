package com.hackdocs.model.response.payload;

import com.hackdocs.model.response.payload.google.RichResponse;
import lombok.Data;

@Data
public class Google {

    public Boolean expectUserResponse;
    public RichResponse richResponse;

    public Google(Boolean expectUserResponse, RichResponse richResponse) {
        this.expectUserResponse = expectUserResponse;
        this.richResponse = richResponse;
    }

}
