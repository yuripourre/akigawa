package akigawa.menu;


import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.layer.ImageLayer;

public class MenuCriarSala extends AkigawaBackMenu{

	private ImageLayer kanji;
	private ImageLayer botaoTitulo;
	private ImageLayer tituloLabel;

	private ImageLayer botaoMaps;
	private ImageLayer botaoMapa[];
	private final int NUMERO_MAPAS = 3;

	public MenuCriarSala(int w, int h){
		super(w,h);
	}
	
	@Override
	public void back(){
		returnApplication = new MenuJogar(w, h);
	}

	public void load(){

		botaoTitulo = new ImageLayer(0,14, "gui/"+"botaocomp.png");
		botaoTitulo.centralizaX(0,w);

		tituloLabel = new ImageLayer(lang+"criarsala.png");
		tituloLabel.centraliza(botaoTitulo);

		kanji = new ImageLayer("gui/kanji.png");
		kanji.centralizaX(0,w);
	
		
		botaoMaps = new ImageLayer("gui/botaomaps.png");
		botaoMaps.centralizaX(0,w);
		botaoMaps.setY(230);
			
		int offset = 26;
		
		botaoMapa = new ImageLayer[NUMERO_MAPAS];
		
		botaoMapa[1] = new ImageLayer("gui/botaomini.png");
		botaoMapa[1].centraliza(botaoMaps);
	
		botaoMapa[0] = new ImageLayer("gui/botaomini.png");
		botaoMapa[0].centralizaY(botaoMaps);
		botaoMapa[0].setX(botaoMapa[1].getX()-botaoMapa[1].getW()-offset);
		
		botaoMapa[2] = new ImageLayer("gui/botaomini.png");
		botaoMapa[2].centralizaY(botaoMaps);
		botaoMapa[2].setX(botaoMapa[1].getX()+botaoMapa[1].getW()+offset);
		
		loading = 100;
		
	}
	
	public void draw(Grafico g){

		kanji.draw(g);
		botaoTitulo.draw(g);
		tituloLabel.draw(g);

		botaoMaps.draw(g);
		
		botaoMapa[0].draw(g);
		botaoMapa[1].draw(g);
		botaoMapa[2].draw(g);
	}

}
