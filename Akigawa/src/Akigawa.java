import akigawa.menu.MenuInicial;
import br.com.etyllica.Etyllica;
import br.com.etyllica.core.Configuration;
import br.com.etyllica.core.control.mouse.arrow.CustomMouseArrow;

public class Akigawa extends Etyllica{
	
	private static final long serialVersionUID = 1L;

	public Akigawa() {
		super(752, 423);
	}

	@Override
	public void startGame() {
		
		Configuration.getInstance().getArrowTheme().setNormalArrow(new CustomMouseArrow("cursor.png"));
		
		setMainApplication(new MenuInicial(w,h));
	}
	
	

}