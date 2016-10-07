import domain.Human;
import testdatasource.TestDataSource;

public class Main {
    public static void main(String[] args) {
        System.out.println("FitnessCalculator");
        Human human = new Human();
        TestDataSource.init(human);
        System.out.println(human);
        if (args.length > 0) {
            System.out.println("age = " + args[0] + ", gender = " + args[1]);
        }
    }
}
