package akigawa.menu;


import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.layer.ImageLayer;


public class MenuOpcoes extends AkigawaBackMenu{

	private ImageLayer kanji;
	private ImageLayer botaoTitulo;
	private ImageLayer tituloLabel;
	private ImageLayer borda;
	
	private ImageLayer semOpcoes;
	

	public MenuOpcoes(int w, int h){
		super(w,h);
		botaoVoltar.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new GUIAction(this, "back"));
	}

	public void load(){
		
		botaoTitulo = new ImageLayer(0, 14, "gui/botaocomp.png");
		botaoTitulo.centralizaX(0,w);
		loading = 18;		

		borda = new ImageLayer(0, 110, "gui/borda.png");
		borda.centralizaX(0,w);
		loading = 28;

		tituloLabel = new ImageLayer(lang+"opcoes.png");
		tituloLabel.centralizaX(0,w);
		tituloLabel.centraliza(botaoTitulo);
		
		
		loading = 49;
		

		kanji = new ImageLayer("gui/kanji.png");
		kanji.centralizaX(0,w);
		loading = 66;
		
		semOpcoes = new ImageLayer(lang+"nenhumaopcao.png");
		semOpcoes.centraliza(borda);
				
		loading = 100;
	}
	
	public void draw(Grafico g){

		kanji.draw(g);
		botaoTitulo.draw(g);
		tituloLabel.draw(g);
		borda.draw(g);
		
		semOpcoes.draw(g);
		
		
	}

}
