package akigawa.objetos;


import etyllica.audio.SomWav;
import etyllica.camada.Camada;
import etyllica.camada.CamadaEstatica;



public class Caido extends Camada{
	private SomWav som;

	private boolean arremessado;
	private boolean parado;

	private int velocidade;
	private int limiteMax;
	private int limiteMin;
	private int yInicial;

	public Caido(CamadaEstatica camEst){

		super(camEst);

		parado = false;
		arremessado = false;
		aparecendo = true;
		velocidade = 1;
		limiteMax = 480;
		limiteMin = -20;

		som = null;
	}

	public Caido(String caminho){

		super(caminho);

		arremessado = false;
		aparecendo = true;
		velocidade = 1;
		limiteMax = 480;
		limiteMin = -20;

		som = null;
	}

	public void setVelocidade(int velocidade){
		this.velocidade = velocidade;
	}

	public void cai(){

		if(!arremessado){
			arremessado = true;
			yInicial = y;
		}

		if(parado){
			aparecendo = true;
			arremessado = true;
			parado = false;
		}

	}
	public void para(){
		parado = true;
	}
	public void gerencia(){

		if(!parado){
			if(arremessado){
				if(velocidade>0){
					if(y<limiteMax){
						setOffsetY(velocidade);
					}
					else{
						y = yInicial;
					}
				}
				else if(velocidade<0){
					if(y<limiteMin){
						setOffsetY(velocidade);
					}
					else{
						y = yInicial;
					}
				}
			}
		}
	}

	public boolean getParado(){
		return parado;
	}

	public void setSom(SomWav som){
		this.som = som;
	}

}
