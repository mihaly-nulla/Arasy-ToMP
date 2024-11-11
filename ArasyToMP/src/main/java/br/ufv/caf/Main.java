package br.ufv.caf;

import br.ufv.caf.ModuloInteracao.entidade.EntidadeItem;

import br.ufv.caf.ModuloInteracao.entidade.EntidadePersonagemPrincipal;
import br.ufv.caf.ModuloInteracao.sistema.SistemaDeInteracao;
import br.ufv.caf.ModuloAcesso.entidade.EntidadeCarregamento;
import br.ufv.caf.ModuloAcesso.sistema.SistemaControladorDeAcesso;
import br.ufv.caf.ModuloMissoes.componente.ComponenteGerenciadorTarefas;
import br.ufv.caf.ModuloMissoes.entidade.EntidadeQuest;
import br.ufv.caf.ModuloMissoes.entidade.EntidadeTarefa;
import br.ufv.caf.ModuloMissoes.entidade.EntidadeTarefaColeta;
import br.ufv.caf.ModuloMissoes.entidade.EntidadeTarefaViagem;
import br.ufv.caf.ModuloMissoes.sistema.SistemaControladorQuests;
import br.ufv.caf.ModuloGeral.sistema.SistemaLogicaGeral;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;

import java.util.ArrayList;

public class Main {
    private static boolean telaLoginExibida = false;
    public static void main(String[] args) {

        SistemaControladorDeAcesso sistemaCredenciamento = SistemaControladorDeAcesso.pegaInstanciaBD();

        /*
         * Trecho em que aspectos da engine são inicializados. TODAS as classes atreladas
         * com a LITIENGINE devem ser instanciadas a partir desse ponto para garantir o
         * seu funcionamento ideal
         */

		SistemaLogicaGeral.inicializarJogo(args);

        Game.loop().attach(new IUpdateable() {
            @Override
            public void update() {
                if (!telaLoginExibida && (!Game.screens().current().getName().equals("LOGIN-SCREEN") && !Game.screens().current().getName().equals("CONTROLS-SCREEN"))) {
                    // O código a seguir será executado quando a tela deixar de ser LOGIN-SCREEN
                    SistemaControladorQuests sistemaControladorQuests = SistemaControladorQuests.instanciarSistema(
                            sistemaCredenciamento.carregaQuestsDoBanco()
                    );

                    sistemaCredenciamento.inicializaMochilaDoBanco();

                    SistemaLogicaGeral.spawnarPersonagemNoMundo(sistemaCredenciamento.getParCoordenadasDaUltimaSessao());

                    Game.world().loadEnvironment("mapa-testeV03");

                    SistemaDeInteracao.iniciarSitema();

                    SistemaLogicaGeral.spawnarColetaveisNoMundo(sistemaControladorQuests.pegaItensDaQuestAtiva());
                    telaLoginExibida = true;  // Evita que este bloco seja executado repetidamente
                }
            }
        });

	    Game.start();
    }
}