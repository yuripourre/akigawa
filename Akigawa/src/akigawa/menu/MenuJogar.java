package akigawa.menu;

import br.com.etyllica.core.event.GUIAction;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.button.ImageButton;
import br.com.etyllica.gui.label.ImageLabel;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.StaticLayer;

public class MenuJogar extends AkigawaBackMenu{

	//id = 11

	private ImageLayer kanji;
	private ImageLayer botaoTitulo;
	private ImageLayer tituloLabel;

	private StaticLayer botaoNormal;
	private StaticLayer botaoOnm;

	private final int numeroBotoes = 3;

	private ImageButton botao[];
	

	//Labels
	private ImageLabel criarSalaLabel;
	private ImageLabel procurarSalaLabel;
	private ImageLabel perfilLabel;

	//Musica music = new Musica();

	public MenuJogar(int w, int h){
		super(w,h);
		botaoVoltar.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new GUIAction(this, "back"));
	}

	public void load(){

		botaoTitulo = new ImageLayer(0,14,"gui/botaocomp.png");
		botaoTitulo.centralizaX(0,w);

		tituloLabel = new ImageLayer(lang+"jogar.png");
		tituloLabel.centraliza(botaoTitulo);

		kanji = new ImageLayer("gui/kanji.png");
		kanji.centralizaX(0, w);
		kanji.setY(0);

		botaoNormal = new StaticLayer("gui/botao.png");
		botaoOnm = new StaticLayer("gui/botaoonm.png");
		
		//Labels
		criarSalaLabel = new ImageLabel(lang+"criarsala.png");

		procurarSalaLabel = new ImageLabel(lang+"procurarsala.png");

		perfilLabel = new ImageLabel(lang+"perfil.png");

		int alturaBotao = 190;

		botao = new ImageButton[numeroBotoes];
		
		for(int i=0;i<numeroBotoes;i++)
		{			
			botao[i] = new ImageButton(w/2-416/2,alturaBotao+80*i,botaoNormal,botaoOnm);
			//botao[i].centralizaX(0, w);
			//botao[i].setSom(app.getSomBeep());
			add(botao[i]);
		}

		botao[0].setLabel(criarSalaLabel);
		botao[0].addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new GUIAction(this, "showCreateRoom"));
		
		botao[1].setLabel(procurarSalaLabel);
		botao[1].addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new GUIAction(this, "showFindRoom"));
		
		botao[2].setLabel(perfilLabel);
	
		loading = 100;

	}
	
	public void showCreateRoom(){
		this.returnApplication = new MenuCriarSala(w,h);
	}
	
	public void showFindRoom(){
		this.returnApplication = new MenuProcurarSala(w,h);
	}

	public void draw(Grafico g){

		kanji.draw(g);
		botaoTitulo.draw(g);
		tituloLabel.draw(g);

	}

}
