package akigawa.vilarejo;

import java.awt.Color;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.TextLayer;


public class Boatman extends Estabelecimento{

	private ImageLayer fundo;
	
	private ImageLayer rect;
	private ImageLayer build;
	private TextLayer aviso;	

	public Boatman(int w, int h, Village village ) {	
		super(w,h,village);
	}

	@Override
	public void load() {
		fundo = new ImageLayer("npc/barqueiro.png");
		
		rect = new ImageLayer("fase/vilainicial/loja/shoprect.png");
		rect.centralizeX(0,w);
		rect.setY(10);

		build = new ImageLayer("fase/vilainicial/loja/build.png");
		build.centralizeX(0,w);
		build.setY(350);

		aviso = new TextLayer(build.getX()+30,build.getY()+46,"Você ainda não pode sair desse vilarejo.");
		aviso.setBorder(true);
		aviso.setBorderColor(new Color(0x0,0x0,0x0));
		
		aviso.setColor(0xff,0xff,0xff);
		aviso.setSize(22f);
				

		loading = 100;
	}
	
	@Override
	public void back() {
		returnApplication = new VilaInicial(w,h);
	}

	public void draw(Graphic g) {

		fundo.draw(g);
		rect.draw(g);
		build.draw(g);
		
		aviso.draw(g);
				
	}

}
