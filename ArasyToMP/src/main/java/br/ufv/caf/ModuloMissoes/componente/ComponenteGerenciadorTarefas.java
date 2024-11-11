package br.ufv.caf.ModuloMissoes.componente;

import java.util.ArrayList;

import br.ufv.caf.ModuloMissoes.entidade.EntidadeTarefa;
import br.ufv.caf.ModuloMissoes.entidade.EntidadeTarefaColeta;
import br.ufv.caf.ModuloMissoes.entidade.EntidadeTarefaViagem;

/*  
* Classe CompoenteGerenciadorTarefas que representa os elementos que compões o Gerenciador de Tarefas
*    
* @author Alan Araújo dos Reis - 5096
*    
* @since 22/10/2023 - 15:29
*    
* @version 1.3
*/

public class ComponenteGerenciadorTarefas {
    private ArrayList<EntidadeTarefa> listaTarefasDaQuest;

    /*
     * * Método ComponenteGerenciadorTarefas, construtor da classe
     * ComponenteGerenciadorTarefas
     * 
     * @author Alan Araújo dos Reis - 5096
     * 
     * @param listaTarefasDaQuest ArrayList<EntidadeTarefa> - Lista de Tarefas de
     * uma Quest
     * 
     * @since 22/10/2023 - 15:32
     */

    public ComponenteGerenciadorTarefas(ArrayList<EntidadeTarefa> listaTarefasDaQuest) {
        this.listaTarefasDaQuest = listaTarefasDaQuest;
    }

    /*
     * * Método verificaConclusaoDeTarefasViagem, tem a finalidade de conferir se as
     * tarefas foram concluídas
     * 
     * @author Alan Araújo dos Reis - 5096
     * 
     * @param coordX double - coordenada no eixo X
     * 
     * @param coordY double - coordenada no eixo Y
     * 
     * @since 22/10/2023 - 15:33
     */

    public void verificaConclusaoDeTarefasViagem(double coordX, double coordY) {
        for (int i = 0; i < listaTarefasDaQuest.size(); i++) {
            if (listaTarefasDaQuest.get(i) instanceof EntidadeTarefaViagem) {
                ((EntidadeTarefaViagem) listaTarefasDaQuest.get(i)).verificaEstadoConclusao(coordX, coordY);
            }
        }
    }

    /*
     * * Método verificaConclusaoDaListaDeTarefas, tem a finalidade de conferir se
     * as tarefas da lista de tarefas foram concluídas
     * 
     * @author Alan Araújo dos Reis - 5096
     * 
     * @return boolean
     * 
     * @since 22/10/2023 - 15:36
     */

    public boolean verificaConclusaoDaListaDeTarefas() {
        for(EntidadeTarefa tarefaDaQuest : listaTarefasDaQuest){
            if(tarefaDaQuest.getEstado() != 1){
                return false;
            }
        }
        return true;
    }

    /*
     * * Método que obtém os dados das tarefas.
     * 
     * @author Alan Araújo dos Reis - 5096
     * 
     * @return ArrayList<ComponenteDadosTarefa> - Lista dos dados das tarefas.
     * 
     * * @since 09/11/2023 - 14:21
     */

    public ArrayList<ComponenteDadosTarefa> pegaDadosDasTarefas() {
        ArrayList<ComponenteDadosTarefa> listaDadosTarefas = new ArrayList<>();
        for (int i = 0; i < listaTarefasDaQuest.size(); i++) { // Melhorar para um for each (for EntidadeTarefa tarefaDaQuest : listaTarefasDaQuest)
            listaDadosTarefas.add(listaTarefasDaQuest.get(i).getDadosDaTarefa());
        }
        return listaDadosTarefas;
    }

    /*
     * * Método que verifica a conclusão de tarefas de coleta.
     *
     * @author Alan Araújo dos Reis - 5096
     * 
     * @param nomeItem String - Nome do item a ser coletado.
     * 
     * @param quantidadeArmazenada int - Quantidade armazenada do item.
     * 
     * @since 09/11/2023 - 14:26
     */

    // Erro vindo da falta da implementação da função verificaEstadoConclusao na
    // classe tarefaColeta
    public void verificaConclusaoDeTarefasColeta(String nomeItem) {
        for(EntidadeTarefa tarefa : listaTarefasDaQuest){
            if(tarefa instanceof EntidadeTarefaColeta){
                ((EntidadeTarefaColeta) tarefa).verificaEstadoConclusao(nomeItem);
            }
        }
    }

    public ArrayList<Integer> pegaListaDeEstadosDasTarefas(){
        ArrayList<Integer> arrayDeEstados = new ArrayList<>();

        for(EntidadeTarefa tarefa : this.listaTarefasDaQuest){
            arrayDeEstados.add(tarefa.getEstado());
        }
        return arrayDeEstados;
    }

    public void adicionarTarefa(EntidadeTarefa tarefaCarregada) {
        this.listaTarefasDaQuest.add(tarefaCarregada);
    }
}
