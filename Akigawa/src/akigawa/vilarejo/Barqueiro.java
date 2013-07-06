package akigawa.vilarejo;

import etyllica.camada.Camada;
import etyllica.camada.CamadaEstatica;
import etyllica.camada.CamadaTexto;
import etyllica.gui.Botao;
import etyllica.nucleo.Gerenciador;


public class Barqueiro extends Estabelecimento{

	private Camada fundo;
	
	private Camada rect;
	private Camada build;
	private CamadaTexto aviso;

	private Botao botaoVoltar;
	private CamadaEstatica voltar;
	private CamadaEstatica voltarOnm; 
	

	public Barqueiro(Gerenciador app, int id, int vila) {	
		super(app, id, vila);
	}

	public void carrega(){
		fundo = new Camada(g.carregaCamada("imagens/npc/barqueiro.png"));

		voltar = new CamadaEstatica(g.carregaCamada(diretorio+"gui/voltarmini.png"));
		voltarOnm = new CamadaEstatica(g.carregaCamada(diretorio+"gui/voltarminionm.png"));

		botaoVoltar = new Botao(g,10,350,voltar,voltarOnm);
		
		rect = new Camada(g.carregaCamada(diretorioFase+"vilainicial/loja/shoprect.png"));
		g.centralizaX(rect);
		rect.setY(10);

		build = new Camada(g.carregaCamada(diretorioFase+"vilainicial/loja/build.png"));
		g.centralizaX(build);
		build.setY(350);

		aviso = new CamadaTexto("Você ainda não pode sair desse vilarejo.");
		aviso.setCorDifusa(0xff,0xff,0xff);
		aviso.setTamanhoFonte(22);
		aviso.centraliza(build);

		carregando = 100;
	}
	public int gerencia(){
		botaoVoltar.gerencia();
		if(botaoVoltar.getAcionado()>0){
			return vila;
		}

		return id;
	}
	public void desenha(){

		g.desenha(fundo);
		g.desenha(rect);
		g.desenha(build);
		g.desenha(aviso);
		
		botaoVoltar.desenha();		
	}

}
