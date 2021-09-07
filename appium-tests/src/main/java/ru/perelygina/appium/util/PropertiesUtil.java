package ru.perelygina.appium.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.util.Properties;

@UtilityClass
public class PropertiesUtil {

    private final String TEST_PROPERTIES_FILE = "test.properties";

    @SneakyThrows
    public String getProperty(String key) {
        Properties properties = new Properties();
        properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(TEST_PROPERTIES_FILE));
        return properties.getProperty(key);
    }
}
