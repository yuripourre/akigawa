package akigawa.jogador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import etyllica.camada.CamadaEstatica;
import etyllica.camada.CamadaMovel;


public class Inimigo extends CamadaMovel{

	private Estado estado;
	private Direcao direcao;

	private CamadaEstatica arremessando;
	private CamadaEstatica atacando;
	private CamadaEstatica correndo;
	private CamadaEstatica parado;

	private int velocidade;

	private Heroi target;
	private int xTarget;
	private int yTarget;

	private int acao;

	private Timer atack;

	public void setTarget(Heroi h){
		this.target = h;
		setTarget(h.getX(),h.getY());
	}

	public void setTarget(int x, int y){
		xTarget = x;
		yTarget = y;
	}

	public void carregaArremessando(CamadaEstatica arremessando){
		this.arremessando = arremessando;

		setArremessando();
	}
	public void carregaAtacando(CamadaEstatica atacando){
		this.atacando = atacando;
	}
	public void carregaParado(CamadaEstatica parado){
		this.parado = parado;
	}
	public void carregaCorrendo(CamadaEstatica correndo){
		this.correndo = correndo;

		setCorrendo();
	}

	private void setArremessando(){

		estado = Estado.ARREMESSANDO;

		igualaImagem(arremessando.getCaminho());

		setAnimaEmX(true);
		setNumeroFrames(5);
		setVelocidadeAnimacao(20);		
	}
	private void setAtacando(){

		estado = Estado.ATACANDO;
		setCoordTiles(90, 91);
		igualaImagem(atacando.getCaminho());
		setAnimaEmX(true);
		setNumeroFrames(2);
		setVelocidadeAnimacao(20);		
	}
	private void setParado(){

		estado = Estado.PARADO;
		setCoordTiles(64, 91);
		igualaImagem(parado.getCaminho());
		setAnimaEmX(true);
		setNumeroFrames(1);
		setVelocidadeAnimacao(20);		
	}
	private void setCorrendo(){
		if(estado!=Estado.CORRENDO){
			estado = Estado.CORRENDO;

			//Calculado Automaticamente
			setCoordTiles(64, 100);

			igualaImagem(correndo.getCaminho());
			setAnimaEmX(true);
			setNumeroFrames(7);
			setVelocidadeAnimacao(80);
		}

	}
	private void setEsperando(){	

	}
	private void setPulando(){	

	}

	public Inimigo(int x, int y, int xTile, int yTile){
		super(x,y,xTile,yTile);

		velocidade = 5;
		acao = 0;

		setVelocidadeMovel(50);
		
		anima();
	}

	public void begin(){
		int tempoSleep = 400;
		//atack = new javax.swing.Timer(1000, new ActionListener() {
		atack = new javax.swing.Timer(tempoSleep, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerencia();
			}
		});
		atack.start();
	}

	public Direcao getDirecao(){
		return direcao;
	}

	public void direciona(Direcao direcao){

		this.direcao = direcao;

		switch (direcao){

		case NORTE:
			incrementoY = -velocidade;
			break;
		case SUL:
			incrementoY = +velocidade;
			break;
		case LESTE:
			incrementoX = +velocidade;
			setYImagem(0);
			break;
		case OESTE:
			incrementoX = -velocidade;
			setYImagem(yTile);
			break;
		case CENTROX:
			incrementoX = 0;
			break;
		case CENTROY:
			incrementoY = 0;
			break;

		default:
			break;
		}

		//acionaMovimento();
		//anima();
	}

	public enum Estado{
		ARREMESSANDO('a'),
		ATACANDO('t'),
		CORRENDO('c'),
		ESPERANDO('e'),
		PARADO('s'),
		PULANDO('p');

		private final char codigo;

		Estado(char codigo){
			this.codigo = codigo;
		}
		public char codigo(){ return codigo; }

	}

	public enum Direcao{
		NORTE('n'),
		SUL('s'),
		LESTE('l'),
		OESTE('o'),
		CENTRO('c'),
		CENTROX('x'),
		CENTROY('y');

		private final char codigo;

		Direcao(char codigo){
			this.codigo = codigo;
		}
		public char codigo(){ return codigo; }

	}

	public int getVelocidade(){
		return velocidade;
	}

	public void setVelocidade(int velocidade){
		this.velocidade = velocidade;
	}

	public void setEstado(Estado estado){

		this.estado = estado;

		switch (estado){

		case ARREMESSANDO:
			setArremessando();
			break;
		case CORRENDO:
			setCorrendo();
			break;
		case ESPERANDO:
			setEsperando();
			break;
		case PARADO:
			setParado();
			break;
		case PULANDO:
			setPulando();
			break;

		default:
			break;
		}

		movimenta();
		//anima();
	}

	public void aproxima(){

		if(xTarget<x){
			//if(x-velocidade>xTarget)
			direciona(Direcao.OESTE);
		}
		else if(xTarget>x){
			//if(x+velocidade<xTarget)
			direciona(Direcao.LESTE);
		}
		else{
			direciona(Direcao.CENTROX);
		}

		if(yTarget<y){
			direciona(Direcao.NORTE);
		}
		else if(yTarget>y){
			direciona(Direcao.SUL);
		}
		else{
			direciona(Direcao.CENTROY);
		}
	}

	private void gerencia(){

		
		if(target.getX()>x+xLimite)
			acao = 0;
		//if(!colideRetangular(target)){
		//	acao = 0;
		//}
		else{
			if(acao==0)
				acao = 1;
		}

		if(acao==0){
			//Se aproxima

			aproxima();
			setCorrendo();
			movimenta();
			//preMovimenta();
		}
		else if(acao==1){
			//Ataca
			//imobiliza();
			animaOnce();
			setAtacando();

			acao = 2;
		}
		else if(acao==2){
			setParado();
			acao = 1;
		}	
	}
}
