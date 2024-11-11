package br.ufv.caf.ModuloMissoes.entidade;

import br.ufv.caf.ModuloMissoes.componente.ComponenteDadosTarefaViagem;

/*
 * * Classe EntidadeTarefaViagem que representa uma tarefa específica do tipo viagem.
 *
 * @author Alan Araújo dos Reis - 5096
 * 
 * @since 08/11/2023 - 10:11
 * 
 * @version 1.0
 */

public class EntidadeTarefaViagem extends EntidadeTarefa {
    private final double distanciaMinimaConclusao = 100; //todo - atualizar no diagrama

    /*
     * *Construtor da classe EntidadeTarefaViagem.
     *
     * @author Alan Araújo dos Reis - 5096
     * 
     * @param estado int - Estado inicial da tarefa.
     * 
     * @param dadosTarefa ComponenteDadosTarefaViagem - Dados específicos da tarefa
     * de viagem.
     * 
     * @since 08/11/2023 - 10:13
     */

    public EntidadeTarefaViagem(int estado, String idTarefa, String descricaoTarefa, double coordenadaX,
            double coordenadaY) {
        super(estado, new ComponenteDadosTarefaViagem(idTarefa, descricaoTarefa, coordenadaX, coordenadaY));
    }

    /*
     * * Método que verifica o estado de conclusão da tarefa de viagem.
     *
     * @author Alan Araújo dos Reis - 5096
     * 
     * @param coordenadaX double - Coordenada no eixo X.
     * 
     * @param coordenadaY double - Coordenada no eixo Y.
     * 
     * @since 08/11/2023 - 10:15
     */

    public void verificaEstadoConclusao(double coordenadaX, double coordenadaY) {
        double distanciaEuclidiana = Math.sqrt(
                Math.pow((coordenadaX - ((ComponenteDadosTarefaViagem) getDadosDaTarefa()).getCoordenadaFinalX()), 2) +
                Math.pow((coordenadaY - ((ComponenteDadosTarefaViagem) getDadosDaTarefa()).getCoordenadaFinalY()), 2)
        );

        if (distanciaEuclidiana <= distanciaMinimaConclusao){
            super.avancarEstado();
        }
    }
}
