package br.ufv.caf.ModuloGeral.tela;

import br.ufv.caf.ModuloMissoes.tela.TelaQuest;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.IRenderable;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;


public class TelaJogo extends GameScreen implements IRenderable, IUpdateable {
	  public static final String NAME = "INGAME-SCREEN";
	  private Screen widgetDeQuest;

	  private final BufferedImage teclaI = Resources.images().get("src/main/resources/imagens/teclaI-1.png");
	  private final BufferedImage teclaEsc = Resources.images().get("src/main/resources/imagens/teclaESC-1.png");
	  private TelaQuest telaDeQuest;
	  public long lastPlayed;
	  public TelaJogo() {
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
		if (this.lastPlayed == 0) {
			Game.audio().playMusic("src/main/resources/audio/ambientSound01.wav");
			Game.audio().getMusic().setVolume(0.7f);
			this.lastPlayed = Game.loop().getTicks();
		}

	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		g.drawImage(teclaI, 1920 - this.teclaI.getWidth() - 10, 70 + this.teclaI.getHeight(), null);
		g.drawImage(teclaEsc, 1920 - this.teclaEsc.getWidth() - 10, 140 + this.teclaI.getHeight(), null);
	}

	@Override
	protected void initializeComponents() {
		super.initializeComponents();
		this.telaDeQuest = new TelaQuest(20, 0, 550, 50);
		this.getComponents().add(telaDeQuest);
	}
}
