package io.crawlab.sdk;

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
        Map<String, Object> data = Map.of("name", "John", "age", 25);
        CrawlabSDK.saveItem(data);
        String output = outputStreamCaptor.toString().trim();

        assertTrue(output.contains("\"type\":\"data\""));
        assertTrue(output.contains("\"payload\":["));
        assertTrue(output.contains("\"name\":\"John\""));
        assertTrue(output.contains("\"age\":25"));
        assertTrue(output.contains("\"ipc\":true"));
    }

    @Test
    void testSaveItemWithClass() {
        TestData data = new TestData("John", 25);
        CrawlabSDK.saveItem(data);
        String output = outputStreamCaptor.toString().trim();

        assertTrue(output.contains("\"type\":\"data\""));
        assertTrue(output.contains("\"payload\":["));
        assertTrue(output.contains("\"name\":\"John\""));
        assertTrue(output.contains("\"age\":25"));
        assertTrue(output.contains("\"ipc\":true"));
    }

    // Helper class for testing object serialization
    private record TestData(String name, int age) {
    }
} 