package com.hsgene.entity;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

/**
 * Created by hjc on 2016/10/21.
 */
@Setter
@Getter
public class ResponseEntity {

    private JSONObject jsonObject;
    private HttpStatus httpStatus;

    public ResponseEntity(JSONObject jsonObject, HttpStatus httpStatus) {
        this.jsonObject = jsonObject;
        this.httpStatus = httpStatus;
    }
}
