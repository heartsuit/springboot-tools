package com.heartsuit.tools.lambda.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class ExampleTest {
    /**
     * Thread的Runnable接口
     * 一般写法
     */
    @Test
    void runnable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread Name: " + Thread.currentThread().getName());
            }
        }).start();
    }

    @Test
    void lambdaRunnable() {
        new Thread(() -> System.out.println("Lambda Thread Name: " + Thread.currentThread().getName())).start();
    }


    /**
     * 集合类，eg: List
     * 一般写法
     */
    @Test
    void list() {
        List<Integer> numbers = Arrays.asList(3, 1, 5, 0, 9);

        numbers.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("Traverse: " + integer);
            }
        });
    }

    @Test
    void lambdaList() {
        List<Integer> numbers = Arrays.asList(3, 1, 5, 0, 9);
        numbers.forEach(integer -> System.out.println("Lambda Traverse: " + integer));
    }

    @Test
    void lambdaReferenceList() {
        List<Integer> numbers = Arrays.asList(3, 1, 5, 0, 9);
        numbers.forEach(System.out::println);
    }

    /**
     * 集合类排序 sort
     * 一般写法
     */
    @Test
    void sort() {
        List<Employee> employees = Arrays.asList(new Employee("E1", 18), new Employee("E2", 22), new Employee("E3", 15));
        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        System.out.println("Sorted: " + employees);
    }

    @Test
    void lambdaSort() {
        List<Employee> employees = Arrays.asList(new Employee("E1", 18), new Employee("E2", 22), new Employee("E3", 15));
//        employees.sort((o1, o2) -> o2.getAge() - o1.getAge());
        System.out.println("Lambda Sorted: " + employees);
    }
}