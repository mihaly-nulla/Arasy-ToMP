package br.ufv.caf.ModuloMissoes.componente;

/*
 * Classe ComponenteDadosTarefaViagem que representa os dados específicos de uma tarefa de viagem.
 *
 * @author Alan Araújo dos Reis - 5096
 * @since 08/11/2023 - 09:14
 * @version 1.0
 */

public class ComponenteDadosTarefaViagem extends ComponenteDadosTarefa {
    private double coordenadaFinalX;
    private double coordenadaFinalY;

    /*
     * * Construtor da classe ComponenteDadosTarefaViagem.
     *
     * @author Alan Araújo dos Reis - 5096
     * 
     * @param idTarefa String - Identificador da tarefa.
     *
     * @param tituloTarefa String - Título da tarefa.
     *
     * @param descricaoTarefa String - Descrição da tarefa.
     *
     * @param coordenadaFinalX double - Coordenada final no eixo X da tarefa de
     * viagem.
     *
     * @param coordenadaFinalY double - Coordenada final no eixo Y da tarefa de
     * viagem.
     * 
     * @since 08/11/2023 - 09:16
     */

    public ComponenteDadosTarefaViagem(String idTarefa, String descricaoTarefa,
            double coordenadaFinalX, double coordenadaFinalY) {
        super(idTarefa, descricaoTarefa);
        this.coordenadaFinalX = coordenadaFinalX;
        this.coordenadaFinalY = coordenadaFinalY;
    }

    /*
     * * Método que define a coordenada final no eixo X da tarefa de viagem.
     *
     * @author Alan Araújo dos Reis - 5096
     * 
     * @param coordenadaFinalX double - Coordenada final no eixo X.
     * 
     * @since 08/11/2023 - 09:19
     */

    public void setCoordenadaFinalX(double coordenadaFinalX) {
        this.coordenadaFinalX = coordenadaFinalX;
    }

    /*
     * * Método que define a coordenada final no eixo Y da tarefa de viagem.
     *
     * @author Alan Araújo dos Reis - 5096
     * 
     * @param coordenadaFinalY double - Coordenada final no eixo Y.
     * 
     * @since 08/11/2023 - 09:21
     */

    public void setCoordenadaFinalY(double coordenadaFinalY) {
        this.coordenadaFinalY = coordenadaFinalY;
    }

    /*
     * * Método que obtém a coordenada final no eixo X da tarefa de viagem.
     *
     * @author Alan Araújo dos Reis - 5096
     * 
     * @return double - Coordenada final no eixo X.
     * 
     * @since 08/11/2023 - 09:22
     */

    public double getCoordenadaFinalX() {
        return coordenadaFinalX;
    }

    /*
     * Método que obtém a coordenada final no eixo Y da tarefa de viagem.
     *
     * @author Alan Araújo dos Reis - 5096
     * 
     * @return double - Coordenada final no eixo Y.
     * 
     * @since 08/11/2023 - 09:23
     */

    public double getCoordenadaFinalY() {
        return coordenadaFinalY;
    }
}
