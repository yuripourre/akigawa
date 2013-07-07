package akigawa.menu;


import br.com.etyllica.core.application.DefaultLoadApplication;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.layer.AnimatedLayer;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.TextLayer;


public class MenuCarregando extends DefaultLoadApplication{

	//id = Coringa

	private ImageLayer kanji;
	private ImageLayer carregando;
	private AnimatedLayer reticencias;
	
	
	private ImageLayer barra;
	private ImageLayer barraFill;
	
	private TextLayer text;
	private TextLayer porcent;
	
	String lang = "lang/br/";

	public MenuCarregando(int w, int h){
		super(w,h);
	}

	public void load(){
		
		kanji = new ImageLayer("gui/kanji.png");
		kanji.centralizaX(0,w);
		
		carregando = new ImageLayer(lang+"carregando.png");
		carregando.centraliza(x, y, w, h);

		reticencias = new AnimatedLayer(520,220,38,12);
		reticencias.cloneLayer("gui/carregando/3pontos.png");
		reticencias.setAnimaEmX(true);
		reticencias.setNumeroFrames(3);
		reticencias.anima();
		
		barra = new ImageLayer("gui/barra.png");
		barra.centralizaX(0,w);
		barra.setY(320);
		
		barraFill = new ImageLayer("gui/barrafill.png");		
		barraFill.centralizaX(0,w);
		barraFill.setY(320);
		//barraFill.setXImagem(400);
		
		porcent = new TextLayer(200,200,"666");
		porcent.setCorDifusa(0xff,0xff,0xff);
		porcent.setTexto("100%");
		porcent.centralizaX(0,w);
		
		text = new TextLayer(200,280,"Carregando Imagens");
		text.centralizaX(0,w);
		
	}

	public void draw(Grafico g){

		kanji.draw(g);
		
		carregando.draw(g);
		reticencias.draw(g);
		
			
		barra.draw(g);
		barraFill.draw(g);
		
		porcent.draw(g);
	}

	public void setText(int andamento){
		porcent.setTexto(Integer.toString(andamento)+"%");
		porcent.centraliza(barra);
		barraFill.setXImage(barraFill.getW()-andamento*4);
	}
}
