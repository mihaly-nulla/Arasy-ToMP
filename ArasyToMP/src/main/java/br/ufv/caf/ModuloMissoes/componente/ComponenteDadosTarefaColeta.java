package br.ufv.caf.ModuloMissoes.componente;

import br.ufv.caf.ModuloInteracao.entidade.EntidadeItem;

/*
 * * Classe ComponenteDadosTarefaColeta que representa os dados específicos de uma tarefa de coleta.
 * * Estende a classe abstrata ComponenteDadosTarefa e inclui informações sobre o item a ser coletado
 * * e a quantidade necessária para conclusão da tarefa.
 *
 * @author Álvaro Gomes da Silva Neto -
 * 
 * @since 09/11/2023 - 11:23
 * 
 * @version 1.0
 */

public class ComponenteDadosTarefaColeta extends ComponenteDadosTarefa {
    private EntidadeItem itemDaTarefa;

    /*
     * * Construtor da classe ComponenteDadosTarefaColeta.
     *
     * @author Álvaro Gomes da Silva Neto - 5095
     * 
     * @param itemDaTarefa EntidadeItem - Item associado à tarefa de coleta.
     * 
     * @param quantidadeNecessaria int - Quantidade necessária para conclusão da
     * tarefa de coleta.
     * 
     * @param idTarefa String - Identificador da tarefa.
     * 
     * @param tituloTarefa String - Título da tarefa.
     * 
     * @param descricaoTarefa String - Descrição da tarefa.
     * 
     * @since 09/11/2023 - 11:25
     */
    
    public ComponenteDadosTarefaColeta(EntidadeItem itemDaTarefa, String idTarefa, String descricaoTarefa) {
        super(idTarefa, descricaoTarefa);
        this.itemDaTarefa = itemDaTarefa;
    }

    /*
     * * Obtém o item associado à tarefa de coleta.
     *
     * @author Álvaro Gomes da Silva Neto - 5095
     * 
     * @return EntidadeItem - Item associado à tarefa de coleta.
     * 
     * @since 09/11/2023 - 11:27
     */

    public EntidadeItem getItemDaTarefa() {
        return itemDaTarefa;
    }
}
