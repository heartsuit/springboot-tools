package com.heartsuit.tools.lambda.withparameter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void fly() {
//        Plane plane = () -> {
//            System.out.println("No parameter method call");
//        };
//        plane.fly();
    }

    @Test
    void speed() {
        Plane plane = new Plane() {
            @Override
            public String speed(String type) {
                System.out.println("One parameter method call: " + type);
                return "One parameter method call: " + type;
            }
        };

        String result = plane.speed("fighter aircraft");
        System.out.println(result);
    }

    @Test
    void speedLambda() {
        Plane plane = (String type) -> {
            System.out.println("One parameter method call: " + type);
            return "One parameter method call: " + type;
        };

        String result = plane.speed("aircraft");
        System.out.println(result);
    }

    @Test
    void speedSimplified0() {
        // Lambda表达式，可以省略参数类型
        Plane plane = (type) -> {
            System.out.println("One parameter method call: " + type);
            return "One parameter method call: " + type;
        };

        String result = plane.speed("aircraft");
        System.out.println(result);
    }

    @Test
    void speedSimplified1() {
        // 只有一个入参时，可以省略()
        Plane plane = type -> {
            System.out.println("One parameter method call: " + type);
            return "One parameter method call: " + type;
        };

        String result = plane.speed("aircraft");
        System.out.println(result);
    }

    @Test
    void speedSimplified2() {
        // 一行语句块，可以省略{}与return
        Plane plane = type -> "One parameter method call: " + type;

        String result = plane.speed("aircraft");
        System.out.println(result);
    }

}