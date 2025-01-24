package gestion_contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

        private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=GestionnaireContact;integratedSecurity=true;trustServerCertificate=true";
        private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        public static Connection getConnection() {
            Connection connection = null;
            try {
                // Charger le driver SQL Server
                Class.forName(DRIVER);
                // Établir la connexion avec authentification Windows
                connection = DriverManager.getConnection(URL);
                System.out.println("Connexion réussie avec authentification Windows !");
            } catch (ClassNotFoundException e) {
                System.err.println("Erreur : Driver JDBC non trouvé ! " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Erreur de connexion : " + e.getMessage());
            }
            return connection;
        }
    }

