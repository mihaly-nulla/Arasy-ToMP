package br.ufv.caf.ModuloInteracao.tela;

import br.ufv.caf.ModuloInteracao.entidade.EntidadeItem;
import br.ufv.caf.ModuloInteracao.entidade.EntidadePersonagemPrincipal;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.IRenderable;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.geom.Point2D;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class TelaInventario extends GameScreen implements IRenderable {
    public static final String NAME = "BACKPACK-SCREEN";
    private static final int slotsPorLinha = 3;
    private static final int larguraDoMonitor = (int) Game.window().getResolution().getWidth();
    private static final int alturaDoMonitor = (int) Game.window().getResolution().getHeight();

    private static final int tamanhoDoSlot = 120;

    private static final int espacamentoPixels = (int) (0.25 * 40);

    private final int larguraDoFundo;
    private final int alturaDoFundo;

    private final int larguraDaTelaDoRegistro;
    private final int alturaDaTelaDoRegistro;
    private BufferedImage slotDoInventario;
    private BufferedImage fundoDoInventario;
    private BufferedImage slotDoRegistro;
    private BufferedImage fundoDoRegistro;

    public TelaInventario() {
        super(NAME);
        this.slotDoInventario = Resources.images().get("src/main/resources/imagens/Slot.png");
        this.fundoDoInventario = Resources.images().get("src/main/resources/imagens/FundoInventario.png");
        this.slotDoRegistro = Resources.images().get("src/main/resources/imagens/Slot.png");
        this.fundoDoRegistro = Resources.images().get("src/main/resources/imagens/FundoRegistro.png");
        this.larguraDoFundo = this.fundoDoInventario.getWidth();
        this.alturaDoFundo = this.fundoDoInventario.getHeight();

        this.alturaDaTelaDoRegistro = this.fundoDoRegistro.getHeight();
        this.larguraDaTelaDoRegistro = this.fundoDoRegistro.getWidth();
    }

    private int calculaCentro(int inicioDaJanela, int larguraDoElemento){
        return (inicioDaJanela + larguraDaTelaDoRegistro/2) - larguraDoElemento/2;
    }

    public static String[] quebrarTextoEmLinhas(String texto, int max) {
        // divide a string em um array de substrings, usando o espaço como delimitador
        String[] palavras = texto.split(" ");
        // cria um ArrayList para armazenar as linhas quebradas
        ArrayList<String> linhas = new ArrayList<>();
        // cria um StringBuilder para armazenar a linha atual
        StringBuilder sb = new StringBuilder();
        // cria uma variável para contar o comprimento da linha atual
        int cont = 0;
        // percorre o array de substrings
        for (String palavra : palavras) {
            // verifica se a palavra cabe na linha atual
            if (cont + palavra.length() <= max) {
                // adiciona a palavra e um espaço ao StringBuilder
                sb.append(palavra).append(" ");
                // incrementa o contador com o comprimento da palavra e do espaço
                cont += palavra.length() + 1;
            } else {
                // adiciona a linha atual ao ArrayList
                linhas.add(sb.toString());
                // limpa o StringBuilder
                sb.setLength(0);
                // adiciona a palavra e um espaço ao StringBuilder
                sb.append(palavra).append(" ");
                // reinicia o contador com o comprimento da palavra e do espaço
                cont = palavra.length() + 1;
            }
        }
        // adiciona a última linha ao ArrayList
        linhas.add(sb.toString());
        // converte o ArrayList em uma array de strings e retorna
        return linhas.toArray(new String[0]);
    }

    private void renderizarJanelaDeRegistros(Graphics2D g, int pontoXDeRenderDoInventario, EntidadeItem itemDoSlot){
        int espacamentoEntreTextos = 50;

        String nomeDoItem = itemDoSlot.getNomeItem();
        String tipoDoItem = "Tipo: "+itemDoSlot.getTipoItem();
        String corDoItem = "Coloracao: "+itemDoSlot.getCorItem();
        String descricaoDoItem = "Descricao: "+itemDoSlot.getDescricaoItem();

        String[] linhasDescricao = quebrarTextoEmLinhas(descricaoDoItem, 40);

        int pontoXDeRenderDaJanela = pontoXDeRenderDoInventario - larguraDaTelaDoRegistro + 5;

        g.drawImage(fundoDoRegistro, pontoXDeRenderDaJanela, (alturaDoMonitor/2) - alturaDaTelaDoRegistro/2, larguraDaTelaDoRegistro, alturaDaTelaDoRegistro, null);

        int slotY = (alturaDaTelaDoRegistro/3) + espacamentoPixels*6;

        g.drawImage(Resources.images().get("src/main/resources/icones/"+itemDoSlot.getNomeReal()+".png"),
                calculaCentro(pontoXDeRenderDaJanela, tamanhoDoSlot), slotY, null);

        Font fonte = new Font("Verdana", Font.BOLD, 18);
        g.setFont(fonte);
        g.setColor(Color.WHITE);
        FontMetrics fontMetrics = g.getFontMetrics(); //Define uma fonte padrão

        int alturaDoTexto = fontMetrics.getHeight();
        int larguraDoNome =  fontMetrics.stringWidth(nomeDoItem);

        g.drawString(nomeDoItem, calculaCentro(pontoXDeRenderDaJanela, larguraDoNome), slotY + tamanhoDoSlot + espacamentoPixels*3);

        int larguraDoTipo =  fontMetrics.stringWidth(tipoDoItem);
        int larguraDaCor =  fontMetrics.stringWidth(corDoItem);
        int larguraDaDescricao =  fontMetrics.stringWidth(descricaoDoItem);
        int posYDescricao = slotY + tamanhoDoSlot + 80 + 130;

        g.drawString(tipoDoItem, calculaCentro(pontoXDeRenderDaJanela, larguraDoTipo), slotY + tamanhoDoSlot + 40 + 70);
        g.drawString(corDoItem, calculaCentro(pontoXDeRenderDaJanela, larguraDaCor), slotY + tamanhoDoSlot + 60 + 100);


        for (String linha : linhasDescricao) {
            g.drawString(linha, calculaCentro(pontoXDeRenderDaJanela, fontMetrics.stringWidth(linha)), posYDescricao);
            posYDescricao += fontMetrics.getHeight() + 5;
        }
        //g.drawString(descricaoDoItem, calculaCentro(pontoXDeRenderDaJanela, larguraDaDescricao), slotY + tamanhoDoSlot + 80 + 130);
    }

    private void renderizarItensNaMochila(Graphics2D g, int larguraDoFundo, int deslocamentoHorizontal, int deslocamentoVertical) {
        EntidadePersonagemPrincipal jogador = EntidadePersonagemPrincipal.pegaInstancia();
        ArrayList<EntidadeItem> itensNaMochila = jogador.getMochilaDoPersonagem().pegaItensDaMochila();

        int margemEntreSlots = 20;  // Ajuste este valor conforme necessário

        // Calcular quantos slots cabem em uma linha
        int slotsPorLinha = larguraDoFundo / (tamanhoDoSlot + margemEntreSlots);

        int larguraDoSlot = tamanhoDoSlot;
        int alturaDoSlot = tamanhoDoSlot;

        // Calcular a largura total ocupada pelos slots e margens
        int larguraTotalSlots = slotsPorLinha * (larguraDoSlot + margemEntreSlots);
        int deslocamentoHorizontalDeAlinhamento = (larguraDoFundo - larguraTotalSlots) / 2;

        int linhaAtual = 0;
        int colunaAtual = 0;
        ArrayList<String> nomesJaRenderizados = new ArrayList<>();

        for (int i = 0; i < itensNaMochila.size(); i++) {
            EntidadeItem item = itensNaMochila.get(i);
            if (!nomesJaRenderizados.contains(item.getNomeItem())) {
                // Calcula a posição do slot considerando o deslocamento horizontal e o tamanho do slot
                int slotX = (colunaAtual * (larguraDoSlot + margemEntreSlots)) + (deslocamentoHorizontal + deslocamentoHorizontalDeAlinhamento);

                // Ajusta a posição Y para centralizar a linha no fundo
                int slotY = (linhaAtual * (alturaDoSlot + margemEntreSlots)) + deslocamentoVertical;

                g.drawImage(slotDoInventario, slotX, slotY, larguraDoSlot, alturaDoSlot, null);

                // Renderiza a janela de registros quando o mouse estiver sobre o slot
                if (mouseSobreSlot(slotX, slotY, larguraDoSlot, alturaDoSlot)) {
                    renderizarJanelaDeRegistros(g, deslocamentoHorizontal - espacamentoPixels,item);
                }

                // Atualiza a posição da próxima coluna e linha
                colunaAtual++;
                if (colunaAtual >= slotsPorLinha) {
                    colunaAtual = 0;
                    linhaAtual++;
                }
                nomesJaRenderizados.add(item.getNomeItem());
            }
        }
    }


    private boolean mouseSobreSlot(int slotX, int slotY, int slotWidth, int slotHeight) {
        Point2D posicaoDoMouse = Input.mouse().getLocation();

        int mouseX = (int)posicaoDoMouse.getX();
        int mouseY = (int)posicaoDoMouse.getY();

        return mouseX >= slotX && mouseX <= (slotX + slotWidth) &&
                mouseY >= slotY && mouseY <= (slotY + slotHeight);
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

        g.setColor(Color.black);

        // Posição X do retângulo de fundo (metade direita da tela)
        int posXDoFundo = (larguraDoMonitor / 2); //O ponto x onde a tela começa a ser renderizada é na metade da tela deslocada por 1/6, chegando próximo do canto direito

        // Posição Y do retângulo de fundo (espaçamento superior)
        int posYDoFundo = espacamentoPixels;

        g.drawImage(fundoDoInventario, posXDoFundo, posYDoFundo, larguraDoFundo, alturaDoFundo, null);
        renderizarItensNaMochila(g, larguraDoFundo, posXDoFundo + espacamentoPixels, alturaDoFundo/4);
    }

    @Override
    public void prepare() {
        super.prepare();
    }

}



