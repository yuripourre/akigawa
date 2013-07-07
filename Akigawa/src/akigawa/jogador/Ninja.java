package akigawa.jogador;

import java.awt.image.BufferedImage;

import br.com.etyllica.layer.BufferedLayer;
import br.com.etyllica.layer.MovementedLayer;

public class Ninja extends MovementedLayer{


	BufferedLayer sombra;
	BufferedLayer kimono;
	BufferedLayer pele;

	BufferedLayer cam;

	int r;
	int g;
	int b;


	public Ninja(int x, int y) {
		super(x, y, 75, 125);

		setAnimaEmX(true);
		setNumeroFrames(5);
		setVelocidadeAnimacao(80);
	}

	public Ninja(int x, int y, int red, int green, int blue) {
		super(x, y, 75, 125);
		this.r = red;
		this.b = blue;
		this.g = green;
	}

	public void setNinja(BufferedLayer sombra, BufferedLayer kimono, BufferedLayer pele){
		this.sombra = sombra;
		this.kimono = kimono;
		this.pele = pele;

		BufferedImage buf = new BufferedImage(375,125,BufferedImage.TYPE_INT_ARGB);

		buf.getGraphics().drawImage(sombra.getImagemBuffer(),0,0,null);
		buf.getGraphics().drawImage(kimono.getImagemBuffer(),0,0,null);
		buf.getGraphics().drawImage(pele.getImagemBuffer(),0,0,null);

		cam = new BufferedLayer(x,y,buf);
		
		cam.setW(75);
	}

	public BufferedLayer getCamada(){
		return cam;
	}

	public void preAnima(){

		if(!stopped){
			if(frameAtual < numeroFrames-1){

				frameAtual++;			
			}
			else{
				frameAtual = 0;
				desAnima();
			}

			cam.setXImage(xTile*frameAtual);
		}
	}

}
