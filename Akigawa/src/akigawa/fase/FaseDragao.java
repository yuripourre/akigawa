package akigawa.fase;

import etyllica.camada.Camada;
import etyllica.camada.CamadaEstatica;
import etyllica.camada.CamadaTexto;
import etyllica.camada.Efeito;
import etyllica.nucleo.Gerenciador;
import etyllica.nucleo.TSe.Tecla;
import akigawa.armas.Arremessavel;
import akigawa.jogador.Jogador;

public class FaseDragao extends Fase{

	private Camada fundo;
	private Camada dragao;

	private int ninjas = 1;
	private Jogador[] ninja;

	private boolean[] outra;
	private boolean[] gone;

	private Arremessavel[] faca;
	private Efeito[] efeito;

	private CamadaEstatica kunai;
	private CamadaEstatica fumaca;

	private CamadaTexto life;
	private int dragLife = 500;

	public FaseDragao(Gerenciador app, int id) {
		super(app, id);
		// TODO Auto-generated constructor stub
		carrega();
	}

	public void carrega(){
		fundo = new Camada(diretorioFase+"drag/fundo.png");
		dragao = new Camada(diretorioFase+"drag/drag.png");
		dragao.setCoordenadas(500,-20);

		outra = new boolean[ninjas];
		gone = new boolean[ninjas];
		ninja = new Jogador[ninjas];

		faca = new Arremessavel[ninjas];

		kunai = new CamadaEstatica(diretorioFase+"kunai.png");

		efeito = new Efeito[ninjas];
		fumaca = new CamadaEstatica(diretorioFase+"drag/smoke.png");

		for(int i=0;i<ninjas;i++){

			//Ninjas
			ninja[i] = new Jogador(10,10+55*i,75,125);
			ninja[i].carregaArremessando(g.getArremessando(200,10,10));
			ninja[i].centralizaY(fundo);

			//faca[i].setCoordenadas(20, ninja[i].getY()+40);
			faca[i] = new Arremessavel(kunai);
			faca[i].setCoordenadas(20, ninja[i].getY()+40);
			faca[i].setVelocidade(20);
			faca[i].para();

			efeito[i] = new Efeito(dragao.getX(),ninja[i].getY(),85,86);
			efeito[i].setAnimaEmX(true);
			efeito[i].setNumeroFrames(4);
			efeito[i].setVelocidadeAnimacao(120);

			efeito[i].igualaImagem(fumaca.getCaminho());

			life = new CamadaTexto(320,20,Integer.toString(dragLife));

			outra[i] = false;
			gone[i] = true;
		}

		//efeito[0].igualaImagem(fumaca);
	}

	public void desenha(){
		g.desenha(fundo);
		g.desenha(dragao);

		g.desenha(life);

		for(int i=0;i<ninjas;i++){
			g.desenha(ninja[i].getSombra());
			g.desenha(ninja[i].getKimono());
			g.desenha(ninja[i].getPele());

			//g.desenha(efeito[i]);
		}
		g.desenha(faca[0]);
		g.desenha(efeito[0]);
	}

	public int gerencia(){

		if(teclado.getTecla(Tecla.TSK_ESPAÇO)){
			if(!outra[0]){
				outra[0] = true;
				ninja[0].animaOnce();
				ninja[0].anima();
				gone[0] = false;
			}
		}

		for(int j=0;j<ninjas;j++){

			if(ninja[j].getParado()){
				if(!gone[j]){
					faca[j].setAparecendo(true);
					faca[j].arremessa();
				}
				outra[0] = false;
			}

			//for(int i = 0; i<numFacas; i++){
			faca[j].gerencia();

			if(!faca[j].getParado()){
				if(faca[j].colideRetangular(dragao)){
					efeito[j].anima();
					faca[j].para();
					faca[j].setAparecendo(false);
					faca[j].setX(ninja[j].getX());
					gone[j] = true;

					dragLife--;
					life.setTexto(Integer.toString(dragLife));
				}
				else if(faca[j].getX()>=750){
					faca[j].para();
					faca[j].setAparecendo(false);
					faca[j].setX(ninja[j].getX());
					gone[j] = true;
				}
			}

		}//Fecha For 		

		return id;
	}	

}
