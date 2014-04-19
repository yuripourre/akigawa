package akigawa.objetos;

import br.com.etyllica.layer.ImageLayer;


public class Plataforma extends ImageLayer{
	
	private boolean passada;
	
	public Plataforma(String path){
		super(path);
		passada = false;
	}

	public boolean foiPassada() {
		return passada;
	}

	public void setPassada(boolean passada) {
		this.passada = passada;
	}
	
	

}
