package com.simpleepic.springbootcicd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class E2ETest {

    @Test
    void sampleTest() {
        System.out.println("E2E Test is running...");
    }
}
