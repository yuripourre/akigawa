package akigawa.vilarejo;

import etyllica.camada.Camada;
import etyllica.camada.CamadaEstatica;
import etyllica.camada.CamadaManipulavel;
import etyllica.camada.CamadaTexto;
import etyllica.gui.Botao;
import etyllica.nucleo.Gerenciador;


public class Rouparia extends Estabelecimento{
	
	private Camada fundo;
	
	private Botao botaoVoltar;
	private CamadaEstatica voltar;
	private CamadaEstatica voltarOnm;
	
	private Botao botaoSalvar;
	private CamadaEstatica salvar;
	private CamadaEstatica salvarOnm;
	
	//private Camada ninjaGlobe;
	private Camada ninjaGlobe;
	
	//Ninja
	private Camada pele;
	private CamadaManipulavel kimono;
	private CamadaManipulavel sombra;
	
	private CamadaManipulavel seta;
	
	//Barras
	private Camada barraVermelha;
	private Camada barraVerde;
	private Camada barraAzul;

	//Indicadores
	private Camada indicadorV;
	private Camada indicadorD;
	private Camada indicadorA;

	//Coisas de Loja
	private Camada build;
	private CamadaTexto aviso;
	
	private CamadaTexto target;
	
	private int red;
	private int green;
	private int blue;
	
	public Rouparia(Gerenciador g, int id, int vila) {
		super(g, id, vila);
		
	}
	
	public void carrega(){
		
		fundo = new Camada(g.carregaCamada("imagens/npc/rouparia.png"));
		
		voltar = new CamadaEstatica(g.carregaCamada(diretorio+"gui/voltarmini.png"));
		voltarOnm = new CamadaEstatica(g.carregaCamada(diretorio+"gui/voltarminionm.png"));
		
		botaoVoltar = new Botao(g,10,350,voltar,voltarOnm);
		//botaoVoltar.setSom(app.getSomBeep());
		
		salvar = new CamadaEstatica(g.carregaCamada(diretorio+"gui/salvarmini.png"));
		salvarOnm = new CamadaEstatica(g.carregaCamada(diretorio+"gui/salvarminionm.png"));
		
		//botaoSalvar = new Botao(g,largura-salvar.getXLimite()-10,350,salvar,salvarOnm);
		botaoSalvar = new Botao(g,largura-72-10,350,salvar,salvarOnm);
		
		//NinjaGlobe
		ninjaGlobe = new Camada(g.carregaCamada(diretorio+"perfil/ninjaglobe.png"));
		g.centralizaX(ninjaGlobe);
		ninjaGlobe.setY(40);
		
		//Ninja
		red = Integer.parseInt(p.get("red"));
		green = Integer.parseInt(p.get("green"));
		blue = Integer.parseInt(p.get("blue"));
		 

		kimono = new CamadaManipulavel(g.carregaImagem(diretorio+"perfil/kimono.png"));
		kimono.centraliza(ninjaGlobe);
		
		sombra = new CamadaManipulavel(g.carregaImagem(diretorio+"perfil/sombra.png"));
		sombra.centraliza(ninjaGlobe);
		
		pele = g.novaCamada(diretorio+"perfil/pele.png");
		pele.centraliza(ninjaGlobe);
		
		//Atualiza a Cor
		kimono.mudaVermelho(red);
		sombra.mudaVermelho(red/2);
		kimono.mudaVerde(green);
		sombra.mudaVerde(green/2);
		kimono.mudaAzul(blue);
		sombra.mudaAzul(blue/2);
		
		carregando = 20;
		seta = new CamadaManipulavel(g.carregaImagem("imagens/cursor.png"));
		
		//Barras
		
		carregando = 30;
		barraVermelha = g.novaCamada(diretorio+"perfil/barrav.png");
		g.centralizaX(barraVermelha);
		barraVermelha.setY(210);
		
		carregando = 40;
		indicadorV = g.novaCamada(diretorio+"perfil/miniv.png");
		indicadorV.centralizaY(barraVermelha);
		indicadorV.setX(barraVermelha.getX()-indicadorV.getXLimite()/2+red);		
		
		carregando = 50;
		barraVerde = g.novaCamada(diretorio+"perfil/barrad.png");
		g.centralizaX(barraVerde);
		barraVerde.setY(260);
		
		carregando = 60;
		indicadorD = g.novaCamada(diretorio+"perfil/minid.png");
		indicadorD.centralizaY(barraVerde);
		indicadorD.setX(barraVerde.getX()-indicadorD.getXLimite()/2+green);
		
		carregando = 70;
		barraAzul = g.novaCamada(diretorio+"perfil/barraa.png");
		g.centralizaX(barraAzul);
		barraAzul.setY(310);
		
		carregando = 80;
		indicadorA = g.novaCamada(diretorio+"perfil/minia.png");
		indicadorA.centralizaY(barraAzul);
		indicadorA.setX(barraVerde.getX()-indicadorA.getXLimite()/2+blue);
		
		
		carregando = 90;
		build = new Camada(g.carregaCamada(diretorioFase+"vilainicial/loja/build.png"));
		g.centralizaX(build);
		build.setY(350);

		aviso = new CamadaTexto("Você pode trocar a cor da sua roupa.");
		aviso.setCorDifusa(0xff,0xff,0xff);
		aviso.setTamanhoFonte(22);
		aviso.centraliza(build);
		
		target = new CamadaTexto("");
		target.setCorDifusa(0xff,0xff,0xff);
		target.setTamanhoFonte(22);
		
		carregando = 100;
	}
	public int gerencia(){
		
		botaoVoltar.gerencia();
		if(mouse.sobMouse(botaoVoltar)){
			target.setTexto("Voltar");
			target.centralizaX(mouse);
			target.setY(mouse.getY()-30);
			target.setAparecendo(true);
		}
		else if(mouse.sobMouse(botaoSalvar)){
			target.setTexto("Salvar");
			target.centralizaX(mouse);
			target.setY(mouse.getY()-30);
			target.setAparecendo(true);
		}
		else{
			target.setAparecendo(false);			
		}
		if(botaoVoltar.getAcionado()>0){
			return vila;
		}
		
		botaoSalvar.gerencia();
		if(botaoSalvar.getAcionado()>0){
			
			
			seta.mudaVermelho(red);
			seta.mudaAzul(blue);
			seta.mudaVerde(green);
			
			mouse.mudaRGB(red,green,blue);
			
			aviso.setTexto("As Cores foram salvas.");
			aviso.centraliza(build);
			
			p.set("red", Integer.toString(red));
			p.set("green", Integer.toString(green));
			p.set("blue", Integer.toString(blue));
		}
		

		gerenciaBarras();
		
		return id;
	}
	private void gerenciaBarras(){
		if(mouse.getPressionado()==1){
			if(mouse.sobMouse(barraVermelha)){
				indicadorV.setX(mouse.getX()-indicadorV.getXLimite()/2);
				
				int fatorX = 1;
				red = mouse.getX()-barraVermelha.getX()/fatorX;
				
				if(kimono.getOffsetVermelho()!=red){
					kimono.mudaVermelho(red);
					sombra.mudaVermelho(red/2);
				}
			}
			else if(mouse.sobMouse(barraVerde)){
				indicadorD.setX(mouse.getX()-indicadorD.getXLimite()/2);
				
				green = mouse.getX()-barraVerde.getX();
				
				if(kimono.getOffsetVerde()!=green){
					kimono.mudaVerde(green);
					sombra.mudaVerde(green/2);
				}
			}
			else if(mouse.sobMouse(barraAzul)){
				indicadorA.setX(mouse.getX()-indicadorA.getXLimite()/2);
				
				blue = mouse.getX()-barraAzul.getX();
				
				if(kimono.getOffsetAzul()!=blue){
					kimono.mudaAzul(blue);
					sombra.mudaAzul(blue/2);
				}
			}
		}
	}
	
	public void desenha(){

		g.desenha(fundo);
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
		
		g.desenha(build);
		g.desenha(aviso);
		
		botaoVoltar.desenha();		
		botaoSalvar.desenha();
		g.desenha(target);
	}
	
}
