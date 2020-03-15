package com.heartsuit.tools.jackson.domain;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.heartsuit.tools.jackson.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Author:  Heartsuit
 * Date:  2020-02-29 10:24
 * Version: 1.0
 */
@Slf4j
class PageRequestDeserializationTest {
    @Test
    void objTest() throws JsonProcessingException {
//        String json = "{\"pageNum\":1,\"pageSize\":9,\"params\":[{\"name\":\"name\",\"value\":\"admin\"}]}"; // 与实体类属性不一致！
        String json = "{\"pageNum\":1,\"pageSize\":9,\"params\":{\"name\":\"name\",\"value\":\"admin\"}}";

        PageRequest pageRequest = JacksonUtils.json2Object(json, PageRequest.class);
        log.info("PageRequest： {}", pageRequest);
    }
}