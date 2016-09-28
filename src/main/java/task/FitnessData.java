package task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Oleksandr_Khodakovsk on 9/28/2016.
 */
public class FitnessData {

    private List<Record<Integer>> water = new ArrayList<>();
    private List<Record<Integer>> calories = new ArrayList<>();
    private List<Record<Double>> hours = new ArrayList<>();
    private List<Record<Integer>> steps = new ArrayList<>();

    public FitnessData() {
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

    public int restWater(Integer waterdose) {
        return waterdose.compareTo((Integer)getSum(water));
    }

    public boolean hasToMuchWater(Integer waterdose) {
        return waterdose.compareTo((Integer)getSum(water)) < 0;
    }

    public boolean hasZeroWater() {
        return (Integer)getSum(water) == 0;
    }

    public int restCallories(Integer calloriesdose) {
        return calloriesdose.compareTo((Integer)getSum(calories));
    }

    public boolean hasNothingEaten() {
        return (Integer)getSum(calories) == 0;
    }

    public boolean hasOverEaten(Integer calloriesdose) {
        return  calloriesdose.compareTo((Integer)getSum(calories)) < 0 ;
    }

    public int restSteps(Integer movestepsdose) {
        return movestepsdose.compareTo((Integer)getSum(steps)) ;
    }

    public boolean hasZeroMoveSteps() {
        return (Integer) getSum(steps) == 0;
    }

    public boolean hasToMuchMoveSteps(Integer movestepsdose) {
        return movestepsdose.compareTo((Integer) getSum(steps)) < 0;
    }

    public double restMoveHours(Double movetimedose) {
        return movetimedose.compareTo((Double) getSum(hours));
    }

    public boolean hasZeroMoveHours() {
        return (Double) getSum(hours) == 0;

    }

    public boolean hasToMuchMoveHours(Double movetimedose) {
        return ((Double) getSum(hours)).compareTo(movetimedose) > 0;
    }

    private static <T extends Number> Number getSum(List<Record<T>> list) {
        Record<Number> sum = new Record<Number>(null, 0);

        for (Record<T> item : list) {
            sum = sum.addValue(item);
        }
        return sum.getValue();
    }
}
