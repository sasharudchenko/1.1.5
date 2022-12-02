package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()){
            statement.executeUpdate("create table if not exists user (id integer not null auto_increment, name varchar(45), lastname varchar(45), age int," +
                    "primary key (id))");
            System.out.println("Таблица создана");

        } catch (SQLException e) {
            System.out.println("Не удалось создать таблицу");;
        }

    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("drop table if exists user");

            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            System.out.println("Не удалсь удалить таблицу");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("insert into user values " +
                "(id, ?, ?, ?)")) {
           // preparedStatement.setString(1, id);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
//            System.out.println("Не удалось сохранить данные");
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("delete from user where id = ?")) {
            preparedStatement.setLong(1,  id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge((resultSet.getByte("age")));
                list.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        list.forEach(System.out::println);

        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("delete  from user");
            System.out.println("Таблица очищена");;

        } catch (SQLException e) {
            System.out.println("Не удалось очистить таблицу");
        }

    }
}
