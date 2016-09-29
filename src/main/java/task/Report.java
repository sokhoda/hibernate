package task;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Oleksandr_Khodakovsk on 9/28/2016.
 */
public class Report {
    private FitnessDataCollector fit;

    public Report(FitnessDataCollector fit) {
        this.fit = fit;
    }

    public int dayRestWater(LocalDate date, Integer waterdose) {
        return fit.restWater(date, waterdose);
    }

    public <T extends Number> void printAll(LocalDate date,
                                            List<Record<T>> list) {
        if (list != null) {
            fit.sort(list);
            int lsize = list.size();
            int i = 0;
            while (i < lsize && !list.get(i).getDate().equals(date)) {
                i++;
            }
            while (i < lsize && list.get(i).getDate().equals(date)) {
                System.out.println(list.get(i++));
            }
        }
        else{
            System.out.println("<empty>");
        }
    }

}
