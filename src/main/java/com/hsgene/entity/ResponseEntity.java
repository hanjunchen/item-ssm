package com.hsgene.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by hjc on 2016/10/21.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity {

    private String status;
    private String message;

}
