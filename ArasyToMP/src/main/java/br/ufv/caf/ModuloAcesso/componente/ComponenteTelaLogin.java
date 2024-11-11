package br.ufv.caf.ModuloAcesso.componente;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

/*
 * Classe ComponenteTelaLogin representa a interface gráfica de login do aluno.
 * Extende JFrame para criar a tela de login com elementos como rótulo, campo de texto e botão.
 * Implementa ActionListener para lidar com eventos do botão "Entrar".
 *
 * @author Gabriel Rodrigues Marques - 5097
 *
 * @since 12/11/2023 - 15:27
 *
 * @version 1.1
 */

public class ComponenteTelaLogin extends JFrame implements ActionListener {
	private String userValue;
	private boolean tentativa;
	private final JTextField textField;

	/*
	 * Construtor da classe ComponenteTelaLogin.
	 *
	 * Cria a interface gráfica da tela de login do aluno.
	 *
	 * @author Gabriel Rodrigues Marques - 5097
	 *
	 * @since 12/11/2023 - 15:30
	 */

	public ComponenteTelaLogin() {
		// Recebendo a resolução da tela do usuario para fazer os botoes proporcionais
		
		int larguraMonitor = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()*(Toolkit.getDefaultToolkit().getScreenResolution()/96f));
		int alturaMonitor = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()*(Toolkit.getDefaultToolkit().getScreenResolution()/96f));
		
		int larguraTela = (int) (larguraMonitor * 0.50f);
		int alturaTela = (int) (alturaMonitor * 0.55f);

		int posicaoX = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.5f - larguraTela*0.5f);
		int posicaoY = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.5f - alturaTela*0.5f);


		setLocation(posicaoX, posicaoY);
		setSize(larguraTela, alturaTela);

		larguraTela *= 0.97;

		textField = inicializaCampo(larguraTela, alturaTela);

		JButton b1 = inicializaBotao(larguraTela, alturaTela);
		JButton x = inicializaX(larguraTela, alturaTela);
		JButton min = inicializaMin(larguraTela, alturaTela);
		
		add(b1);
		
		add(x);
		
		add(min);
		
		add(textField);
		

        setUndecorated(true);
		  
        File f = new File("src/main/resources/imagens/Tela de Login V02.png"); 
		ImageIcon backgroundImage = (new ImageIcon(f.getAbsolutePath()));
		Image image = backgroundImage.getImage(); // transform it 
		Image newimg = image.getScaledInstance((int)(larguraTela*1.05),(int) (alturaTela*1.05),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		backgroundImage = new ImageIcon(newimg);  // transform it back
		JLabel background = new JLabel(backgroundImage);
		
		add(background);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // mata o programa caso feche a tela de login
		setResizable(false);
	}

	/*
	 * Método actionPerformed para lidar com o evento do botão "Entrar".
	 *
	 * Atualiza o valor do usuário e inicia a tentativa de login.
	 *
	 * @author Gabriel Rodrigues Marques - 5097
	 *
	 * @param ae ActionEvent - O evento do botão "Entrar".
	 *
	 * @since 12/11/2023 - 15:33
	 */

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().hashCode() == 1485891705)
			System.exit(0);
		if(ae.getSource().hashCode() == 1681920301) {
			setState(ICONIFIED);
			return;
		}
		userValue = textField.getText();
		tentarLogin();
	}

	/*
	 * Obtém o valor do usuário inserido no campo de texto.
	 *
	 * @author Gabriel Rodrigues Marques - 5097
	 *
	 * @return String - Valor inserido no campo de texto.
	 *
	 * @since 12/11/2023 - 15:37
	 */

	public String getUserValue() {
		return userValue;
	}

	/*
	 * Inicia uma tentativa de login.
	 *
	 * @author Gabriel Rodrigues Marques - 5097
	 *
	 * @since 12/11/2023 - 15:39
	 */

	private void tentarLogin() {
		tentativa = true;
	}

	/*
	 * Verifica se houve tentativa de login.
	 *
	 * @author Gabriel Rodrigues Marques - 5097
	 *
	 * @return boolean - Verdadeiro se houve tentativa de login, falso caso
	 * contrário.
	 *
	 * @since 12/11/2023 - 15:39
	 */

	public boolean getTentativa() {
		return tentativa;
	}

	/*
	 * Retorna e redefine a tentativa de login.
	 *
	 * @author Gabriel Rodrigues Marques - 5097
	 *
	 * @return boolean - Verdadeiro se houve uma nova tentativa de login, falso caso
	 * contrário.
	 *
	 * @since 12/11/2023 - 15:42
	 */

	public boolean novaTentativa() {
		return tentativa = false;
	}

	/*
	 * Inicializa o botão "Entrar" na tela de login.
	 *
	 * @author Gabriel Rodrigues Marques - 5097
	 *
	 * @param larguraTela int - Largura da tela de login.
	 *
	 * @param alturaTela int - Altura da tela de login.
	 *
	 * @return JButton - Botão "Entrar" da tela de login.
	 *
	 * @since 12/11/2023 - 15:48
	 */

	private JButton inicializaBotao(int larguraTela, int alturaTela) {
		JButton b1 = new JButton();
		int largura = (int) (larguraTela * 0.24);
		int altura = (int) (alturaTela * 0.1);
		int x = (int) (larguraTela * 0.5 - largura / 2);
		int y = (int) (alturaTela * 0.695);
		b1.setBounds(x, y, largura, altura);
		b1.setFont(new Font("Courier", Font.PLAIN, (int) (larguraTela * 0.03)));
		b1.addActionListener(this);
		b1.setOpaque(false);
		b1.setContentAreaFilled(false);
		b1.setBorderPainted(false);
		return b1;
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

	/*
	 * Inicializa o campo de texto para inserção do ID na tela de login.
	 *
	 * @author Gabriel Rodrigues Marques - 5097
	 *
	 * @param larguraTela int - Largura da tela de login.
	 *
	 * @param alturaTela int - Altura da tela de login.
	 *
	 * @return JTextField - Campo de texto para inserção do ID na tela de login.
	 *
	 * @since 12/11/2023 - 15:56
	 */

	private JTextField inicializaCampo(int larguraTela, int alturaTela) {
		JTextField textField = new JTextField();
		int largura = (int) (larguraTela * 0.35);
		int altura = (int) (alturaTela * 0.1);
		int x = (int) (larguraTela * 0.54 - largura / 2);
		int y = (int) (alturaTela * 0.55);
		textField.setForeground((new Color(103,144,100,255)).darker());
		textField.setOpaque(false);
		textField.setBorder(null);
		textField.setFont(new Font("SansSerif", Font.BOLD, (int) (larguraTela * 0.03)));
		textField.setBounds(x, y, largura, altura);
		return textField;
	}
}
