package br.ufv.caf.ModuloGeral.tela;

import br.ufv.caf.ModuloAcesso.sistema.SistemaControladorDeAcesso;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.gui.ImageComponent;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class TelaControles extends Screen implements IUpdateable {
    private static final String NAME = "CONTROLS-SCREEN";
    private final BufferedImage imagemDeFundo = Resources.images().get("src/main/resources/imagens/TelaControles.png");
    public TelaControles() {
        super(NAME);

        Input.keyboard().onKeyReleased(event -> {
            if (this.isSuspended()) {
                return;
            }

            if (event.getKeyCode() == KeyEvent.VK_ESCAPE && Game.screens().current().getName().equals(NAME)) {
                this.voltarParaMenu();
            }
        });
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D g){
        g.drawImage(imagemDeFundo, 0, 0, null);
    }

    private void voltarParaMenu(){
        Game.window().getRenderComponent().fadeOut(1500);

        Game.loop().perform(1500, () -> {
            Game.window().getRenderComponent().fadeIn(1500);
            TelaIntroducao0.defineTempoInicialDeChamada();
            Game.screens().display("LOGIN-SCREEN");
            // GameManager.levelTransition();
        });
    }

}
