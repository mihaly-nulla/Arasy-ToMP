package br.ufv.caf.ModuloMissoes.entidade;

import java.util.ArrayList;

import br.ufv.caf.ModuloMissoes.componente.ComponenteDadosQuest;
import br.ufv.caf.ModuloMissoes.componente.ComponenteDadosTarefa;

/* 
 * * Classe QuestPendente que implementa o estado pendente de uma quest.
 * * Neste estado, a quest está aguardando para ser iniciada.
 * * Esta classe implementa a interface EstadoQuest.
 * * A cor ANSI é utilizada para destacar informações na saída do console.
 * * O avanço de estado para o próximo estado (QuestAtuva) é implementado.
 *
 * @author Arthur Ataíde de Melo Saraiva -
 * 
 * @since 09/11/2023 - 13:04
 * 
 * @version 1.0
 */
public class QuestPendente implements EstadoQuest {

    /*
     * * Mostra os dados da quest e das tarefas associadas no estado pendente.
     *
     * @author Arthur Ataíde de Melo Saraiva -
     * 
     * @param dadosDaQuest ComponenteDadosQuest - Dados da quest.
     * 
     * @param dadosDasTarefas ArrayList<ComponenteDadosTarefa> - Lista de dados das
     * tarefas associadas à quest.
     * 
     * @since 09/11/2023 - 13:06
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
     * * Avança para o próximo estado da quest (QuestAtiva).
     *
     * @author Arthur Ataíde de Melo Saraiva -
     * 
     * @return EstadoQuest - Próximo estado da quest (QuestAtiva).
     * 
     * @since 09/11/2023 - 13:07
     */
    @Override
    public EstadoQuest avancarEstado() {
        return new QuestAtiva();
    }
}
