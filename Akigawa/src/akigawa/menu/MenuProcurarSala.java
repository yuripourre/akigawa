package akigawa.menu;


import etyllica.camada.Camada;
import etyllica.camada.CamadaEstatica;
import etyllica.gui.Botao;
import etyllica.nucleo.Gerenciador;


public class MenuProcurarSala extends MenuAkigawa{

	//id = 11	

	private Camada kanji;
	private Camada botaoTitulo;
	private Camada tituloLabel;

	private Botao botaoVoltar;
	private CamadaEstatica voltar;
	private CamadaEstatica voltarOnm;

	//private Camada botaoMaps;
	//private Camada botaoMapa[];
	//private int numeroMapas;
	
	//Labels
	/*
	private Camada criarSalaLabel;
	private Camada procurarSalaLabel;
	private Camada perfilLabel;
	*/

	//CamadaTexto t;
	//Musica music = new Musica();

	public MenuProcurarSala(Gerenciador app, int id){

		super(app,id);

		//carrega();

	}

	public void carrega(){

		botaoTitulo = new Camada(0,14, diretorio+"gui/"+"botaocomp.png");
		g.centralizaX(botaoTitulo);

		tituloLabel = g.novaCamada(lang+"procurarsala.png");
		tituloLabel.centraliza(botaoTitulo);

		kanji = new Camada(diretorio+"gui/kanji.png");
		g.centralizaX(kanji);

		voltar = new CamadaEstatica(diretorio+"gui/voltarmini.png");
		voltarOnm = new CamadaEstatica(diretorio+"gui/voltarminionm.png");
		
		botaoVoltar = new Botao(g,10,350,voltar,voltarOnm);
		//botaoVoltar.setSom(app.getSomBeep());
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
		
		//Salas Encontradas
		/*
		app.desenha(botaoMaps);
		
		app.desenha(botaoMapa[0]);
		app.desenha(botaoMapa[1]);
		app.desenha(botaoMapa[2]);
		*/
	}

}
