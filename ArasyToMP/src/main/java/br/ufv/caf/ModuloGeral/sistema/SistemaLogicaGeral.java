package br.ufv.caf.ModuloGeral.sistema;

import br.ufv.caf.ModuloAcesso.tela.TelaLogin;
import br.ufv.caf.ModuloGeral.tela.*;
import br.ufv.caf.ModuloInteracao.entidade.EntidadeItem;
import br.ufv.caf.ModuloInteracao.entidade.EntidadePersonagemPrincipal;

import br.ufv.caf.ModuloInteracao.tela.TelaInventario;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.configuration.DisplayMode;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map.Entry;

public final class SistemaLogicaGeral {
    private SistemaLogicaGeral() {}

    public static void inicializarJogo(String[] args) {
        configurarInformacoesDeJogo();

        Game.init(args);
        Game.graphics().setBaseRenderScale(4f);
        Game.config().graphics().setDisplayMode(DisplayMode.WINDOWED);
        // carregando o jogo do arquivo utiLITI
        Resources.load("game.litidata");
        adicionarTelas();
        inicializarLayersDeTelas();
        // seleciona o icone para o jogo (O jogo nao roda sem isso)
        Game.window().setIcon(Resources.images().get("imagens/icon.png"));
        Game.screens().display("LOGIN-SCREEN");

    }

    private static void adicionarTelas() {
        Screen telaDeJogo = new TelaJogo();
        Screen telaDeInventario = new TelaInventario();
        Screen telaDePausa = new TelaPausa();
        Screen telaDeLogin = new TelaLogin();

        Screen telaDeControles = new TelaControles();

        Screen telaDeIntroducao0 = new TelaIntroducao0();
        Screen telaDeIntroducao1 = new TelaIntroducao1();
        Screen telaDeIntroducao2 = new TelaIntroducao2();
        Screen telaDeIntroducao3 = new TelaIntroducao3();
        Screen telaDeIntroducao4 = new TelaIntroducao4();

        Game.screens().add(telaDeLogin);
        Game.screens().add(telaDeControles);
        Game.screens().add(telaDeIntroducao0);
        Game.screens().add(telaDeIntroducao1);
        Game.screens().add(telaDeIntroducao2);
        Game.screens().add(telaDeIntroducao3);
        Game.screens().add(telaDeIntroducao4);
        Game.screens().add(telaDeJogo);
        Game.screens().add(telaDeInventario);
        Game.screens().add(telaDePausa);
    }


    private static void inicializarLayersDeTelas() {

        Input.keyboard().onKeyTyped(KeyEvent.VK_I, e2 -> {
            switch (Game.screens().current().getName()){
                case "BACKPACK-SCREEN":
                    Game.screens().display("INGAME-SCREEN");
                    recuperarGameLoop();
                    break;
                case "INGAME-SCREEN":
                    Game.screens().display("BACKPACK-SCREEN");
                    pausarGameLoop();
                    break;
            }
        });

        Input.keyboard().onKeyTyped(KeyEvent.VK_ESCAPE, e2 -> {
            switch(Game.screens().current().getName()){
                case "BACKPACK-SCREEN", "PAUSE-SCREEN", "TUTORIAL-SCREEN":
                    Game.screens().display("INGAME-SCREEN");
                    recuperarGameLoop();
                    break;


                case "INGAME-SCREEN":
                    Game.screens().display("PAUSE-SCREEN");
                    pausarGameLoop();

                    Input.mouse().onPressed(e -> {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (((TelaPausa)(Game.screens().get("PAUSE-SCREEN"))).mouseSobreBotaoDeSair()) {
                                Game.exit();
                            } else if (((TelaPausa)(Game.screens().get("PAUSE-SCREEN"))).mouseSobreBotaoDeVoltar()) {
                                Game.screens().display("INGAME-SCREEN");
                                recuperarGameLoop();
                            }
                        }
                    });
                    break;
            }
        });

        Input.keyboard().onKeyTyped(KeyEvent.VK_H, e2 -> {
            switch (Game.screens().current().getName()){
                case "INGAME-SCREEN":
                    Game.screens().display("TUTORIAL-SCREEN");
                    pausarGameLoop();
                    break;

                case "TUTORIAL-SCREEN":
                    Game.screens().display("INGAME-SCREEN");
                    recuperarGameLoop();
                    break;
            }
        });
    }

    private static void configurarInformacoesDeJogo() {
        Game.info().setName("Arasy");
        Game.info().setSubTitle("Tales of a Magic Pact");
        Game.info().setVersion("v0.0.1");
        Game.info().setDescription("Jogo");
    }

    public static void spawnarPersonagemNoMundo(Entry<Double,Double> parDeCoordenadas) {
        //todo - mudar no diagrama de classes para static
        Camera camera = new PositionLockCamera(EntidadePersonagemPrincipal.pegaInstancia());
        camera.setClampToMap(true);

        camera.setZoom(camera.getZoom()/5, 0);
        Game.world().setCamera(camera);

        Game.world().onLoaded(e -> {
            // geracao do usuario no spawnpoint "entrada"
            Spawnpoint spawnDeEntrada = new Spawnpoint(parDeCoordenadas.getKey(), parDeCoordenadas.getValue());
            if (spawnDeEntrada != null) {
                spawnDeEntrada.spawn(EntidadePersonagemPrincipal.pegaInstancia());
            }
        });
    }

    public static void spawnarColetaveisNoMundo(ArrayList<EntidadeItem> itensColetaveis) {
        //todo - mudar no diagrama a relação paramétrica que define de onde vem o dado
        for(EntidadeItem item : itensColetaveis) {
            Game.world().environment().add(item);
        }
    }

    private static void pausarGameLoop() {
        Game.loop().setTimeScale(0);
    }

    private static void recuperarGameLoop() {
        Game.loop().setTimeScale(1.0f);
    }
}
