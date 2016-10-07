import domain.Human;
import testdatasource.TestDataSource;

public class Main {
    public static void main(String[] args) {
        System.out.println("FitnessCalculator");
        Human human = new Human();
        TestDataSource.init(human);
        System.out.println(human);//d
    }
}
