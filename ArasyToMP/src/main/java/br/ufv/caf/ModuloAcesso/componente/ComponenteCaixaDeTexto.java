package br.ufv.caf.ModuloAcesso.componente;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.RenderComponent;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.Align;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class ComponenteCaixaDeTexto extends GuiComponent {
    private StringBuilder texto = new StringBuilder();
    private boolean processandoBackspace = false;
    private int marcadorPosicaoX = 0;
    private boolean mostraCursor = true;
    private long temporizadorCursor = 0;
    private final double posicaoX;
    private final double posicaoY;

    private static final Font fonteCustomizada = Resources.fonts().get("src/main/resources/fontes/Motley.ttf").deriveFont(30f);

    public ComponenteCaixaDeTexto(double posicaoX, double posicaoY, double altura, double largura) {
        super(posicaoX, posicaoY, largura, altura);

        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.setFont(fonteCustomizada);
        this.setText(texto.toString());
        this.setTextAlign(Align.LEFT);
        this.getAppearance().setForeColor(new Color(28, 22, 39));
        this.getAppearanceHovered().setForeColor(new Color(172, 96, 123));

        this.onClicked(e -> {
            texto.setLength(0);
            marcadorPosicaoX = 0;
        });

        Input.keyboard().onKeyTyped(e -> {
            if (this.isVisible() && this.isEnabled()) {
                if (Character.isLetterOrDigit(e.getKeyChar())) {
                    texto.append(e.getKeyChar());
                    // A cada letra digitada move o cursor em 25px
                    marcadorPosicaoX += 17;
                }
            }
        });

        Input.keyboard().onKeyPressed(e -> {
            if (this.isVisible() && this.isEnabled() && e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                if (!processandoBackspace && !texto.isEmpty()) {
                    processandoBackspace = true;
                    texto.deleteCharAt(texto.length() - 1);
                    // A cada letra apagada move o cursor em -25px
                    marcadorPosicaoX -= 17;
                }
            }
        });

        Input.keyboard().onKeyReleased(e -> {
            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                processandoBackspace = false;
            }
        });
    }

    public String getText() {
        return texto.toString();
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        renderCursor(g);
        atualizarCursor();
    }

    private void renderCursor(Graphics2D g) {
        if (mostraCursor) {
            int posicaoCursorX = (int) (this.posicaoX + marcadorPosicaoX);
            int posicaoCursorY = (int) (this.posicaoY + getHeight() / 2) + 10;
            if (posicaoCursorX >= posicaoX + this.getWidth()){
                posicaoCursorX = (int)(posicaoX + this.getWidth());
            }
            g.drawString("|", posicaoCursorX, posicaoCursorY);
        }
    }

    // realiza o efeito de cursor piscando
    private void atualizarCursor() {
        long horaAtual = System.currentTimeMillis();
        if (horaAtual - temporizadorCursor >= 500) {
            temporizadorCursor = horaAtual;
            mostraCursor = !mostraCursor;
        }
    }
}
