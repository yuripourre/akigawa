package akigawa.vilarejo;

import etyllica.nucleo.Gerenciador;
import akigawa.fase.Fase;

public abstract class Estabelecimento extends Fase{
	
	int vila;
	
	public Estabelecimento(Gerenciador app, int id, int vila) {
		super(app, id);
		this.vila = vila;
	}
	
}
