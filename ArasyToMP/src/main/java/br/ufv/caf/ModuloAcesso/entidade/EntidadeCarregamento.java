package br.ufv.caf.ModuloAcesso.entidade;

import br.ufv.caf.ModuloAcesso.componente.ComponenteTelaPopup;
import de.gurkenlabs.litiengine.environment.GameWorld;

public class EntidadeCarregamento extends Thread {
	private GameWorld e;
	
	public EntidadeCarregamento(GameWorld e) {
		this.e = e;
	}
	
	public void run(){
		ComponenteTelaPopup popup = new ComponenteTelaPopup("Tela de Carregamento 1");
		popup.setVisible(true);
		int num = 0;
		while (e.environment() == null || !e.environment().isLoaded()) {
			try {
				Thread.sleep(100); // Eu nao sei porque nao funciona sem isso
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			popup.mudarImagem("Tela de Carregamento "+num);
			num++;
			if (num == 5)
				num = 1;
		}
		popup.dispose();
	}
}
