package example05;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Map;

public class DeveloperRunner {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        DeveloperRunner developerRunner = new DeveloperRunner();

        System.out.println("Adding developer's records to the database...");
        Integer developerId1 = developerRunner.addDeveloper("Proselyte", "Developer", "Java Developer", 3, 2000);
        Integer developerId2 = developerRunner.addDeveloper("First", "Developer", "C++ Developer", 10, 5000);
        Integer developerId3 = developerRunner.addDeveloper("Second", "Developer", "C# Developer", 5, 4000);
        Integer developerId4 = developerRunner.addDeveloper("Third", "Developer", "PHP Developer", 1, 1000);

        System.out.println("List of Developers using Entity Query:");
        developerRunner.listDevelopers();

        System.out.println("List of Developers using Scalar Query:");
        developerRunner.listDevelopersScalar();
        sessionFactory.close();
    }

    public Integer addDeveloper(String firstName, String lastName, String specialty, int experience, int salary){
        Session session = sessionFactory.openSession();
        Integer developerId = null;

        Developer developer = new Developer(firstName,lastName,specialty,experience,salary);
        developerId = (Integer) session.save(developer);
        session.close();

        return developerId;
    }

    public void listDevelopers() {
        Session session = sessionFactory.openSession();

        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM HIBERNATE_DEVELOPERS");
        sqlQuery.addEntity(Developer.class);

        List<Developer> developers = sqlQuery.list();
        for (Developer developer : developers) {
            System.out.println("=======================");
            System.out.println(developer);
            System.out.println("=======================");
        }
        session.close();
    }

    public void listDevelopersScalar() {
        Session session = sessionFactory.openSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM HIBERNATE_DEVELOPERS");
        sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

        List<Developer> developers = sqlQuery.list();

        for (Object developer : developers) {
            Map row = (Map) developer;
            System.out.println("=======================");
            System.out.println("id: " + row.get("id"));
            System.out.println("First Name: " + row.get("FIRST_NAME"));
            System.out.println("Last Name: " + row.get("LAST_NAME"));
            System.out.println("Specialty: " + row.get("SPECIALTY"));
            System.out.println("Experience: " + row.get("EXPERIENCE"));
            System.out.println("Salary: " + row.get("SALARY"));
            System.out.println("=======================");
        }
        session.close();
    }
}
