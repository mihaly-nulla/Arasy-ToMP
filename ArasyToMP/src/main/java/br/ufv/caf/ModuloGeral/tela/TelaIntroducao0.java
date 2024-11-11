package br.ufv.caf.ModuloGeral.tela;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TelaIntroducao0 extends Screen implements IUpdateable {
    private static final String NAME = "INTRO0-SCREEN";

    private final BufferedImage imagemDeFundo = Resources.images().get("src/main/resources/imagens/Intro0.png");

    private boolean trocandoTela = false;

    public long lastPlayed;
    private static long tempoInicial;

    private long tempoFinal;

    private final long duracaoMaxima = 12000;

    public TelaIntroducao0(){
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

        if (this.lastPlayed == 0) {
            Game.audio().playMusic("src/main/resources/audio/loadingIntroMusic.wav");
            Game.audio().getMusic().setVolume(0.8f);
            this.lastPlayed = Game.loop().getTicks();
        }

        if (tempoFinal - tempoInicial >= duracaoMaxima && !trocandoTela) {
            this.trocandoTela = true;
            Game.window().getRenderComponent().fadeOut(1500);
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
            if (!Game.screens().current().getName().equals("INTRO1-SCREEN")) {
                TelaIntroducao1.defineTempoInicialDeChamada();
                Game.screens().display("INTRO1-SCREEN");
            }
        });
    }
}

