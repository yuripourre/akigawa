package akigawa.vilarejo;

import etyllica.camada.Camada;
import etyllica.camada.CamadaTexto;
import etyllica.nucleo.Gerenciador;
import etyllica.nucleo.TSe.Tecla;
import akigawa.fase.Fase;


public class VilaInicial extends Fase{

	private Camada fundo;
	private Camada guarda;
	private Camada barqueiro;
	private Camada ferreiro;

	private Camada alfaiate;

	private Camada alvo;

	//Nome Mouse
	private CamadaTexto target;


	public VilaInicial(Gerenciador app, int id) {
		super(app, id);
		// TODO Auto-generated constructor stub
	}
	public void carrega() {

		fundo = new Camada(g.carregaCamada(diretorioFase+"vilainicial/vila.png"));

		guarda = new Camada(g.carregaCamada(diretorioFase+"vilainicial/minigatevect.png"));
		guarda.setCoordenadas(356,80);

		//
		barqueiro = new Camada(g.carregaCamada(diretorioFase+"vilainicial/ocasa.png"));
		barqueiro.setCoordenadas(170,240);

		ferreiro = new Camada(g.carregaCamada(diretorioFase+"vilainicial/fcasa.png"));
		ferreiro.setCoordenadas(260,180);

		alfaiate = new Camada(g.carregaCamada(diretorioFase+"vilainicial/rcasa.png"));
		alfaiate.setCoordenadas(540,250);
		
		alvo = new Camada(g.carregaCamada(diretorioFase+"vilainicial/alvo.png"));
		alvo.setCoordenadas(520,140);

		target = new CamadaTexto("");
		target.setCorDifusa(0xff,0xff,0xff);


		carregando = 100;
	}
	public void desenha() {
		g.desenha(fundo);
		g.desenha(guarda);
		g.desenha(barqueiro);
		g.desenha(ferreiro);

		g.desenha(alfaiate);
		g.desenha(alvo);

		g.desenha(target);

	}
	public int gerencia(){


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

		//E se não sumir?
		//Se eu ficar puto, eu faço na mão :D
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
		/*
		if(teclado.getTecla(Tecla.TSK_ENTER)){
			if(!trava){
				if(camp.isVisible()){
					camp.setText("");
					camp.setFocusable(false);
					camp.setVisible(false);
				}				
				else{
					camp.setVisible(true);
					camp.requestFocus();
				}

				trava = true; 
			}
		}
		else{
			trava = false;
		}
		 */

		return id;
	}

}
