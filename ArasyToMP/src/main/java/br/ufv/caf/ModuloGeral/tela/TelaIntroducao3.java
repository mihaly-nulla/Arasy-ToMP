package br.ufv.caf.ModuloGeral.tela;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TelaIntroducao3 extends Screen implements IUpdateable {
    private static final String NAME = "INTRO3-SCREEN";

    private final BufferedImage imagemDeFundo = Resources.images().get("src/main/resources/imagens/Intro3.png");

    private boolean trocandoTela = false;

    public long lastPlayed;
    private static long tempoInicial;

    private long tempoFinal;

    private final long duracaoMaxima = 8000;

    public TelaIntroducao3(){
        super(NAME);

    }

    @Override
    public void prepare() {
        super.prepare();
        Game.loop().attach(this);
    }

    @Override
    public void suspend() {
        super.suspend();
        Game.loop().detach(this);
    }


    @Override
    public void update() {

        tempoFinal = System.currentTimeMillis();

        if (tempoFinal - tempoInicial >= duracaoMaxima && !trocandoTela) {
            this.trocandoTela = true;
            Game.window().getRenderComponent().fadeOut(1500);
            Game.audio().fadeMusic(1500);
            proximaJanela();
        }
    }

    public static void defineTempoInicialDeChamada(){
        tempoInicial = System.currentTimeMillis();
    }

    @Override
    public void render(Graphics2D g){
        g.drawImage(imagemDeFundo, 0, 0, null);
    }

    private void proximaJanela() {
        Game.loop().perform(1500, () -> {
            Game.window().getRenderComponent().fadeIn(1500);
            if (!Game.screens().current().getName().equals("INTRO4-SCREEN")) {
                TelaIntroducao4.defineTempoInicialDeChamada();
                Game.screens().display("INTRO4-SCREEN");
            }
        });
    }
}

