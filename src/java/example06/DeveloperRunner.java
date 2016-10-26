package example06;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeveloperRunner {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        DeveloperRunner developerRunner = new DeveloperRunner();

        System.out.println("Adding 100,000 developer's records to the database...");
        developerRunner.addDevelopers();
        System.out.println("100,000 developer's records successfully added to the database...");
        sessionFactory.close();
    }

    private void addDevelopers() {
        Session session = sessionFactory.openSession();
        Integer developerId = null;

        for (int i=0; i<100000; i++){
            String firstName = "First Name " + i;
            String lastName = "Last Name " + i;
            String specialty = "Specialty " + i;
            int experience = i;
            int salary = i * 10;

            Developer developer = new Developer(firstName, lastName, specialty, experience, salary);

            session.save(developer);
            if (i%50 == 0){
                session.flush();
                session.clear();
            }
        }
        session.close();
    }
}
