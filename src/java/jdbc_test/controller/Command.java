package jdbc_test.controller;

import java.io.IOException;
import java.sql.SQLException;

public interface Command {
    void execute() throws IOException, ClassNotFoundException, SQLException;
}
