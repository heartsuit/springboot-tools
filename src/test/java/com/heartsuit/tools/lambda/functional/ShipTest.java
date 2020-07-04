package com.heartsuit.tools.lambda.functional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    @Test
    void speed() {
        Ship ship = type -> "Function interface: " + type;
        String result = ship.speed("SpaceShip");
        System.out.println(result);
    }
}