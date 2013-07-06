package akigawa.travessia;

import java.util.Random;

import etyllica.camada.Camada;
import etyllica.camada.CamadaEstatica;
import etyllica.nucleo.Gerenciador;
import etyllica.nucleo.TSe.Tecla;


import akigawa.fase.Fase;
import akigawa.jogador.Heroi;
import akigawa.jogador.Inimigo;
import akigawa.jogador.Jogador;
import akigawa.jogador.Jogador.Direcao;

public class TravessiaSaigo extends Fase{

	public TravessiaSaigo(Gerenciador app, int id) {
		super(app, id);
	}

	private Camada fundo;
	private int ninjas = 1;
	private Heroi[] ninja;

	private CamadaEstatica correndo;
	private CamadaEstatica atacando;
	private CamadaEstatica parado;
	private Inimigo inimigo;
	
	public void carrega() {
		fundo = new Camada(diretorioFase+"abrigo/fundo.png");

		ninja = new Heroi[ninjas];
		for(int i=0;i<ninjas;i++){
			ninja[i] = new Heroi(640+64*i,270,75,125);

			Random r = new Random();
			Random g = new Random();
			Random b = new Random();
			//Problema
			ninja[i].carregaCorrendo(g.getCorrendo(r.nextInt(200),g.nextInt(20),b.nextInt(200)));
		}
		
		correndo = new CamadaEstatica("imagens/inimigo.png");
		atacando = new CamadaEstatica("imagens/punch.png");
		parado = new CamadaEstatica("imagens/stand.png");
		inimigo = new Inimigo(100,300,64,100);
		inimigo.carregaAtacando(atacando);
		inimigo.carregaParado(parado);
		inimigo.carregaCorrendo(correndo);
		inimigo.begin();
		
		carregando = 100;
	}

	public void desenha(){
		g.desenha(fundo);

		for(int i=0;i<ninjas;i++){
			//app.desenha(ninja[i].getSombra());
			//app.desenha(ninja[i].getKimono());
			
			//Agora Pode desenhar Direto
			g.desenha(ninja[i].getPele());
		}
		
		g.desenha(inimigo);
	}

	public int gerencia(){

		if(teclado.getTecla(Tecla.TSK_D)){
			ninja[0].direciona(Direcao.LESTE);
		}
		else if(teclado.getTecla(Tecla.TSK_A)){
			ninja[0].direciona(Direcao.OESTE);
		}
		else{
			if(!teclado.getTecla(Tecla.TSK_D))
				ninja[0].direciona(Direcao.CENTROX);
		}

		if(teclado.getTecla(Tecla.TSK_W)){
			ninja[0].direciona(Direcao.NORTE);
		}
		else if(teclado.getTecla(Tecla.TSK_S)){
			ninja[0].direciona(Direcao.SUL);
		}
		else{
			ninja[0].direciona(Direcao.CENTROY);
		}

		if((!teclado.getTecla(Tecla.TSK_D))&&
				(!teclado.getTecla(Tecla.TSK_A))&&
				(!teclado.getTecla(Tecla.TSK_W))&&
				(!teclado.getTecla(Tecla.TSK_S))){
			ninja[0].desAnima();
		}
		
		inimigo.setTarget(ninja[0]);
		//inimigo.gerencia();
		
		
		//Tirar daqui
		if(ninja[0].getX()<-40){
			return 10;
		}
		if(teclado.getTecla(Tecla.TSK_L)){
			return 10;
		}

		return id;
	}

}
