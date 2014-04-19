package akigawa.menu;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;


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
	}

	public void load(){

		botaoTitulo = new ImageLayer(0,14, "gui/"+"botaocomp.png");
		botaoTitulo.centralizeX(0,w);
		
		borda = new ImageLayer(0,110, "gui/"+"borda.png");
		borda.centralizeX(0,w);

		tituloLabel = new ImageLayer(lang+"creditos.png");
		tituloLabel.centralizeX(0,w);
		tituloLabel.centralize(botaoTitulo);

		kanji = new ImageLayer("gui/kanji.png");
		kanji.centralizeX(0,w);
	
		desenhista = new ImageLayer(lang+"creditos/desenhista.png");
		desenhista.centralizeX(0,w);
		desenhista.setY(140);
		
		marcello = new ImageLayer(lang+"creditos/marcello.png");
		marcello.centralizeX(0,w);
		marcello.setY(170);
		
		musicaMenu = new ImageLayer(lang+"creditos/musicamenu.png");
        musicaMenu.centralizeX(0,w);
		musicaMenu.setY(235);
		
		monster = new ImageLayer(lang+"creditos/monster.png");
		monster.centralizeX(0,w);
		monster.setY(265);
		
		desenvolvedor = new ImageLayer(lang+"creditos/desenvolvedor.png");
		desenvolvedor.centralizeX(0,w);
		desenvolvedor.setY(330);
		
		yuri = new ImageLayer(lang+"creditos/yuri.png");
		yuri.centralizeX(0,w);
		yuri.setY(360);
		
		loading = 100;
		
	}
	
	public void draw(Graphic g){

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
