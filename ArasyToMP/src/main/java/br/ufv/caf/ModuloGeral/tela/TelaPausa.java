package br.ufv.caf.ModuloGeral.tela;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.IRenderable;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TelaPausa extends GameScreen implements IRenderable {
    private static final int LARGURA_BOTAO = 317;
    private static final int ALTURA_BOTAO = 68;

    private BufferedImage imagemBotaoSair;
    private BufferedImage imagemBotaoVoltar;

    public static final String NAME = "PAUSE-SCREEN";
    public TelaPausa() {
        super(NAME);
        // Carregar as imagens dos botões
        imagemBotaoSair = Resources.images().get("src/main/resources/imagens/BotaoSairdoJogo.png");
        imagemBotaoVoltar = Resources.images().get("src/main/resources/imagens/BotaoVoltar.png");
    }

    public boolean mouseSobreBotaoDeSair(){
        double centroDaTelaX = Game.window().getResolution().getWidth() / 2;
        double centroDaTelaY = Game.window().getResolution().getHeight() / 2;

        Point2D coordenadasDoMouse = Input.mouse().getLocation();

        return (coordenadasDoMouse.getX() >= centroDaTelaX - LARGURA_BOTAO/2.0
                && coordenadasDoMouse.getX() <= centroDaTelaX + LARGURA_BOTAO/2.0)
                && (coordenadasDoMouse.getY() >= centroDaTelaY - 100
                &&  coordenadasDoMouse.getY() <= centroDaTelaY - 100 + ALTURA_BOTAO);
    }

    public boolean mouseSobreBotaoDeVoltar(){
        double centroDaTelaX = Game.window().getResolution().getWidth() / 2;
        double centroDaTelaY = Game.window().getResolution().getHeight() / 2;

        Point2D coordenadasDoMouse = Input.mouse().getLocation();

        return (coordenadasDoMouse.getX() >= centroDaTelaX - LARGURA_BOTAO/2.0
                && coordenadasDoMouse.getX() <= centroDaTelaX + LARGURA_BOTAO/2.0)
                && (coordenadasDoMouse.getY() >= centroDaTelaY + 100
                &&  coordenadasDoMouse.getY() <= centroDaTelaY + 100 + ALTURA_BOTAO);
    }


    @Override
    public void render(Graphics2D g) {
        super.render(g);

        int larguraDaTela = (int)Game.window().getResolution().getWidth();
        int alturaDaTela =  (int)Game.window().getResolution().getHeight();
        int centroDaTelaX = larguraDaTela / 2;
        int centroDaTelaY = alturaDaTela / 2;

        g.setColor(new Color(0, 0, 0, 128)); //Cor para deixar um preto translucido
        g.fillRect(0, 0, larguraDaTela, alturaDaTela);

        renderizarBotao(g, imagemBotaoSair, (int) (centroDaTelaX - LARGURA_BOTAO / 2), centroDaTelaY - 100, LARGURA_BOTAO, ALTURA_BOTAO);
        renderizarBotao(g, imagemBotaoVoltar, (int) (centroDaTelaX - LARGURA_BOTAO / 2),  centroDaTelaY + 100, LARGURA_BOTAO, ALTURA_BOTAO);
    }

    private void renderizarBotao(Graphics2D g, BufferedImage imagemDoBotao, int centroX, int centroY, int larguraDoBotao, int alturaDoBotao) {
        g.drawImage(imagemDoBotao, centroX, centroY, larguraDoBotao, alturaDoBotao, null);
    }


    @Override
    public void prepare() {
        super.prepare();
    }

}
