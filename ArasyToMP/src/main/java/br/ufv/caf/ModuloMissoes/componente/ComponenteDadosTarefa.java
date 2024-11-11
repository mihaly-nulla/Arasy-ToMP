package br.ufv.caf.ModuloMissoes.componente;

/*
 * * Classe abstrata ComponenteDadosTarefa que representa os dados de uma tarefa genérica.
 * As classes específicas de dados de tarefa devem estender esta classe.
 *
 * @author Alan Araújo dos Reis - 5096
 * 
 * @since 08/11/2023 - 13:24
 * 
 * @version 1.1
 */

public abstract class ComponenteDadosTarefa {
    private String idTarefa;
    private String descricaoTarefa;

    /*
     * * Construtor da classe ComponenteDadosTarefa.
     *
     * @author Alan Araújo dos Reis - 5096
     * 
     * @param idTarefa String - Identificador da tarefa.
     * 
     * @param tituloTarefa String - Título da tarefa.
     * 
     * @param descricaoTarefa String - Descrição da tarefa.
     * 
     * @since 08/11/2023 - 13:26
     */

    public ComponenteDadosTarefa(String idTarefa, String descricaoTarefa) {
        this.idTarefa = idTarefa;
        this.descricaoTarefa = descricaoTarefa;
    }

    /*
     * * Obtém o identificador da tarefa.
     *
     * @author Alan Araújo dos Reis - 5096
     * 
     * @return String - Identificador da tarefa.
     * 
     * @since 08/11/2023 - 13:29
     */

    public String getIdTarefa() {
        return idTarefa;
    }

    /*
     * * Define o identificador da tarefa.
     *
     * @author Alan Araújo dos Reis - 5096
     * 
     * @param idTarefa String - Novo identificador da tarefa.
     * 
     * @since 08/11/2023 - 13:31
     */

    public void setIdTarefa(String idTarefa) {
        this.idTarefa = idTarefa;
    }

    /*
     * * Obtém a descrição da tarefa.
     *
     * @author Alan Araújo dos Reis - 5096
     * 
     * @return String - Descrição da tarefa.
     * 
     * @since 08/11/2023 - 13:36
     */

    public String getDescricaoTarefa() {
        return descricaoTarefa;
    }

    /*
     * * Define a descrição da tarefa.
     *
     * @author Alan Araújo dos Reis - 5096
     * 
     * @param descricaoTarefa String - Nova descrição da tarefa.
     * 
     * @since 08/11/2023 - 13:38
     */

    public void setDescricaoTarefa(String descricaoTarefa) {
        this.descricaoTarefa = descricaoTarefa;
    }
}
