package jdbc_test.dao;

import jdbc_test.view.ConsoleHelper;
import jdbc_test.model.Project;
import jdbc_test.model.Team;
import org.hibernate.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao{
    private List<Project> listProjects = null;
    private List<Team> listTeams = null;
    private ResultSet resultSetProjects;

    public List<Project> readingAllElements() throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        listProjects = session.createQuery("FROM Project").list();
        return listProjects;
    }

    public List<Project> readingProjectsElements(int projectID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        listProjects = session.createQuery("FROM Project WHERE projectID="+projectID).list();
        session.close();
        return listProjects;
    }

    public void updateElement(int projectID, String name) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Project project = (Project) session.get(Project.class, projectID);
        project.setName(name);
        session.update(project);
        session.close();
    }

    public void deleteElement(int projectID) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Project project = (Project) session.get(Project.class, projectID);
        session.delete(project);
        session.close();
    }

    public void createElement(int projectID, String name) throws SQLException {
        Session session = ConnectDao.sessionFactory.openSession();
        Project project = new Project(projectID, name);
        session.saveOrUpdate(project);
        session.close();
    }

    public void showAllProjects() throws SQLException {
        readingAllElements();
        for (Project project : listProjects){
            ConsoleHelper.writeMessage(project.toString());
        }
    }

    public void showProject(int projectID) throws SQLException {
        readingProjectsElements(projectID);
        for (Project project : listProjects){
            ConsoleHelper.writeMessage(project.toString());
        }
    }

    /*private void resultProcessing(String sql) throws SQLException {
        resultSetProjects = ConnectDao.selectRecord(sql);
        listProjects = new ArrayList<>();

        while (resultSetProjects.next()){
            int projectID = resultSetProjects.getInt("projectID");
            String name = resultSetProjects.getString("name");
            Project project = new Project(projectID, name);
            listProjects.add(project);
        }
        resultSetProjects.close();

        TeamDao teamDao = new TeamDao();
        for (Project project : listProjects){
            listTeams = teamDao.readingProjectsElements(project.getProjectID());
            project.setTeams(listTeams);
        }
    }*/
}
