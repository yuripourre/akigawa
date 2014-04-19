package akigawa.menu;


import br.com.etyllica.context.load.DefaultLoadApplication;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.AnimatedLayer;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.TextLayer;


public class MenuCarregando extends DefaultLoadApplication {

	private ImageLayer kanji;
	private ImageLayer carregando;
	private AnimatedLayer reticencias;
	
	
	private ImageLayer bar;
	private ImageLayer barFill;
	
	private TextLayer text;
	private TextLayer porcent;
	
	String lang = "lang/br/";

	public MenuCarregando(int w, int h){
		super(w,h);
	}

	public void load(){
		
		kanji = new ImageLayer("gui/kanji.png");
		kanji.centralizeX(0,w);
		
		carregando = new ImageLayer(lang+"carregando.png");
		carregando.centralize(x, y, w, h);

		reticencias = new AnimatedLayer(520,220,38,12);
		reticencias.cloneLayer("gui/carregando/3pontos.png");
		reticencias.setAnimaEmX(true);
		reticencias.setFrames(3);
		reticencias.animate();
		
		bar = new ImageLayer("gui/barra.png");
		bar.centralizeX(0,w);
		bar.setY(320);
		
		barFill = new ImageLayer("gui/barrafill.png");		
		barFill.centralizeX(0,w);
		barFill.setY(320);
		//barraFill.setXImagem(400);
		
		porcent = new TextLayer(200,200,"666");
		porcent.setColor(0xff,0xff,0xff);
		porcent.setText("100%");
		porcent.centralizeX(0,w);
		
		text = new TextLayer(200,280,"Carregando Imagens");
		text.centralizeX(0,w);
		
	}

	public void draw(Graphic g) {

		kanji.draw(g);
		
		carregando.draw(g);
		reticencias.draw(g);
		
			
		bar.draw(g);
		barFill.draw(g);
		
		porcent.draw(g);
	}

	public void setText(int andamento){
		porcent.setText(Integer.toString(andamento)+"%");
		porcent.centralize(bar);
		barFill.setXImage(barFill.getW()-andamento*4);
	}
}
