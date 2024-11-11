package br.ufv.caf.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    private static final Connection dbConnection = DBConnection.connect();
    
    // Função para adicionar dados à tabela TB_Quest
    private static void addQuest(String pkIdQuest, int estado, String tituloQuest, String descricaoQuest) throws SQLException {
        String sql = "INSERT INTO TB_Quest (PK_IDQuest, Estado, TituloQuest, DescricaoQuest) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            pstmt.setString(1, pkIdQuest);
            pstmt.setInt(2, estado);
            pstmt.setString(3, tituloQuest);
            pstmt.setString(4, descricaoQuest);
            pstmt.executeUpdate();
        }
    }

    // Função para adicionar dados à tabela TB_Tarefa
    private static void addTarefa(String pkIdTarefa, String descricaoTarefa, int estado, String fkQuestIdQuest) throws SQLException {
        String sql = "INSERT INTO TB_Tarefa (PK_IDTarefa, DescricaoTarefa, Estado, FK_Quest_IDQuest) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            pstmt.setString(1, pkIdTarefa);
            pstmt.setString(2, descricaoTarefa);
            pstmt.setInt(3, estado);
            pstmt.setString(4, fkQuestIdQuest);
            pstmt.executeUpdate();
        }
    }

    // Função para adicionar dados à tabela TB_TarefaViagem
    private static void addTarefaViagem(String pkFkTarefaIdTarefa, double coordenadaFinalX, double coordenadaFinalY) throws SQLException {
        String sql = "INSERT INTO TB_TarefaViagem (PK_FK_Tarefa_IDTarefa, CoordenadaFinalX, CoordenadaFinalY) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
                pstmt.setString(1, pkFkTarefaIdTarefa);
                pstmt.setDouble(2, coordenadaFinalX);
                pstmt.setDouble(3, coordenadaFinalY);
                pstmt.executeUpdate();
            }
    }

    // Função para adicionar dados à tabela TB_Aluno
    private static void addAluno(String pkIdAluno, String nome, Double tempoUltimaSessao, Double coordXUltimaSessao, Double coordYUltimaSessao, Double distanciaTotalPercorrida) throws SQLException {
        String sql = "INSERT INTO TB_Aluno (PK_IDAluno, Nome, TempoUltimaSessao, CoordXUltimaSessao, CoordYUltimaSessao, DistanciaTotalPercorrida) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            pstmt.setString(1, pkIdAluno);
                pstmt.setString(2, nome);
                pstmt.setDouble(3, tempoUltimaSessao);
                pstmt.setDouble(4, coordXUltimaSessao);
                pstmt.setDouble(5, coordYUltimaSessao);
                pstmt.setDouble(6, distanciaTotalPercorrida);
                pstmt.executeUpdate();
            }
    }

    // Função para adicionar dados à tabela TB_QuestsDoAluno
    private static void addQuestsDoAluno(String pkFkAlunoIdAluno,
                                         String pkFkQuestIdQuest,
                                         Double tempoGastoConclusao) throws SQLException {
        String sql = "INSERT INTO TB_QuestsDoAluno (PK_FK_Aluno_IDAluno, PK_FK_Quest_IDQuest, TempoGastoConclusao) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            pstmt.setString(1, pkFkAlunoIdAluno);
            pstmt.setString(2, pkFkQuestIdQuest);
            if (tempoGastoConclusao != null) {
                pstmt.setDouble(3, tempoGastoConclusao);
            } else {
                pstmt.setNull(3, java.sql.Types.DOUBLE); // Define o valor nulo para campos do tipo DOUBLE
            }

            pstmt.executeUpdate();
        }
    }

    // Função para adicionar dados à tabela TB_Item
    private static void addItem(String pkIdItem, String nomeItem, String descricaoItem, String corItem, String tipoItem, Double coordenadaX, Double coordenadaY, Integer contabilizavel) throws SQLException {
        String sql = "INSERT INTO TB_Item (PK_IDItem, NomeItem, DescricaoItem, CorItem, TipoItem, CoordenadaX, CoordenadaY, Contabilizavel) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            pstmt.setString(1, pkIdItem);
            pstmt.setString(2, nomeItem);
            pstmt.setString(3, descricaoItem);
            pstmt.setString(4, corItem);
            pstmt.setString(5, tipoItem);
            pstmt.setDouble(6, coordenadaX);
            pstmt.setDouble(7, coordenadaY);
            pstmt.setInt(8, contabilizavel);
            pstmt.executeUpdate();
        }
    }

    // Função para adicionar dados à tabela TB_TarefaColeta
    private static void addTarefaColeta(String pkFkTarefaIdTarefa, String fkItemIdItem) throws SQLException {
        String sql = "INSERT INTO TB_TarefaColeta (PK_FK_Tarefa_IDTarefa, FK_Item_IDItem) VALUES (?, ?)";
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            pstmt.setString(1, pkFkTarefaIdTarefa);
            pstmt.setString(2, fkItemIdItem);
            pstmt.executeUpdate();
        }
    }

    // Função para adicionar dados à tabela TB_Mochila
    private static void addMochila(String pkFkAlunoIdAluno) throws SQLException {
        String sql = "INSERT INTO TB_Mochila (PK_FK_Aluno_IDAluno) VALUES (?)";
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            pstmt.setString(1, pkFkAlunoIdAluno);
            pstmt.executeUpdate();
        }
    }

    // Função para adicionar dados à tabela TB_ItensDaMochila
    private static void addItensDaMochila(String pkFkItemIdItem, String pkFkMochilaIdAluno) throws SQLException {
        String sql = "INSERT INTO TB_ItensDaMochila (PK_FK_Item_IDItem, PK_FK_Mochila_IDAluno) VALUES (?, ?)";
        try (PreparedStatement pstmt = dbConnection.prepareStatement(sql)) {
            pstmt.setString(1, pkFkItemIdItem);
            pstmt.setString(2, pkFkMochilaIdAluno);
            pstmt.executeUpdate();
        }
    }

    // Função principal para simular a inserção de dados
    public static void main(String[] args) {
        // Nessa função, são inseridos dados de exemplo nas tabelas do banco de dados, a conexão com o banco de dados é feita por meio da classe DBConnection
        try {
            // Adicionando Quest
            addQuest("mq001", 1, "Consertando o trem", "Descubra os segredos " +
                    "da floresta.");
            addQuest("mq002", 0, "De volta aos trilhos!", "Descubra os segredos " +
                    "da floresta.");

            // Adicionando Itens
            addItem("item1", "CarcoaldoSelvagem", "Uma criaturinha mágica bastante utilizada para " +
                            "esquentar chaleiras e fazer um bom pãozinho quente!", "Cinza Escuro",
                    "Criatura Mágica Rara", 3500.0, 5.0, 1);
            addItem("item2", "MartyOMartelo", "Uma ferramenta de ferreiro bastante " +
                    "robusta e resistente. Serve para consertar coisas, ou quebrar!", "Marrom e Cinza Fosco", "Equipamento Comum", 4000.0, 2800.0, 1);
            addItem("item3", "WandaAVarinha", "Uma varinha utilizada por bruxas e feiticeiros para" +
                    "realizar feitos incríveis. Serve para criar coisas e ajudar os feridos, desde que saiba como utilizar!", "Preto", "Equipamento Raro", 2560.0, 3600.0, 1);

            // Adicionando múltiplas Tarefas relacionadas à Quest (IDs no formato correto)
            // Tarefas do tipo "TT" (Tarefa de Viagem)
            addTarefa("TT00001", "Encontre um caminho pelo riacho nas coordenadas: LINHA:11 | COLUNA:31", 0, "mq001");

            // Tarefas do tipo "TG" (Tarefa de Coleta)
            addTarefa("TG00001", "Encontre algum objeto que sirva para aquecer a fornalha, nas coordenadas: LINHA:1 | COLUNA:35", 0, "mq001");
            addTarefa("TG00002", "Recupere o martelo que o ferreiro perdeu, próximo de: LINHA:50 | COLUNA:35", 0, "mq001");
            addTarefa("TG00003", "Encontre a varinha que a feiticeira deixou cair perto do martelo do ferreiro", 0, "mq001");

            // Adicionando Tarefa de Viagem relacionada às Tarefas do tipo "TT"
            addTarefaViagem("TT00001", 2480.0, 880.0);

            // Adicionando Tarefa de Coleta (TG) relacionada às Tarefas de Coleta
            addTarefaColeta("TG00001", "item1");
            addTarefaColeta("TG00002", "item2");
            addTarefaColeta("TG00003", "item3");


            addTarefa("TT00002", "Verifique se existe algum tripulante perdido nas coordenadas: LINHA:29 | COLUNA:17", 0, "mq002");
            addTarefa("TT00003", "Verifique se existe algum tripulante nas coordenadas: LINHA:40 | COLUNA:2", 0, "mq002");
            addTarefa("TT00004", "Retorne ao túnel do trem próximo de LINHA:3 | COLUNA:3", 0, "mq002");
            addTarefaViagem("TT00002", 1360.0, 2320.0);
            addTarefaViagem("TT00003", 160.0, 3200.0);
            addTarefaViagem("TT00004", 240.0, 240.0);
            // Adicionando Aluno
            addAluno("aluno1", "João da Silva", 5.0, 150.0, 200.0, 100.0);

            // Adicionando Mochila para o Aluno
            addMochila("aluno1");

            // Adicionando Quest do Aluno
            addQuestsDoAluno("aluno1", "mq001", null);
            addQuestsDoAluno("aluno1", "mq002", null);

            // Adicionando Itens à Mochila
            //addItensDaMochila("item1", "aluno1");
            //addItensDaMochila("item2", "aluno1");

            System.out.println("Dados inseridos com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        }
    }
}
