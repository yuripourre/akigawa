package akigawa.fase;


import akigawa.jogador.Sprite;
import akigawa.menu.MenuAkigawa;
import br.com.etyllica.layer.BufferedLayer;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.StaticLayer;


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
	
	public Fase(int w, int h) {
		super(w, h);
	
		diretorioFase = "fase/";
		publica = true;
		//numJogadores = app.getNumJogadores();
		numJogadores = 1;
		//maxJogadores = app.getMaxJogadores();
		maxJogadores = 1;
		
		//p = (Profile) sessionMap.get("PROFILE");
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
		Sprite arremesso = new Sprite(aPele, aKimono, aSombra);

		return arremesso;
	}

	public Sprite getCorrendo(int red, int green, int blue){
		//CamadaEstatica cPele = new CamadaEstatica(url, "imagens/jogador/correndo/pele.png");
		StaticLayer cPele = new StaticLayer("jogador/correndo/ninja.png");

		BufferedLayer cKimono = new BufferedLayer("jogador/correndo/kimono.png");
		cKimono.offsetRGB(red, green, blue);
		
		BufferedLayer cSombra = new BufferedLayer("jogador/correndo/sombra.png");

		int sombra = 40;

		red -= (red*sombra)/100;
		green -= (green*sombra)/100;
		blue -= (blue*sombra)/100;

		cSombra.offsetRGB(red, green, blue);

		Sprite correndo = new Sprite(cPele, cKimono, cSombra);		

		return correndo;
	}	
	

}
