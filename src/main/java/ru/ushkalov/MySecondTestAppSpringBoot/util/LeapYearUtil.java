package ru.ushkalov.MySecondTestAppSpringBoot.util;

import java.time.LocalDate;

public class LeapYearUtil {
    public static int isLeapYear() {
        int currentYear = LocalDate.now().getYear();
        return (currentYear % 4 == 0 && currentYear % 100 != 0) || (currentYear % 400 == 0) ? 366 : 365;
    }
}
