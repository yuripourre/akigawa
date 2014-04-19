package akigawa.objetos;


import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.multimedia.Sound;



public class Caido extends ImageLayer{
	private Sound som;

	private boolean arremessado;
	private boolean parado;

	private int velocidade;
	private int limiteMax;
	private int limiteMin;
	private int yInicial;

	public Caido(String caminho){

		super(caminho);

		arremessado = false;
		visible = true;
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
			visible = true;
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

	public void setSom(Sound som){
		this.som = som;
	}

}
