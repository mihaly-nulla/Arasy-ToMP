package br.ufv.caf.ModuloInteracao.entidade;

import java.util.ArrayList;

public class EntidadeMochila{
    private ArrayList<EntidadeItem> inventario;

    public EntidadeMochila(ArrayList<EntidadeItem> inventarioDoBanco){
        this.inventario = inventarioDoBanco;
    }

    public void adicionaItem(EntidadeItem itemColetado){
        this.inventario.add(itemColetado);
    }

    public String getNomeDoItemArmazenado(){
        return this.inventario.get(this.inventario.size() - 1).getNomeItem();
    }

    public ArrayList<EntidadeItem> getInventario() {
        return this.inventario;
    }

    public String getNomeDoItemArmazenado(int indice){
        return this.inventario.get(indice).getNomeItem();
    }

}
