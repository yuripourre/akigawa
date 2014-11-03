package akigawa.jogador;

import java.awt.image.BufferedImage;

import br.com.etyllica.layer.AnimatedLayer;
import br.com.etyllica.layer.BufferedLayer;

public class Ninja extends AnimatedLayer {

	private BufferedLayer sombra;
	private BufferedLayer kimono;
	private BufferedLayer pele;

	private BufferedLayer cam;

	private int r;
	private int g;
	private int b;

	public Ninja(int x, int y) {
		super(x, y, 75, 125);

		setAnimateHorizontally(true);
		setFrames(5);
		setSpeed(80);
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

		buf.getGraphics().drawImage(sombra.getBuffer(),0,0,null);
		buf.getGraphics().drawImage(kimono.getBuffer(),0,0,null);
		buf.getGraphics().drawImage(pele.getBuffer(),0,0,null);

		cam = new BufferedLayer(x, y, buf);
		
		cam.setW(75);
	}

	public BufferedLayer getLayer() {
		return cam;
	}
	
	@Override
	protected void notifyFrameChangeListener(long now) {
		super.notifyFrameChangeListener(now);
		cam.setXImage(tileW*currentFrame);
	}

}
