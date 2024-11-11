package br.ufv.caf.ModuloAcesso.entidade;

import br.ufv.caf.ModuloAcesso.componente.ComponenteTelaPopup;
import br.ufv.caf.ModuloAcesso.componente.ComponenteTelaLogin;

public class EntidadeLogin {
	private final ComponenteTelaLogin telaLogin;
	private ComponenteTelaPopup telaPopup;

	public EntidadeLogin() {
		telaLogin = new ComponenteTelaLogin();
		mostrarLogin();
	}

	public String fazerLogin() {
		while (!telaLogin.getTentativa()) {
			try {
				Thread.sleep(100); // Eu nao sei porque nao funciona sem isso
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return telaLogin.getUserValue(); // user ID
	}

	private void mostrarLogin() {
		telaLogin.setVisible(true);
	}

	public void exibirErro() {
		telaPopup = new ComponenteTelaPopup("Tela de Erro 2");
		telaPopup.setVisible(true);
		telaLogin.novaTentativa();
	}

	public void sucesso() {
		telaLogin.dispose();
	}
}
