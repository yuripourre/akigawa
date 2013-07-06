package akigawa.menu;

import etyllica.camada.Camada;
import etyllica.camada.CamadaEstatica;
import etyllica.gui.Botao;
import etyllica.nucleo.Gerenciador;



public class MenuCriarAventura extends MenuAkigawa{

	//id = 11

	private Camada kanji;
	private Camada botaoTitulo;
	private Camada tituloLabel;


	private Botao botaoVoltar;
	private CamadaEstatica voltar;
	private CamadaEstatica voltarOnm;

	private Camada botaoMaps;
	
	//Labels
	/*
	private Camada criarSalaLabel;
	private Camada procurarSalaLabel;
	private Camada perfilLabel;
	*/

	//CamadaTexto t;
	//Musica music = new Musica();

	public MenuCriarAventura(Gerenciador app, int id){

		super(app,id);

		//carrega();

	}

	public void carrega(){

		botaoTitulo = new Camada(0,18,diretorio+"gui/"+"botaocomp.png");
		g.centralizaX(botaoTitulo);

		tituloLabel = new Camada(g.carregaCamada(lang+"criarsala.png"));
		tituloLabel.centraliza(botaoTitulo);

		kanji = new Camada(diretorio+"gui/kanji.png");
		kanji.centralizaX(0,largura);
		kanji.setY(16);

		voltar = new CamadaEstatica(diretorio+"gui/voltar.png");
		voltarOnm = new CamadaEstatica(diretorio+"gui/voltarOnm.png");		
		
		botaoVoltar = new Botao(g,30,262,voltar,voltarOnm);
		
		botaoMaps = g.novaCamada(diretorio+"gui/botaomaps.png");
		botaoMaps.centralizaY(botaoVoltar);
		botaoMaps.setX(150);
		
		carregando = 100;
		
	}
	public int gerencia(){
		
		botaoVoltar.gerencia();

		//if(botao[0].getAcionado()>0){
		//	return 111;
		//}
		//else 
			if(botaoVoltar.getAcionado()>0){
			return id/10;
		}

		return id;

	}

	public void desenha(){

		g.desenha(kanji);
		g.desenha(botaoTitulo);
		g.desenha(tituloLabel);

		/*
		for(int i=0;i<numeroBotoes;i++)
		{	
			botao[i].desenha();
		}
		*/
		
		botaoVoltar.desenha();
		g.desenha(botaoMaps);
	}

}
