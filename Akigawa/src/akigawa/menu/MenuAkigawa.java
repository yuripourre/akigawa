package akigawa.menu;


import akigawa.Defaults;
import akigawa.model.Profile;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.theme.mouse.CustomArrowTheme;


public abstract class MenuAkigawa extends Application {
	
	//Common Resources
	protected String lang;
	
	protected Profile p;	
	
	public MenuAkigawa(int w, int h){
		super(w,h);
		lang = "lang/br/";
	}

	public void load() {
		p = (Profile) session.get(Defaults.PARAM_PROFILE);
		
		if(p == null) {
			p = new Profile();
			session.put(Defaults.PARAM_PROFILE, p);
		}
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
		
}
