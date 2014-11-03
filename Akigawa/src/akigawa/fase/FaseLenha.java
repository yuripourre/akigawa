package akigawa.fase;

import jtse.Gerenciador;
import jtse.TSe.Tecla;
import jtse.audio.SomWav;
import jtse.camada.Camada;
import jtse.camada.CamadaAnimacao;
import jtse.camada.CamadaEstatica;
import jtse.camada.CamadaTexto;
import jtse.camada.Fx;

import akigawa.GerenciadorAkigawa;
import akigawa.armas.Arremessavel;
import akigawa.jogador.Jogador;

public class FaseLenha extends Fase{

	private Camada fundo;
	private Camada troncos;

	//Novo Ninja
	private Camada[] botaoNome;

	//private SomWav somArremesso;

	private CamadaTexto pontosLabel[];
	private CamadaTexto nomeLabel[];

	private int pontos[];

	private Jogador[] ninja;

	private int ninjas = 1;
	
	private CamadaTexto letra;
	
	private Fx[] efeito;
	private CamadaEstatica estrelaBoa;
	private CamadaEstatica estrelaRuim;

	public FaseLenha(GerenciadorAkigawa app, int id) {
		super(app, id);

		fundo = new Camada(url, diretorioFase+"alvo/chao.png");
		troncos = new Camada(url, diretorioFase+"lenha/tronco.png");
		troncos.setCoordenadas(230, 120);

		botaoNome = new Camada[ninjas];
		nomeLabel = new CamadaTexto[ninjas];
		pontosLabel = new CamadaTexto[ninjas];

		pontos = new int[ninjas];

		ninja = new Jogador[ninjas];
		efeito = new Fx[ninjas];

		estrelaBoa = new CamadaEstatica(url, diretorioFase+"abrigo/starfx.png");
		estrelaRuim = new CamadaEstatica(url, diretorioFase+"abrigo/redstar.png");
		
		//Letra
		//letra = new CamadaTexto(90,90,"A");
		char davez = (char)65;
		//z = 90
		//letra = new CamadaTexto(90,90,Character.toString((char)Tecla.TSK_Z.codigo()));
		letra = new CamadaTexto(90,90,Integer.toString(Tecla.TSK_Z.codigo()));
		
		for(int i=0;i<ninjas;i++){

			//Ninjas
			ninja[i] = new Jogador(10,10+55*i,75,125);
			ninja[i].carregaArremessando(app.getArremessando(200,10,10));
			ninja[i].centralizaY(fundo);

			botaoNome[i] = new Camada(url, diretorio+"gui/botaotransp.png");
			botaoNome[i].centralizaX(fundo);
			//botaoNome[i].setY(20+50*i);
			botaoNome[i].centralizaY(ninja[i]);
			
			//nomeLabel[i] = new CamadaTexto(0,300,p.getNome());
			nomeLabel[i] = new CamadaTexto(0,300,"Ninja "+(i+1));
			//nomeLabel.setCorDifusa(p.getCor());
			nomeLabel[i].setCorDifusa(20,20,20);
			nomeLabel[i].setTamanhoFonte(30);
			nomeLabel[i].centraliza(botaoNome[i]);
			
			efeito[i] = new Fx(0,0,86,88);
			efeito[i].setAnimaEmX(true);
			efeito[i].setNumeroFrames(4);
			efeito[i].setVelocidadeAnimacao(120);
						
			mudaPontos(0,i);
			
		}

	}

	@Override
	public int gerencia() {

		if(teclado.getTecla(Tecla.TSK_ESPA�O)){
				ninja[0].animaOnce();
				ninja[0].anima();
				
				//Estrela
				int i = 0;
				efeito[i].setCoordenadas(ninja[i].getX(), ninja[i].getY()-70);
				efeito[i].igualaImagem(estrelaRuim);
				efeito[i].anima();
		}

		
		return id;
	}

	@Override
	public void desenha() {

		app.desenha(fundo);
		app.desenha(troncos);

		for(int i=0;i<ninjas;i++){
			
			app.desenha(botaoNome[i]);

			app.desenha(nomeLabel[i]);

			app.desenha(pontosLabel[i]);
			
			
			//app.desenha(ninja[j]);
			app.desenha(ninja[i].getSombra());
			app.desenha(ninja[i].getKimono());
			app.desenha(ninja[i].getPele());
			
			app.desenha(efeito[i]);
		}

		app.desenha(letra);

	}

	public void mudaPontos(int pontos, int player){
		this.pontos[player] = pontos;
		pontosLabel[player] = new CamadaTexto(0,300,Integer.toString(pontos));
		pontosLabel[player].setCorDifusa(255, 255, 255);

		pontosLabel[player].setTamanhoFonte(30);
		pontosLabel[player].setX(550);
		//pontosLabel[i].centralizaY(botaoNome[i]);
		pontosLabel[player].centralizaY(botaoNome[player]);
		//FX de pontos
	}

	@Override
	public void carrega() {
		// TODO Auto-generated method stub
		
	}

}
