package br.ufv.caf.ModuloAcesso.sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.ufv.caf.ModuloAcesso.entidade.EntidadeAluno;
import br.ufv.caf.ModuloAcesso.entidade.EntidadeLogin;
import br.ufv.caf.ModuloInteracao.entidade.EntidadeItem;
import br.ufv.caf.ModuloInteracao.entidade.EntidadeMochila;
import br.ufv.caf.ModuloInteracao.entidade.EntidadePersonagemPrincipal;
import br.ufv.caf.ModuloMissoes.entidade.EntidadeQuest;
import br.ufv.caf.ModuloMissoes.entidade.EntidadeTarefa;
import br.ufv.caf.ModuloMissoes.entidade.EntidadeTarefaColeta;
import br.ufv.caf.ModuloMissoes.componente.ComponenteGerenciadorTarefas;
import br.ufv.caf.ModuloAcesso.componente.ComponenteMetricasDaSessao;
import br.ufv.caf.ModuloMissoes.entidade.EntidadeTarefaViagem;

import br.ufv.caf.Database.DBConnection;

public class SistemaControladorDeAcesso {
    private EntidadeAluno usuarioAtual;
    private static SistemaControladorDeAcesso instanciaBD;
    private Connection dbConnection;

    private static final String SELECT_ALUNO_BY_ID = "SELECT PK_IDAluno, Nome, TempoUltimaSessao, DistanciaTotalPercorrida "
            +
            "FROM TB_Aluno " +
            "WHERE PK_IDAluno = ?";

    private static final String SELECT_LAST_COORDINATE = "SELECT CoordXUltimaSessao, CoordYUltimaSessao " +
            "FROM TB_Aluno " +
            "WHERE PK_IDAluno = ?";

    private static final String SELECT_ALL_STUDENT_QUESTS = "SELECT quests.PK_IDQuest, quests.Estado AS EstadoQuest, quests.TituloQuest, quests.DescricaoQuest, " +
            "tarefas.PK_IDTarefa, tarefas.DescricaoTarefa, tarefas.Estado AS EstadoTarefa, " +
            "tarefasviagem.CoordenadaFinalX, tarefasviagem.CoordenadaFinalY, " +
            "itens.PK_IDItem, itens.NomeItem, itens.DescricaoItem, itens.CorItem, itens.TipoItem, itens.CoordenadaX, itens.CoordenadaY, itens.Contabilizavel " +
            "FROM TB_Quest quests " +
            "LEFT JOIN TB_Tarefa tarefas ON quests.PK_IDQuest = tarefas.FK_Quest_IDQuest " +
            "LEFT JOIN TB_TarefaViagem tarefasviagem ON tarefas.PK_IDTarefa = tarefasviagem.PK_FK_Tarefa_IDTarefa " +
            "LEFT JOIN TB_TarefaColeta tarefascoleta ON tarefas.PK_IDTarefa = tarefascoleta.PK_FK_Tarefa_IDTarefa " +
            "LEFT JOIN TB_Item itens ON tarefascoleta.FK_Item_IDItem = itens.PK_IDItem " +
            "WHERE quests.PK_IDQuest IN ( " +
            "SELECT PK_FK_Quest_IDQuest " +
            "FROM TB_QuestsDoAluno " +
            "WHERE PK_FK_Aluno_IDAluno = ?" +
            ") " +
            "ORDER BY quests.PK_IDQuest, tarefas.PK_IDTarefa";

    private static final String SELECT_ALL_BACKPACK_ITENS = "SELECT itemDaMochila.PK_IDItem, itemDaMochila.NomeItem, itemDaMochila.DescricaoItem, itemDaMochila.CorItem, " +
            "itemDaMochila.TipoItem, itemDaMochila.CoordenadaX, itemDaMochila.CoordenadaY, itemDaMochila.Contabilizavel " +
            "FROM TB_ItensDaMochila " +
            "RIGHT JOIN TB_Item itemDaMochila ON PK_FK_Item_IDItem = PK_IDItem " +
            "WHERE PK_IDItem IN ( " +
            "    SELECT PK_FK_Item_IDItem " +
            "    FROM TB_ItensDaMochila " +
            "    WHERE PK_FK_Mochila_IDAluno = ? " +
            ")";

    // Constantes abaixo não são necessárias em um banco de dados local SQLite, eram necessárias anteriormente na conexão com o MySQL
    //private final String url;
    //private final String username;
    //private final String password;

    // todo - verificar se a presença desses atributos é coerente para a classe
    public static SistemaControladorDeAcesso pegaInstanciaBD() {
        if (instanciaBD == null)
            instanciaBD = new SistemaControladorDeAcesso();
        return instanciaBD;
    }

    private SistemaControladorDeAcesso() {
        // URL é definida no pacote Database
        //this.url = '';

        // Variáveis abaixo não são necessárias em um banco de dados local SQLite
        //this.username = "root";
        //this.password = "123arasy987msql";

        //Class.forName("com.mysql.cj.jdbc.Driver");

        //connection = DriverManager.getConnection(url, username, password);

        dbConnection = DBConnection.connect();

    }

    public boolean verificaFormatoId(String id) { //todo - voltar para private
        String regex = "^[A-Za-z]{2}\\d{6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);

        return matcher.matches();
    }

    private int verificaTipoDeTarefaDoBanco(String id) {
        if (id.startsWith("TT") && id.length() == 7 &&
                id.substring(2).matches("\\d+")) {
            return 0;
        } else if (id.startsWith("TG") && id.length() == 7 &&
                id.substring(2).matches("\\d+")) {
            return 1;
        }
        return -1;
    }

    public boolean login(String idDigitado) {
        boolean logado = false;

        while (!logado) {
            try (PreparedStatement preparedStatement = dbConnection.prepareStatement(SELECT_ALUNO_BY_ID)) {
                preparedStatement.setString(1, idDigitado); // Define o ID do aluno na consulta
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {

                    ComponenteMetricasDaSessao metricasDoAluno = new ComponenteMetricasDaSessao(
                            resultSet.getDouble("TempoUltimaSessao"),
                            resultSet.getDouble("DistanciaTotalPercorrida"));

                    // todo - possivelmente introduzir um try-catch
                    this.usuarioAtual = new EntidadeAluno(
                            resultSet.getString("PK_IDAluno"),
                            resultSet.getString("Nome"),
                            metricasDoAluno);
                    logado = true;
                } else {
                    logado = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return logado;
    }

    public Entry<Double, Double> getParCoordenadasDaUltimaSessao() {
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(SELECT_LAST_COORDINATE)) {
            preparedStatement.setString(1, this.usuarioAtual.getIdAluno()); // Define o ID do aluno na consulta

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Entry<Double, Double> parCoordenadas =
                        new AbstractMap.SimpleEntry<>(500.0,
                        500.0);
//                Entry<Double, Double> parCoordenadas = new AbstractMap.SimpleEntry<>(resultSet.getDouble("CoordXUltimaSessao"),
//                        resultSet.getDouble("CoordYUltimaSessao"));

                    System.out.println("Coordenadas da última sessão: " + resultSet.getDouble("CoordXUltimaSessao") + " " +
                        resultSet.getDouble("CoordYUltimaSessao"));
                return parCoordenadas;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<EntidadeQuest> carregaQuestsDoBanco() {
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(
                SELECT_ALL_STUDENT_QUESTS
        )) {

            // Define o ID do aluno na consulta
            preparedStatement.setString(1, this.usuarioAtual.getIdAluno());
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<EntidadeQuest> questsDoBanco = new ArrayList<>();

            // Itera sobre os resultados e imprime os dados
//            while (resultSet.next()) {
//                // Aqui você obtém os dados de cada coluna do ResultSet
//                String idQuest = resultSet.getString("PK_IDQuest");
//                String estado = resultSet.getString("Estado");
//                String tituloQuest = resultSet.getString("TituloQuest");
//                String descricaoQuest = resultSet.getString("DescricaoQuest");
//                String idTarefa = resultSet.getString("PK_IDTarefa");
//                String descricaoTarefa = resultSet.getString("DescricaoTarefa");
//
//                // Imprime os dados recuperados
//                System.out.println("Quest ID: " + idQuest);
//                System.out.println("Estado: " + estado);
//                System.out.println("Título: " + tituloQuest);
//                System.out.println("Descrição: " + descricaoQuest);
//                System.out.println("Tarefa ID: " + idTarefa);
//                System.out.println("Descrição Tarefa: " + descricaoTarefa);
//                System.out.println("-------------------------------");
//            }

            while (resultSet.next()) {
                ArrayList<EntidadeTarefa> tarefasDaQuest = new ArrayList<>();
                ComponenteGerenciadorTarefas gerenciadorTarefasCarregadas;

                String questId = resultSet.getString("PK_IDQuest");
                EntidadeQuest questCarregada = new EntidadeQuest(
                        resultSet.getInt("EstadoQuest"),
                        questId,
                        resultSet.getString("TituloQuest"),
                        resultSet.getString("DescricaoQuest"));

                do {
                    String tarefaId = resultSet.getString("PK_IDTarefa");
                    int tipoTarefa = verificaTipoDeTarefaDoBanco(tarefaId);

                    EntidadeTarefa tarefaCarregada = null;
                    switch (tipoTarefa) {
                        case 0:
                            tarefaCarregada = new EntidadeTarefaViagem(
                                    resultSet.getInt("EstadoTarefa"),
                                    tarefaId,
                                    resultSet.getString("DescricaoTarefa"),
                                    resultSet.getDouble("CoordenadaFinalX"),
                                    resultSet.getDouble("CoordenadaFinalY"));
                            System.out.println("Tarefa viagem do banco. Estado : " + tarefaCarregada.getEstado());
                            break;

                        case 1:
                            tarefaCarregada = new EntidadeTarefaColeta(
                                    resultSet.getInt("EstadoTarefa"),
                                    tarefaId,
                                    resultSet.getString("DescricaoTarefa"),
                                    new EntidadeItem(
                                            resultSet.getString("PK_IDItem"),
                                            resultSet.getString("TipoItem"),
                                            resultSet.getString("CorItem"),
                                            resultSet.getString("DescricaoItem"),
                                            resultSet.getString("NomeItem"),
                                            resultSet.getDouble("CoordenadaX"),
                                            resultSet.getDouble("CoordenadaY"),
                                            resultSet.getInt("Contabilizavel") == 1
                                    )
                            );
                            System.out.println("Tarefa viagem do banco. Estado : " + tarefaCarregada.getEstado());
                            break;

                        default:
                            System.err.println("Erro, lançar exceção!!");
                            System.exit(1);
                    }

                    tarefasDaQuest.add(tarefaCarregada);
                } while (resultSet.next() && questId.equals(resultSet.getString("PK_IDQuest")));

                // Movemos a linha do resultado para trás para considerar todas as tarefas da quest
                //resultSet.previous();

                // todo - Integrar os componentes de dados
                gerenciadorTarefasCarregadas = new ComponenteGerenciadorTarefas(tarefasDaQuest);
                questCarregada.setComponenteGerenciadorTarefas(gerenciadorTarefasCarregadas);

                questsDoBanco.add(questCarregada);
            }

            return questsDoBanco;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void inicializaMochilaDoBanco(){
        ArrayList<EntidadeItem> mochilaDoBanco = new ArrayList<>();
        try(PreparedStatement preparedStatement = dbConnection.prepareStatement(SELECT_ALL_BACKPACK_ITENS)) {

            preparedStatement.setString(1, this.usuarioAtual.getIdAluno());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                EntidadeItem itemDaMochila = new EntidadeItem(
                        resultSet.getString("PK_IDItem"),
                        resultSet.getString("TipoItem"),
                        resultSet.getString("CorItem"),
                        resultSet.getString("DescricaoItem"),
                        resultSet.getString("NomeItem"),
                        resultSet.getDouble("CoordenadaX"),
                        resultSet.getDouble("CoordenadaY"),
                        resultSet.getInt("Contabilizavel")==1
                );
                mochilaDoBanco.add(itemDaMochila);
            }

        }catch(SQLException e){
            System.exit(0);
        }
        EntidadePersonagemPrincipal.pegaInstancia().inicializaMochilaDoPersonagem(
                new EntidadeMochila(mochilaDoBanco)
        );
    }

    public EntidadeAluno getUsuarioLogado() {
        return this.usuarioAtual;
    }

//    public static void main(String[] args) {
//        SistemaControladorDeAcesso sistema = SistemaControladorDeAcesso.pegaInstanciaBD();
//        if (sistema.login("aluno1")) {
//            System.out.println("Login realizado com sucesso!");
//            System.out.println(String.format("Usuário logado: %s (ID: %s)", sistema.getUsuarioLogado().getNome(), sistema.getUsuarioLogado().getIdAluno()));
//            System.out.println("Coordenadas da última sessão: " + sistema.getParCoordenadasDaUltimaSessao());
//            System.out.println("Quests do usuário carregadas!\n" + sistema.carregaQuestsDoBanco());
//            //sistema.inicializaMochilaDoBanco();
//        } else {
//            System.out.println("Erro ao realizar login!");
//        }
//    }
}
