package akigawa.menu;

import akigawa.GerenciadorAkigawa;
import akigawa.fase.Fase;
import jtse.gui.CampoTexto;

public class MenuSwing extends Fase{

	private CampoTexto camp;
	
	public MenuSwing(GerenciadorAkigawa app, int id) {
		super(app, id);
	}
	public void carrega(){
		camp = new CampoTexto(140); 
		camp.setBounds(100,120,200,20);
		app.getGui().adiciona(camp);
		
		carregando = 100;
	}
	public void desenha(){
		app.getGui().desenha();
	}

}
