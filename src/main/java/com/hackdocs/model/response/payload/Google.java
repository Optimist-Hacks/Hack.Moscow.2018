package com.hackdocs.model.response.payload;

import com.hackdocs.model.response.payload.google.RichResponse;
import lombok.Data;

@Data
public class Google {

    public Boolean expectUserResponse;
    public RichResponse richResponse;

    public String imageUrl = "http://images.math.cnrs.fr/IMG/png/section8-image.png";

    public String messages = "[\n" +
            "  {\n" +
            "    \"imageUrl\": \"http://images.math.cnrs.fr/IMG/png/section8-image.png\",\n" +
            "    \"platform\": \"google\",\n" +
            "    \"type\": 3\n" +
            "  }\n" +
            "]";

    public Google(Boolean expectUserResponse, RichResponse richResponse) {
        this.expectUserResponse = expectUserResponse;
        this.richResponse = richResponse;
    }

}
