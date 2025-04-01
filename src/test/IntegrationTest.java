package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    private int a;
    private int b;

    @BeforeEach
    void setup() {
        a = 5;
        b = 10;
    }

    @Test
    void testAddition() {
        int result = a + b;
        assertEquals(15, result, "Addition should be correct");
    }

    @Test
    void testMultiplication() {
        int result = a * b;
        assertEquals(50, result, "Multiplication should be correct");
    }
}
