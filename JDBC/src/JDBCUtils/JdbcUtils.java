package JDBCUtils;


import jdbc01.Jdbc01;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
 * @description:JDBC工具类
 * @author Gaoshang
 * @date 2020/10/29
 * @email gs_nuaa@163.com
 */
public class JdbcUtils {

    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {

        Properties properties = new Properties();
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        properties.load(resourceAsStream);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;

    }

    public static void closeConnection(Connection connection, Statement statement){


       try {
           if (connection != null) {
               connection.close();
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }

       try {
           if(statement != null){
               statement.close();
           }
       }catch (SQLException e){
           e.printStackTrace();
       }

    }
}
