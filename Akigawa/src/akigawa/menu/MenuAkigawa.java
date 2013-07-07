package akigawa.menu;


import akigawa.model.Profile;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyboardEvent;
import br.com.etyllica.core.event.PointerEvent;



public abstract class MenuAkigawa extends Application{

	//Common Resources
	protected String lang;
	
	protected Profile p;
	
	public MenuAkigawa(int w, int h){
		super(w,h);
		lang = "lang/br/";
		loadApplication = new MenuCarregando(w, h);
		//p = (Profile) sessionMap.get("PROFILE");
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
		

