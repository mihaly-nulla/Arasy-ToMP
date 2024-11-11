package br.ufv.caf.ModuloMissoes.entidade;

import java.util.ArrayList;

import br.ufv.caf.ModuloMissoes.componente.ComponenteDadosQuest;
import br.ufv.caf.ModuloMissoes.componente.ComponenteDadosTarefa;

/* 
 * * Classe QuestCompleta que implementa o estado completo de uma quest.
 * * Neste estado, a quest foi concluída e não há mais tarefas associadas.
 * * Esta classe implementa a interface EstadoQuest.
 * * A cor ANSI é utilizada para destacar informações na saída do console.
 * * O avanço de estado para o próximo estado (permanecendo em QuestCompleta) é implementado.
 *
 * @author Arthur Ataíde de Melo Saraiva -
 * 
 * @since 09/11/2023 - 12:56
 * 
 * @version 1.0
 */
public class QuestCompleta implements EstadoQuest {
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    /*
     * * Mostra os dados da quest e das tarefas associadas no estado completo.
     *
     * @author Arthur Ataíde de Melo Saraiva -
     * 
     * @param dadosDaQuest ComponenteDadosQuest - Dados da quest.
     * 
     * @param dadosDasTarefas ArrayList<ComponenteDadosTarefa> - Lista de dados das
     * tarefas associadas à quest.
     * 
     * @since 09/11/2023 - 12:58
     */
    @Override
    public String mostrarDadosQuest(ComponenteDadosQuest dadosDaQuest, ArrayList<ComponenteDadosTarefa> dadosDasTarefas) {
        StringBuilder infomacoesDasTarefas = new StringBuilder();

        return dadosDaQuest.getTituloQuest();
    }

    /*
     * * Avança para o próximo estado da quest (permanecendo em QuestCompleta).
     *
     * @author Arthur Ataíde de Melo Saraiva -
     * 
     * @return EstadoQuest - Próximo estado da quest (QuestCompleta).
     * 
     * @since 09/11/2023 - 13:00
     */
    @Override
    public EstadoQuest avancarEstado() {
        return this;
    }
}
