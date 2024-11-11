package br.ufv.caf.ModuloInteracao.componente;

import br.ufv.caf.ModuloInteracao.entidade.EntidadeItem;
import br.ufv.caf.ModuloInteracao.entidade.EntidadePersonagemPrincipal;

import java.util.AbstractMap;
import java.util.Map.Entry;

public class ComponenteDetectorDeColisao {
    //todo - Remover componente item gráfico do diagrama e colocar esse no lugar
    private static final int distanciaMinimaParaExibirBotao = 100;

    private boolean verificaProximidadeDoJogador(Entry<Double, Double> coordenadasDoJogador,
                                                Entry<Double,Double> coordenadasDoItem) {
        double distanciaEuclidiana = Math.sqrt(Math.pow((coordenadasDoJogador.getKey() - coordenadasDoItem.getKey()), 2) +
                                    Math.pow((coordenadasDoJogador.getValue() - coordenadasDoItem.getValue()), 2));
        return distanciaEuclidiana <= distanciaMinimaParaExibirBotao;

    }

    public boolean verificaPossibilidadeDeInteracao(Entry<Double, Double> coordenadasDoJogador,
                                                    EntidadeItem itemAssociado) {
        return verificaProximidadeDoJogador(
                new AbstractMap.SimpleEntry<>(
                                            EntidadePersonagemPrincipal.pegaInstancia().getCoordenadaX(),
                                            EntidadePersonagemPrincipal.pegaInstancia().getCoordenadaY()),
                new AbstractMap.SimpleEntry<>(
                                            itemAssociado.getX(),
                                            itemAssociado.getY())
        );

    }
}
