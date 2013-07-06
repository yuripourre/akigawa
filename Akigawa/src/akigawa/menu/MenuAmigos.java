package akigawa.menu;

import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.Layer;


public class MenuAmigos extends MenuAkigawa{

	//id = 1131

	private ImageLayer kanji;
	private ImageLayer botaoTitulo;
	private ImageLayer tituloLabel;
	
	private ImageLayer corLabel;
	private ImageLayer nomeLabel;
	private ImageLayer idLabel;
	private ImageLayer estadoLabel;
	

	private Botao botaoVoltar;
	private CamadaEstatica voltar;
	private CamadaEstatica voltarOnm;

	public MenuAmigos(int w, int h){

		super(w,h);

	}
	
	@Override
	public void load(){

		kanji = new ImageLayer("gui/kanji.png");
		g.centralizaX(kanji);
		
		botaoTitulo = new ImageLayer(0,14,diretorio+"gui/botaocomp.png");
		g.centralizaX(botaoTitulo);

		tituloLabel = new ImageLayer(lang+"opcoes.png");
		g.centralizaX(tituloLabel);
		tituloLabel.centraliza(botaoTitulo);
		
		corLabel = new Camada(100,115, lang+"mcor.png");
		nomeLabel = new Camada(250,115, lang+"mnome.png");
		idLabel = new Camada(550,115, lang+"mid.png");
		estadoLabel = new Camada(605,115, lang+"mestado.png");		

		voltar = new CamadaEstatica(diretorio+"gui/voltarmini.png");
		voltarOnm = new CamadaEstatica(diretorio+"gui/voltarminionm.png");
		
		botaoVoltar = new Botao(g,10,350,voltar,voltarOnm);
		//botaoVoltar.setSom(g.getSomBeep());
		
	}
	@Override
	public int gerencia(){
		
		botaoVoltar.gerencia();

 
		if(botaoVoltar.getAcionado()>0){
			return id/10;
		}

		return id;

	}
	@Override
	public void desenha(){

		g.desenha(kanji);
		g.desenha(botaoTitulo);
		g.desenha(tituloLabel);
		
		g.desenha(corLabel);
		g.desenha(nomeLabel);
		g.desenha(idLabel);
		g.desenha(estadoLabel);

		/*
		for(int i=0;i<numeroBotoes;i++)
		{	
			botao[i].desenha();
		}
		*/
		
		botaoVoltar.desenha();
		
		//Salas Encontradas
		/*
		g.desenha(botaoMaps);
		
		g.desenha(botaoMapa[0]);
		g.desenha(botaoMapa[1]);
		g.desenha(botaoMapa[2]);
		*/
	}

}

