package task;

import org.mockito.cglib.core.Local;

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
    private List<Record<Integer>> water = new ArrayList<>();
    private List<Record<Integer>> calories = new ArrayList<>();
    private List<Record<Double>> hours = new ArrayList<>();
    private List<Record<Integer>> steps = new ArrayList<>();

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

    public int restWater(LocalDate date, Integer waterdose) {
        return waterdose - getSum(date, water);
    }

    public boolean hasToMuchWater(LocalDate date, Integer waterdose) {
        return (waterdose - getSum(date, water)) < 0;
    }

    public boolean hasZeroWater(LocalDate date) {
        return (Integer.valueOf(0)).equals(getSum(date, water));
    }

    public int restCallories(LocalDate date, Integer calloriesdose) {
        return calloriesdose - getSum(date, calories);
    }

    public boolean hasNothingEaten(LocalDate date) {
        return (Integer.valueOf(0)).equals(getSum(date, calories));
    }

    public boolean hasOverEaten(LocalDate date, Integer calloriesdose) {
        return (calloriesdose - getSum(date, calories)) < 0;
    }

    public int restSteps(LocalDate date, Integer movestepsdose) {
        return movestepsdose - getSum(date, steps);
    }

    public boolean hasZeroMoveSteps(LocalDate date) {
        return (Integer.valueOf(0)).equals(getSum(date, steps));
    }

    public boolean hasToMuchMoveSteps(LocalDate date, Integer movestepsdose) {
        return (movestepsdose - getSum(date, steps)) < 0;
    }

    public double restMoveHours(LocalDate date, Double movetimedose) {
        return movetimedose - getSum(date, hours);
    }

    public boolean hasZeroMoveHours(LocalDate date) {
        return (Double.valueOf(0)).equals(getSum(date, hours));
    }

    public boolean hasToMuchMoveHours(LocalDate date, Double movetimedose) {
        return (getSum(date, hours) - movetimedose) > 0;
    }

    public static <T extends Number> void sort(List<Record<T>> list) {
        Collections.sort(list, new Comparator<Record<T>>() {
            @Override
            public int compare(Record<T> o1, Record<T> o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

    }


    private static <T extends Number> T getSum(LocalDate date, List<Record<T>>
            list) {
        T result = null;
        if (list == null) {
            return result;
        }
        int lsize = list.size();
        if (lsize > 0) {
            sort(list);
            int i = 0;
            while (i < lsize && !list.get(i).getDate().equals(date)) {
                i++;
            }

            if (i < lsize) {
                if (list.get(0).getValue() instanceof Integer) {
                    Record<Integer> sum = new Record<>(null, 0);

                    while (i < lsize && list.get(i).getDate().equals(date)) {
                        sum = sum.addValue((Record<Integer>) list.get(i++));
                    }
                    result = (T) sum.getValue();
                }
                else if (list.get(0).getValue() instanceof Double) {
                    Record<Double> sum = new Record<>(null, 0.);
                    while (i < lsize && list.get(i).getDate().equals(date)) {
                        sum = sum.addValue((Record<Double>) list.get(i++));
                    }
                    result = (T) sum.getValue();
                }
            }
        }

        return result;
    }

    public LocalDate getNow() {
        return now;
    }

    public List<Record<Integer>> getWater() {
        return water;
    }

    public void setWater(List<Record<Integer>> water) {
        this.water = water;
    }

    public List<Record<Integer>> getCalories() {
        return calories;
    }

    public void setCalories(List<Record<Integer>> calories) {
        this.calories = calories;
    }

    public List<Record<Double>> getHours() {
        return hours;
    }

    public void setHours(List<Record<Double>> hours) {
        this.hours = hours;
    }

    public List<Record<Integer>> getSteps() {
        return steps;
    }

    public void setSteps(List<Record<Integer>> steps) {
        this.steps = steps;
    }
}
