package akigawa.menu;



import etyllica.camada.Camada;
import etyllica.camada.CamadaEstatica;
import etyllica.gui.Botao;
import etyllica.nucleo.Gerenciador;



public class MenuCriarSala extends MenuAkigawa{

	//id = 11	

	private Camada kanji;
	private Camada botaoTitulo;
	private Camada tituloLabel;

	private Botao botaoVoltar;
	private CamadaEstatica voltar;
	private CamadaEstatica voltarOnm;

	private Camada botaoMaps;
	private Camada botaoMapa[];
	private int numeroMapas;
	
	//Labels
	/*
	private Camada criarSalaLabel;
	private Camada procurarSalaLabel;
	private Camada perfilLabel;
	*/

	//CamadaTexto t;
	//Musica music = new Musica();

	public MenuCriarSala(Gerenciador app, int id){

		super(app,id);

		//carrega();

	}

	public void carrega(){

		botaoTitulo = new Camada(0,14, diretorio+"gui/"+"botaocomp.png");
		g.centralizaX(botaoTitulo);

		tituloLabel = g.novaCamada(lang+"criarsala.png");
		tituloLabel.centraliza(botaoTitulo);

		kanji = new Camada(diretorio+"gui/kanji.png");
		g.centralizaX(kanji);

		voltar = new CamadaEstatica(diretorio+"gui/voltarmini.png");
		voltarOnm = new CamadaEstatica(diretorio+"gui/voltarminionm.png");
		
		botaoVoltar = new Botao(g,10,350,voltar,voltarOnm);
		//botaoVoltar.setSom(app.getSomBeep());
		
		botaoMaps = new Camada(diretorio+"gui/botaomaps.png");
		g.centralizaX(botaoMaps);
		botaoMaps.setY(230);
		
		numeroMapas = 3;
	
		int offset = 26;
		
		botaoMapa = new Camada[numeroMapas];
		
		botaoMapa[1] = g.novaCamada(diretorio+"mapa1.png");
		botaoMapa[1].centraliza(botaoMaps);
	
		botaoMapa[0] = g.novaCamada(diretorio+"mapa1.png");
		botaoMapa[0].centralizaY(botaoMaps);
		botaoMapa[0].setX(botaoMapa[1].getX()-botaoMapa[1].getXLimite()-offset);
		
		botaoMapa[2] = g.novaCamada(diretorio+"mapa1.png");
		botaoMapa[2].centralizaY(botaoMaps);
		botaoMapa[2].setX(botaoMapa[1].getX()+botaoMapa[1].getXLimite()+offset);
		
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
		
		g.desenha(botaoMapa[0]);
		g.desenha(botaoMapa[1]);
		g.desenha(botaoMapa[2]);
	}

}
