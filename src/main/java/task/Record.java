package task;

import java.time.LocalDate;

/**
 * Created by Oleksandr_Khodakovsk on 9/28/2016.
 */
public class Record <T extends Number>{
    private LocalDate date;
    private T value;

    public Record(LocalDate date, T value) {
        this.date = date;
        this.value = value;
    }

    public Record<T> addValue(Record<T> rec1){
        T val1 = rec1.getValue();
        T val2 = (T) this.getValue();
        if(val1 instanceof Double || val2 instanceof Double) {
            setValue((T) new Double( val1.doubleValue() +
                    val2.doubleValue()));
        }
        if(val1 instanceof Integer || val2 instanceof Integer) {
            setValue((T) new Integer( val1.intValue() +
                    val2.intValue()));
        }
        return null;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
