package akigawa.vilarejo;

import akigawa.fase.Fase;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.event.Tecla;
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

		//target.draw(g);


	}
	/*

	@Override
	public GUIEvent updateMouse(PointerEvent event){


		if(mouse.sobMouse(guarda)){
			target.setTexto("Guarda");
			target.setCoordenadas(mouse.getX()-50, mouse.getY()-40);
			target.setAparecendo(true);
			if(mouse.getPressionado()==1){
				return 13;
			}
		}
		else if(mouse.sobMouse(barqueiro)){
			target.setTexto("Barqueiro");
			target.setCoordenadas(mouse.getX()-50, mouse.getY()-40);
			target.setAparecendo(true);
			if(mouse.getPressionado()==1){
				return 16;
			}
		}
		else if(mouse.sobMouse(ferreiro)){
			target.setTexto("Ferreiro");
			target.setCoordenadas(mouse.getX()-50, mouse.getY()-40);
			target.setAparecendo(true);
			if(mouse.getPressionado()==1){
				return 12;
			}
		}
		else if(mouse.sobMouse(alfaiate)){
			target.setTexto("Rouparia");
			target.setCoordenadas(mouse.getX()-50, mouse.getY()-40);
			target.setAparecendo(true);
			if(mouse.getPressionado()==1){
				//return 999;
				return 15;
			}
		}
		else if(mouse.sobMouse(alvo)){
			target.setTexto("Tiro ao Alvo");
			target.setCoordenadas(mouse.getX()-50, mouse.getY()-40);
			target.setAparecendo(true);

			if(mouse.getPressionado()==1){
				return 14;
			}
		}
		else{
			target.setAparecendo(false);
		}

		//E se n�o sumir?
		//Se eu ficar puto, eu fa�o na m�o :D
			if(teclado.getTecla(Tecla.TSK_M)){
					//camp.requestFocus();
				//	camp.setText("");
				//}
				//travaEnter = true;
			}
			if(teclado.getTecla(Tecla.TSK_N)){
				//camp.setText("");
				//return 999;
			}

		return id;
	}

	 */

}
