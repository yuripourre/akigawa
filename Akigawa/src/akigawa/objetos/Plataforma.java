package akigawa.objetos;

import etyllica.camada.Camada;
import etyllica.camada.CamadaEstatica;


public class Plataforma extends Camada{
	
	private boolean passada;
	
	public Plataforma(CamadaEstatica camEst){
		super(camEst);
		passada = false;
	}

	public boolean foiPassada() {
		return passada;
	}

	public void setPassada(boolean passada) {
		this.passada = passada;
	}
	
	

}
