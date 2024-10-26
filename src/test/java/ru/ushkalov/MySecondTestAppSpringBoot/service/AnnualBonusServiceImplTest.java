package ru.ushkalov.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.ushkalov.MySecondTestAppSpringBoot.model.Positions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class AnnualBonusServiceImplTest {

    @Test
    void calculate() {
        Positions position = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        double result = new AnnualBonusServiceImpl().calculate(position, salary, bonus, workDays);

        double expected = 360493.8271604938;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateQuarterlyBonus() {
        Positions position = Positions.MANAGER;
        double salary = 120000.00;

        double result = new AnnualBonusServiceImpl().calculateQuarterlyBonus(position, salary);

        double expected = 30000.00;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateQuarterlyBonusTest2() {
        Positions positions = Positions.DEV;
        double salary = 150000;
        AnnualBonusServiceImpl annualBonusService = new AnnualBonusServiceImpl();

        assertThatThrownBy(() -> {
            annualBonusService.calculateQuarterlyBonus(positions, salary);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Премия может быть рассчитана только для менеджеров и управленцев.");
    }
}