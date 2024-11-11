package br.ufv.caf.ModuloInteracao.entidade;

import br.ufv.caf.ModuloInteracao.componente.ComponenteDadosItem;
import br.ufv.caf.ModuloInteracao.componente.ComponenteDetectorDeColisao;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.graphics.IRenderable;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.util.AbstractMap;
import java.util.Map.Entry;

@CollisionInfo(collisionBoxWidth = 30, collisionBoxHeight = 12, collision = false)
public class EntidadeItem extends Prop implements IRenderable, IUpdateable {
    private final ComponenteDadosItem dadosDoItem;
    private boolean flagContabilizavel;
    private final ComponenteDetectorDeColisao componenteDetectorDeColisao;

    private final BufferedImage teclaEImagem1 = Resources.images().get("src/main/resources/imagens/teclaE-1.png");

    public EntidadeItem(String idItem, String tipoItem, String corItem,
                        String descricaoItem, String nomeItem,
                        double coordenadaXColetada, double coordenadaYColetada,
                        boolean flagContabilizavel) {
        super(coordenadaXColetada, coordenadaYColetada, nomeItem);
        this.dadosDoItem = new ComponenteDadosItem(idItem, tipoItem, corItem, descricaoItem,
                nomeItem, coordenadaXColetada, coordenadaYColetada);
        this.componenteDetectorDeColisao = new ComponenteDetectorDeColisao();
        this.flagContabilizavel = flagContabilizavel;
    }

    public String getNomeItem(){
        return nomeToString();
    }

    public String getNomeReal(){
        return this.dadosDoItem.getNomeItem();
    }

    public String getTipoItem(){
        return this.dadosDoItem.getTipoItem();
    }

    public String getCorItem(){
        return this.dadosDoItem.getCorItem();
    }

    public String getDescricaoItem(){
        return this.dadosDoItem.getDescricaoItem();
    }

    public Entry<Double, Double> getParCoordenadas() {
        return this.dadosDoItem.getParCoordenadas();
    }

    private String nomeToString() {
        StringBuilder frase = new StringBuilder();
        String nomeDoItem = this.dadosDoItem.getNomeItem();

        for (int i = 0; i < nomeDoItem.length(); i++) {
            char currentChar = nomeDoItem.charAt(i);

            if (i > 0 && Character.isUpperCase(currentChar)) {
                frase.append(" ");
            }

            if (i > 0) {
                frase.append(Character.toLowerCase(currentChar));
            } else {
                frase.append(currentChar);
            }
        }
        return frase.toString();
    }

    public ComponenteDetectorDeColisao getComponenteDetectorDeColisao(){
        return this.componenteDetectorDeColisao;
    }

    @Override
    public void render(Graphics2D g) {
        if(this.componenteDetectorDeColisao.verificaPossibilidadeDeInteracao(
                new AbstractMap.SimpleEntry<>
                    (EntidadePersonagemPrincipal.pegaInstancia().getCoordenadaX(), EntidadePersonagemPrincipal.pegaInstancia().getCoordenadaY()),
                this)){

            g.drawImage(teclaEImagem1, 1535, 10, null);
        }
    }

    @Override
    public void update() {}
}
