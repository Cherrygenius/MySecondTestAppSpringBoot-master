package ru.ushkalov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.ushkalov.MySecondTestAppSpringBoot.model.Positions;

@Service
public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays);
}
