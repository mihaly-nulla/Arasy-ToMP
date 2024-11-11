package br.ufv.caf.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateSQLiteDB {
    // Método para criar a tabela TB_Quest
    private static void createTableQuest(Connection conn) {
        String sql = """
            CREATE TABLE IF NOT EXISTS TB_Quest (
                PK_IDQuest TEXT NOT NULL,
                Estado INTEGER NOT NULL,
                TituloQuest TEXT NOT NULL,
                DescricaoQuest TEXT NOT NULL,
                PRIMARY KEY (PK_IDQuest)
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela TB_Quest criada com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para criar a tabela TB_Tarefa
    private static void createTableTarefa(Connection conn) {
        String sql = """
            CREATE TABLE IF NOT EXISTS TB_Tarefa (
                PK_IDTarefa TEXT NOT NULL,
                DescricaoTarefa TEXT NOT NULL,
                Estado INTEGER NOT NULL,
                FK_Quest_IDQuest TEXT NOT NULL,
                PRIMARY KEY (PK_IDTarefa),
                FOREIGN KEY (FK_Quest_IDQuest) REFERENCES TB_Quest(PK_IDQuest)
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela TB_Tarefa criada com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para criar a tabela TB_TarefaViagem
    private static void createTableTarefaViagem(Connection conn) {
        // Nessa função são criadas as tabelas do banco de dados, de acordo com o diagrama definido anteriormente, a conexão com o banco de dados é feita por meio da classe DBConnection

        String sql = """
            CREATE TABLE IF NOT EXISTS TB_TarefaViagem (
                PK_FK_Tarefa_IDTarefa TEXT NOT NULL,
                CoordenadaFinalX REAL NOT NULL,
                CoordenadaFinalY REAL NOT NULL,
                PRIMARY KEY (PK_FK_Tarefa_IDTarefa),
                FOREIGN KEY (PK_FK_Tarefa_IDTarefa) REFERENCES TB_Tarefa(PK_IDTarefa)
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela TB_TarefaViagem criada com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para criar a tabela TB_Aluno
    private static void createTableAluno(Connection conn) {
        String sql = """
            CREATE TABLE IF NOT EXISTS TB_Aluno (
                PK_IDAluno TEXT NOT NULL,
                Nome TEXT NOT NULL,
                TempoUltimaSessao REAL,
                CoordXUltimaSessao REAL,
                CoordYUltimaSessao REAL,
                DistanciaTotalPercorrida REAL,
                PRIMARY KEY (PK_IDAluno)
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela TB_Aluno criada com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para criar a tabela TB_QuestsDoAluno
    private static void createTableQuestsDoAluno(Connection conn) {
        String sql = """
            CREATE TABLE IF NOT EXISTS TB_QuestsDoAluno (
                PK_FK_Aluno_IDAluno TEXT NOT NULL,
                PK_FK_Quest_IDQuest TEXT NOT NULL,
                TempoGastoConclusao REAL,
                PRIMARY KEY (PK_FK_Aluno_IDAluno, PK_FK_Quest_IDQuest),
                FOREIGN KEY (PK_FK_Aluno_IDAluno) REFERENCES TB_Aluno(PK_IDAluno),
                FOREIGN KEY (PK_FK_Quest_IDQuest) REFERENCES TB_Quest(PK_IDQuest)
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela TB_QuestsDoAluno criada com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para criar a tabela TB_Item
    private static void createTableItem(Connection conn) {
        String sql = """
            CREATE TABLE IF NOT EXISTS TB_Item (
                PK_IDItem TEXT NOT NULL,
                NomeItem TEXT,
                DescricaoItem TEXT,
                CorItem TEXT,
                TipoItem TEXT,
                CoordenadaX REAL,
                CoordenadaY REAL,
                Contabilizavel INTEGER,
                PRIMARY KEY (PK_IDItem)
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela TB_Item criada com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para criar a tabela TB_TarefaColeta
    private static void createTableTarefaColeta(Connection conn) {
        String sql = """
            CREATE TABLE IF NOT EXISTS TB_TarefaColeta (
                PK_FK_Tarefa_IDTarefa TEXT NOT NULL,
                FK_Item_IDItem TEXT NOT NULL,
                PRIMARY KEY (PK_FK_Tarefa_IDTarefa),
                FOREIGN KEY (PK_FK_Tarefa_IDTarefa) REFERENCES TB_Tarefa(PK_IDTarefa),
                FOREIGN KEY (FK_Item_IDItem) REFERENCES TB_Item(PK_IDItem)
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela TB_TarefaColeta criada com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para criar a tabela TB_Mochila
    private static void createTableMochila(Connection conn) {
        String sql = """
            CREATE TABLE IF NOT EXISTS TB_Mochila (
                PK_FK_Aluno_IDAluno TEXT NOT NULL,
                PRIMARY KEY (PK_FK_Aluno_IDAluno),
                FOREIGN KEY (PK_FK_Aluno_IDAluno) REFERENCES TB_Aluno(PK_IDAluno)
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela TB_Mochila criada com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para criar a tabela TB_ItensDaMochila
    private static void createTableItensDaMochila(Connection conn) {
        String sql = """
            CREATE TABLE IF NOT EXISTS TB_ItensDaMochila (
                PK_FK_Item_IDItem TEXT NOT NULL,
                PK_FK_Mochila_IDAluno TEXT NOT NULL,
                PRIMARY KEY (PK_FK_Item_IDItem, PK_FK_Mochila_IDAluno),
                FOREIGN KEY (PK_FK_Item_IDItem) REFERENCES TB_Item(PK_IDItem),
                FOREIGN KEY (PK_FK_Mochila_IDAluno) REFERENCES TB_Mochila(PK_FK_Aluno_IDAluno)
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela TB_ItensDaMochila criada com sucesso.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Connection dbConnection = DBConnection.connect();

        if (dbConnection != null) {
            System.out.println("Inicializando SQLite database...\n");
            createTableQuest(dbConnection);
            createTableTarefa(dbConnection);
            createTableTarefaViagem(dbConnection);
            createTableAluno(dbConnection);
            createTableQuestsDoAluno(dbConnection);
            createTableItem(dbConnection);
            createTableTarefaColeta(dbConnection);
            createTableMochila(dbConnection);
            createTableItensDaMochila(dbConnection);
            try {
                dbConnection.close(); // Fechar a conexão após as operações
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}