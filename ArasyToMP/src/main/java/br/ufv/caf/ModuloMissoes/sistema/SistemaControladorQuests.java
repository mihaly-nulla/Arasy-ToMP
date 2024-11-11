package br.ufv.caf.ModuloMissoes.sistema;

import br.ufv.caf.ModuloGeral.sistema.SistemaLogicaGeral;
import br.ufv.caf.ModuloInteracao.entidade.EntidadeItem;
import br.ufv.caf.ModuloMissoes.componente.ComponenteDadosTarefa;
import br.ufv.caf.ModuloMissoes.componente.ComponenteDadosTarefaColeta;
import br.ufv.caf.ModuloMissoes.entidade.EntidadeQuest;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class SistemaControladorQuests {
    private static SistemaControladorQuests instanciaDoSistema; //TODO - Atualizar diagrama
    private ArrayList<EntidadeQuest> questsPendentesDaSessao;
    private EntidadeQuest questAtivaDaSessao; //TODO - Trocar no diagrama por uma quest única. O aluno não pode possuir mais de uma quest ativa
    private ArrayList<EntidadeQuest> questsConcluidasDaSessao;
    private static boolean instanciado = false;

    private SistemaControladorQuests(ArrayList<EntidadeQuest> questsDoBanco) {
        this.questsPendentesDaSessao = new ArrayList<>();
        this.questAtivaDaSessao = null;
        this.questsConcluidasDaSessao = new ArrayList<>();
        inicializaQuestsDaSessao(questsDoBanco);
        instanciado = true;
    }

    public static SistemaControladorQuests instanciarSistema(ArrayList<EntidadeQuest> questsDoBanco) {
        if (instanciaDoSistema == null) {
            try {
                instanciaDoSistema = new SistemaControladorQuests(questsDoBanco);
            } catch (Exception e) {
                System.err.println("Erro ao criar instância da EntidadePersonagemPrincipal: "
                        + e.getMessage());
            }
        }
        return instanciaDoSistema;
    }

    public static SistemaControladorQuests pegarInstancia() {
        if (instanciado) {
            return instanciaDoSistema;
        }
        return null;
    }

    private void inicializaQuestsDaSessao(ArrayList<EntidadeQuest> questsDoBanco) {
        for (EntidadeQuest quest : questsDoBanco) {
            switch (quest.getEstado()) {
                case 0:
                    this.questsPendentesDaSessao.add(quest);
                    break;

                case 1:
                    this.questAtivaDaSessao = quest;
                    break;

                default:
                    this.questsConcluidasDaSessao = new ArrayList<>();
                    break;
            }

            System.out.println("Quest adicionada: " + quest.getTituloDaQuest());
        }
    }

    private void avaliaConclusaoDeQuest() {
        if (questAtivaDaSessao.getGerenciadorTarefas().verificaConclusaoDaListaDeTarefas()) {
            if(!questsPendentesDaSessao.isEmpty()){
                this.questAtivaDaSessao.avancarEstado();
                this.questsConcluidasDaSessao.add(questAtivaDaSessao);

                this.questAtivaDaSessao = questsPendentesDaSessao.get(0);
                questsPendentesDaSessao.remove(0);
                this.questAtivaDaSessao.avancarEstado();
                SistemaLogicaGeral.spawnarColetaveisNoMundo(this.pegaItensDaQuestAtiva());
            }
            else{
                this.questAtivaDaSessao.avancarEstado();
                this.questsConcluidasDaSessao.add(questAtivaDaSessao);

                this.questAtivaDaSessao = null;
            }
            Game.audio().playSound("audio/missaoCompleta.wav");
        }
    }

    public void avaliaQuestAtivaPorCoordenadas(double coordenadaX, double coordenadaY) {
        questAtivaDaSessao.getGerenciadorTarefas().verificaConclusaoDeTarefasViagem(coordenadaX,
                coordenadaY);
        avaliaConclusaoDeQuest();
    }

    public void avaliaQuestAtivaPorItemColetado(String nomeItem) {
        questAtivaDaSessao.getGerenciadorTarefas().verificaConclusaoDeTarefasColeta(nomeItem);
        avaliaConclusaoDeQuest();
    }

    public ArrayList<EntidadeItem> pegaItensDaQuestAtiva() {
        if (questAtivaDaSessao != null) {
            ArrayList<EntidadeItem> itensDaQuest = new ArrayList<>();
            for (ComponenteDadosTarefa dadosDaTarefaDaQuest : questAtivaDaSessao.
                    getGerenciadorTarefas().pegaDadosDasTarefas()) {
                if (dadosDaTarefaDaQuest instanceof ComponenteDadosTarefaColeta) {
                    itensDaQuest.add(((ComponenteDadosTarefaColeta) dadosDaTarefaDaQuest).getItemDaTarefa());
                }
            }
            return itensDaQuest;
        }
        return null;
    }

    public EntidadeQuest pegaQuestAtiva() {
        return this.questAtivaDaSessao;
    }
}