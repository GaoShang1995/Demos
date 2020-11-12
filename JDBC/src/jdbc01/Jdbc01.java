package jdbc01;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Jdbc01 {


    @Test
    public void testConnection() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver)aClass.newInstance();

        //数据库连接
        String url = "jdbc:mysql://localhost:3306/test";

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","123");

        Connection connection = driver.connect(url,properties);
        System.out.println(connection);

        connection.close();

    }

    @Test
    public void testConnection2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        Class aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();

        String url = "jdbc:mysql://localhost:3306/wedu";
        String user = "root";
        String password = "123";

        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);
        connection.close();

    }

    @Test
    public void testConnection3() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "123";

        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

    }

    @Test
    public static Connection testConnection4() throws IOException, ClassNotFoundException, SQLException {

        Properties properties = new Properties();
        InputStream resourceAsStream = Jdbc01.class.getClassLoader().getResourceAsStream("jdbc.properties");
        properties.load(resourceAsStream);

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;

    }
}
