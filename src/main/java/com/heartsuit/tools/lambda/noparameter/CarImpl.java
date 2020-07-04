package com.heartsuit.tools.lambda.noparameter;

/**
 * @Author Heartsuit
 * @Date 2020-06-05
 */
public class CarImpl implements Car {
    @Override
    public void drink() {
        System.out.println("Oil, Subclass");
    }
}
