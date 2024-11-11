package br.ufv.caf.ModuloMissoes.entidade;

import java.util.ArrayList;

import br.ufv.caf.ModuloMissoes.componente.ComponenteDadosQuest;
import br.ufv.caf.ModuloMissoes.componente.ComponenteDadosTarefa;

/*
 * * Classe QuestAtiva que implementa o estado ativo de uma quest.
 * * Neste estado, a quest está em andamento e pode ter tarefas associadas a serem realizadas.
 * * Esta classe implementa a interface EstadoQuest.
 * * A cor ANSI é utilizada para destacar informações na saída do console.
 * * O avanço de estado para o próximo estado (QuestCompleta) é implementado.
 *
 * @author Arthur Ataíde de Melo Saraiva - 
 * @since 09/11/2023 - 12:49
 * @version 1.0
 */
public class QuestAtiva implements EstadoQuest {

    /*
     * * Mostra os dados da quest e das tarefas associadas no estado ativo.
     *
     * @author Arthur Ataíde de Melo Saraiva -
     * 
     * @param dadosDaQuest ComponenteDadosQuest - Dados da quest.
     * 
     * @param dadosDasTarefas ArrayList<ComponenteDadosTarefa> - Lista de dados das
     * tarefas associadas à quest.
     * 
     * @since 09/11/2023 - 12:51
     */
    @Override
    public String mostrarDadosQuest(ComponenteDadosQuest dadosDaQuest, ArrayList<ComponenteDadosTarefa> dadosDasTarefas) {
        StringBuilder infomacoesDasTarefas = new StringBuilder();

        for(ComponenteDadosTarefa dadosTarefa : dadosDasTarefas){
            infomacoesDasTarefas.append(dadosTarefa.getDescricaoTarefa());
            infomacoesDasTarefas.append("\n");
        }

        return infomacoesDasTarefas.toString();

    }

    /*
     * * Avança para o próximo estado da quest (QuestCompleta).
     * 
     * @author Arthur Ataíde de Melo Saraiva -
     * 
     * @return EstadoQuest - Próximo estado da quest.
     * 
     * @since 09/11/2023 - 12:53
     */
    @Override
    public EstadoQuest avancarEstado() {
        return new QuestCompleta();
    }
}
