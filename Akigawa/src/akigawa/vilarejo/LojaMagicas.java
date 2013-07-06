package akigawa.vilarejo;

import java.util.ArrayList;

import etyllica.camada.Camada;
import etyllica.camada.CamadaEstatica;
import etyllica.camada.CamadaTexto;
import etyllica.gui.Botao;
import etyllica.nucleo.Gerenciador;


import akigawa.fase.Fase;
import akigawa.vilarejo.Item.Objetos;

public class LojaMagicas extends Fase{

	private Camada fundo;

	//Gui
	private Camada rect;
	private Camada build;
	private CamadaTexto building;

	private Botao[] vender;
	private Botao[] miniBotoes;

	private CamadaEstatica card;
	private CamadaEstatica cardOnm;
	private CamadaEstatica cardClk;

	private CamadaEstatica mini;
	private CamadaEstatica miniOnm;
	private CamadaEstatica miniAtivo;

	private Camada minikunai;
	private Camada minishuriken;

	private Botao botaoVoltar;
	private CamadaEstatica voltar;
	private CamadaEstatica voltarOnm;

	//Estoque
	private ArrayList<Item> estoqueMagico = new ArrayList<Item>();
	
	private CamadaTexto nomeItem[][];
	private Camada miniItem;
	private CamadaTexto quantidade[];
	private CamadaTexto req[];

	public LojaMagicas(Gerenciador app, int id) {
		super(app, id);
	} 

	public void carrega(){

		voltar = new CamadaEstatica(diretorio+"gui/voltarmini.png");
		voltarOnm = new CamadaEstatica(diretorio+"gui/voltarminionm.png");
		botaoVoltar = new Botao(g,10,350,voltar,voltarOnm);


		fundo = new Camada(diretorioFase+"vilainicial/blacksmith.png");
		carregando = 50;

		rect = new Camada(diretorioFase+"vilainicial/loja/shoprect.png");
		rect.setX(10);
		rect.setY(10);
		
		build = new Camada(diretorioFase+"vilainicial/loja/build.png");
		g.centralizaX(build);
		build.setY(350);
		
		building = new CamadaTexto(0,0,"Não há nada sendo construído");
		building.setCorDifusa(0xff,0xff,0xff);
		building.setTamanhoFonte(22);
		building.centraliza(build);

		card = new CamadaEstatica(diretorioFase+"vilainicial/loja/card.png");
		cardOnm = new CamadaEstatica(diretorioFase+"vilainicial/loja/cardonm.png");
		cardClk = new CamadaEstatica(diretorioFase+"vilainicial/loja/cardclk.png");

		mini = new CamadaEstatica(diretorioFase+"vilainicial/loja/minibot.png");
		miniOnm = new CamadaEstatica(diretorioFase+"vilainicial/loja/minionm.png");
		miniAtivo = new CamadaEstatica(diretorioFase+"vilainicial/loja/miniactive.png");		

		int espacamento = 10;
		
		vender = new Botao[3];
		for(int i=0;i<3;i++){
			vender[i] = new Botao(g,103,rect.getY()+espacamento+40,card,cardOnm,cardClk);
		}

		vender[1].centralizaX(rect);
		vender[0].setX(vender[1].getX()-vender[0].getXLimite()-espacamento);
		vender[2].setX(vender[1].getX()+vender[1].getXLimite()+espacamento);


		miniBotoes = new Botao[2];
		miniBotoes[0] = new Botao(g,150,260,mini,miniOnm,miniAtivo);
		miniBotoes[1] = new Botao(g,220,260,mini,miniOnm,miniAtivo);

		minikunai = new Camada(diretorioFase+"vilainicial/loja/minikunai.png");
		minikunai.centraliza(miniBotoes[0]);

		minishuriken = new Camada(diretorioFase+"vilainicial/loja/minishuriken.png");
		minishuriken.centraliza(miniBotoes[1]);

		CamadaEstatica pVida = new CamadaEstatica(diretorio+"loja/pvida.png");
		CamadaEstatica pEnergia = new CamadaEstatica(diretorio+"loja/penergia.png");
		estoqueMagico.add(new Item(Objetos.POCAO_VIDA,pVida));
		estoqueMagico.add(new Item(Objetos.POCAO_ENERGIA,pEnergia));
		
		nomeItem = new CamadaTexto[3][3];
		quantidade = new CamadaTexto[3];
		req = new CamadaTexto[3];
		
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				nomeItem[j][i] = new CamadaTexto(0,0,"");		
			}
			quantidade[i] = new CamadaTexto(0,0,"");
			req[i] = new CamadaTexto(0,0,"");
		}
		
		setItem(0,0,estoqueMagico);
		setItem(1,1,estoqueMagico);
		setItem(1,2,estoqueMagico);
		
		carregando = 100;
	}
	
	private void setItem(int itm, int a, ArrayList<Item> estoque){
		
		int tamanhoFonte = 20;
		int offsetY = 12;
		
		switch(estoque.get(itm).getNome().split(" ").length){
		case 3:
			nomeItem[2][a].setTexto(estoque.get(itm).getNome().split(" ")[2]);
			nomeItem[2][a].setCorDifusa(0xff,0xff,0xff);
			nomeItem[2][a].setTamanhoFonte(tamanhoFonte);
			nomeItem[2][a].centralizaX(vender[a]);
			nomeItem[2][a].setY(vender[a].getY()+offsetY+tamanhoFonte*2);			
		case 2:
			nomeItem[1][a].setTexto(estoque.get(itm).getNome().split(" ")[1]);
			nomeItem[1][a].setCorDifusa(0xff,0xff,0xff);
			nomeItem[1][a].setTamanhoFonte(tamanhoFonte);
			nomeItem[1][a].centralizaX(vender[a]);
			nomeItem[1][a].setY(vender[a].getY()+offsetY+tamanhoFonte);
		case 1:
		nomeItem[0][a].setTexto(estoque.get(itm).getNome().split(" ")[0]);
		nomeItem[0][a].setCorDifusa(0xff,0xff,0xff);
		nomeItem[0][a].setTamanhoFonte(tamanhoFonte);
		nomeItem[0][a].centralizaX(vender[a]);
		nomeItem[0][a].setY(vender[a].getY()+offsetY);
		break;
		}
		quantidade[a].setTexto("x 10");
		quantidade[a].setCorDifusa(0xff,0xff,0xff);
		quantidade[a].setTamanhoFonte(tamanhoFonte);
		quantidade[a].setY(210);
		quantidade[a].centralizaX(vender[a]);
	}
	
	public void desenha(){
		g.desenha(fundo);

		g.desenha(rect);
		g.desenha(build);
		g.desenha(building);

		for(int i=0;i<3;i++){
			vender[i].desenha();
		}

		miniBotoes[0].desenha();
		miniBotoes[1].desenha();

		g.desenha(minikunai);
		g.desenha(minishuriken);
		
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				g.desenha(nomeItem[j][i]);		
			}
			g.desenha(quantidade[i]);
		}

		botaoVoltar.desenha();
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
			return 1;//Vila Inicial
		}

		return id;
	}
}
