package com.hsgene.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

/**
 * Created by hjc on 2016/10/21.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity {

    private JSONObject jsonObject;
    private HttpStatus httpStatus;

}
