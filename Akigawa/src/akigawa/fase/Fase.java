package akigawa.fase;


import br.com.etyllica.layer.BufferedLayer;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.StaticLayer;
import etyllica.nucleo.Gerenciador;
import etyllica.nucleo.Perfil;
import etyllica.camada.Camada;
import etyllica.camada.CamadaEstatica;
import etyllica.camada.CamadaManipulavel;


import akigawa.jogador.Sprite;
import akigawa.menu.MenuAkigawa;


public abstract class Fase extends MenuAkigawa{
	
	private boolean publica;
	private boolean mic;
	
	protected int numJogadores;
	protected int maxJogadores;
	
	protected String diretorioFase;
	
	//Camadas do Jogador
	//e dos amigos
	protected ImageLayer pele;
	protected BufferedLayer kimono;
	protected BufferedLayer sombra;

	protected Perfil p;
	
	public Fase(int w, int h) {
		super(w, h);
	
		diretorioFase = "fase/";
		publica = true;
		//numJogadores = app.getNumJogadores();
		numJogadores = 1;
		//maxJogadores = app.getMaxJogadores();
		maxJogadores = 1;
		
		p = app.getPerfil();
	}
	
	//public getCorrendo()...
	
	
	//M�todos de Sala P�blica
	//M�todos de Microfone
	//public void metodos
	//Sprites
	public Sprite getArremessando(int red, int green, int blue){
		
		StaticLayer aPele = new StaticLayer("imagens/jogador/arremesso/pele.png");

		BufferedLayer aKimono = new BufferedLayer("imagens/jogador/arremesso/kimono.png");
		aKimono.offsetRGB(red, green, blue);

		BufferedLayer aSombra = new BufferedLayer("imagens/jogador/arremesso/sombra.png");

		int sombra = 40;

		red -= (red*sombra)/100;
		green -= (green*sombra)/100;
		blue -= (blue*sombra)/100;

		aSombra.offsetRGB(red, green, blue);

		//Sprite arremesso = new Sprite(aPele.getCaminho(), aKimono.getCaminho(), aSombra.getCaminho());
		Sprite arremesso = new Sprite(g.carregaImagem(aPele.getCaminho()), aKimono.getImagemBuffer(), aSombra.getImagemBuffer());

		return arremesso;
	}

	public Sprite getCorrendo(int red, int green, int blue){
		//CamadaEstatica cPele = new CamadaEstatica(url, "imagens/jogador/correndo/pele.png");
		StaticLayer cPele = new StaticLayer("imagens/jogador/correndo/ninja.png");

		CamadaManipulavel cKimono = new CamadaManipulavel(g.carregaImagem("imagens/jogador/correndo/kimono.png"));
		cKimono.mudaAzul(blue);
		cKimono.mudaVerde(green);
		cKimono.mudaVermelho(red);

		CamadaManipulavel cSombra = new CamadaManipulavel(g.carregaImagem("imagens/jogador/correndo/sombra.png"));

		int sombra = 40;

		red -= (red*sombra)/100;
		green -= (green*sombra)/100;
		blue -= (blue*sombra)/100;

		cSombra.mudaAzul(blue);
		cSombra.mudaVerde(green);
		cSombra.mudaVermelho(red);

		Sprite correndo = new Sprite(g.carregaImagem(cPele.getCaminho()), cKimono.getImagemBuffer(), cSombra.getImagemBuffer());		

		return correndo;
	}	
	

}
