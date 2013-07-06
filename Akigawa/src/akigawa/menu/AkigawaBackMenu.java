package akigawa.menu;


import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.gui.button.ImageButton;
import br.com.etyllica.layer.StaticLayer;



public abstract class AkigawaBackMenu extends MenuAkigawa{

	protected ImageButton botaoVoltar;
	private StaticLayer voltar;
	private StaticLayer voltarOnm;


	public AkigawaBackMenu(int w, int h){

		super(w,h);

		voltar = new StaticLayer("gui/voltarmini.png");
		voltarOnm = new StaticLayer("gui/voltarminionm.png");

		botaoVoltar = new ImageButton(10,350,voltar,voltarOnm);
		add(botaoVoltar);

	}

	public void back(){
		returnApplication = new MenuInicial(w, h);
	}

}


