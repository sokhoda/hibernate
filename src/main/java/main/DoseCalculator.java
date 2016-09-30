package main;

/**
 * Created by s_okhoda on 29.09.2016.
 */
public class DoseCalculator {
    private int height;
    private int age;
    private int sex;
    private Integer water;
    private Integer callories;
    private Double movetime = 2.;
    private Integer movesteps = 2000;

    public DoseCalculator() {
       calcDose();
    }

    public DoseCalculator(int height, int age, int sex) {
        this.height = height;
        this.age = age;
        this.sex = sex;
        calcDose();
    }

    private void calcDose() {
        water = 3000;
        callories = 2500;
        movetime = 2.;
        movesteps = 2000;
    }

    public Integer getWater(){
        return water;
    }

    public Integer getCallories() {
        return callories;
    }

    public Double getMovetime() {
        return movetime;
    }

    public Integer getMovesteps() {
        return movesteps;
    }
}
