package br.ufv.caf.ModuloInteracao.componente;

import java.util.AbstractMap;
import java.util.Map.Entry;

public class ComponenteDadosItem {
    private final String idItem;
    private final String tipoItem;

    private final String corItem;

    private final String descricaoItem;

    private final String nomeItem;
    private final double coordenadaXColetada;
    private final double coordenadaYColetada;

    public ComponenteDadosItem(String idItem, String tipoItem, String corItem,
                               String descricaoItem, String nomeItem,
                               double coordenadaXColetada, double coordenadaYColetada){
        this.idItem = idItem;
        this.tipoItem = tipoItem;
        this.corItem = corItem;
        this.descricaoItem = descricaoItem;
        this.nomeItem = nomeItem;
        this.coordenadaXColetada = coordenadaXColetada;
        this.coordenadaYColetada = coordenadaYColetada;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public String getTipoItem() { return tipoItem; }

    public String getCorItem() {
        return corItem;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public Entry<Double, Double> getParCoordenadas(){
        return new AbstractMap.SimpleEntry<>(this.coordenadaXColetada, this.coordenadaYColetada);
    }
}
