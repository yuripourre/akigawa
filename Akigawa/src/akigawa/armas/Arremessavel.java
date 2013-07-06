package akigawa.armas;

import etyllica.audio.SomWav;
import etyllica.camada.Camada;
import etyllica.camada.CamadaEstatica;

public class Arremessavel extends Camada{

	private SomWav som;
	
	private boolean arremessado;
	private boolean parado;

	private int velocidade;
	private int limiteMax;
	private int limiteMin;
	private int xInicial;

	public Arremessavel(CamadaEstatica kunai){
		
		super(kunai);
		
		arremessado = false;
		aparecendo = false;
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
					if(x<limiteMax){
						setOffsetX(velocidade);
					}
					else{
						aparecendo = false;
						arremessado = false;
						x = xInicial;
					}
				}
				else if(velocidade<0){
					if(x<limiteMin){
						setOffsetX(velocidade);
					}
					else{
						aparecendo = false;
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
	
	public boolean colideRetangular(Camada b)
	{
		if(b.getX() + b.getXLimite() < x)	return false;
		if(b.getX() > x + xLimite)		return false;

		if(b.getY() + b.getYLimite() < y)	return false;
		if(b.getY() > y + yLimite)		return false;
		
		if(b.getY() + b.getYLimite() < y + yLimite/2)	return false;
		if(b.getY() > y + yLimite - yLimite/2)		return false;

		return true;
	}
	
	public void setSom(SomWav som){
		this.som = som;
	}

}
