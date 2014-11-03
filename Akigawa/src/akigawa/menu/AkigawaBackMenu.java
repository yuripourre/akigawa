package akigawa.menu;


import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.gui.button.ImageButton;
import br.com.etyllica.layer.StaticLayer;



public abstract class AkigawaBackMenu extends MenuAkigawa {

	protected ImageButton botaoVoltar;
	private StaticLayer voltar;
	private StaticLayer voltarOnm;

	public AkigawaBackMenu(int w, int h) {
		super(w,h);
	}
	
	public void load() {
		super.load();
		
		voltar = new StaticLayer("gui/voltarmini.png");
		voltarOnm = new StaticLayer("gui/voltarminionm.png");

		botaoVoltar = new ImageButton(10, 350, voltar, voltarOnm);
		botaoVoltar.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new Action(this, "back"));
		add(botaoVoltar);
	}

	public void back(){
		nextApplication = new MenuInicial(w, h);
	}

}


