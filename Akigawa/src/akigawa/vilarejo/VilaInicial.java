package akigawa.vilarejo;

import akigawa.fase.Fase;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.TextLayer;


public class VilaInicial extends Fase{

	private ImageLayer fundo;
	private ImageLayer guarda;
	private ImageLayer barqueiro;
	private ImageLayer ferreiro;

	private ImageLayer alfaiate;

	private ImageLayer alvo;

	//Nome Mouse
	private TextLayer target;


	public VilaInicial(int w, int h) {
		super(w, h);
	}
	
	public void load() {

		fundo = new ImageLayer(diretorioFase+"vilainicial/vila.png");

		guarda = new ImageLayer(diretorioFase+"vilainicial/minigatevect.png");
		guarda.setCoordenadas(356,80);

		//
		barqueiro = new ImageLayer(diretorioFase+"vilainicial/ocasa.png");
		barqueiro.setCoordenadas(170,240);

		ferreiro = new ImageLayer(diretorioFase+"vilainicial/fcasa.png");
		ferreiro.setCoordenadas(260,180);

		alfaiate = new ImageLayer(diretorioFase+"vilainicial/rcasa.png");
		alfaiate.setCoordenadas(540,250);

		alvo = new ImageLayer(diretorioFase+"vilainicial/alvo.png");
		alvo.setCoordenadas(520,140);

		target = new TextLayer("");
		//target.setCorDifusa(0xff,0xff,0xff);


		loading = 100;
	}
	public void draw (Grafico g) {

		fundo.draw(g);
		guarda.draw(g);
		barqueiro.draw(g);
		ferreiro.draw(g);

		alfaiate.draw(g);
		alvo.draw(g);

		target.draw(g);

	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event){
		
		boolean overSomething = false;
		
		if(guarda.onMouse(event)){
			target.setTexto("Guarda");
			overSomething = true;
		}else if(barqueiro.onMouse(event)){
			target.setTexto("Barqueiro");
			overSomething = true;
		}else if(ferreiro.onMouse(event)){
			target.setTexto("Ferreiro");
			overSomething = true;
		}else if(alfaiate.onMouse(event)){
			target.setTexto("Alfaiate");
			overSomething = true;
		}else if(alvo.onMouse(event)){
			target.setTexto("Alvo");
			overSomething = true;
		}else{
			target.setVisible(false);
			return GUIEvent.NONE;
		}
		
		
		if(overSomething){			
			target.setCoordenadas(event.getX()-50, event.getY()-40);
			target.setVisible(true);
		}
		
		
		return GUIEvent.NONE;

	}

	 

}
