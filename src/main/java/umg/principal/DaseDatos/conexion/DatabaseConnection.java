package umg.principal.DaseDatos.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:\\frontEnd\\identifier.sqlite";

    public static Connection getConnection() throws SQLException, SQLException {
        return DriverManager.getConnection(URL);
    }
}
