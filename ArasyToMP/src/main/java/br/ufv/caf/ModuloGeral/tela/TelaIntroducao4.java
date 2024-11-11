package br.ufv.caf.ModuloGeral.tela;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.gui.screens.Screen;

import java.awt.*;

public class TelaIntroducao4 extends Screen implements IUpdateable {
    private static final String NAME = "INTRO4-SCREEN";

    private final String soundFilePath = "src/main/resources/audio/trem-SFX.wav";

    private boolean trocandoTela = false;

    public long lastPlayed;
    private static long tempoInicial;

    private long tempoFinal;

    private final long duracaoMaxima = 17000;

    public TelaIntroducao4(){
        super(NAME);
    }

    @Override
    public void prepare() {
        super.prepare();
        Game.audio().stopMusic();
        Game.loop().attach(this);
    }

    @Override
    public void suspend() {
        super.suspend();
        Game.loop().detach(this);
    }


    @Override
    public void update() {
        if (this.lastPlayed == 0) {
            Game.audio().playSound(soundFilePath);
            this.lastPlayed = Game.loop().getTicks();
        }

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
        g.setColor(Color.BLACK);
        g.fillRect(0,0, Game.window().getWidth(), Game.window().getHeight());
    }

    private void proximaJanela() {

        Game.loop().perform(1500, () -> {
            Game.window().getRenderComponent().fadeIn(1500);
            if (!Game.screens().current().getName().equals("INGAME-SCREEN")) {
                Game.screens().display("INGAME-SCREEN");
                // GameManager.levelTransition();
            }
            // GameManager.levelTransition();
        });
    }
}
