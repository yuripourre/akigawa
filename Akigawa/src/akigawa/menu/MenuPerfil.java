package akigawa.menu;

import etyllica.camada.Camada;
import etyllica.camada.CamadaEstatica;
import etyllica.camada.CamadaManipulavel;
import etyllica.gui.Botao;
import etyllica.nucleo.Gerenciador;


public class MenuPerfil extends MenuAkigawa{

	//id = 113

	private Camada kanji;
	private Camada botaoTitulo;
	private Camada tituloLabel;

	private Botao botaoVoltar;
	private CamadaEstatica voltar;
	private CamadaEstatica voltarOnm;
	
	
	//Ninja Descamado
	private Camada ninjaGlobe;
	
	private Camada pele;
	private CamadaManipulavel kimono;
	private CamadaManipulavel sombra;
	
	//Barras
	private Camada barraVermelha;
	private Camada barraVerde;
	private Camada barraAzul;

	//Indicadores
	private Camada indicadorV;
	private Camada indicadorD;
	private Camada indicadorA;
	
	//Indicadores
	//private Camada medalhas;
	
	private Botao botaoAmigos;
	private Camada amigosLabel;

	public MenuPerfil(Gerenciador app, int id){

		super(app,id);

		//carrega();

	}

	public void carrega(){

		botaoTitulo = new Camada(0,14,diretorio+"gui/"+"botaocomp.png");
		g.centralizaX(botaoTitulo);

		tituloLabel = new Camada(lang+"perfil.png");
		g.centralizaX(tituloLabel);
		tituloLabel.centraliza(botaoTitulo);

		kanji = new Camada(diretorio+"gui/kanji.png");
		g.centralizaX(kanji);
		//kanji.setY();

		voltar = new CamadaEstatica(diretorio+"gui/voltarmini.png");
		voltarOnm = new CamadaEstatica(diretorio+"gui/voltarminionm.png");
		
		botaoVoltar = new Botao(g,10,350,voltar,voltarOnm);
		//botaoVoltar.setSom(app.getSomBeep());
		
		//NinjaGlobe
		ninjaGlobe = new Camada(diretorio+"perfil/ninjaglobe.png");
		g.centralizaX(ninjaGlobe);
		ninjaGlobe.setY(100);
		
		//Ninja

		kimono = new CamadaManipulavel(diretorio+"perfil/kimono.png");
		kimono.centraliza(ninjaGlobe);
		
		sombra = new CamadaManipulavel(diretorio+"perfil/sombra.png");
		sombra.centraliza(ninjaGlobe);
		
		pele = new Camada(diretorio+"perfil/pele.png");
		pele.centraliza(ninjaGlobe);
		
		
		//Barras
		
		barraVermelha = new Camada(diretorio+"perfil/barrav.png");
		g.centralizaX(barraVermelha);
		barraVermelha.setY(270);
		
		
		indicadorV = new Camada(diretorio+"perfil/miniv.png");
		indicadorV.centralizaY(barraVermelha);
		indicadorV.setX(barraVermelha.getX()-indicadorV.getXLimite()/2);
		
		barraVerde = new Camada(diretorio+"perfil/barrad.png");
		g.centralizaX(barraVerde);
		barraVerde.setY(320);
		
		indicadorD = new Camada(diretorio+"perfil/minid.png");
		indicadorD.centralizaY(barraVerde);
		indicadorD.setX(barraVerde.getX()-indicadorD.getXLimite()/2);
		
		barraAzul = new Camada(diretorio+"perfil/barraa.png");
		g.centralizaX(barraAzul);
		barraAzul.setY(370);
		
		indicadorA = new Camada(diretorio+"perfil/minia.png");
		indicadorA.centralizaY(barraAzul);
		indicadorA.setX(barraVerde.getX()-indicadorA.getXLimite()/2);
		
		//Lista de Amigos
		amigosLabel = new Camada(lang+"mlistaamigos.png");
		botaoAmigos = new Botao(g,500,155,new CamadaEstatica(diretorio+"gui/botaomini.png"),new CamadaEstatica(diretorio+"gui/botaominionm.png"));
		//botaoAmigos = new Botao(app,480,350,voltar,voltarOnm);
		botaoAmigos.setLabel(amigosLabel);	
		//botaoAmigos.setSom(app.getSomBeep());
		carregando = 100;
		
	}
	public int gerencia(){
		
		botaoVoltar.gerencia();
		botaoAmigos.gerencia();

		if(botaoVoltar.getAcionado()>0){
			return id/10;
		}
		else if(botaoAmigos.getAcionado()>0){
			return 1131;
		}
		

		gerenciaBarras();
		
		return id;
	}
	
	private void gerenciaBarras(){
		if(mouse.getPressionado()==1){
			if(mouse.sobMouse(barraVermelha)){
				indicadorV.setX(mouse.getX()-indicadorV.getXLimite()/2);
				
				int cor = mouse.getX()-barraVermelha.getX();
				
				if(kimono.getOffsetVermelho()!=cor){
					kimono.mudaVermelho(cor);
					sombra.mudaVermelho(cor/2);
				}
			}
			else if(mouse.sobMouse(barraVerde)){
				indicadorD.setX(mouse.getX()-indicadorD.getXLimite()/2);
				
				int cor = mouse.getX()-barraVerde.getX();
				
				if(kimono.getOffsetVerde()!=cor){
					kimono.mudaVerde(cor);
					sombra.mudaVerde(cor/2);
				}
			}
			else if(mouse.sobMouse(barraAzul)){
				indicadorA.setX(mouse.getX()-indicadorA.getXLimite()/2);
				
				int cor = mouse.getX()-barraAzul.getX();
				
				if(kimono.getOffsetAzul()!=cor){
					kimono.mudaAzul(cor);
					sombra.mudaAzul(cor/2);
				}
			}
		}
	}

	public void desenha(){

		g.desenha(kanji);
		g.desenha(botaoTitulo);
		g.desenha(tituloLabel);
		
		//g.desenha(botaoVoltar);
		botaoVoltar.desenha();
		
		g.desenha(ninjaGlobe);
		
		g.desenha(sombra);
		g.desenha(kimono);
		g.desenha(pele);
		
		g.desenha(barraVermelha);
		g.desenha(barraVerde);
		g.desenha(barraAzul);
		
		g.desenha(indicadorV);
		g.desenha(indicadorD);
		g.desenha(indicadorA);
		
		botaoAmigos.desenha();
		
	}

}
/*
 * public class Perfil extends Principal{

	private Camada barraVermelha;
	private Camada barraVerde;
	private Camada barraAzul;

	private CamadaEstatica miniShuriken;
	private Camada[] indicador;
	
	//Ninja Descamado
	private Camada pele;
	private CamadaManipulavel kimono;
	private CamadaManipulavel detalhes;

	private int velocidade = 2;

	protected void inicia(){

		pele = new Camada(url, "perfil/ninja/pele.png");
		pele.setCoordenadas(100,100);
		kimono = new CamadaManipulavel(url, "perfil/ninja/kimono.png");
		kimono.setCoordenadas(100,100);
		detalhes = new CamadaManipulavel(url, "perfil/ninja/detalhes.png");
		detalhes.setCoordenadas(100,100);
		

		barraVermelha = new Camada(url, "perfil/barrav.png");
		barraVermelha.setCoordenadas(75, 250);
		
		barraVerde = new Camada(url, "perfil/barrad.png");
		barraVerde.setCoordenadas(75, 300);
		
		barraAzul = new Camada(url, "perfil/barraa.png");
		barraAzul.setCoordenadas(75, 350);

		miniShuriken = new CamadaEstatica(url, "perfil/minishu.png");
		indicador = new Camada[3];

		for(int i=0;i<3;i++){
			indicador[i] = new Camada();
			indicador[i].igualaImagem(miniShuriken);
			indicador[i].setX(barraVermelha.getX()-indicador[i].getXLimite()/2);
		}
		indicador[0].centralizaY(barraVermelha);
		indicador[1].centralizaY(barraVerde);
		indicador[2].centralizaY(barraAzul);

	}

	protected void gerencia(){

		if(teclado.getTecla(Tecla.PK_D)){
			kimono.setX(kimono.getX()+velocidade);
		}
		if(teclado.getTecla(Tecla.PK_A)){
			kimono.setX(kimono.getX()-velocidade);
		}
		if(teclado.getTecla(Tecla.PK_S)){
			kimono.setY(kimono.getY()+velocidade);
		}
		if(teclado.getTecla(Tecla.PK_W)){
			kimono.setY(kimono.getY()-velocidade);
		}

		gerenciaBarras();
	}
	
	private void gerenciaBarras(){
		if(mouse.getPressionado()==1){
			if(mouse.sobMouse(barraVermelha)){
				indicador[0].setX(mouse.getX()-indicador[0].getXLimite()/2);
				
				int cor = mouse.getX()-barraVermelha.getX();
				
				if(kimono.getOffsetVermelho()!=cor)
					kimono.mudaVermelho(cor);
			}
			else if(mouse.sobMouse(barraVerde)){
				indicador[1].setX(mouse.getX()-indicador[1].getXLimite()/2);
				
				int cor = mouse.getX()-barraVerde.getX();
				
				if(kimono.getOffsetVerde()!=cor)
					kimono.mudaVerde(cor);
			}
			else if(mouse.sobMouse(barraAzul)){
				indicador[2].setX(mouse.getX()-indicador[2].getXLimite()/2);
				
				int cor = mouse.getX()-barraAzul.getX();
				
				if(kimono.getOffsetAzul()!=cor)
					kimono.mudaAzul(cor);
			}
		}
	}

	protected void desenhaPrincipal(){
		desenha(detalhes);
		desenha(kimono);
		desenha(pele);
		
		desenha(barraVermelha);
		desenha(barraVerde);
		desenha(barraAzul);
		
		for(int i=0;i<3;i++){
			desenha(indicador[i]);
		}
		
	}	

}

 */

