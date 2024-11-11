package br.ufv.caf.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static final String DB_URL = "jdbc:sqlite:src/main/java/br/ufv/caf/Database/arasydb.db";

    // Método para conectar ao banco de dados SQLite
    public static Connection connect() {
        // Caminho para o banco de dados SQLite
        String url = "jdbc:sqlite:src/main/java/br/ufv/caf/Database/arasydb.db";
        Connection conn = null;

        try {
            // Estabelecendo a conexão
            conn = DriverManager.getConnection(url);
            System.out.println("Conexão estabelecida com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }

        return conn;
    }
}
