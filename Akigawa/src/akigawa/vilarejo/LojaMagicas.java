package akigawa.vilarejo;

import java.util.ArrayList;

import akigawa.fase.Fase;
import akigawa.vilarejo.Item.Objetos;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.Button;
import br.com.etyllica.gui.button.ImageButton;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.StaticLayer;
import br.com.etyllica.layer.TextLayer;

public class LojaMagicas extends Fase {

	private ImageLayer fundo;

	//Gui
	private ImageLayer rect;
	private ImageLayer build;
	private TextLayer building;

	private ImageButton[] vender;
	private ImageButton[] miniBotoes;

	private StaticLayer card;
	private StaticLayer cardOnm;
	private StaticLayer cardClk;

	private StaticLayer mini;
	private StaticLayer miniOnm;
	private StaticLayer miniAtivo;

	private ImageLayer minikunai;
	private ImageLayer minishuriken;

	private ImageButton botaoVoltar;
	private StaticLayer voltar;
	private StaticLayer voltarOnm;

	//Estoque
	private ArrayList<Item> estoqueMagico = new ArrayList<Item>();
	
	private TextLayer nomeItem[][];
	private ImageLayer miniItem;
	private TextLayer quantidade[];
	private TextLayer req[];

	public LojaMagicas(int x, int y) {
		super(x, y);
	} 

	public void load() {

		voltar = new StaticLayer(diretorio+"gui/voltarmini.png");
		voltarOnm = new StaticLayer(diretorio+"gui/voltarminionm.png");
		botaoVoltar = new ImageButton(10,350,voltar,voltarOnm);
		
		this.add(botaoVoltar);


		fundo = new ImageLayer(diretorioFase+"vilainicial/blacksmith.png");
		loading = 50;

		rect = new ImageLayer(diretorioFase+"vilainicial/loja/shoprect.png");
		rect.setX(10);
		rect.setY(10);
		
		build = new ImageLayer(diretorioFase+"vilainicial/loja/build.png");
		build.centralizeX(0, w);
		build.setY(350);
		
		building = new TextLayer(0,0,"N�o h� nada sendo constru�do");
		building.setColor(0xff,0xff,0xff);
		building.setSize(22f);
		building.centralize(build);

		card = new StaticLayer(diretorioFase+"vilainicial/loja/card.png");
		cardOnm = new StaticLayer(diretorioFase+"vilainicial/loja/cardonm.png");
		cardClk = new StaticLayer(diretorioFase+"vilainicial/loja/cardclk.png");

		mini = new StaticLayer(diretorioFase+"vilainicial/loja/minibot.png");
		miniOnm = new StaticLayer(diretorioFase+"vilainicial/loja/minionm.png");
		miniAtivo = new StaticLayer(diretorioFase+"vilainicial/loja/miniactive.png");		

		int espacamento = 10;
		
		vender = new ImageButton[3];
		for(int i=0;i<3;i++) {
			vender[i] = new ImageButton(103,rect.getY()+espacamento+40,card,cardOnm,cardClk);
			
			this.add(vender[i]);
		}

		vender[1].centralizeX(rect);
		vender[0].setX(vender[1].getX()-vender[0].getW()-espacamento);
		vender[2].setX(vender[1].getX()+vender[1].getW()+espacamento);


		miniBotoes = new ImageButton[2];
		miniBotoes[0] = new ImageButton(150,260,mini,miniOnm,miniAtivo);
		miniBotoes[1] = new ImageButton(220,260,mini,miniOnm,miniAtivo);

		minikunai = new ImageLayer(diretorioFase+"vilainicial/loja/minikunai.png");
		minikunai.centralize(miniBotoes[0]);

		minishuriken = new ImageLayer(diretorioFase+"vilainicial/loja/minishuriken.png");
		minishuriken.centralize(miniBotoes[1]);

		StaticLayer pVida = new StaticLayer(diretorio+"loja/pvida.png");
		StaticLayer pEnergia = new StaticLayer(diretorio+"loja/penergia.png");
		estoqueMagico.add(new Item(Objetos.POCAO_VIDA,pVida));
		estoqueMagico.add(new Item(Objetos.POCAO_ENERGIA,pEnergia));
		
		nomeItem = new TextLayer[3][3];
		quantidade = new TextLayer[3];
		req = new TextLayer[3];
		
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				nomeItem[j][i] = new TextLayer(0,0,"");		
			}
			quantidade[i] = new TextLayer(0,0,"");
			req[i] = new TextLayer(0,0,"");
		}
		
		setItem(0,0,estoqueMagico);
		setItem(1,1,estoqueMagico);
		setItem(1,2,estoqueMagico);
		
		loading = 100;
	}
	
	private void setItem(int itm, int a, ArrayList<Item> estoque){
		
		int tamanhoFonte = 20;
		int offsetY = 12;
		
		switch(estoque.get(itm).getNome().split(" ").length){
		case 3:
			nomeItem[2][a].setText(estoque.get(itm).getNome().split(" ")[2]);
			nomeItem[2][a].setColor(0xff,0xff,0xff);
			nomeItem[2][a].setSize(tamanhoFonte);
			nomeItem[2][a].centralizeX(vender[a]);
			nomeItem[2][a].setY(vender[a].getY()+offsetY+tamanhoFonte*2);			
		case 2:
			nomeItem[1][a].setText(estoque.get(itm).getNome().split(" ")[1]);
			nomeItem[1][a].setColor(0xff,0xff,0xff);
			nomeItem[1][a].setSize(tamanhoFonte);
			nomeItem[1][a].centralizeX(vender[a]);
			nomeItem[1][a].setY(vender[a].getY()+offsetY+tamanhoFonte);
		case 1:
		nomeItem[0][a].setText(estoque.get(itm).getNome().split(" ")[0]);
		nomeItem[0][a].setColor(0xff,0xff,0xff);
		nomeItem[0][a].setSize(tamanhoFonte);
		nomeItem[0][a].centralizeX(vender[a]);
		nomeItem[0][a].setY(vender[a].getY()+offsetY);
		break;
		}
		quantidade[a].setText("x 10");
		quantidade[a].setColor(0xff,0xff,0xff);
		quantidade[a].setSize(tamanhoFonte);
		quantidade[a].setY(210);
		quantidade[a].centralizeX(vender[a]);
	}
	
	public void draw(Graphic g) {
		fundo.draw(g);

		rect.draw(g);
		build.draw(g);
		building.draw(g);

		miniBotoes[0].draw(g);
		miniBotoes[1].draw(g);

		minikunai.draw(g);
		minishuriken.draw(g);
		
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				nomeItem[j][i].draw(g);		
			}
			quantidade[i].draw(g);
		}

	}
	public int gerencia(){

		for(int i=0;i<3;i++){
			vender[i].gerencia();
			if(vender[i].getAcionado()>0){

			}
		}
				
		for(int i=0;i<2;i++){
			miniBotoes[i].gerencia();
		}
		
		botaoVoltar.gerencia();
		
		if(botaoVoltar.getAcionado()>0){
			nextApplication = new VilaInicial(w, h);//Vila Inicial
		}

	}
}
