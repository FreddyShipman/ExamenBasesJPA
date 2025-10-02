package Conexion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author JOSE ALFREDO GUZMAN MORENO - 00000252524
 */
public class ConexionJPA {
    public class ConnectionDB {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {
        try(InputStream input = ConnectionDB.class.getClassLoader().getResourceAsStream("db.properties")){
            Properties props = new Properties();
            if (input == null){
                throw new RuntimeException("No se logo obtener el archivo de propiedades");
            }
            props.load(input);
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
            driver = props.getProperty("db.driver");
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

}
