package com.heartsuit.tools.jackson.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static com.fasterxml.jackson.core.JsonParser.Feature.*;


/**
 * Java对象与Json字符串 互转 工具类
 * Author:  Heartsuit
 * Date:  2020-02-29 10:49
 * Version: 1.0
 */
public class JacksonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        // 关闭默认转换的timestamps形式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        // 设置东八区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        // 所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));

        // 设置枚举输出toString()
//        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        // 设置枚举输出索引
//        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true);

        // 格式化输出
//        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        // root，包含根元素
//        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);

        // list，一个元素时不用数组[]包裹，即输出单个对象
        objectMapper.configure(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED, true);

        // char[]，修改字符数组的默认转换行为：单个字符串为字符数组
        objectMapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true);

        // 对象的所有字段全部列入及其他选项
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);


        // 忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许没用双引号括起来的属性
        objectMapper.configure(ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许用单引号括起来的值（如果是属性用单引号，Jackson默认不报错，正常解析）
        objectMapper.configure(ALLOW_SINGLE_QUOTES, true);
        // 允许数字前的前导0
        objectMapper.configure(ALLOW_NUMERIC_LEADING_ZEROS, true);
        // 允许json串中存在未赋值的属性
        objectMapper.configure(ALLOW_MISSING_VALUES, true);
        // 严格校验重复属性
        objectMapper.configure(STRICT_DUPLICATE_DETECTION, true);

        // 忽略空Bean转json的错误
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
    }

    /**
     * Java对象序列化为Json字符串
     *
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String object2Json(Object obj) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(obj);
        return json;
    }

    /**
     * Json字符串反序列为Java对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T json2Object(String json, Class<T> clazz) throws JsonProcessingException {
        T obj = objectMapper.readValue(json, clazz);
        return obj;
    }

    /**
     *
     * @param json
     * @param valueTypeRef
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    public static <T> T json2Object(String json, TypeReference<T> valueTypeRef) throws JsonProcessingException {
        T obj = objectMapper.readValue(json, valueTypeRef);
        return obj;
    }
}
