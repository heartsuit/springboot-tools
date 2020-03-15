package com.heartsuit.tools.jackson.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.heartsuit.tools.jackson.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Author:  Heartsuit
 * Date:  2020-02-29 10:04
 * Version: 1.0
 */
@Data
public class Employee{
    //    @JsonProperty("userName")
    private String name;
    private Date workFromDay;
    //    @JsonIgnore // 忽略对该字段的序列化与反序列化
    private Integer age;
    private GenderEnum gender;
}
