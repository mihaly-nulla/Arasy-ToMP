package br.ufv.caf.ModuloAcesso.tela;

import br.ufv.caf.ModuloAcesso.componente.ComponenteCaixaDeTexto;
import br.ufv.caf.ModuloAcesso.sistema.SistemaControladorDeAcesso;
import br.ufv.caf.ModuloGeral.tela.TelaIntroducao0;
import br.ufv.caf.ModuloGeral.tela.TelaIntroducao4;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.gui.*;
import de.gurkenlabs.litiengine.gui.Menu;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class TelaLogin extends Screen implements IUpdateable {
    private static final String NAME = "LOGIN-SCREEN";
    //private static final BufferedImage BG = Imaging.scale(Resources.images().get("landscape.png"), Game.window().getWidth(), Game.window().getHeight());

    private final double larguraDaTela = Game.window().getResolution().getWidth();
    private final double alturaDaTela =  Game.window().getResolution().getHeight();

    private static final Font fonteDoMenu = Resources.fonts().get("src/main/resources/fontes/Starborn.ttf").deriveFont(32f);

    private final int larguraDaCaixaDeTexto = 700;

    private final int alturaDaCaixaDeTexto = 59;
    private Menu menuPrincipal;
    private ComponenteCaixaDeTexto caixaDeTexto;

    private ImageComponent backgroundImage;
    public long lastPlayed;

    private GuiComponent textField;
    public TelaLogin(){
        super(NAME);
    }

    @Override
    public void prepare() {
        super.prepare();
        Game.loop().attach(this);
        Game.window().getRenderComponent().setBackground(Color.BLACK);
        Game.graphics().setBaseRenderScale(6f * Game.window().getResolutionScale());
        this.menuPrincipal.setForwardMouseEvents(true);
        this.menuPrincipal.getCellComponents().forEach(comp -> {
            comp.setFont(fonteDoMenu);
            //comp.setSpriteSheet(Resources.spritesheets().get("button-background"));
            comp.setTextAntialiasing(true);
            comp.getAppearance().setForeColor(Color.WHITE);
            comp.getAppearanceHovered().setForeColor(new Color(255, 250, 176));
            comp.setForwardMouseEvents(true);
        });

        this.menuPrincipal.setEnabled(true);
        this.menuPrincipal.getCellComponents().get(0).setHovered(true);
    }

    @Override
    public void render(Graphics2D g) {
        //ImageRenderer.render(g, BG, 0, 0);
        super.render(g);
        double scale = 1.4 + 0.15 * Math.sin(Game.time().sinceEnvironmentLoad() / 400.0);
        g.setFont(fonteDoMenu);
        g.setColor(new Color(26, 47, 4));

        RenderingHints originalHints = g.getRenderingHints();

        g.setRenderingHints(originalHints);
    }

    @Override
    public void update() {
        if (this.lastPlayed == 0) {
            Game.audio().playMusic("src/main/resources/audio/mainMenuSong.wav");
            this.lastPlayed = Game.loop().getTicks();
        }

    }

    @Override
    public void suspend() {
        super.suspend();
        Game.loop().detach(this);
    }

    @Override
    protected void initializeComponents() {
        super.initializeComponents();
        final double centerX = Game.window().getResolution().getWidth() / 2.0;
        final double centerY = Game.window().getResolution().getHeight() * 1 / 2;
        final double buttonWidth = 700;

        this.menuPrincipal = new Menu(centerX - buttonWidth / 2, centerY * 1.2, buttonWidth, centerY / 2, "Jogar", "Controles e Comandos", "Sair");

        this.caixaDeTexto = new ComponenteCaixaDeTexto((centerX - larguraDaCaixaDeTexto/2.0) + 20, centerY - alturaDaCaixaDeTexto*1.55, alturaDaCaixaDeTexto, larguraDaCaixaDeTexto);
        // Obtenha as dimensões da tela
        final double screenWidth = Game.window().getResolution().getWidth();
        final double screenHeight = Game.window().getResolution().getHeight();
        BufferedImage backgroundImage = Resources.images().get("src/main/resources/imagens/menu-background.png");
        this.backgroundImage = new ImageComponent(0, 0, screenWidth, screenHeight, backgroundImage);
        //this.backgroundImage = new ImageComponent(0,0, Game.win);

        this.getComponents().add(this.backgroundImage);
        this.getComponents().add(caixaDeTexto);

        Input.keyboard().onKeyReleased(event -> {
            if (this.isSuspended()) {
                return;
            }

            if (event.getKeyCode() == KeyEvent.VK_UP) {
                this.menuPrincipal.setCurrentSelection(Math.max(0, this.menuPrincipal.getCurrentSelection() - 1));
                for (ImageComponent comp : this.menuPrincipal.getCellComponents()) {
                    comp.setHovered(false);
                }
                this.menuPrincipal.getCellComponents().get(this.menuPrincipal.getCurrentSelection()).setHovered(true);
                Game.audio().playSound("src/main/resources/audio/select.wav");
            }

            if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                this.menuPrincipal.setCurrentSelection(Math.min(1, this.menuPrincipal.getCurrentSelection() + 1));
                for (ImageComponent comp : this.menuPrincipal.getCellComponents()) {
                    comp.setHovered(false);
                }
                this.menuPrincipal.getCellComponents().get(this.menuPrincipal.getCurrentSelection()).setHovered(true);
                Game.audio().playSound("src/main/resources/audio/select.wav");
            }

            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                Game.audio().playSound("src/main/resources/audio/select.wav");
                switch (this.menuPrincipal.getCurrentSelection()) {
                    case 0:
                        String idDigitado = this.caixaDeTexto.getText();
                        if(SistemaControladorDeAcesso.pegaInstanciaBD().login(idDigitado)){
                            Game.audio().fadeMusic(1500);
                            this.startGame();
                        }
                        break;
                    case 1:
                        this.trocarParaTelaDeControles();
                        break;
                    case 2:
                        System.exit(0);
                        break;
                }

            }
        });

        Input.mouse().onReleased(event -> {
            if (event.getButton() == MouseEvent.BUTTON1) {
                Game.audio().playSound("src/main/resources/audio/select.wav");
                // Verificar se o mouse está sobre um dos componentes do menuPrincipal
                for (ImageComponent comp : this.menuPrincipal.getCellComponents()) {
                    if (comp.getBoundingBox().contains(event.getPoint()) && comp.isEnabled() && comp.isVisible()) {
                        // Executar ação apenas se o mouse estiver sobre este componente
                        switch (this.menuPrincipal.getCurrentSelection()) {
                            case 0:
                                String idDigitado = this.caixaDeTexto.getText();
                                if(SistemaControladorDeAcesso.pegaInstanciaBD().login(idDigitado)){
                                    Game.audio().fadeMusic(1500);
                                    this.startGame();
                                }
                                break;
                            case 1:
                                this.trocarParaTelaDeControles();
                                break;
                            case 2:
                                System.exit(0);
                                break;
                        }
                        break; // Encerrar o loop, pois já encontramos o componente correspondente
                    }
                }
            }
        });

        this.getComponents().add(this.menuPrincipal);
    }

    private void trocarParaTelaDeControles(){
        Game.window().getRenderComponent().fadeOut(1500);

        Game.loop().perform(1500, () -> {
            Game.window().getRenderComponent().fadeIn(1500);
            TelaIntroducao0.defineTempoInicialDeChamada();
            Game.screens().display("CONTROLS-SCREEN");
            // GameManager.levelTransition();
        });
    }

    private void startGame() {
        Game.window().getRenderComponent().fadeOut(1500);

        Game.loop().perform(1500, () -> {
            Game.window().getRenderComponent().fadeIn(1500);
            TelaIntroducao0.defineTempoInicialDeChamada();
            Game.screens().display("INTRO0-SCREEN");
           // GameManager.levelTransition();
        });
    }

}
