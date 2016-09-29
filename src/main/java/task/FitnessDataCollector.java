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
        return waterdose - getSum(water, date);
    }

    public boolean hasToMuchWater(LocalDate date, Integer waterdose) {
        return (waterdose - getSum(water, date)) < 0;
    }

    public boolean hasZeroWater(LocalDate date) {
        return (Integer.valueOf(0)).equals(getSum(water, date));
    }

    public int restCallories(LocalDate date, Integer calloriesdose) {
        return calloriesdose - getSum(calories, date);
    }

    public boolean hasNothingEaten(LocalDate date) {
        return (Integer.valueOf(0)).equals(getSum(calories, date));
    }

    public boolean hasOverEaten(LocalDate date, Integer calloriesdose) {
        return (calloriesdose - getSum(calories, date)) < 0;
    }

    public int restSteps(LocalDate date, Integer movestepsdose) {
        return movestepsdose - getSum(steps, date);
    }

    public boolean hasZeroMoveSteps(LocalDate date) {
        return (Integer.valueOf(0)).equals(getSum(steps, date));
    }

    public boolean hasToMuchMoveSteps(LocalDate date, Integer movestepsdose) {
        return (movestepsdose - getSum(steps, date)) < 0;
    }

    public double restMoveHours(LocalDate date, Double movetimedose) {
        return movetimedose - getSum(hours, date);
    }

    public boolean hasZeroMoveHours(LocalDate date) {
        return (Double.valueOf(0)).equals(getSum(hours, date));
    }

    public boolean hasToMuchMoveHours(LocalDate date, Double movetimedose) {
        return (getSum(hours, date) - movetimedose) > 0;
    }

    public static <T extends Number> void sort(List<Record<T>> list) {
        Collections.sort(list, new Comparator<Record<T>>() {
            @Override
            public int compare(Record<T> o1, Record<T> o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

    }


    private static <T extends Number> T getSum(List<Record<T>> list,
                                               LocalDate... date) {
        T result = null;
        if (list != null) {
            int lsize = list.size();
            if (lsize > 0) {
                sort(list);
                if (date == null) {
                    result = getTotalSum(list);
                }
                else if (date.length == 1) {
                    result = getSingleDateSum(list, date[0]);
                }
                else {
                    result = getRangeDateSum(list, date);
                }
            }
        }
        return result;
    }

    private static <T extends Number> T getRangeDateSum(
            List<Record<T>> list, LocalDate[] date) {
        T result = null;
        int lsize = list.size();
        LocalDate currDate = null;
        int i = getInxOfDateMatch(list, date[0], lsize);

        if (list.get(0).getValue() instanceof Integer) {
            Record<Integer> sum = new Record<>(null, 0);
            if (i < lsize) {
                do {
                    currDate = list.get(i).getDate();
                    sum = sum.addValue((Record<Integer>) list.get(i++));
                } while (i < lsize && date[0].compareTo(currDate) <= 0 &&
                        date[1].compareTo(currDate) >= 0);
            }
            result = (T) sum.getValue();
        }
        else if (list.get(0).getValue() instanceof Double) {
            Record<Double> sum = new Record<>(null, 0.);
            if (i < lsize) {
                do {
                    currDate = list.get(i).getDate();
                    sum = sum.addValue((Record<Double>) list.get(i++));
                } while (i < lsize && date[0].compareTo(currDate) <= 0 &&
                        date[1].compareTo(currDate) >= 0);
            }
            result = (T) sum.getValue();
        }
        return result;
    }

    private static <T extends Number> T getSingleDateSum(
            List<Record<T>> list, LocalDate date) {
        T result = null;
        int lsize = list.size();
        int i = getInxOfDateMatch(list, date, lsize);

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
        return result;
    }

    private static <T extends Number> int getInxOfDateMatch(
            List<Record<T>> list, LocalDate date, int lsize) {
        int i = 0;
        while (i < lsize && !list.get(i).getDate().equals(date)) {
            i++;
        }
        return i;
    }

    private static <T extends Number> T getTotalSum(List<Record<T>> list) {
        T result = null;
        int lsize = list.size();
        int i = 0;
        if (list.get(0).getValue() instanceof Integer) {
            Record<Integer> sum = new Record<>(null, 0);
            while (i < lsize) {
                sum = sum.addValue((Record<Integer>) list.get(i++));
            }
            result = (T) sum.getValue();
        }
        else if (list.get(0).getValue() instanceof Double) {
            Record<Double> sum = new Record<>(null, 0.);
            while (i < lsize) {
                sum = sum.addValue((Record<Double>) list.get(i++));
            }
            result = (T) sum.getValue();
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
