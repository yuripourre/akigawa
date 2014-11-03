package akigawa.menu;


import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;


public class MenuOpcoes extends AkigawaBackMenu{

	private ImageLayer kanji;
	private ImageLayer botaoTitulo;
	private ImageLayer tituloLabel;
	private ImageLayer borda;
	
	private ImageLayer semOpcoes;
	

	public MenuOpcoes(int w, int h){
		super(w,h);
	}

	public void load() {
		super.load();
		
		botaoTitulo = new ImageLayer(0, 14, "gui/botaocomp.png");
		botaoTitulo.centralizeX(0,w);
		loading = 18;		

		borda = new ImageLayer(0, 110, "gui/borda.png");
		borda.centralizeX(0,w);
		loading = 28;

		tituloLabel = new ImageLayer(lang+"opcoes.png");
		tituloLabel.centralizeX(0,w);
		tituloLabel.centralize(botaoTitulo);
		
		
		loading = 49;
		

		kanji = new ImageLayer("gui/kanji.png");
		kanji.centralizeX(0,w);
		loading = 66;
		
		semOpcoes = new ImageLayer(lang+"nenhumaopcao.png");
		semOpcoes.centralize(borda);
				
		loading = 100;
	}
	
	public void draw(Graphic g){

		kanji.draw(g);
		botaoTitulo.draw(g);
		tituloLabel.draw(g);
		borda.draw(g);
		
		semOpcoes.draw(g);
		
		
	}

}
