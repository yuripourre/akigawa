package akigawa.menu;


import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.button.ImageButton;
import br.com.etyllica.gui.label.ImageLabel;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.StaticLayer;


public class MenuInicial extends MenuAkigawa{

	//id = 1	

	private ImageLayer kanji;
	private ImageLayer titulo;

	private StaticLayer botaoNormal;
	private StaticLayer botaoOnm;

	private int numeroBotoes = 3;

	private ImageButton botao[] = new ImageButton[numeroBotoes];	

	//Labels
	private ImageLabel jogarLabel;
	private ImageLabel opcoesLabel;
	private ImageLabel creditosLabel;

	public MenuInicial(int w , int h){
		super(w,h);
	}

	public void load(){

		titulo = new ImageLayer(0,10,lang+"titulo.png");
		titulo.centralizaX(0, w);
		
		loading = 10;

		kanji = new ImageLayer("gui/kanji.png");
		kanji.centralizaX(0,w);

		loading = 30;
		
		botaoNormal = new StaticLayer("gui/botao.png");
		botaoOnm = new StaticLayer("gui/botaoonm.png");

		loading = 50;
		
		jogarLabel = new ImageLabel(lang+"jogar.png");

		loading = 70;
		
		opcoesLabel = new ImageLabel(lang+"opcoes.png");
		
		creditosLabel = new ImageLabel(lang+"creditos.png");

		loading = 75;
		
		int alturaBotao = 185;

		for(int i=0;i<numeroBotoes;i++)
		{			
			botao[i] = new ImageButton(w/2-412/2,alturaBotao+80*i,botaoNormal,botaoOnm);
			
			add(botao[i]);
		}
		loading = 80;

		botao[0].setLabel(jogarLabel);
		botao[0].addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new GUIAction(this, "showPlay"));
		
		botao[1].setLabel(opcoesLabel);
		botao[1].addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new GUIAction(this, "showOptions"));
		
		botao[2].setLabel(creditosLabel);
		botao[2].addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new GUIAction(this, "showCredits"));

		
		loading = 100;

	}
	
	public void showPlay(){
		this.returnApplication = new MenuJogar(w,h);
	}
	
	public void showOptions(){
		this.returnApplication = new MenuOpcoes(w,h);
	}
	
	public void showCredits(){
		this.returnApplication = new MenuCreditos(w,h);
	}

	public void draw(Grafico g){

		kanji.draw(g);
		titulo.draw(g);

	}
	
}
