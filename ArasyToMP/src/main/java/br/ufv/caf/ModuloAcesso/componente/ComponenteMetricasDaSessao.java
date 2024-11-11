package br.ufv.caf.ModuloAcesso.componente;

import java.util.ArrayList;

/* 
    *Classe que representa os elementos que compões as Métricas da Sessão
    *
    * @author Gabriel Rodrigues Marques - 5097
    * 
    * @since 22/10/2023  - 15:42
    * 
    * @version 1.3
*/

public class ComponenteMetricasDaSessao {
    private double tempoDeSessao;
    private double distanciaTotalPercorrida;

    /*
     * * Método ComponenteMetricasDaSessao, construtor da classe
     * ComponenteMetricasDaSessao
     * 
     * @author Gabriel Rodrigues Marques - 5097
     * 
     * @param tempoDeSessao double - Tempo decorrido
     * 
     * @param distanciaTotalPercorrida double - Distância total percorrida
     * 
     * @since 22/10/2023 - 15:44
     */

    public ComponenteMetricasDaSessao(double tempoDeSessao, double distanciaTotalPercorrida) {
        this.tempoDeSessao = tempoDeSessao;
        this.distanciaTotalPercorrida = distanciaTotalPercorrida;
    }

    /*
     * *Método incrementarTempoDeSessao, tem a finalidade de incrementar
     * do tempo total decorrido na sessão
     * 
     * @author Gabriel Rodrigues Marques - 5097
     * 
     * @since 22/10/2023 - 15:48
     */

    // To-Do - Implementar método
    public void incrementarTempoDeSessao() {
        // Pegar tempo de início de jogo e tempo de fim de jogo
    }

    /*
     * *Método incrementarDistanciaTotalPercorrida, tem a finalidade de incrementar
     * da distância total percorrida pelo personagem
     * 
     * @author Gabriel Rodrigues Marques - 5097
     * 
     * @param pontoInicial ArrayList<Double> - Ponto Inicial do personagem
     * 
     * @param pontoFinal ArrayList<Double> - Ponto Final do personagem
     * 
     * @since 22/10/2023 - 15:51
     */

    public void incrementarDistanciaTotalPercorrida(ArrayList<Double> pontoInicial, ArrayList<Double> pontoFinal) {
        double xa, xb, ya, yb, distancia;
        xa = pontoInicial.get(0);
        xb = pontoFinal.get(0);
        ya = pontoInicial.get(1);
        yb = pontoFinal.get(1);
        distancia = Math.sqrt(Math.pow((xb - xa), 2) + Math.pow((yb - ya), 2));
        this.distanciaTotalPercorrida += distancia;
    }

    /*
     * * Método getTempoDeSessao, tem a finalidade de retornar o tempo de sessão
     * 
     * @author Gabriel Rodrigues Marques - 5097
     * 
     * @return double - Tempo de Sessão
     * 
     * @since 22/10/2023 - 15:58
     */

    public double getTempoDeSessao() {
        return tempoDeSessao;
    }

    /*
     * * Método setTempoDeSessao, tem a finalidade de atribuir/atualizar o tempo de
     * sessão
     * 
     * @author Gabriel Rodrigues Marques - 5097
     * 
     * @param tempoDeSessao double - Tempo decorrido
     * 
     * @since 22/10/2023 - 16:01
     */

    public void setTempoDeSessao(double tempoDeSessao) {
        this.tempoDeSessao = tempoDeSessao;
    }

    /*
     * * Método getDistanciaTotalPercorrida, tem a finalidade de retornar a
     * distância total percorrida
     * 
     * @author Gabriel Rodrigues Marques - 5097
     * 
     * @return double - Distancia Total Percorrida
     * 
     * @since 22/10/2023 - 16:04
     */

    public double getDistanciaTotalPercorrida() {
        return distanciaTotalPercorrida;
    }

    /*
     * * Método setDistanciaTotalPercorrida, tem a finalidade de atribuir/atualizar
     * a distância total percorrida
     * 
     * @author Gabriel Rodrigues Marques - 5097
     * 
     * @param distanciaTotalPercorrida double - Distância total percorrida
     * 
     * @since 22/10/2023 - 16:06
     */

    public void setDistanciaTotalPercorrida(double distanciaTotalPercorrida) {
        this.distanciaTotalPercorrida = distanciaTotalPercorrida;
    }
}