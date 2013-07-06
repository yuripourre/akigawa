package akigawa.fase;

import java.util.Random;

import akigawa.GerenciadorAkigawa;
import akigawa.jogador.Jogador;
import akigawa.jogador.Jogador.Direcao;
import akigawa.objetos.Caido;
import jtse.Gerenciador;
import jtse.TSe.Tecla;
import jtse.camada.Camada;
import jtse.camada.CamadaEstatica;
import jtse.camada.Fx;

public class FaseTron extends Fase{

	private Camada fundo;

	private int ninjas = 1;
	private Jogador[] ninja;
	private Fx[] efeito;

	private CamadaEstatica estrelaRuim;

	private CamadaEstatica cannonballImage;
	private Caido cannonball[];
	private int cannons;

	public FaseTron(GerenciadorAkigawa app, int id) {
		super(app, id);

		fundo = new Camada(url, diretorioFase+"tron/chao.png");

		ninja = new Jogador[ninjas];
		efeito = new Fx[ninjas];

		cannons = 1;
		cannonball = new Caido[cannons];

		estrelaRuim = new CamadaEstatica(url, diretorioFase+"tron/bluefx.png");

		for(int i=0;i<ninjas;i++){
			ninja[i] = new Jogador(64+64*i,270,75,125);
			
			if(i%2==0)
				ninja[i].direciona(Direcao.LESTE);
			else
				ninja[i].direciona(Direcao.OESTE);

			//ninja[i].setArremessando(app.getArremessando(200,10,10));
			Random r = new Random();
			Random g = new Random();
			Random b = new Random();
			ninja[i].carregaCorrendo(app.getCorrendo(r.nextInt(200),g.nextInt(20),b.nextInt(200)));
			//ninja[i].setVelocidadeAnimacao(2000);
			ninja[i].setVelocidadeMovel(800);

			efeito[i] = new Fx(0,0,86,88);
			efeito[i].setAnimaEmX(true);
			efeito[i].setNumeroFrames(4);
			efeito[i].setVelocidadeAnimacao(120);

		}

		//Fallen Objects
		cannonballImage = new CamadaEstatica(url, diretorioFase+"abrigo/cannonball.png");
		for(int c=0;c<cannons;c++){
			cannonball[c] = new Caido(cannonballImage);
			cannonball[c].para();
		}

	}

	public void desenha(){
		app.desenha(fundo);

		for(int i=0;i<ninjas;i++){
			//app.desenha(ninja[i].getSombra());
			//app.desenha(ninja[i].getKimono());
			app.desenha(ninja[i].getPele());

			app.desenha(efeito[i]);
		}

		for(int c=0;c<cannons;c++){
			app.desenha(cannonball[c]);
		}

	}

	public int gerencia(){

		if(teclado.getTecla(Tecla.TSK_D)){
			ninja[0].direciona(Direcao.LESTE);
			
			if((!teclado.getTecla(Tecla.TSK_W))&&
					(!teclado.getTecla(Tecla.TSK_S)))
				//ninja[0].direciona(Direcao.CENTROY);
				ninja[0].setIncrementoY(0);
		}
		else if(teclado.getTecla(Tecla.TSK_A)){
			ninja[0].direciona(Direcao.OESTE);
			if((!teclado.getTecla(Tecla.TSK_W))&&
					(!teclado.getTecla(Tecla.TSK_S)))
				//ninja[0].direciona(Direcao.CENTROY);
				ninja[0].setIncrementoY(0);
		}

		if(teclado.getTecla(Tecla.TSK_W)){			
			ninja[0].direciona(Direcao.NORTE);
			if((!teclado.getTecla(Tecla.TSK_D))&&
					(!teclado.getTecla(Tecla.TSK_A)))
				//ninja[0].direciona(Direcao.CENTROX);
				ninja[0].setIncrementoX(0);
		}
		else if(teclado.getTecla(Tecla.TSK_S)){
			ninja[0].direciona(Direcao.SUL);
			if((!teclado.getTecla(Tecla.TSK_D))&&
					(!teclado.getTecla(Tecla.TSK_A)))
				//ninja[0].direciona(Direcao.CENTROX);
				ninja[0].setIncrementoX(0);
		}

		/*
		if((!teclado.getTecla(Tecla.TSK_D))&&
				(!teclado.getTecla(Tecla.TSK_A))&&
				(!teclado.getTecla(Tecla.TSK_W))&&
				(!teclado.getTecla(Tecla.TSK_S))){
			ninja[0].desAnima();
		}
		 */

		gerenciaColisaoNinja();
		gerenciaObjetos();

		return id;
	}

	private void gerenciaObjetos(){

		//cannonball.setOffsetY(1);

		for(int c=0;c<cannons;c++){
			cannonball[c].gerencia();

			for(int i=0;i<ninjas;i++){
				if(ninja[i].colideRetangular(cannonball[c])){
					//Estrela boa

					efeito[i].setCoordenadas(ninja[i].getX(), ninja[i].getY()-70);
					efeito[i].igualaImagem(estrelaRuim);
					efeito[i].anima();
					ninja[0].setVelocidade(ninja[0].getVelocidade()+1);
					cannonball[c].para();
				}
			}

			if(cannonball[c].getParado()){
				cannonball[c].setCoordenadas(new Random().nextInt(700),-10-new Random().nextInt(20));
				cannonball[c].setVelocidade(2+(new Random().nextInt(6)));
				cannonball[c].cai();
			}
		}
	}

	private void gerenciaColisaoNinja(){
		if(ninja[0].getX()+ninja[0].getXTile()+ninja[0].getIncrementoX()>fundo.getXLimite()){
			//ninja[0].setIncrementoX(0);
		}

		if(ninja[0].getX()<0){
			//ninja[0].setIncrementoX(0);
			ninja[0].setX(0);
		}
		if(ninja[0].getY()<0){
			//ninja[0].setIncrementoY(0);
			ninja[0].setY(0);
		}
		else if(ninja[0].getY()+ninja[0].getYTile()>423){
			//ninja[0].setIncrementoY(0);
			ninja[0].setY(423-ninja[0].getYTile());
		}

	}

	@Override
	public void carrega() {
		// TODO Auto-generated method stub
		
	}

}
