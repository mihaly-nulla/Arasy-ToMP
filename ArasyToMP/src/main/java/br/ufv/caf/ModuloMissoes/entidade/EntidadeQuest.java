package br.ufv.caf.ModuloMissoes.entidade;

import br.ufv.caf.ModuloMissoes.componente.ComponenteDadosQuest;
import br.ufv.caf.ModuloMissoes.componente.ComponenteGerenciadorTarefas;

/*
 * * Classe EntidadeQuest que representa uma quest no sistema.
 * * Contém informações sobre o estado da quest, dados da quest, e um gerenciador de tarefas associado.
 * * A classe permite mostrar os dados da quest, avançar seu estado e realizar operações relacionadas à tarefa.
 * * Todo - Atualizar o diagrama de classes com o construtor e o método calculaEstadoNumerico.
 *
 * @author Álvaro Gomes da Silva Neto - 5095
 * 
 * @since 09/11/2023 - 09:08
 * 
 * @version 1.2
 */

public class EntidadeQuest {
    private EstadoQuest estado;
    private ComponenteDadosQuest dadosDaQuest;
    private ComponenteGerenciadorTarefas gerenciadorTarefas;// todo - atualizar no diagrama de classes

    /*
     * * Construtor da classe EntidadeQuest.
     *
     * @author Álvaro Gomes da Silva Neto - 5095
     * 
     * @param estado int - Estado inicial da quest.
     * 
     * @param idQuest String - Identificador da quest.
     * 
     * @param tituloQuest String - Título da quest.
     * 
     * @param descricaoQuest String - Descrição da quest.
     * 
     * @since 09/11/2023 - 09:10
     */

    public EntidadeQuest(int estado, String idQuest,
            String tituloQuest, String descricaoQuest) {
        // todo - inserir o construtor no diagrama de classes

        inicializaEstado(estado);
        this.dadosDaQuest = new ComponenteDadosQuest(idQuest, tituloQuest, descricaoQuest);
    }

    /*
     * * Mostra os dados da quest, incluindo informações sobre as tarefas
     * associadas.
     * 
     * @author Álvaro Gomes da Silva Neto - 5095
     * 
     * @since 09/11/2023 - 09:12
     */

    public String mostrarDadosQuest() {
        return this.estado.mostrarDadosQuest(dadosDaQuest, gerenciadorTarefas.pegaDadosDasTarefas());
    }

    /*
     * * Avança o estado da quest para o próximo estado.
     * 
     * @author Álvaro Gomes da Silva Neto - 5095
     * 
     * @since 09/11/2023 - 09:13
     */

    public void avancarEstado() {
        this.estado = this.estado.avancarEstado();
    }

    /*
     * * Inicializa um estado.
     * 
     * @author Álvaro Gomes da Silva Neto - 5095
     * 
     * @param estado int - Estado da quest.
     * 
     * @since 09/11/2023 - 09:15
     */

    private void inicializaEstado(int estado) {
        switch (estado) {
            case 0:
                this.estado = new QuestPendente();
                break;
            case 1:
                this.estado = new QuestAtiva();
                break;

            case 2:
                this.estado = new QuestCompleta();
                break;

            default:
                this.estado = new QuestPendente();
                break;
        }
    }

    /*
     * * Calcula Estado Numérico.
     * 
     * @author Álvaro Gomes da Silva Neto - 5095
     * 
     * @return int
     * 
     * @since 09/11/2023 - 09:18
     */

    private int calculaEstadoNumerico() {
        int estadoNumerico = -1;

        if (this.estado != null) {
            switch (this.estado.getClass().getSimpleName()) {
                case "QuestPendente":
                    estadoNumerico = 0;
                    break;
                case "QuestAtiva":
                    estadoNumerico = 1;
                    break;
                case "QuestCompleta":
                    estadoNumerico = 2;
                    break;
                default:
                    // Caso nenhuma correspondência seja encontrada.
                    break;
            }
        }
        return estadoNumerico;
    }

    /*
     * * Obtém o estado numérico da quest.
     *
     * @author Álvaro Gomes da Silva Neto - 5095
     * 
     * @return int - Estado numérico da quest.
     * 
     * @since 09/11/2023 - 09:24
     */

    public int getEstado() {
        return calculaEstadoNumerico();
    }

    /*
     * * Obtém o identificador da quest.
     *
     * @author Álvaro Gomes da Silva Neto - 5095
     * 
     * @return String - Identificador da quest.
     * 
     * @since 09/11/2023 - 09:26
     */

    public String getId() {
        return this.dadosDaQuest.getIdQuest();
    }

    public String getTituloDaQuest(){
        return this.dadosDaQuest.getTituloQuest();
    }

    /*
     * * Define o componente gerenciador de tarefas associado à quest.
     * 
     * @author Álvaro Gomes da Silva Neto - 5095
     *
     * @param gerenciadorTarefas ComponenteGerenciadorTarefas - Gerenciador de
     * tarefas associado à quest.
     * 
     * @since 09/11/2023 - 09:28
     */

    public void setComponenteGerenciadorTarefas(ComponenteGerenciadorTarefas gerenciadorTarefas) {
        this.gerenciadorTarefas = gerenciadorTarefas;
    }

    /*
     * * Obtém o gerenciador de tarefas associado à quest.
     *
     * @author Álvaro Gomes da Silva Neto - 5095
     * 
     * @return ComponenteGerenciadorTarefas - Gerenciador de tarefas associado à
     * quest.
     * 
     * @since 09/11/2023 - 09:29
     */

    public ComponenteGerenciadorTarefas getGerenciadorTarefas() {
        return this.gerenciadorTarefas;
    }
}
