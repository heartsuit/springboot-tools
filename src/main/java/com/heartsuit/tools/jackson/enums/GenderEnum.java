package com.heartsuit.tools.jackson.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * Author:  Heartsuit
 * Date:  2020-02-29 10:05
 * Version: 1.0
 */
@Getter
@ToString
public enum GenderEnum {
    Male("man", "男"),
    Female("woman", "女"),
    ;

    private String code;
    private String name;

    GenderEnum(String code, String name){
        this.code = code;
        this.name = name;
    }
}
