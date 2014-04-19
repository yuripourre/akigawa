import akigawa.menu.MenuInicial;
import br.com.etyllica.Etyllica;
import br.com.etyllica.context.Application;
import br.com.etyllica.theme.ThemeManager;
import br.com.etyllica.theme.mouse.CustomArrowTheme;

public class Akigawa extends Etyllica {
	
	private static final long serialVersionUID = 1L;

	public Akigawa() {
		super(752, 423);
	}

	@Override
	public Application startApplication() {
		
		CustomArrowTheme akigawaArrowTheme = new CustomArrowTheme("cursor.png");
		
		ThemeManager.getInstance().setArrowTheme(akigawaArrowTheme);
				
		MenuInicial initialMenu = new MenuInicial(w,h); 
		
		initialMenu.setArrowTheme(akigawaArrowTheme);
		
		return initialMenu;
		
	}

}