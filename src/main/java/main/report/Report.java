package main.report;

import domain.Record;
import main.DoseCalculator;
import main.FitnessDataCollector;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


/**
 * Created by Oleksandr_Khodakovsk on 9/28/2016.
 */
public class Report {
    private final static int DIGITS = 2;
    private FitnessDataCollector fitData;
    private DoseCalculator dc;
    private final ReportLogic rl = new ReportLogic(DIGITS);

    public Report(FitnessDataCollector fitData, DoseCalculator dc) {
        this.fitData = fitData;
        this.dc = dc;
    }

    public BigDecimal calcWaterConsumpPercent(LocalDate... date) {
        Integer dose = dc.getWater();
        List<Record<Integer>> list = fitData.getWater();
        return rl.calcConsumpPercentage(dose, list, date);
    }

    public BigDecimal calcWaterConsumpMedian(LocalDate... date) {
        return rl.getMedian(fitData.getWater(), date);
    }

    public BigDecimal calcFoodConsumpPercent(LocalDate... date) {
        Integer dose = dc.getCalories();
        List<Record<Integer>> list = fitData.getCalories();
        return rl.calcConsumpPercentage(dose, list, date);
    }

    public BigDecimal calcFoodConsumpMedian(LocalDate... date) {
        return rl.getMedian(fitData.getCalories(), date);
    }

    public BigDecimal calcStepsPercent(LocalDate... date) {
        Integer dose = dc.getSteps();
        List<Record<Integer>> list = fitData.getSteps();
        return rl.calcConsumpPercentage(dose, list, date);
    }

    public BigDecimal calcStepsMedian(LocalDate... date) {
        return rl.getMedian(fitData.getSteps(), date);
    }

    public BigDecimal calcHoursPercent(LocalDate... date) {
        Double dose = dc.getHours();
        List<Record<Double>> list = fitData.getHours();
        return rl.calcConsumpPercentage(dose, list, date);
    }

    public BigDecimal calcHoursMedian(LocalDate... date) {
        return rl.getMedian(fitData.getHours(), date);
    }

    public static int getDigits() {
        return DIGITS;
    }

    public ReportLogic getRl() {
        return rl;
    }
}
