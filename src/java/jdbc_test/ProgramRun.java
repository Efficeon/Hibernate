package jdbc_test;

import jdbc_test.controller.Command;
import jdbc_test.controller.DeveloperCommand;
import jdbc_test.controller.ProjectCommand;
import jdbc_test.controller.TeamCommand;
import jdbc_test.dao.ConnectDao;
import jdbc_test.view.ConsoleHelper;

import java.io.IOException;
import java.sql.SQLException;

public class ProgramRun {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        ConnectDao.ConnectDB();
        Command command = null;
        int commandNumber;
        ConsoleHelper.writeMessage("Вас приветствует система CRUD!");

        while (true) {
        ConsoleHelper.writeMessage("\nВыберите раздел: 1 - Разработчики | 2 - Рабочие группы разработчиков | 3 - Проекты | 4 - Выход из системы");
            commandNumber = ConsoleHelper.readInt();
            switch (commandNumber) {
                case 1:
                    command = new DeveloperCommand();
                    command.execute();
                    break;
                case 2:
                    command = new TeamCommand();
                    command.execute();
                    break;
                case 3:
                    command = new ProjectCommand();
                    command.execute();
                    break;
                case 4:
                    ConnectDao.closeConnect();
                    return;
                default:
                    break;
            }

        }
    }
}
