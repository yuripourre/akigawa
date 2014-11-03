package akigawa.vilarejo;

import java.awt.Color;

import akigawa.fase.FaseAlvo;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.TextLayer;


public class VilaInicial extends Village{

	private ImageLayer background;
	
	private ImageLayer guardLayer;
	private ImageLayer boatmanLayer;
	private ImageLayer blacksmithLayer;
	private ImageLayer seamstressLayer;

	private ImageLayer alvo;
	
	private Estabelecimento boatman;
	private Estabelecimento seamstress;

	//Nome Mouse
	private TextLayer targetName;

	public VilaInicial(int w, int h) {
		super(w, h);
	}
	
	public void load() {

		background = new ImageLayer(diretorioFase+"vilainicial/vila.png");

		guardLayer = new ImageLayer(diretorioFase+"vilainicial/minigatevect.png");
		guardLayer.setCoordinates(356,80);

		//
		boatmanLayer = new ImageLayer(diretorioFase+"vilainicial/ocasa.png");
		boatmanLayer.setCoordinates(170,240);

		blacksmithLayer = new ImageLayer(diretorioFase+"vilainicial/fcasa.png");
		blacksmithLayer.setCoordinates(260,180);

		seamstressLayer = new ImageLayer(diretorioFase+"vilainicial/rcasa.png");
		seamstressLayer.setCoordinates(540,250);

		alvo = new ImageLayer(diretorioFase+"vilainicial/alvo.png");
		alvo.setCoordinates(520,140);

		targetName = new TextLayer("");
		targetName.setBorder(true);
		targetName.setColor(Color.WHITE);
		targetName.setBorderColor(Color.BLACK);
		targetName.setBorderWidth(4f);

		//Estabelecimentos
		boatman = new Boatman(w,h,this);
		seamstress = new Seamstress(w,h,this);

		loading = 100;
	}
	public void draw (Graphic g) {

		background.draw(g);
		guardLayer.draw(g);
		boatmanLayer.draw(g);
		blacksmithLayer.draw(g);

		seamstressLayer.draw(g);
		alvo.draw(g);

		targetName.draw(g);

	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		
		boolean overSomething = false;
		
		if(guardLayer.onMouse(event)) {
			targetName.setText("Guarda");
			overSomething = true;
		}else if(boatmanLayer.onMouse(event)) {
			
			targetName.setText("Barqueiro");
			overSomething = true;
			
			if(event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
				nextApplication = boatman;
			}
			
		}else if(blacksmithLayer.onMouse(event)) {
			
			targetName.setText("Ferreiro");
			overSomething = true;
			
		}else if(seamstressLayer.onMouse(event)) {
			
			targetName.setText("Alfaiate");
			overSomething = true;
			
			if(event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
				nextApplication = seamstress;
			}
			
		}else if(alvo.onMouse(event)) {
			targetName.setText("Alvo");
			overSomething = true;
			
			if(event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
				nextApplication = new FaseAlvo(w, h);
			}
			
		}else{
			targetName.setVisible(false);
			return GUIEvent.NONE;
		}
		
		
		if(overSomething) {			
			targetName.setCoordinates(event.getX()-50, event.getY());
			targetName.setVisible(true);
		}		
		
		return GUIEvent.NONE;

	}

	 

}
