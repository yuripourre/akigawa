package akigawa.vilarejo;

import akigawa.menu.AkigawaBackMenu;

public abstract class Estabelecimento extends AkigawaBackMenu{
		
	protected Village village;
	
	public Estabelecimento(int w, int h, Village village) {
		super(w, h);
		
		this.village = village;
	}
	
	public void back(){
		returnApplication = village;
	}
}
