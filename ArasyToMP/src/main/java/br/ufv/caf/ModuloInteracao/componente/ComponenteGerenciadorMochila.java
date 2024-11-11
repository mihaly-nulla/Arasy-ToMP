package br.ufv.caf.ModuloInteracao.componente;

import br.ufv.caf.ModuloInteracao.entidade.EntidadeItem;
import br.ufv.caf.ModuloInteracao.entidade.EntidadeMochila;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map.Entry;

/*
 * Classe ComponenteGerenciadorMochila responsável por gerenciar a mochila do jogador.
 * Realiza operações como contagem da quantidade de um item, adição de itens à mochila,
 * obtenção dos itens na mochila e retorno do par de nome e quantidade do último item adicionado.
 * A classe utiliza a EntidadeMochila para manipulação dos itens na mochila..
 *
 * @author Gabriel Rodrigues Marques - 5097
 *
 * @since 12/11/2023 - 13:41
 *
 * @version 1.1
 */

public class ComponenteGerenciadorMochila {
    private EntidadeMochila mochila;

    /*
     * Construtor da classe ComponenteGerenciadorMochila.
     *
     * @author Gabriel Rodrigues Marques - 5097
     *
     * @param mochilaDoAluno EntidadeMochila - Instância da mochila do jogador.
     *
     * @since 12/11/2023 - 13:43
     */

    public ComponenteGerenciadorMochila(EntidadeMochila mochilaDoAluno) {
        this.mochila = mochilaDoAluno;
    }

    /*
     * Conta a quantidade de um item específico na mochila.
     *
     * @author Gabriel Rodrigues Marques - 5097
     *
     * @param nomeDoItem String - Nome do item a ser contado.
     *
     * @return int - Quantidade do item na mochila.
     *
     * @since 12/11/2023 - 13:45
     */

    public int contaQuantidadeItem(String nomeDoItem) {
        int contadorDeItem = 0;
        for(EntidadeItem itemArmazenado : this.mochila.getInventario()) {
            if(itemArmazenado.getNomeItem().equals(nomeDoItem)) {
                contadorDeItem++;
            }
        }
        return contadorDeItem;
    }

    /*
     * Adiciona um item à mochila do jogador.
     *
     * @author Gabriel Rodrigues Marques - 5097
     *
     * @param itemColetado EntidadeItem - Item a ser adicionado à mochila.
     *
     * @since 12/11/2023 - 13:47
     */

    public void guardarItem(EntidadeItem itemColetado) {
        this.mochila.adicionaItem(itemColetado);
    }

    /*
     * Imprime todos os itens presentes na mochila.
     *
     * @author Gabriel Rodrigues Marques - 5097
     *
     * @since 12/11/2023 - 13:48
     */

    /*
     * Retorna o par de nome e quantidade do último item adicionado à mochila.
     *
     * @author Gabriel Rodrigues Marques - 5097
     *
     * @return Entry<String, Integer> - Par de nome e quantidade do último item
     * adicionado.
     *
     * @since 12/11/2023 - 13:51
     */

    /*
     * todo - Verificar uma simplificação das tarefas, onde dependem de um único
     * item
     * necessário mudar no diagrama de classes.
     */

    public Entry<String, Integer> retornaParNomeQuantidadeDoNovoItem() {
        //todo - Verificar se é melhor remover a necessidade de uma classe específica para a mochila.

        String nomeDoUltimoItem = this.mochila.getNomeDoItemArmazenado();

        int quantidadeItemDoUltimoItem = contaQuantidadeItem(nomeDoUltimoItem);

        return new AbstractMap.SimpleEntry<>(nomeDoUltimoItem,
                                            quantidadeItemDoUltimoItem);
    }

    /*
     * Obtém todos os itens presentes na mochila.
     *
     * @author Gabriel Rodrigues Marques - 5097
     *
     * @return ArrayList<EntidadeItem> - Lista contendo todos os itens presentes na
     * mochila.
     *
     * @since 12/11/2023 - 13:54
     */

    public ArrayList<EntidadeItem> pegaItensDaMochila() {
        return this.mochila.getInventario();
    }
}
