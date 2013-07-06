package akigawa.jogador;


import java.awt.Image;

public class Sprite{
	
	//Parado, Andando, Lançando shuriken...
	
	private Image pele;
	private Image kimono;
	private Image sombra;
	
	public Sprite(Image pele, Image kimono, Image sombra){
		this.pele = pele;
		this.kimono = kimono;
		this.sombra = sombra;
	}
	
	public Image getPele(){
		return pele;
	}
	public Image getKimono(){
		return kimono;
	}
	public Image getSombra(){
		return sombra;
	}
	
}
