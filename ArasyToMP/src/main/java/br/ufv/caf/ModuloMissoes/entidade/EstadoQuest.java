package br.ufv.caf.ModuloMissoes.entidade;

import java.util.ArrayList;

import br.ufv.caf.ModuloMissoes.componente.ComponenteDadosQuest;
import br.ufv.caf.ModuloMissoes.componente.ComponenteDadosTarefa;

/*
 * * Interface EstadoQuest que define o contrato para os estados de uma quest.
 * Cada estado deve implementar os métodos para mostrar dados da quest e avançar para o próximo estado.
 * Os estados podem ser utilizados para representar diferentes fases ou estados de conclusão de uma quest.
 *
 * @author Arthur Ataíde de Melo Saraiva - 
 * @since 09/11/2023 - 12:42
 * @version 1.0
 */
public interface EstadoQuest {

    /*
     * * Mostra os dados da quest e das tarefas associadas.
     *
     * @author Arthur Ataíde de Melo Saraiva -
     * 
     * @param dadosDaQuest ComponenteDadosQuest - Dados da quest.
     * 
     * @param dadosDasTarefas ArrayList<ComponenteDadosTarefa> - Lista de dados das
     * tarefas associadas à quest.
     * 
     * @since 09/11/2023 - 12:43
     */
    public String mostrarDadosQuest(ComponenteDadosQuest dadosDaQuest, ArrayList<ComponenteDadosTarefa> dadosDasTarefas); //todo - mudar no diagrama o retorno

    /*
     * * Avança para o próximo estado da quest.
     *
     * @author Arthur Ataíde de Melo Saraiva -
     * 
     * @return EstadoQuest - Próximo estado da quest.
     * 
     * @since 09/11/2023 - 12:44
     */
    public EstadoQuest avancarEstado();
}
