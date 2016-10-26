package jdbc_test.factory;

import jdbc_test.dao.TeamDao;

import java.sql.SQLException;

public class TeamFactory {
    public void createTeam(int teamID, String name, int projectID) throws SQLException {
        TeamDao teamDao = new TeamDao();
        teamDao.createElement(teamID, name, projectID);
    }
}
