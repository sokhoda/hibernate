package task;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Oleksandr_Khodakovsk on 9/29/2016.
 */
public class ReportLogic {

    public  <T extends Number> void sort(List<Record<T>> list) {
        Collections.sort(list, new Comparator<Record<T>>() {
            @Override
            public int compare(Record<T> o1, Record<T> o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
    }

    public  <T extends Number> T getSum(List<Record<T>> list,
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

    private  <T extends Number> T getRangeDateSum(
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

    private  <T extends Number> T getSingleDateSum(
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

    private  <T extends Number> int getInxOfDateMatch(
            List<Record<T>> list, LocalDate date, int lsize) {
        int i = 0;
        while (i < lsize && !list.get(i).getDate().equals(date)) {
            i++;
        }
        return i;
    }

    private  <T extends Number> T getTotalSum(List<Record<T>> list) {
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
}
