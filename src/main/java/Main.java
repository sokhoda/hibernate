import domain.Human;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import persistence.HibernateUtil;
import testdatasource.TestDataSource;

public class Main {
    public static void main(String[] args) {

        Human human = new Human("Alex");
        System.out.println("FitnessCalculator");
        TestDataSource.init(human);
        System.out.println(human);
        if (args.length > 0) {
            System.out.println("age = " + args[0] + ", gender = " + args[1]);
        }
        SessionFactory factory = HibernateUtil.buildSessionFactory();

        Session session = factory.openSession();

        Transaction tr = session.getTransaction();
        tr.begin();
        session.save(human);
        tr.commit();

        session.close();
        factory.close();

    }
}
