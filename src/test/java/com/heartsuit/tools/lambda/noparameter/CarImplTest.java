package com.heartsuit.tools.lambda.noparameter;

import org.junit.jupiter.api.Test;

class CarImplTest {

    @Test
    // 通过实现类重写方法，完成调用
    void drink() {
        Car car = new CarImpl();
        car.drink();
        car.run();
    }

    @Test
    // 通过匿名内部类调用
    void anonymous(){
        Car car = new Car(){
            @Override
            public void drink() {
                System.out.println("Electricity, Anonymous");
            }
        };
        car.drink();
        car.run();
    }

    @Test
    void lambda(){
        Car car = () -> System.out.println("Electricity, Lambda");
        car.drink();
        car.run();
    }

}