package com.heartsuit.tools.jackson.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.heartsuit.tools.jackson.enums.GenderEnum;
import com.heartsuit.tools.jackson.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author:  Heartsuit
 * Date:  2020-02-29 10:24
 * Version: 1.0
 */
@Slf4j
class EmployeeSerializationTest {
    @Test
    void basicTest() throws ParseException, JsonProcessingException {
        Employee employee = new Employee();
        employee.setName("初音未来");
        employee.setGender(GenderEnum.Female);
        employee.setAge(28);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = "2020-02-29 10:28:20";
        employee.setWorkFromDay(simpleDateFormat.parse(dateStr));

        String json = JacksonUtils.object2Json(employee);

        // 日期格式、枚举、格式化输出、root
        log.info("Json： {}", json);
    }

    @Test
    void listTest() throws ParseException, JsonProcessingException {
        Employee employee = new Employee();
        employee.setName("初音未来");
        employee.setGender(GenderEnum.Female);
        employee.setAge(28);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = "2020-02-29 10:28:20";
        employee.setWorkFromDay(simpleDateFormat.parse(dateStr));

        Employee employeeB = new Employee();
        employeeB.setName("Heartsuit");
        employeeB.setGender(GenderEnum.Male);
        employeeB.setAge(30);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        employeeList.add(employeeB);

        String json = JacksonUtils.object2Json(employeeList);
        log.info("List： {}", json);
    }

    @Test
    void charArrayTest() throws JsonProcessingException {
        char[] chars = new char[]{'j', 'a', 'c', 'k'};

        String json = JacksonUtils.object2Json(chars);
        log.info("char[]： {}", json);
    }

    @Test
    void nullTest() throws JsonProcessingException {
        Employee employee = new Employee();
        employee.setName("初音未来");
        employee.setGender(GenderEnum.Female);
//        employee.setAge(28);

        String json = JacksonUtils.object2Json(employee);

        // null 值不进行序列化输出
        log.info("Json： {}", json); // {"name":"初音未来","gender":"Female"}
    }

    @Test
    void emptyTest() throws JsonProcessingException {
        Employee employee = new Employee();
        employee.setName("");
        employee.setGender(GenderEnum.Female);
        employee.setAge(0);

        String json = JacksonUtils.object2Json(employee);

        // empty 空值 不进行序列化输出
        log.info("Json： {}", json); // {"age":0,"gender":"Female"}
    }

    @Test
    void defaultTest() throws JsonProcessingException {
        Employee employee = new Employee();
        employee.setName("");
        employee.setGender(GenderEnum.Female);
        employee.setAge(0);

        String json = JacksonUtils.object2Json(employee);

        // 0, 空值 不进行序列化输出（数字0，空字符串，null对象）
        log.info("Json： {}", json); //{"gender":"Female"}
    }

    @Test
    void alwaysTest() throws JsonProcessingException {
        Employee employee = new Employee();
        employee.setName("");
        employee.setGender(GenderEnum.Female);
        employee.setAge(0);

        String json = JacksonUtils.object2Json(employee);

        // 无论数字0，空字符串，null对象，均进行序列化输出
        log.info("Json： {}", json); //{"name":"","workFromDay":null,"age":0,"gender":"Female"}
    }

}