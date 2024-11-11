package br.ufv.caf.ModuloMissoes.tela;

import br.ufv.caf.ModuloMissoes.componente.ComponenteCorpoDopravel;
import br.ufv.caf.ModuloMissoes.componente.ComponenteDadosTarefa;
import br.ufv.caf.ModuloMissoes.sistema.SistemaControladorQuests;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TelaQuest extends GuiComponent {
    private BufferedImage cabecalhoDaCaixa;

    private final ComponenteCorpoDopravel corpoDaCaixa;
    private boolean corpoExpandido;

    public TelaQuest(double x, double y, double larguraDaCaixa, double alturaMaximaDaCaixa) {
        super(x, y, larguraDaCaixa, alturaMaximaDaCaixa);
        this.cabecalhoDaCaixa = Resources.images().get("src/main/resources/imagens/BarraSuperiorQuest.png");
        this.corpoDaCaixa = new ComponenteCorpoDopravel(this.getX(), this.getY() + 90);
        this.corpoExpandido = false;

        this.onClicked(e -> {
            this.corpoExpandido = !corpoExpandido;
        });
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        g.drawImage(cabecalhoDaCaixa, (int) this.getX(), (int) this.getY(), null);
        if(SistemaControladorQuests.pegarInstancia().pegaQuestAtiva() != null){
            String tituloDaQuestAtiva = SistemaControladorQuests.pegarInstancia().pegaQuestAtiva().getTituloDaQuest();
            g.setFont(new Font("Verdana", Font.PLAIN, 25));
            g.setColor(Color.BLACK);
            g.drawString(tituloDaQuestAtiva, (int)this.getX() + 20, (int)this.getY() + (int)this.getHeight()/2 + 10);
            if(corpoExpandido) {
                corpoDaCaixa.render(g);
            }
        }
    }

}
