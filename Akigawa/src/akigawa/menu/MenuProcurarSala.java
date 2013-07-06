package akigawa.menu;


import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.layer.ImageLayer;


public class MenuProcurarSala extends AkigawaBackMenu{

	private ImageLayer kanji;
	private ImageLayer botaoTitulo;
	private ImageLayer tituloLabel;

	public MenuProcurarSala(int w, int h){
		super(w,h);
	}
	
	@Override
	public void back(){
		returnApplication = new MenuJogar(w, h);
	}

	public void load(){

		botaoTitulo = new ImageLayer(0,14, "gui/botaocomp.png");
		botaoTitulo.centralizaX(0,w);

		tituloLabel = new ImageLayer(lang+"procurarsala.png");
		tituloLabel.centraliza(botaoTitulo);

		kanji = new ImageLayer("gui/kanji.png");
		kanji.centralizaX(0,w);

		loading = 100;
		
	}
	
	public void draw(Grafico g){

		kanji.draw(g);
		botaoTitulo.draw(g);
		tituloLabel.draw(g);

	}

}
