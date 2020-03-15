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

import static org.junit.jupiter.api.Assertions.*;


/**
 * Author:  Heartsuit
 * Date:  2020-02-29 10:24
 * Version: 1.0
 */
@Slf4j
class EmployeeDeserializationTest {
    @Test
    void basicTest() throws JsonProcessingException {
        String json = "{\"name\":\"初音未来\",\"workFromDay\":\"2020-02-29 10:28:20\",\"age\":28,\"gender\":\"Female\"}";

        Employee employee = JacksonUtils.json2Object(json, Employee.class);
        log.info("Employee： {}", employee);
    }

    @Test
    void morePropertyTest() throws JsonProcessingException {
        // 多了一个name1属性
        String json = "{\"name\":\"初音未来\",\"workFromDay\":\"2020-02-29 10:28:20\",\"age\":28,\"name1\":\"多余的属性XXX\", \"gender\":\"Female\"}";

        //com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "name1"
        Employee employee = JacksonUtils.json2Object(json, Employee.class);
        log.info("Employee： {}", employee);
    }

    @Test
    void noDoubleQuoteTest() throws JsonProcessingException {
        // age 无双引号
        String json = "{\"name\":\"初音未来\",\"workFromDay\":\"2020-02-29 10:28:20\",age:28,\"gender\":\"Female\"}";

        //com.fasterxml.jackson.core.JsonParseException: Unexpected character ('a' (code 97)): was expecting double-quote to start field name
        Employee employee = JacksonUtils.json2Object(json, Employee.class);
        log.info("Employee： {}", employee);
    }

    @Test
    void singleQuoteTest() throws JsonProcessingException {
        // name 值 单引号
        String json = "{\"name\":'初音未来',\"workFromDay\":\"2020-02-29 10:28:20\",\"age\":28,\"gender\":\"Female\"}";

        Employee employee = JacksonUtils.json2Object(json, Employee.class);
        log.info("Employee： {}", employee);
    }

    @Test
    void leadingZeroTest() throws JsonProcessingException {
        // age 前导0
        String json = "{\"name\":\"初音未来\",\"workFromDay\":\"2020-02-29 10:28:20\",\"age\":0028,\"gender\":\"Female\"}";

        //com.fasterxml.jackson.core.JsonParseException: Invalid numeric value: Leading zeroes not allowed
        Employee employee = JacksonUtils.json2Object(json, Employee.class);
        log.info("Employee： {}", employee);
    }

    @Test
    void noValueTest() throws JsonProcessingException {
        // age 未赋值
        String json = "{\"name\":\"初音未来\",\"workFromDay\":\"2020-02-29 10:28:20\",\"age\":,\"gender\":\"Female\"}";

        //com.fasterxml.jackson.core.JsonParseException: Unexpected character (',' (code 44)): expected a valid value (JSON String, Number, Array, Object or token 'null', 'true' or 'false')
        Employee employee = JacksonUtils.json2Object(json, Employee.class);
        log.info("Employee： {}", employee);
    }

    @Test
    void duplicateTest() {
        assertThrows(JsonParseException.class, ()->{
            // name 有两个
            String json = "{\"name\":\"初音未来\",\"name\":\"Heartsuit\",\"workFromDay\":\"2020-02-29 10:28:20\",\"age\":30,\"gender\":\"Female\"}";

            //有两个重复的属性，竟然没报错！！
            //加了校验之后，报错:com.fasterxml.jackson.core.JsonParseException: Duplicate field 'name'
            Employee employee = JacksonUtils.json2Object(json, Employee.class);
            log.info("Employee： {}", employee);
        });
    }

    @Test
    void aliasTest() throws JsonProcessingException {
        // name 别名：userName
        String json = "{\"userName\":\"初音未来\",\"workFromDay\":\"2020-02-29 10:28:20\",\"age\":30,\"gender\":\"Female\"}";

        //实体属性上加了@JsonProperty("userName")， 正常解析
        Employee employee = JacksonUtils.json2Object(json, Employee.class);
        log.info("Employee： {}", employee);
    }

    @Test
    void listTest() throws JsonProcessingException {
        String json = "[{\"name\":\"初音未来\",\"workFromDay\":\"2020-02-29 10:28:20\",\"age\":30,\"gender\":\"Female\"}," +
                "{\"name\":\"Heartsuit\",\"workFromDay\":\"2020-02-29 10:28:20\",\"age\":28,\"gender\":\"Male\"}]";

        // 转为1：List<Map<String, String>>
        List employeeList = JacksonUtils.json2Object(json, List.class); // 注意传入的参数类型 List
        log.info("List Employee： {}", employeeList);

        for (Object e: employeeList){
            log.info("Object: {}", e.toString());
            if(e instanceof Map){
                Map<String, String> map = (HashMap) e;
                log.info("Map: {}, {}, {}, {}", map.get("name"), map.get("age"), map.get("workFromDay"), map.get("gender"));
            }
        }

        // 转为2：List<Employee>，with TypeReference
        List<Employee> employeeList2 = JacksonUtils.json2Object(json, new TypeReference<List<Employee>>(){}); //  调用了重载的readValue
        log.info("TypeReference List Employee： {}", employeeList2);
        for (Employee e: employeeList2){
            log.info("Employee obj: {}", e.toString());
        }
        employeeList2.forEach(System.out::println);
    }
}