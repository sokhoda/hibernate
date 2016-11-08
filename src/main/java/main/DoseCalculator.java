package main;

import domain.Human;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class DoseCalculator implements Serializable{
    @Id
    @SequenceGenerator(initialValue = 1, name = "DOSEGEN")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOSEGEN" )
    private Long id;

    private int height;
    private int age;
    private int sex;
    private Integer water;
    private Integer calories;
    private Double hours;
    private Integer steps;

    @OneToOne(mappedBy = "dc")
    private Human human;

    public DoseCalculator() {
       calcDose();
    }

    public DoseCalculator(int height, int age, int sex) {
        this.height = height;
        this.age = age;
        this.sex = sex;
        calcDose();
    }

    @Override
    public String toString() {
        return "\nDoseCalculator{" +
                "height=" + height +
                ", age=" + age +
                ", sex=" + sex +
                ", water=" + water +
                ", calories=" + calories +
                ", hours=" + hours +
                ", steps=" + steps +
                '}';
    }

    private void calcDose() {
        water = 3000;
        calories = 2500;
        hours = 2.;
        steps = 2000;
    }

    public Integer getWater(){
        return water;
    }

    public Integer getCalories() {
        return calories;
    }

    public Double getHours() {
        return hours;
    }

    public Integer getSteps() {
        return steps;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setWater(Integer water) {
        this.water = water;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }
}
