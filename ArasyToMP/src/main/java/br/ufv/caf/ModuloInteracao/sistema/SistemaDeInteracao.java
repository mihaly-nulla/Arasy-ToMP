package br.ufv.caf.ModuloInteracao.sistema;

import br.ufv.caf.ModuloInteracao.entidade.EntidadeItem;
import br.ufv.caf.ModuloInteracao.entidade.EntidadePersonagemPrincipal;
import br.ufv.caf.ModuloMissoes.sistema.SistemaControladorQuests;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.input.Input;

import java.util.AbstractMap;

public final class SistemaDeInteracao {
    //todo - colocar no diagrama
    private SistemaDeInteracao() {}

    public static void iniciarSitema() {
        Input.keyboard().onKeyTyped(java.awt.event.KeyEvent.VK_E, event -> {
            EntidadePersonagemPrincipal jogador = EntidadePersonagemPrincipal.pegaInstancia();
            Game.world().environment().getEntities(EntidadeItem.class).forEach(item -> {
                if (item.getComponenteDetectorDeColisao().verificaPossibilidadeDeInteracao(
                        new AbstractMap.SimpleEntry<>(jogador.getCoordenadaX(), jogador.getCoordenadaY()), item)) {
                    efetuarColetaDeItem(item, jogador);
                }
            });
        });
    }

    private static void efetuarColetaDeItem(EntidadeItem item, EntidadePersonagemPrincipal jogador) {
        // Remover o item do ambiente
        if(SistemaControladorQuests.pegarInstancia().pegaQuestAtiva() != null) {
            SistemaControladorQuests.pegarInstancia().avaliaQuestAtivaPorItemColetado(item.getNomeItem());
        }
        Game.world().environment().remove(item);
        Game.audio().playSound("audio/SomDeColeta.wav");

        // Guardar o item no jogador
        jogador.guardarItem(item);
    }
}
