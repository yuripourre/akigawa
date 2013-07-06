package akigawa.menu;


import etyllica.camada.Camada;
import etyllica.camada.CamadaAnimacao;
import etyllica.camada.CamadaTexto;
import etyllica.nucleo.Gerenciador;


public class MenuCarregando extends MenuAkigawa{

	//id = Coringa

	private Camada kanji;
	private Camada carregando;
	private CamadaAnimacao reticencias;
	
	
	private Camada barra;
	private Camada barraFill;
	
	private CamadaTexto text;
	private CamadaTexto porcent;

	public MenuCarregando(Gerenciador app, int id){

		super(app,id);
		//Só o MenuCarregando pode ser assim;
		carrega();

	}

	public void carrega(){
		
		kanji = new Camada(diretorio+"gui/kanji.png");
		g.centralizaX(kanji);
		
		carregando = new Camada(lang+"carregando.png");		
		g.centralizaX(carregando);
		g.centralizaY(carregando);

		reticencias = new CamadaAnimacao(520,220,38,12);
		reticencias.igualaImagem("imagens/gui/carregando/3pontos.png");
		reticencias.setAnimaEmX(true);
		reticencias.setNumeroFrames(3);
		reticencias.anima();
		
		barra = new Camada("imagens/gui/barra.png");
		g.centralizaX(barra);
		barra.setY(320);
		
		barraFill = new Camada("imagens/gui/barrafill.png");		
		g.centralizaX(barraFill);
		barraFill.setY(320);
		//barraFill.setXImagem(400);
		
		porcent = new CamadaTexto(200,200,"666");
		porcent.setCorDifusa(0xff,0xff,0xff);
		porcent.setTexto("100%");
		g.centralizaX(porcent);
		
		text = new CamadaTexto(200,280,"Carregando Imagens");
		g.centralizaX(text);
		
	}

	public int gerencia(){
		
		return id;

	}

	public void desenha(){

		g.desenha(kanji);
		
		g.desenha(carregando);
		g.desenha(reticencias);
			
		g.desenha(barra);
		g.desenha(barraFill);
		
		g.desenha(porcent);
	}

	public void setId(int id){
		this.id = id;
	}
	
	public void setText(int andamento){
		porcent.setTexto(Integer.toString(andamento)+"%");
		porcent.centraliza(barra);
		barraFill.setXImagem(barraFill.getXLimite()-andamento*4);
	}
}
