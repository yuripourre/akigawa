package akigawa.jogador;


import br.com.etyllica.layer.StaticLayer;

public class Sprite{
	
	//Parado, Andando, Lan�ando shuriken...
	
	private StaticLayer pele;
	private StaticLayer kimono;
	private StaticLayer sombra;
	
	public Sprite(StaticLayer pele, StaticLayer kimono, StaticLayer sombra){
		this.pele = pele;
		this.kimono = kimono;
		this.sombra = sombra;
	}
	
	public StaticLayer getPele(){
		return pele;
	}
	public StaticLayer getKimono(){
		return kimono;
	}
	public StaticLayer getSombra(){
		return sombra;
	}
	
}
