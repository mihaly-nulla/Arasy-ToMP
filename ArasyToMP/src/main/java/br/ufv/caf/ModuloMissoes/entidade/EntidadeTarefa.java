package br.ufv.caf.ModuloMissoes.entidade;

import br.ufv.caf.ModuloMissoes.componente.ComponenteDadosTarefa;

/*
 * * Classe abstrata EntidadeTarefa que representa uma tarefa genérica.
 * As classes específicas de tarefas devem estender esta classe.
 *
 * @author Alan Araújo dos Reis - 5096
 * 
 * @since 08/11/2023 - 13:18
 * 
 * @version 1.2
 */

public abstract class EntidadeTarefa {
    private int estado;
    private ComponenteDadosTarefa dadosDaTarefa;

    /*
     * * Construtor da classe EntidadeTarefa.
     * 
     * @author Alan Araújo dos Reis - 5096
     *
     * @param estado int - Estado inicial da tarefa.
     * 
     * @param dadosDaTarefa ComponenteDadosTarefa - Dados específicos da tarefa.
     * 
     * @since 08/11/2023 - 13:20
     */

    protected EntidadeTarefa(int estado, ComponenteDadosTarefa dadosDaTarefa) {
        this.estado = estado;
        this.dadosDaTarefa = dadosDaTarefa;
    }

    /*
     * * Método que avança o estado da tarefa para conclusão.
     * Este método só avança o estado se o estado atual for 0 (não concluído).
     * 
     * @author Alan Araújo dos Reis - 5096
     *
     * @since 08/11/2023 - 13:22
     */

    protected void avancarEstado() {
        if (this.estado == 0) {
            this.estado = 1;
        }
    }

    /*
     * * Método que obtém o estado atual da tarefa.
     *
     * @author Alan Araújo dos Reis - 5096
     * 
     * @return int - Estado da tarefa (0 - não concluído, 1 - concluído).
     * 
     * @since 08/11/2023 - 13:23
     */

    public int getEstado() {
        return this.estado;
    }

    /*
     * * Método que obtêm os dados específicos da tarefa.
     * 
     * @author Alan Araújo dos Reis - 5096
     *
     * @return ComponenteDadosTarefa - Dados específicos da tarefa.
     * 
     * @since 08/11/2023 - 13:25
     */

    public ComponenteDadosTarefa getDadosDaTarefa() {
        return dadosDaTarefa;
    }
}