package task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Oleksandr_Khodakovsk on 9/28/2016.
 */
public class FitnessDataCollector {

    private final LocalDate now = LocalDate.now();
    private final List<Record<Integer>> water = new ArrayList<>();
    private final List<Record<Integer>> calories = new ArrayList<>();
    private final List<Record<Double>> hours = new ArrayList<>();
    private final List<Record<Integer>> steps = new ArrayList<>();
    private final ReportLogic rl = new ReportLogic();

    public FitnessDataCollector() {
        init();
    }

    public void init() {
        water.add(new Record<>(LocalDate.MIN, 0));
        calories.add(new Record<>(LocalDate.MIN, 0));
        hours.add(new Record<>(LocalDate.MIN, 0.));
        steps.add(new Record<>(LocalDate.MIN, 0));
    }

    public void drink(LocalDate date, int volume) {
        water.add(new Record<Integer>(date, volume));
    }

    public void eat(LocalDate date, int volume) {
        calories.add(new Record<Integer>(date, volume));
    }

    public void move(LocalDate date, double hours, int steps) {
        this.hours.add(new Record<Double>(date, hours));
        this.steps.add(new Record<Integer>(date, steps));
    }

    public int dayRestWater(LocalDate date, Integer waterdose) {
        return waterdose - rl.getSum(water, date);
    }

    public boolean hasToMuchWater(LocalDate date, Integer waterdose) {
        return (waterdose - rl.getSum(water, date)) < 0;
    }

    public boolean hasZeroWater(LocalDate date) {
        return (Integer.valueOf(0)).equals(rl.getSum(water, date));
    }

    public int restCallories(LocalDate date, Integer calloriesdose) {
        return calloriesdose - rl.getSum(calories, date);
    }

    public boolean hasNothingEaten(LocalDate date) {
        return (Integer.valueOf(0)).equals(rl.getSum(calories, date));
    }

    public boolean hasOverEaten(LocalDate date, Integer calloriesdose) {
        return (calloriesdose - rl.getSum(calories, date)) < 0;
    }

    public int restSteps(LocalDate date, Integer movestepsdose) {
        return movestepsdose - rl.getSum(steps, date);
    }

    public boolean hasZeroMoveSteps(LocalDate date) {
        return (Integer.valueOf(0)).equals(rl.getSum(steps, date));
    }

    public boolean hasToMuchMoveSteps(LocalDate date, Integer movestepsdose) {
        return (movestepsdose - rl.getSum(steps, date)) < 0;
    }

    public double restMoveHours(LocalDate date, Double movetimedose) {
        return movetimedose - rl.getSum(hours, date);
    }

    public boolean hasZeroMoveHours(LocalDate date) {
        return (Double.valueOf(0)).equals(rl.getSum(hours, date));
    }

    public boolean hasToMuchMoveHours(LocalDate date, Double movetimedose) {
        return (rl.getSum(hours, date) - movetimedose) > 0;
    }

    public LocalDate getNow() {
        return now;
    }

    public List<Record<Integer>> getWater() {
        return water;
    }

    public List<Record<Integer>> getCalories() {
        return calories;
    }

    public List<Record<Double>> getHours() {
        return hours;
    }

    public List<Record<Integer>> getSteps() {
        return steps;
    }
}
