package com.hackdocs;

import com.hackdocs.localization.Localization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseHelper {

    private final Localization localization;

    @Autowired
    public ResponseHelper(Localization localization) {
        this.localization = localization;
    }

    public ResponseEntity notFound(String key) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(localization.getString(key));
    }

    public ResponseEntity badRequest(String key) {
        return ResponseEntity.badRequest().body(localization.getString(key));
    }

    public <Body> ResponseEntity<Body> ok(Body body) {
        return ResponseEntity.ok().body(body);
    }

}
