package akigawa.menu;


import akigawa.model.Profile;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.theme.mouse.CustomArrowTheme;


public abstract class MenuAkigawa extends Application {
	
	protected CustomArrowTheme arrowTheme;
	//Common Resources
	protected String lang;
	
	protected Profile p;
	
	public MenuAkigawa(int w, int h){
		super(w,h);
		lang = "lang/br/";
		
		//loadApplication = new MenuCarregando(w, h);
		//p = (Profile) sessionMap.get("PROFILE");
	}


	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}


	@Override
	public GUIEvent updateMouse(PointerEvent arg0) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}


	public CustomArrowTheme getArrowTheme() {
		return arrowTheme;
	}

	public void setArrowTheme(CustomArrowTheme arrowTheme) {
		this.arrowTheme = arrowTheme;
	}
		
}
		

