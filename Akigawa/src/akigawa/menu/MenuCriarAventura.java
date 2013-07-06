package akigawa.menu;

import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.layer.ImageLayer;



public class MenuCriarAventura extends AkigawaBackMenu{

	//id = 11

	private ImageLayer kanji;
	private ImageLayer botaoTitulo;
	private ImageLayer tituloLabel;


	
	private ImageLayer botaoMaps;
	
	//Labels
	/*
	private Camada criarSalaLabel;
	private Camada procurarSalaLabel;
	private Camada perfilLabel;
	*/

	//CamadaTexto t;
	//Musica music = new Musica();

	public MenuCriarAventura(int w, int h){

		super(w,h);


	}

	public void load(){

		botaoTitulo = new ImageLayer(0,18,"gui/"+"botaocomp.png");
		botaoTitulo.centralizaX(0,w);

		tituloLabel = new ImageLayer(lang+"criarsala.png");
		tituloLabel.centraliza(botaoTitulo);

		kanji = new ImageLayer("gui/kanji.png");
		kanji.centralizaX(0,w);
		kanji.setY(16);

		botaoMaps = new ImageLayer("gui/botaomaps.png");
		botaoMaps.centralizaY(botaoVoltar);
		botaoMaps.setX(150);
		
		loading = 100;
		
	}
	
	public void draw(Grafico g){

		kanji.draw(g);
		botaoTitulo.draw(g);
		tituloLabel.draw(g);

		botaoMaps.draw(g);
	}

}
