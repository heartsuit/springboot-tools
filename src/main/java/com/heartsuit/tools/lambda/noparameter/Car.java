package com.heartsuit.tools.lambda.noparameter;

/**
 * @Author Heartsuit
 * @Date 2020-06-05
 */
public interface Car {
    // 抽象方法
    void drink();

    // 普通方法
    default void run() {
        System.out.println("Faster..");
    }
}
