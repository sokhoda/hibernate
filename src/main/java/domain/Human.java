package domain;

import converters.DateConverter;
import main.DoseCalculator;
import report.Report;
import report.ReportLogic;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Human implements Serializable {
    @Id
    @SequenceGenerator(initialValue = 1, name = "HUMANGEN")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HUMANGEN")
    private Long id;
    private String name;

    @Convert(converter = DateConverter.class)
    private final LocalDateTime now = LocalDateTime.now();
    @Transient
    private final List<Record<Integer>> water = new ArrayList<>();
    @Transient
    private final List<Record<Integer>> calories = new ArrayList<>();
    @Transient
    private final List<Record<Double>> hours = new ArrayList<>();
    @Transient
    private final List<Record<Integer>> steps = new ArrayList<>();
    @Transient
    private final ReportLogic rl = new ReportLogic(Report.getDigits());

    @OneToOne(cascade = CascadeType.ALL)
    private DoseCalculator dc;

    public Human(String name) {
        this();
        this.name = name;
    }

    public Human() {
        init();
        dc = new DoseCalculator();
    }

    private void init() {
        water.add(new Record<>(LocalDate.MIN, 0));
        calories.add(new Record<>(LocalDate.MIN, 0));
        hours.add(new Record<>(LocalDate.MIN, 0.));
        steps.add(new Record<>(LocalDate.MIN, 0));
    }

    public void drink(LocalDate date, int volume) {
        water.add(new Record<>(date, volume));
    }

    public void eat(LocalDate date, int volume) {
        calories.add(new Record<>(date, volume));
    }

    public void move(LocalDate date, double hours, int steps) {
        this.hours.add(new Record<>(date, hours));
        this.steps.add(new Record<>(date, steps));
    }


    public boolean dayZeroWater(LocalDate date) {
        return (Integer.valueOf(0)).equals(rl.getSum(water, date));
    }

    public boolean dayZeroSteps(LocalDate date) {
        return (Integer.valueOf(0)).equals(rl.getSum(steps, date));
    }

    public boolean dayZeroHours(LocalDate date) {
        return (Double.valueOf(0)).equals(rl.getSum(hours, date));
    }

    public boolean dayZeroCalories(LocalDate date) {
        return (Integer.valueOf(0)).equals(rl.getSum(calories, date));
    }

    public boolean dayTooMuchWater(LocalDate date) {
        return (rl.getSum(water, date) - dc.getWater()) > 0;
    }

    public boolean dayTooMuchCalories(LocalDate date) {
        return (rl.getSum(calories, date) - dc.getCalories()) > 0;
    }

    public boolean dayTooMuchSteps(LocalDate date) {
        return (rl.getSum(steps, date) - dc.getSteps()) > 0;
    }

    public boolean dayTooMuchHours(LocalDate date) {
        return (rl.getSum(hours, date) - dc.getHours()) > 0;
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

    @Override
    public String toString() {
        return "\nHuman{" +
                "now=" + now +
                ", water=" + water +
                ", calories=" + calories +
                ", hours=" + hours +
                ", steps=" + steps +
                ", rl=" + rl +
                ", dc=" + dc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Human human = (Human) o;

        if (now != null ? !now.equals(human.now) : human.now != null)
            return false;
        if (water != null ? !water.equals(human.water) : human.water != null)
            return false;
        if (calories != null ? !calories.equals(human.calories) : human.calories != null)
            return false;
        if (hours != null ? !hours.equals(human.hours) : human.hours != null)
            return false;
        if (steps != null ? !steps.equals(human.steps) : human.steps != null)
            return false;
        if (rl != null ? !rl.equals(human.rl) : human.rl != null) return false;
        return dc != null ? dc.equals(human.dc) : human.dc == null;

    }

    @Override
    public int hashCode() {
        int result = now != null ? now.hashCode() : 0;
        result = 31 * result + (water != null ? water.hashCode() : 0);
        result = 31 * result + (calories != null ? calories.hashCode() : 0);
        result = 31 * result + (hours != null ? hours.hashCode() : 0);
        result = 31 * result + (steps != null ? steps.hashCode() : 0);
        result = 31 * result + (rl != null ? rl.hashCode() : 0);
        result = 31 * result + (dc != null ? dc.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getNow() {
        return now;
    }

    public ReportLogic getRl() {
        return rl;
    }

    public DoseCalculator getDc() {
        return dc;
    }

    public void setDc(DoseCalculator dc) {
        this.dc = dc;
    }
}
