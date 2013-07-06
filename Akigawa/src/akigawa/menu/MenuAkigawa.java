package akigawa.menu;


import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;



public abstract class MenuAkigawa extends Application{

	//Aki podem ficar resources em comum
	protected String lang;

	public MenuAkigawa(int w, int h){

		super(w,h);
		lang = "lang/br/";
		
	}


	@Override
	public GUIEvent updateKeyboard(KeyboardEvent arg0) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}


	@Override
	public GUIEvent updateMouse(PointerEvent arg0) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}
	
}
		

