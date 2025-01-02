# Crawlab Java SDK

This is the Java SDK for Crawlab.

## Installation

```bash
# Install dependencies with Maven
mvn clean install
```

## Usage

```java
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;

import com.crawlab.sdk.CrawlabSDK;

public class Main {
    public static void main(String[] args) {
        // Save a data item (will be serialized to JSON)
        Map<String, Object> data = Map.of("name", "John", "age", 25);
        CrawlabSDK.saveItem(data);

        // Save multiple data items
        Map<String, Object> data1 = Map.of("name", "John", "age", 25);
        Map<String, Object> data2 = Map.of("name", "Jane", "age", 30);
        CrawlabSDK.saveItem(data1, data2);

        // Save dynamic-sized data items
        ArrayList<Map<String, Object>> dataList = new ArrayList<>();
        dataList.add(Map.of("name", "John", "age", 25));
        dataList.add(Map.of("name", "Jane", "age", 30));
        CrawlabSDK.saveItem(dataList);

        // Passing Dynamic Arguments
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", "John");
        dataMap.put("age", 25);
        dataMap.put("skills", Arrays.asList("Java", "Python"));
        CrawlabSDK.saveItem(dataMap);

        // Using varargs for multiple items
        CrawlabSDK.saveItem(
            Map.of("name", "John", "age", 25),
            Map.of("name", "Jane", "age", 30)
        );

        // Using custom objects
        public class Person {
            private String name;
            private int age;
            
            // Constructor, getters, setters...
        }

        Person person1 = new Person("John", 25);
        Person person2 = new Person("Jane", 30);
        CrawlabSDK.saveItem(person1, person2);
    }
}
```
