package com.hsgene.entity;

import lombok.*;

import java.io.Serializable;

/**
 * Created by hjc on 2016/11/16.
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UpToken implements Serializable{

    private static final long serialVersionUID = 6684138771045714749L;
    private String upToken;
}
