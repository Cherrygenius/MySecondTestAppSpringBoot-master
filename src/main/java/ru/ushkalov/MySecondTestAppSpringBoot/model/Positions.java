package ru.ushkalov.MySecondTestAppSpringBoot.model;

import lombok.Getter;

@Getter
public enum Positions {
    DEV(2.2, false),
    HR(1.2, false),
    TL(2.6, true),
    MANAGER(3.0, true),
    QA(1.8, false),
    DESIGNER(2.0, false);

    private final double positionsCoefficient;
    private final boolean isManager;

    Positions(double positionsCoefficient, boolean isManager) {
        this.positionsCoefficient = positionsCoefficient;
        this.isManager = isManager;
    }
}
