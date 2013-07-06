package akigawa.jogador;

import br.com.etyllica.layer.AnimatedImageLayer;
import br.com.etyllica.layer.MovementedLayer;


public class Jogador extends MovementedLayer{

	private Estado estado;
	private Direcao direcao;

	private Sprite arremessando;
	private Sprite correndo;

	private int velocidade;

	//public void setArremessando(Sprite arremessando){
	public void carregaArremessando(Sprite arremessando){
		this.arremessando = arremessando;

		estado = Estado.ARREMESSANDO;
		direcao = Direcao.LESTE;
		
		setArremessando();
	}
	public void carregaCorrendo(Sprite correndo){
		this.correndo = correndo;

		estado = Estado.CORRENDO;
		direcao = Direcao.LESTE;

		setCorrendo();
	}
	
	private void setArremessando(){

		setAnimaEmX(true);
		setNumeroFrames(5);
		setVelocidadeAnimacao(20);		
	}
	private void setCorrendo(){

		setAnimaEmX(true);
		setNumeroFrames(8);
		setVelocidadeAnimacao(80);
		//setVelocidadeAnimacao(8000);

		setVelocidadeMovel(50);	

	}
	private void setEsperando(){	

	}
	private void setPulando(){	

	}

	public Jogador(int x, int y, int xTile, int yTile){
		super(x,y,xTile,yTile);

		//Pode variar
		velocidade = 10;
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
			setYImage(0);
			break;
		case OESTE:
			incrementoX = -velocidade;
			setYImage(yTile);
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
		movimenta();
		anima();
	}

	public AnimatedImageLayer getPele(){

		Sprite s = state();

		cloneLayer(s.getPele());

		return (AnimatedImageLayer) this;
	}
	public AnimatedImageLayer getKimono(){

		Sprite s = state();

		cloneLayer(s.getKimono());

		return (AnimatedImageLayer) this;

	}
	public AnimatedImageLayer getSombra(){

		Sprite s = state();

		cloneLayer(s.getSombra());
		return (AnimatedImageLayer) this;
	}

	private Sprite state(){
		Sprite s;
		switch(estado){
		case ARREMESSANDO:
			s = arremessando;
			break;
		case CORRENDO:
			s = correndo;
			break;
		default:
			s = correndo;
		break;
		}
		return s;
	}

	public enum Estado{
		ARREMESSANDO('a'),
		CORRENDO('c'),
		ESPERANDO('e'),
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
		case PULANDO:
			setPulando();
			break;

		default:
			break;
		}

		movimenta();
		anima();
	}

}
