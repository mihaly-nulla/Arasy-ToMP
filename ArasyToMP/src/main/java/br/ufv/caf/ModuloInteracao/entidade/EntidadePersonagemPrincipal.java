package br.ufv.caf.ModuloInteracao.entidade;

import br.ufv.caf.ModuloInteracao.componente.ComponenteGerenciadorMochila;

import br.ufv.caf.ModuloMissoes.sistema.SistemaControladorQuests;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;

import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;

import de.gurkenlabs.litiengine.graphics.IRenderable;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;

import de.gurkenlabs.litiengine.physics.IMovementController;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

@EntityInfo(width = 64, height = 128)
@MovementInfo(velocity = 350)
@CollisionInfo(collisionBoxWidth = 30, collisionBoxHeight = 30, collision = true)
public class EntidadePersonagemPrincipal extends Creature implements IUpdateable, IRenderable {
    private static EntidadePersonagemPrincipal instancia;
    private final float velocidade;
    private ComponenteGerenciadorMochila mochilaDoPersonagem;
    private KeyboardEntityController<EntidadePersonagemPrincipal> movementController;
	private ArrayList<Integer> right;
	private ArrayList<Integer> left;
	private ArrayList<Integer> up;
	private ArrayList<Integer> down;

    private EntidadePersonagemPrincipal() {
        super("ada");
        
        MovementInfo movementInfoAnnotation = EntidadePersonagemPrincipal.class.getAnnotation(MovementInfo.class);
        if (movementInfoAnnotation != null) {
            velocidade = movementInfoAnnotation.velocity();
        } else {
            // Defina um valor padrão se a anotação não estiver presente
            velocidade = 0; // ou qualquer outro valor padrão que você desejar
        }
        setName("Player");
    }

    public static EntidadePersonagemPrincipal pegaInstancia() {
        if (instancia == null) {
            try {
                instancia = new EntidadePersonagemPrincipal();
            } catch (Exception e) {
                System.err.println("Erro ao criar instância da EntidadePersonagemPrincipal: "
                        + e.getMessage());
            }
        }

        return instancia;
    }

    public final float getVelocidade() {
        return this.velocidade;
    }

    public double getCoordenadaX() {
        return this.getX();
    }

    public double getCoordenadaY() {
        return this.getY();
    }

    public void guardarItem(EntidadeItem itemColetado){
        this.mochilaDoPersonagem.guardarItem(itemColetado);
    }

    @Override
    public void update() {
        if(SistemaControladorQuests.pegarInstancia().pegaQuestAtiva() != null) {
            SistemaControladorQuests.pegarInstancia().avaliaQuestAtivaPorCoordenadas(getCoordenadaX(), getCoordenadaY());
        }
    }
    
    public  void  handlePressedKey(final  KeyEvent  keyCode) {
      if  (movementController.getUpKeys().contains(keyCode.getKeyCode()) || movementController.getDownKeys().contains(keyCode.getKeyCode())) {
    	  movementController.setLeftKeys();
    	  movementController.setRightKeys();
      } 
      if  (movementController.getRightKeys().contains(keyCode.getKeyCode()) || movementController.getLeftKeys().contains(keyCode.getKeyCode())) {
    	  movementController.setUpKeys();
    	  movementController.setDownKeys();
      } 
    }
    
    public  void  handleReleasedKey(final  KeyEvent  keyCode) {
      if  (up.contains(keyCode.getKeyCode()) || down.contains(keyCode.getKeyCode())) {
    	  movementController.setLeftKeys(left);
    	  movementController.setRightKeys(right);
      } 
      if  (right.contains(keyCode.getKeyCode()) || left.contains(keyCode.getKeyCode())) {
    	  movementController.setUpKeys(up);
    	  movementController.setDownKeys(down);
      } 
    }

    @Override
    protected IMovementController createMovementController() {
        movementController = new KeyboardEntityController<>(this);
        
        movementController.addUpKey(KeyEvent.VK_UP);
        movementController.addDownKey(KeyEvent.VK_DOWN);
        movementController.addLeftKey(KeyEvent.VK_LEFT);
        movementController.addRightKey(KeyEvent.VK_RIGHT);
       
    	right = new ArrayList<Integer>();
    	left = new ArrayList<Integer>();
    	up = new ArrayList<Integer>();
    	down = new ArrayList<Integer>();
  	  
  	    up.add(KeyEvent.VK_UP);
  	    up.add(KeyEvent.VK_W);
  	  
  	    down.add(KeyEvent.VK_DOWN);
  	    down.add(KeyEvent.VK_S);
  	  
  	    right.add(KeyEvent.VK_RIGHT);
  	    right.add(KeyEvent.VK_D);
  	  
  	    left.add(KeyEvent.VK_LEFT);
  	    left.add(KeyEvent.VK_A);
        
        Input.keyboard().onKeyPressed(this::handlePressedKey);
        Input.keyboard().onKeyReleased(this::handleReleasedKey);

        return movementController;
    }

    @Override
    public void render(Graphics2D g) {

        double coordenadaXDaCabeca = getCoordenadaX() + 10; //offset para centralizar melhor com a sprite
        double coordenadaYDaCabeca = getCoordenadaY() + getHeight()/5; //offset para ficar em cima da cabeça do personagem

        Point2D pontoDeRenderizacao = Game.world().camera().getViewportLocation(new Point2D.Double(coordenadaXDaCabeca, coordenadaYDaCabeca));
        Font fonte = new Font("Arial", Font.PLAIN, 12);
        g.setFont(fonte);

        int larguraDaTela = 150;
        int alturaDaTela = g.getFontMetrics().getHeight() + 10;

        g.setColor(new Color(0, 0, 0, 128)); //Cor para deixar um preto translucido
        g.fillRect((int) pontoDeRenderizacao.getX() - larguraDaTela / 3, (int) pontoDeRenderizacao.getY() - alturaDaTela, larguraDaTela, alturaDaTela);


        g.setColor(Color.WHITE); //Define a cor do texto das coordenadas
        FontMetrics fontMetrics = g.getFontMetrics(); //Define uma fonte padrão

        String coordenadas = "LINHA: " + (int)this.getCoordenadaY()/80 + " - COLUNA: " + (int)this.getCoordenadaX()/80; //Define a String que é exibida

        int larguraDoTexto = fontMetrics.stringWidth(coordenadas); /*Define os tamanhos da string com base na fonte escolhida*/
        int alturaDoTexto = fontMetrics.getHeight();

        int coordenadaXDaString = (int) pontoDeRenderizacao.getX() - larguraDoTexto / 3;
        int coordenadaYDaString = (int) pontoDeRenderizacao.getY() - alturaDaTela + (alturaDaTela + alturaDoTexto) / 2;
        g.drawString(coordenadas, coordenadaXDaString, coordenadaYDaString);
    }

    public ComponenteGerenciadorMochila getMochilaDoPersonagem(){
        return this.mochilaDoPersonagem;
    }

    public void inicializaMochilaDoPersonagem(EntidadeMochila mochilaDoBanco){
        this.mochilaDoPersonagem = new ComponenteGerenciadorMochila(mochilaDoBanco);
    }


}
