package akigawa.armas;

import sound.model.Sound;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.Layer;

public class Arremessavel extends ImageLayer{

	private Sound som;
	
	private boolean arremessado;
	private boolean parado;

	private int velocidade;
	private int limiteMax;
	private int limiteMin;
	private int xInicial;

	public Arremessavel(String path){
		super(path);
		
		arremessado = false;
		visible = false;
		velocidade = 1;
		limiteMax = 780;
		limiteMin = -20;
		
		som = null;
	}


	public void setVelocidade(int velocidade){
		this.velocidade = velocidade;
	}

	public void arremessa(){

		if(!arremessado)
			xInicial = x;

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
					if(x<limiteMax){
						setOffsetX(velocidade);
					}
					else{
						visible = false;
						arremessado = false;
						x = xInicial;
					}
				}
				else if(velocidade<0){
					if(x<limiteMin){
						setOffsetX(velocidade);
					}
					else{
						visible = false;
						arremessado = false;
						x = xInicial;
					}
				}
			}
		}
	}
	
	public boolean getParado(){
		return parado;
	}
	
	public boolean colideRetangular(Layer b)
	{
		if(b.getX() + b.getW() < x)	return false;
		if(b.getX() > x + w)		return false;

		if(b.getY() + b.getH() < y)	return false;
		if(b.getY() > y + h)		return false;
		
		if(b.getY() + b.getH() < y + h/2)	return false;
		if(b.getY() > y + h - h/2)		return false;

		return true;
	}
	
	public void setSom(Sound som){
		this.som = som;
	}

}
