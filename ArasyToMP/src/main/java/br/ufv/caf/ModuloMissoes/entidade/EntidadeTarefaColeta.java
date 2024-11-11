package br.ufv.caf.ModuloMissoes.entidade;

import br.ufv.caf.ModuloMissoes.componente.ComponenteDadosTarefaColeta;
import br.ufv.caf.ModuloInteracao.entidade.EntidadeItem;

/*
 * * Classe EntidadeTarefaColeta que representa uma tarefa específica do tipo coleta.
 * * Esta classe estende EntidadeTarefa e implementa a verificação do estado de conclusão
 * * baseado na quantidade de um item coletado.
 * * A classe utiliza dados específicos de tarefa de coleta (ComponenteDadosTarefaColeta).
 *
 * @author Álvaro Gomes da Silva Neto - 5095
 * 
 * @since 09/11/2023 - 11:16
 * 
 * @version [Versão]
 */

public class EntidadeTarefaColeta extends EntidadeTarefa {

    /*
     * * Construtor da classe EntidadeTarefaColeta.
     *
     * @author Álvaro Gomes da Silva Neto - 5095
     * 
     * @param estado int - Estado inicial da tarefa.
     * 
     * @param dadosTarefaColeta ComponenteDadosTarefaColeta - Dados específicos da
     * tarefa de coleta.
     * 
     * @since 09/11/2023 - 11:18
     */

    public EntidadeTarefaColeta(int estado, String idTarefa, String descricaoTarefa, EntidadeItem itemDaTarefa) {
        super(estado, new ComponenteDadosTarefaColeta(itemDaTarefa, idTarefa, descricaoTarefa));
    }

    /*
     * * Verifica o estado de conclusão da tarefa de coleta com base na quantidade
     * do item coletado.
     *
     * @author Álvaro Gomes da Silva Neto - 5095
     * 
     * @param nomeItem String - Nome do item coletado.
     * 
     * @param quantidadeDoItem int - Quantidade do item coletado.
     * 
     * @since 09/11/2023 - 11:19
     */

    // Falta a entidade Item
    public void verificaEstadoConclusao(String nomeItem) { //todo - Cada tarefa deve possuir um único item associado. Atualizar no diagrama.
        ComponenteDadosTarefaColeta dadosTarefa = (ComponenteDadosTarefaColeta) getDadosDaTarefa();

        String nomeItemDaTarefa = dadosTarefa.getItemDaTarefa().getNomeItem();
        if (nomeItem.equals(nomeItemDaTarefa)) {
            super.avancarEstado();
        }
    }
}
