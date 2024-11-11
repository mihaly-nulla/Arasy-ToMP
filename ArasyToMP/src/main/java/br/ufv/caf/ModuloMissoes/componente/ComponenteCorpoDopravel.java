package br.ufv.caf.ModuloMissoes.componente;

import br.ufv.caf.ModuloMissoes.entidade.EntidadeQuest;
import br.ufv.caf.ModuloMissoes.sistema.SistemaControladorQuests;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComponenteCorpoDopravel extends GuiComponent {
    private BufferedImage corpoDopravel;

    public ComponenteCorpoDopravel(double x, double y) {
        super(x, y);
        this.corpoDopravel = Resources.images().get("src/main/resources/imagens/CorpoQuest.png");
    }

    private String[] quebrarTextoEmLinhas(String[] texto, FontMetrics fm, int larguraMaxima) {
        ArrayList<String> linhas = new ArrayList<>();

        for (String linha : texto) {
            StringBuilder linhaAtual = new StringBuilder();
            String[] palavras = linha.split(" ");

            for (String palavra : palavras) {
                if (fm.stringWidth(linhaAtual + palavra) < larguraMaxima) {
                    linhaAtual.append(palavra).append(" ");
                } else {
                    linhas.add(linhaAtual.toString().trim());
                    linhaAtual = new StringBuilder(palavra).append(" ");
                }
            }

            if (!linhaAtual.isEmpty()) {
                linhas.add(linhaAtual.toString().trim());
            }
        }

        return linhas.toArray(new String[0]);
    }


    private void colorirCoordenadas(Graphics2D g, String linha, int posicaoXDoTexto, int posicaoYDoTexto){
        Matcher matcher = Pattern.compile("(LINHA|COLUNA) = \\d+").matcher(linha);
        while (matcher.find()) {
            // Define a cor verde para as partes correspondentes
            g.setColor(new Color(115, 90, 205));
            // Obtém a posição inicial e final da correspondência na linha
            int start = matcher.start();
            int end = matcher.end();
            // Calcula a posição X para a parte correspondente
            int widthBeforeMatch = g.getFontMetrics().stringWidth(linha.substring(0, start));
            // Desenha a parte correspondente em verde
            g.drawString(linha.substring(start, end), posicaoXDoTexto + widthBeforeMatch, posicaoYDoTexto);
        }
    }

    private void desenharTextoTarefa(Graphics2D g, String texto, int posicaoX, int posicaoY, Color cor) {
        g.setColor(cor);
        g.drawString(texto, posicaoX, posicaoY);
        colorirCoordenadas(g, texto, posicaoX, posicaoY);
    }



    @Override
    public void render(Graphics2D g) {
        super.render(g);
        g.drawImage(corpoDopravel, (int) this.getX(), (int) this.getY(), null);

        EntidadeQuest questAtiva = SistemaControladorQuests.pegarInstancia().pegaQuestAtiva();

        if (questAtiva != null) {
            String textoDeExibicao = new String(questAtiva.mostrarDadosQuest().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
            ArrayList<Integer> listaDeEstadosDasTarefas = questAtiva.getGerenciadorTarefas().pegaListaDeEstadosDasTarefas();

            int posicaoXDoTexto = (int) this.getX() + 20;
            int larguraMaximaTexto = corpoDopravel.getWidth() - 40; // Ajuste de margem
            g.setFont(new Font("Verdana", Font.PLAIN, 19));
            FontMetrics fm = g.getFontMetrics();
            String[] linhas = quebrarTextoEmLinhas(textoDeExibicao.replace("\\n", "\n").split("\n"), fm, larguraMaximaTexto);

            int posicaoYDoTexto = (int) this.getY() + 100;

            for (int i = 0; i < listaDeEstadosDasTarefas.size(); i++) {
                if (listaDeEstadosDasTarefas.get(i) == 1) {
                    // Se a tarefa estiver concluída, pule duas linhas
                    if (i * 2 < linhas.length) {
                        linhas[i * 2] = "";
                    }
                    if (i * 2 + 1 < linhas.length) {
                        linhas[i * 2 + 1] = "";
                    }
                }
            }

            for (int j = 0; j < linhas.length; j++) {
                if (!linhas[j].isEmpty()) {
                    g.setColor(Color.BLACK);
                    g.drawString(linhas[j], posicaoXDoTexto, posicaoYDoTexto);
                    colorirCoordenadas(g, linhas[j], posicaoXDoTexto, posicaoYDoTexto);
                    posicaoYDoTexto += 10 + g.getFontMetrics().getHeight();
                }
            }
        }
    }



}
