package ru.ushkalov.MySecondTestAppSpringBoot.util;

import java.text.SimpleDateFormat;

public class DataTimeUtil {
    public static SimpleDateFormat getCustomFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }
}
