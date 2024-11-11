package br.ufv.caf.ModuloAcesso.componente;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/*
 * Classe ComponenteTelaPopup representa uma tela pop-up exibindo uma mensagem de erro.
 * É uma janela modal com um título, mensagem e um botão "Ok".
 * Extende a classe JFrame e implementa a interface ActionListener para lidar com eventos.
 *
 * @author Gabriel Rodrigues Marques - 5097
 *
 * @since 12/11/2023 - 16:28
 *
 * @version 1.2
 */

public class ComponenteTelaPopup extends JFrame implements ActionListener {
	
	private JButton x;
	private JButton min;
	private JLabel background;
	private int larguraTela;
	private int alturaTela;

	/*
	 * Construtor da classe ComponenteTelaPopup.
	 *
	 * Cria uma tela pop-up para exibir uma mensagem de erro.
	 *
	 * @author Gabriel Rodrigues Marques - 5097
	 *
	 * @param tituloTexto String - Texto a ser exibido como título da tela.
	 *
	 * @param mensagemTexto String - Texto a ser exibido como mensagem de erro.
	 *
	 * @since 12/11/2023 - 16:32
	 */

	public ComponenteTelaPopup(String popup) {
		
		int larguraMonitor = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()*(Toolkit.getDefaultToolkit().getScreenResolution()/96f));
		int alturaMonitor = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()*(Toolkit.getDefaultToolkit().getScreenResolution()/96f));

		larguraTela = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.50);
		alturaTela = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.55);

		int posicaoX = (int) (larguraMonitor * 0.5 - larguraTela / 2);
		int posicaoY = (int) (alturaMonitor * 0.5 - alturaTela / 2);

		setLocation(posicaoX, posicaoY);
		setSize(larguraTela, alturaTela);

		larguraTela *= 0.97;
		
		x = inicializaX(larguraTela, alturaTela);
		min = inicializaMin(larguraTela, alturaTela);
		
        if (!popup.contains("Carregamento")) {
        	add(x);
			add(min);
		}

        setUndecorated(true);
        //setLayout(null);
		  
        File f = new File("src/main/resources/imagens/"+popup+".png"); 
		ImageIcon backgroundImage = (new ImageIcon(f.getAbsolutePath()));
		Image image = backgroundImage.getImage(); // transform it 
		Image newimg = image.getScaledInstance((int)(larguraTela*1.05),(int) (alturaTela*1.02),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		backgroundImage = new ImageIcon(newimg);  // transform it back
		background = new JLabel(backgroundImage);
		
		add(background);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // continua ativo o programa mesmo apos fechar
		setResizable(false);
	}
	
	public void mudarImagem(String popup){
        File f = new File("src/main/resources/imagens/"+popup+".png"); 
		Image image = (new ImageIcon(f.getAbsolutePath())).getImage(); // transform it 
		Image newimg = image.getScaledInstance((int)(larguraTela*1.05),(int) (alturaTela*1.02),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		ImageIcon backgroundImage = new ImageIcon(newimg);  // transform it back
		background.setIcon(backgroundImage);
	}

	/*
     * Lida com eventos de botão.
	 *
	 * @author Gabriel Rodrigues Marques - 5097
	 *
     * Ao ser acionado, fecha a tela pop-up.
     *
     * @param ae ActionEvent - Evento de ação associado ao botão.
	 *
	 * @since 12/11/2023 - 16:37
     */

	public void actionPerformed(ActionEvent ae) {
		if(x.equals(ae.getSource()))
			dispose();
		if(min.equals(ae.getSource()))
			setState(ICONIFIED);
	}

	private JButton inicializaX(int larguraTela, int alturaTela) {
		JButton b1 = new JButton();
		int largura = (int) (larguraTela * 0.08);
		int altura = (int) (alturaTela * 0.1);
		int x = (int) (larguraTela - largura*0.75);
		int y = (int) (0);
		b1.setBounds(x, y, largura, altura);
		b1.setFont(new Font("Courier", Font.PLAIN, (int) (larguraTela * 0.03)));
		b1.addActionListener(this);
		b1.setOpaque(false);
		b1.setContentAreaFilled(false);
		b1.setBorderPainted(false);
		return b1;
	}

	private JButton inicializaMin(int larguraTela, int alturaTela) {
		JButton b1 = new JButton();
		int largura = (int) (larguraTela * 0.08);
		int altura = (int) (alturaTela * 0.1);
		int x = (int) (larguraTela - largura*2.5);
		int y = (int) (0);
		b1.setBounds(x, y, largura, altura);
		b1.setFont(new Font("Courier", Font.PLAIN, (int) (larguraTela * 0.03)));
		b1.addActionListener(this);
		b1.setOpaque(false);
		b1.setContentAreaFilled(false);
		b1.setBorderPainted(false);
		return b1;
	}
}
