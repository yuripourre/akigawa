package akigawa.vilarejo;

import java.util.ArrayList;

import etyllica.camada.Camada;
import etyllica.camada.CamadaEstatica;
import etyllica.camada.CamadaTexto;
import etyllica.gui.Botao;
import etyllica.nucleo.Gerenciador;


import akigawa.vilarejo.Item.Objetos;

public class LojaArmas extends Estabelecimento{

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
	private ArrayList<Item> estoqueKunai = new ArrayList<Item>();
	private ArrayList<Item> estoqueShuriken = new ArrayList<Item>();

	private ArrayList<Item> estoqueAtual;

	private CamadaTexto nomeItem[][];
	private Camada miniatura[];
	private CamadaTexto quantidade[];
	private Camada quantidadeInf[]; 
	private CamadaTexto req[];

	private CamadaEstatica infinito;

	//Pra clicar nas setinhas
	private int itemAtual = 0;

	private boolean off;

	public LojaArmas(Gerenciador app, int id, int vila) {
		super(app, id, vila);
	} 

	public void carrega(){

		voltar = new CamadaEstatica(g.carregaCamada(diretorio+"gui/voltarmini.png"));
		voltarOnm = new CamadaEstatica(g.carregaCamada(diretorio+"gui/voltarminionm.png"));
		botaoVoltar = new Botao(g,10,350,voltar,voltarOnm);


		fundo = new Camada(g.carregaCamada(diretorioFase+"vilainicial/blacksmith.png"));
		carregando = 50;

		rect = new Camada(g.carregaCamada(diretorioFase+"vilainicial/loja/shoprect.png"));
		//rect.setX(10);
		g.centralizaX(rect);
		rect.setY(10);

		build = new Camada(g.carregaCamada(diretorioFase+"vilainicial/loja/build.png"));
		//build.setX(80);
		g.centralizaX(build);
		build.setY(350);

		building = new CamadaTexto(0,0,"N�o h� nada sendo constru�do");
		building.setCorDifusa(0xff,0xff,0xff);
		building.setTamanhoFonte(22);
		building.centraliza(build);

		card = new CamadaEstatica(g.carregaCamada(diretorioFase+"vilainicial/loja/card.png"));
		cardOnm = new CamadaEstatica(g.carregaCamada(diretorioFase+"vilainicial/loja/cardonm.png"));
		cardClk = new CamadaEstatica(g.carregaCamada(diretorioFase+"vilainicial/loja/cardclk.png"));

		mini = new CamadaEstatica(g.carregaCamada(diretorioFase+"vilainicial/loja/minibot.png"));
		miniOnm = new CamadaEstatica(g.carregaCamada(diretorioFase+"vilainicial/loja/minionm.png"));
		miniAtivo = new CamadaEstatica(g.carregaCamada(diretorioFase+"vilainicial/loja/miniactive.png"));

		infinito = new CamadaEstatica(g.carregaCamada(diretorioFase+"vilainicial/loja/infinit.png"));

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

		minikunai = new Camada(g.carregaCamada(diretorioFase+"vilainicial/loja/minikunai.png"));
		minikunai.centraliza(miniBotoes[0]);

		minishuriken = new Camada(g.carregaCamada(diretorioFase+"vilainicial/loja/minishuriken.png"));
		minishuriken.centraliza(miniBotoes[1]);

		//Item e CamadaEstatica
		CamadaEstatica kunaiTreino = new CamadaEstatica(g.carregaCamada(diretorio+"fase/loja/kunais/treino.png"));
		CamadaEstatica kunaiSimples = new CamadaEstatica(g.carregaCamada(diretorio+"fase/loja/kunais/simples.png"));
		CamadaEstatica kunaiOssos = new CamadaEstatica(g.carregaCamada(diretorio+"fase/loja/kunais/osso.png"));
		estoqueKunai.add(new Item(Objetos.KUNAI_TREINO,kunaiTreino));
		estoqueKunai.add(new Item(Objetos.KUNAI_SIMPLES,kunaiSimples));
		estoqueKunai.add(new Item(Objetos.KUNAI_OSSO,kunaiOssos));

		estoqueAtual = estoqueKunai;

		nomeItem = new CamadaTexto[3][3];
		quantidade = new CamadaTexto[3];
		quantidadeInf = new Camada[3];
		req = new CamadaTexto[3];
		miniatura = new Camada[3];

		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				nomeItem[j][i] = new CamadaTexto(0,0,"");		
			}
			quantidade[i] = new CamadaTexto(0,0,"");
			quantidadeInf[i] = new Camada(infinito.getCaminho());
			req[i] = new CamadaTexto(0,0,"");
			miniatura[i] = new Camada();
		}

		setItem(0,0,estoqueKunai);
		setItem(1,1,estoqueKunai);
		setItem(2,2,estoqueKunai);

		carregando = 100;
	}

	private void setItem(int itm, int a, ArrayList<Item> estoque){

		int tamanhoFonte = 20;
		int offsetY = 10;

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
		if(estoque.get(itm).getQuantidade()>0){
			quantidade[a].setTexto("x "+estoque.get(itm).getQuantidade());

			quantidade[a].setCorDifusa(0xff,0xff,0xff);
			quantidade[a].setTamanhoFonte(tamanhoFonte);
			quantidade[a].setY(212);
			quantidade[a].centralizaX(vender[a]);
			quantidadeInf[a].setAparecendo(false);
		}
		else{
			quantidadeInf[a].setY(216);
			quantidadeInf[a].centralizaX(vender[a]);	
			quantidade[a].setAparecendo(false);
		}



		miniatura[a].copy(estoque.get(itm).getMiniatura().getCaminho());
		miniatura[a].centralizaX(vender[a]);
		miniatura[a].setY(vender[a].getY()+86);
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
			g.desenha(miniatura[i]);
			g.desenha(quantidade[i]);
			g.desenha(quantidadeInf[i]);
		}

		botaoVoltar.desenha();
	}
	public int gerencia(){

		for(int i=0;i<3;i++){
			vender[i].gerencia();
			if(!off){
				if(vender[i].getAcionado()>0){
					off = true;
					Item item = estoqueAtual.get(itemAtual+i);
					String reqTipo = item.getReqTipo();
					int reqQuantidade = item.getReqQuantidade();
					boolean adquire = false;
					//Estoque da Vez
					if("".equals(reqTipo)){
						adquire = true;
					}
					//Items de Recorde
					//Pontos vira Item)(
					else if("pontos".equalsIgnoreCase(reqTipo)){
						//building
						if(p.getInt("recordealvo")<reqQuantidade){
							building.setTexto("Voc� precisa de "+reqQuantidade+" pontos no Tiro ao Alvo.");
						}
						else{
							adquire = true;
						}
					}
					//Demais Itens - Generalizar
					//else if("ossos".equalsIgnoreCase(reqTipo)){
					else{
						//building
						//if(p.getRecordeAlvo()<quantidade)
						int quantOssos = p.getInt(reqTipo);
						if(quantOssos==0)
							building.setTexto("Voc� precisa de "+reqQuantidade+" "+reqTipo+" mas n�o tem nenhum.");
						else if(quantOssos<reqQuantidade)
							building.setTexto("Voc� precisa de "+reqQuantidade+" "+reqTipo+" mas s� tem "+quantOssos+".");
						else{
							p.set(reqTipo, quantOssos-reqQuantidade);
							building.setTexto("Voc� acaba de adquirir "+item.getQuantidade()+" "+item.getNomePlural());
						}

					}

					if(adquire){
						if(p.getInt(item.getNome())<item.getMaxQuantidade()){		
							p.set(item.getNome(), item.getQuantidade()+p.getInt(item.getNome()));

							if(item.getQuantidade()>0)
								building.setTexto("Voc� acaba de adquirir "+item.getQuantidade()+" "+item.getNomePlural()+".");
							else
								building.setTexto("Voc� acaba de adquirir "+item.getNomePlural()+".");
						}
						else{
							building.setTexto("Voc� j� tem "+item.getNomePlural()+" demais.");
						}
					}

					building.centraliza(build);

				}
			}
		}
		if(mouse.getPressionado()==0){
			off = false;
		}


		for(int i=0;i<2;i++){
			miniBotoes[i].gerencia();
		}
		if(miniBotoes[1].getAcionado()>0){
			building.setTexto("Voc� ainda n�o pode usar Shurikens");
			building.centraliza(build);
		}

		botaoVoltar.gerencia();
		if(botaoVoltar.getAcionado()>0){
			return vila;
		}

		return id;
	}
}
