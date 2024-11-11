package br.ufv.caf.ModuloAcesso.entidade;

//import java.util.ArrayList;

import br.ufv.caf.ModuloAcesso.componente.ComponenteMetricasDaSessao;
import br.ufv.caf.ModuloInteracao.entidade.EntidadePersonagemPrincipal;

import java.sql.SQLOutput;
import java.util.AbstractMap;
import java.util.Map.Entry;

/* 
   * Classe que representa o Aluno (usuário)
   * 
   * @author Gabriel Rodrigues Marques - 5097
   * 
   * @since 22/10/2023  - 15:00
   * 
   * @version 1.3
*/

public class EntidadeAluno {
    private String idAluno;
    private String nome;
    private ComponenteMetricasDaSessao metricas;

    /*
     * * Método EntidadeAluno, construtor da classe EntidadeAluno
     * 
     * @author Gabriel Rodrigues Marques - 5097
     * 
     * @param idAluno String - Identificador individual de cada Aluno
     * 
     * @param nome String - Nome do Aluno
     * 
     * @param metricas ComponenteMetricas - Metricas da Sessao
     * 
     * @since 22/10/2023 - 15:04
     */

    public EntidadeAluno(String idAluno, String nome, ComponenteMetricasDaSessao metricas) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.metricas = metricas;
    }

    /*
     * Classe atualizadora de Métricas
     * 
     * @author Gabriel Rodrigues Marques - 5097
     * 
     * @since 22/10/2023 - 15:09
     * 
     * @version 1.3
     */

    // todo - melhorar o método
    public void atualizarMetricas() {}

    /*
     * * Método getIdAluno, tem a finalidade de retornar os Identificadores dos
     * Alunos
     * 
     * @author Gabriel Rodrigues Marques - 5097
     * 
     * @return String - Identificador do Aluno
     * 
     * @since 22/10/2023 - 15:09
     */

    public String getIdAluno() {
        return idAluno;
    }

    /*
     * * Método getNome, tem a finalidade de retornar os nomes
     * 
     * @author Gabriel Rodrigues Marques - 5097
     * 
     * @return String - Nome do ALuno
     * 
     * @since 22/10/2023 - 15:09
     */

    public String getNome() {
        return this.nome;
    }

    /*
     * * Método setIdAlunos, tem a finalidade de atribuir/atualizar os
     * Identificadores dos Alunos
     * 
     * @author Gabriel Rodrigues Marques - 5097
     * 
     * @param idAluno String - Identificador individual de cada Aluno
     * 
     * @since 22/10/2023 - 15:11
     */

    public void setIdAluno(String idAluno) {
        this.idAluno = idAluno;
    }

    /*
     * public EntidadePersonagemPrincipal getPersonagem() {
     * return personagem;
     * }
     */

    /*
     * * Método getMetricas, tem a finalidade de retornar as métricas
     * 
     * @author Gabriel Rodrigues Marques - 5097
     * 
     * @return ComponenteMetricasDaSessao - Metricas da Sessão
     * 
     * @since 22/10/2023 - 15:14
     */

    public ComponenteMetricasDaSessao getMetricas() {
        return metricas;
    }

    /*
     * * Método setMetricas, tem a finalidade de atribuir/atualizar as métricas
     * 
     * @author Gabriel Rodrigues Marques - 5097
     * 
     * @param metricas ComponenteMetricasDaSessao - Metricas da Sessao
     * 
     * @since 22/10/2023 - 15:16
     */

    public void setMetricas(ComponenteMetricasDaSessao metricas) {
        this.metricas = metricas;
    }
}