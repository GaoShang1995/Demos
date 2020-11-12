package jdbc01;

import JDBCUtils.JdbcUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.*;

public class Jdbc02 {

    @Test
    public void preparedStatementTest() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = Jdbc01.testConnection4();

            String sql ="select * from student where  Sname = ?";

            preparedStatement = connection.prepareStatement(sql);

            //下标从1开始
            preparedStatement.setString(1,"张三");

            ResultSet resultSet = preparedStatement.executeQuery();


            System.out.println(resultSet);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){

                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void preparedStatementTest2() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();

            String sql = "update student set Sname= ? where Sname=?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setObject(1,"张3");
            preparedStatement.setObject(2,"张三");
            int execute = preparedStatement.executeUpdate();
            System.out.println(execute);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(connection,preparedStatement);
        }

    }
}
