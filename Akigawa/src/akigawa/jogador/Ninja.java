package akigawa.jogador;

import java.awt.image.BufferedImage;


import etyllica.camada.CamadaManipulavel;
import etyllica.camada.CamadaMovel;

public class Ninja extends CamadaMovel{


	CamadaManipulavel sombra;
	CamadaManipulavel kimono;
	CamadaManipulavel pele;

	CamadaManipulavel cam;

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

	public void setNinja(CamadaManipulavel sombra, CamadaManipulavel kimono, CamadaManipulavel pele){
		this.sombra = sombra;
		this.kimono = kimono;
		this.pele = pele;

		BufferedImage buf = new BufferedImage(375,125,BufferedImage.TYPE_INT_ARGB);

		buf.getGraphics().drawImage(sombra.getImagemBuffer(),0,0,null);
		buf.getGraphics().drawImage(kimono.getImagemBuffer(),0,0,null);
		buf.getGraphics().drawImage(pele.getImagemBuffer(),0,0,null);

		cam = new CamadaManipulavel(x,y,buf);
		
		cam.setXLimite(75);
	}

	public CamadaManipulavel getCamada(){
		return cam;
	}

	public void preAnima(){

		if(!parado){
			if(frameAtual < numeroFrames-1){

				frameAtual++;			
			}
			else{
				frameAtual = 0;
				desAnima();
			}

			cam.setXImagem(xTile*frameAtual);
		}
	}

}
