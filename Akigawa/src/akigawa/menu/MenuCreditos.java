package akigawa.menu;

import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.button.ImageButton;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.StaticLayer;


public class MenuCreditos extends AkigawaBackMenu{

	private ImageLayer kanji;
	private ImageLayer botaoTitulo;
	private ImageLayer tituloLabel;
	private ImageLayer borda;

	
	//Musica music = new Musica();
	
	private ImageLayer desenhista;
	private ImageLayer marcello;
	
	private ImageLayer musicaMenu;
	private ImageLayer monster;
	
	private ImageLayer desenvolvedor;
	private ImageLayer yuri;
	

	public MenuCreditos(int w, int h){
		super(w,h);
		botaoVoltar.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new GUIAction(this, "back"));
	}

	public void load(){

		botaoTitulo = new ImageLayer(0,14, "gui/"+"botaocomp.png");
		botaoTitulo.centralizaX(0,w);
		
		borda = new ImageLayer(0,110, "gui/"+"borda.png");
		borda.centralizaX(0,w);

		tituloLabel = new ImageLayer(lang+"creditos.png");
		tituloLabel.centralizaX(0,w);
		tituloLabel.centraliza(botaoTitulo);

		kanji = new ImageLayer("gui/kanji.png");
		kanji.centralizaX(0,w);
	
		desenhista = new ImageLayer(lang+"creditos/desenhista.png");
		desenhista.centralizaX(0,w);
		desenhista.setY(140);
		
		marcello = new ImageLayer(lang+"creditos/marcello.png");
		marcello.centralizaX(0,w);
		marcello.setY(170);
		
		musicaMenu = new ImageLayer(lang+"creditos/musicamenu.png");
        musicaMenu.centralizaX(0,w);
		musicaMenu.setY(235);
		
		monster = new ImageLayer(lang+"creditos/monster.png");
		monster.centralizaX(0,w);
		monster.setY(265);
		
		desenvolvedor = new ImageLayer(lang+"creditos/desenvolvedor.png");
		desenvolvedor.centralizaX(0,w);
		desenvolvedor.setY(330);
		
		yuri = new ImageLayer(lang+"creditos/yuri.png");
		yuri.centralizaX(0,w);
		yuri.setY(360);
		
		loading = 100;
		
	}
	
	public void draw(Grafico g){

		kanji.draw(g);
		botaoTitulo.draw(g);
		tituloLabel.draw(g);
		
		borda.draw(g);

		desenhista.draw(g);
		marcello.draw(g);
		
		musicaMenu.draw(g);
		monster.draw(g);
		
		desenvolvedor.draw(g);
		yuri.draw(g);
		
	}

}
