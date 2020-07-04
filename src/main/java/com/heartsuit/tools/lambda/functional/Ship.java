package com.heartsuit.tools.lambda.functional;

/**
 * @Author Heartsuit
 * @Date 2020-06-05
 */
@FunctionalInterface
public interface Ship {
//    void swim(); // can be common method with default keyword
    String speed(String type);
    String toString();
}
