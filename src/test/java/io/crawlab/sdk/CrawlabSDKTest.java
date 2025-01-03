package io.crawlab.sdk;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CrawlabSDKTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testSaveItemWithMap() {
        Map<String, Object> data1 = Map.of("name", "John", "age", 25);
        Map<String, Object> data2 = Map.of("name", "Jane", "age", 30);
        CrawlabSDK.saveItem(data1, data2);
        String output = outputStreamCaptor.toString().trim();

        assertTrue(output.contains("\"type\":\"data\""));
        assertTrue(output.contains("\"payload\":["));
        assertTrue(output.contains("\"name\":\"John\""));
        assertTrue(output.contains("\"age\":25"));
        assertTrue(output.contains("\"name\":\"Jane\""));
        assertTrue(output.contains("\"age\":30"));
        assertTrue(output.contains("\"ipc\":true"));
    }

    @Test
    void testSaveItemWithClass() {
        Person data1 = new Person("John", 25);
        Person data2 = new Person("Jane", 30);
        CrawlabSDK.saveItem(data1, data2);
        String output = outputStreamCaptor.toString().trim();

        assertTrue(output.contains("\"type\":\"data\""));
        assertTrue(output.contains("\"payload\":["));
        assertTrue(output.contains("\"name\":\"John\""));
        assertTrue(output.contains("\"age\":25"));
        assertTrue(output.contains("\"name\":\"Jane\""));
        assertTrue(output.contains("\"age\":30"));
        assertTrue(output.contains("\"ipc\":true"));
    }

    // Helper class for testing object serialization
    @JsonSerialize
    class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}