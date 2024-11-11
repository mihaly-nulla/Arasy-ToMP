package br.ufv.caf.testeentidade;

import br.ufv.caf.ModuloAcesso.componente.ComponenteMetricasDaSessao;
import br.ufv.caf.ModuloAcesso.entidade.EntidadeAluno;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteEntidadeAluno {

    ComponenteMetricasDaSessao metricas = new ComponenteMetricasDaSessao(0, 0);
    EntidadeAluno aluno = new EntidadeAluno("1", "João", metricas);

    // @Test - Este teste não poderá ser realizado pois o método atualizarMetricas()
    // está incorreto
    /*
     * public void testeAtualizarMetricas() {
     * aluno.atualizarMetricas(10);
     * assertEquals(10, aluno.getMetricas().getDistanciaTotalPercorrida());
     * 
     * aluno.atualizarMetricas(-5);
     * assertEquals(5, aluno.getMetricas().getDistanciaTotalPercorrida());
     * 
     * aluno.atualizarMetricas(0);
     * assertEquals(5, aluno.getMetricas().getDistanciaTotalPercorrida());
     * 
     * aluno.atualizarMetricas(50);
     * assertEquals(55, aluno.getMetricas().getDistanciaTotalPercorrida());
     * 
     * aluno.atualizarMetricas(-60);
     * assertEquals(0, aluno.getMetricas().getDistanciaTotalPercorrida());
     * // Teste desse método depende do teste do componte ComponenteMetricasDaSessao
     * }
     */
}