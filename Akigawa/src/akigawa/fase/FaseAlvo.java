package akigawa.fase;

import java.awt.Color;

import akigawa.armas.Arremessavel;
import akigawa.jogador.Ninja;
import br.com.etyllica.core.event.Tecla;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.effects.Effect;
import br.com.etyllica.gui.button.ImageButton;
import br.com.etyllica.gui.label.ImageLabel;
import br.com.etyllica.layer.AnimatedLayer;
import br.com.etyllica.layer.BufferedLayer;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.StaticLayer;
import br.com.etyllica.layer.TextLayer;

public class FaseAlvo extends Fase{

	private String nomeFase;

	private ImageLayer fundo;
	private ImageLayer alvo;

	private ImageLayer suporte;

	private StaticLayer kunai;
	private StaticLayer miniKunai;

	//Pergaminho
	private boolean ready;
	private boolean again;

	private ImageLayer titulo;	
	private ImageLayer borda;

	//Recomenda��es
	private ImageLayer recomendacoes;

	private ImageLayer useEspacoLabel;
	private ImageLayer comandosLabel;
	private ImageLayer municaoLabel;
	private ImageLayer pontuacaoLabel;
	private ImageLayer pontosAcumuladosLabel;


	//JogarNovamente
	private ImageLayer jogarNovamente;
	private ImageButton sim;
	private ImageButton nao;

	private ImageLabel simLabel;
	private ImageLabel naoLabel;
	private TextLayer recorde;
	private TextLayer novoRecorde;

	private StaticLayer botaoRedondo;
	private StaticLayer botaoRedondoOnm;


	//Novo Ninja
	private ImageLayer[] botaoNome;

	private ImageLayer[][] miniFaca;

	private Arremessavel[][] faca;

	private int numFacas;
	private int[] facaVez;

	private boolean[] outra;
	private boolean[] gone;

	private int[] numMiniFacas;

	private int alvoVelocidade;
	private boolean alvoSobe = false;

	//private SomWav somArremesso;

	private TextLayer pontosLabel[];
	private TextLayer nomeLabel[];

	private int ninjas = 1;

	private int pontos[];

	//private Jogador[] ninja;
	private BufferedLayer sombra;
	private BufferedLayer kimono;
	private BufferedLayer pele;

	private int stopping;


	private StaticLayer ponto1;
	private StaticLayer ponto2;
	private StaticLayer ponto3;
	private StaticLayer ponto5;

	private Effect[] efeito;

	private AnimatedLayer manivelaCima;
	private AnimatedLayer manivelaBaixo;
	
	Ninja nn;

	public FaseAlvo(int w, int h) {
		super(w, h);
	}
	@Override
	public void load(){

		nomeFase = "alvo/";
		numFacas = 10;
		alvoVelocidade = 4;

		titulo = new ImageLayer(lang+"fase/"+nomeFase+"titulo.png");
		loading = 10;

		fundo = new ImageLayer(diretorioFase+nomeFase+"chao.png");
		loading = 15;
		alvo = new ImageLayer(diretorioFase+nomeFase+"alvo.png");
		alvo.setCoordenadas(650, 20);
		loading = 20;


		suporte = new ImageLayer(diretorioFase+nomeFase+"suporte.png");
		suporte.setCoordenadas(666, 12);
		loading = 30;
		
		
		manivelaCima = new AnimatedLayer(suporte.getX()+20, suporte.getY()+20, 27,30);
		manivelaCima.setPath(diretorioFase+nomeFase+"manivela.png");
		manivelaCima.setNumeroFrames(4);
		manivelaCima.setAnimaEmX(false);
		manivelaCima.setVelocidadeAnimacao(130);
		loading = 40;
		
		
		manivelaBaixo = new AnimatedLayer(suporte.getX()+20, suporte.getY()+suporte.getH()-40,27,30);
		manivelaBaixo.setPath(diretorioFase+nomeFase+"manivela.png");
		manivelaBaixo.setNumeroFrames(4);
		manivelaBaixo.setAnimaEmX(false);
		manivelaBaixo.setVelocidadeAnimacao(130);
		loading = 50;

		kunai = new StaticLayer(diretorioFase+"kunai.png");
		loading = 55;
		
		miniKunai = new StaticLayer(diretorioFase+"minikunai.png");
		loading = 60;


		botaoNome = new ImageLayer[ninjas];
		nomeLabel = new TextLayer[ninjas];
		pontosLabel = new TextLayer[ninjas];

		pontos = new int[ninjas];
		numMiniFacas = new int[ninjas];

		//ninja = new Jogador[ninjas];
		facaVez = new int[ninjas];

		outra = new boolean[ninjas];
		gone = new boolean[ninjas];

		//Efeitos Especiais

		efeito = new Effect[ninjas];
		ponto1 = new StaticLayer(diretorioFase+nomeFase+"ponto1.png");
		loading = 62;
		ponto2 = new StaticLayer(diretorioFase+nomeFase+"ponto2.png");
		loading = 64;
		ponto3 = new StaticLayer(diretorioFase+nomeFase+"ponto3.png");
		loading = 67;
		ponto5 = new StaticLayer(diretorioFase+nomeFase+"ponto5.png");
		loading = 70;

		//somArremesso = new SomWav("throw.wav");

		miniFaca = new ImageLayer[numFacas][ninjas];
		faca = new Arremessavel[numFacas][ninjas];
		loading = 75;
		
		
		sombra = new BufferedLayer("imagens/jogador/arremesso/sombra.png");
		sombra.offsetRGB(p.getInt("red")/2, p.getInt("green")/2, p.getInt("blue")/2);
		
		kimono = new BufferedLayer("imagens/jogador/arremesso/kimono.png");
		kimono.offsetRGB(p.getInt("red"), p.getInt("green"), p.getInt("blue"));
		
		pele = new BufferedLayer("imagens/jogador/arremesso/pele.png");
		
		nn = new Ninja(10,160);
		//g.centralizaY(nn);
		nn.setNinja(sombra, kimono, pele);
		
		for(int i=0;i<ninjas;i++){

			//Ninjas
			//ninja[i] = new Jogador(90,10+55*i,75,125);
			//ninja[i].carregaArremessando(getArremessando(200,10,10));
			//ninja[i].centralizaY(fundo);
			//ninja[i].setY(fundo);

			botaoNome[i] = new ImageLayer("gui/botaotransp.png");
			g.centralizaX(botaoNome[i]);
			g.centralizaY(botaoNome[i]);
			//botaoNome[i].setY(20+50*i);
			//botaoNome[i].centralizaY(nn);

			//nomeLabel[i] = new CamadaTexto(0,300,p.getNome());
			//Ninja 1
			//nomeLabel[i] = new CamadaTexto(0,300,"Ninja "+(i+1));
			//nomeLabel.setCorDifusa(p.getCor());
			nomeLabel[i] = new TextLayer(200,300,"NINJA");
			//nomeLabel[i].setCorDifusa(20,20,20);
			nomeLabel[i].setSize(26);
			nomeLabel[i].centraliza(botaoNome[i]);

			efeito[i] = new Effect(alvo.getX(),nn.getY(),85,86);
			efeito[i].setNumeroFrames(4);
			efeito[i].setVelocidadeAnimacao(120);
			//efeito[i].setSom(somBatendo);
		}
		loading = 80;
		//Come�a
		recomeca();
		//Centralizar os Bot�es.
		/*
		botaoNome[ninjas/2].centralizaY(fundo);
		int y = 50;
		for(int i=ninjas/2;i>0;i--){
			botaoNome[i].setY(botaoNome[ninjas/2].getY()-y);
			y+=50;
		}
		for(int i=ninjas/2;i<ninjas;i++){
			botaoNome[i].setY(botaoNome[ninjas/2].getY()+50*i);
		}
		 */

		//comeca();


		//ninja[1].setArremessando(app.getArremessando(0,0,100));
		//ninja[2].setArremessando(app.getArremessando(220,0,220));


		ready = false;
		
		//ninja[0].carregaArremessando(getArremessando(p.getInt("red"),p.getInt("green"),p.getInt("blue")));
		
		nomeLabel[0].setColor(p.getInt("red"),p.getInt("green"),p.getInt("blue"));
		nomeLabel[0].setText("NINJA");
		loading = 88;
		carregaRecomendacoes();
		loading = 90;
		carregaJogarDenovo();
		loading = 100;
		
	}

	private void recomeca(){

		for(int i=0;i<ninjas;i++){

			numMiniFacas[i] = numFacas;

			facaVez[i] = 0;
			outra[i] = false;
			gone[i] = true;

			mudaPontos(0,i);

			for(int j = 0;j<numMiniFacas[i];j++){
				faca[j][i] = new Arremessavel(kunai.getPath());
				faca[j][i].setCoordenadas(20, nn.getY()+40);
				faca[j][i].setVelocidade(20);
				faca[j][i].para();

				//MiniFacas
				miniFaca[j][i] = new ImageLayer(miniKunai.getPath());
				miniFaca[j][i].setX(botaoNome[i].getX()+10+(10*j));
				//miniFaca[j][i].centralizaY(botaoNome[i]);
				miniFaca[j][i].centralizaY(0, h);
			}
		}

		//int r = new Random().nextInt(180);
		//int g = new Random().nextInt(180);
		//int b = new Random().nextInt(180);

		//ninja[0].carregaArremessando(app.getArremessando(r,g,b));

		stopping = 0;
		//ready = false;
		again = false;
	}


	@Override
	public int gerencia() {

		if((ready)&&(!again)){

			if(teclado.getTeclaOnce(Tecla.TSK_ESPACO)){
				arremessoNinja(0);
				nn.anima();
			}
			else if(teclado.getTecla(Tecla.TSK_CTRL)){
				arremessoNinja(0);
			}
			else if(teclado.getTecla(Tecla.TSK_R)){
				recomeca();
			}

			/*
			if(teclado.getTecla(Tecla.TSK_Z)){
				arremessoNinja(1);
			}

			if(teclado.getTecla(Tecla.TSK_X)){
				arremessoNinja(2);
			}
			if(teclado.getTecla(Tecla.TSK_C)){
				arremessoNinja(3);
			}
			if(teclado.getTecla(Tecla.TSK_V)){
				arremessoNinja(4);
			}
			if(teclado.getTecla(Tecla.TSK_B)){
				arremessoNinja(5);
			}
			 */

			//else{			outra = false;	}

			//Movimenta��o do Alvo em Y;
			if(alvoSobe){
				alvo.setOffsetY(-alvoVelocidade);
				if(alvo.getY()<0){
					alvoSobe = false;
				}
			}
			else{
				alvo.setOffsetY(alvoVelocidade);
				if(alvo.getY()>altura-alvo.getH()){
					alvoSobe = true;
				}
			}

			for(int j=0;j<ninjas;j++){

				if(nn.getParado()){
					if((!gone[j])&&(facaVez[j]<numFacas)){
						faca[facaVez[j]][j].arremessa();
						//Bug Fix
						facaVez[j]++;
						numMiniFacas[j]--;

						gone[j] = true;
					}
					outra[j] = false;
				}

				for(int i = 0; i<numFacas; i++){
					faca[i][j].gerencia();
					if(!faca[i][j].getParado()){
						if(faca[i][j].colideRetangular(alvo)){
							efeito[j].anima();

							if(faca[i][j].getY()<alvo.getY()+8){
								mudaPontos(pontos[j]+1,j);
								efeito[j].igualaImagem(ponto1.getPath());
							}
							else if(faca[i][j].getY()<alvo.getY()+8+11){
								mudaPontos(pontos[j]+2,j);
								efeito[j].igualaImagem(ponto2.getPath());							
							}
							else if(faca[i][j].getY()<alvo.getY()+8+11+11){
								mudaPontos(pontos[j]+3,j);
								efeito[j].igualaImagem(ponto3.getPath());
							}
							//Meio
							else if(faca[i][j].getY()<alvo.getY()+8+11+11+19){
								mudaPontos(pontos[j]+5,j);
								efeito[j].igualaImagem(ponto5.getPath());
							}

							else if(faca[i][j].getY()<alvo.getY()+30+19+11){
								mudaPontos(pontos[j]+3,j);
								efeito[j].igualaImagem(ponto3.getPath());			
							}
							else if(faca[i][j].getY()<alvo.getY()+60+10){
								mudaPontos(pontos[j]+2,j);
								efeito[j].igualaImagem(ponto2.getPath());
							}
							else{
								mudaPontos(pontos[j]+1,j);
								efeito[j].igualaImagem(ponto1.getPath());
							}

							faca[i][j].para();
							faca[i][j].setVisible(false);
							stopping++;
						}
						else if(faca[i][j].getX()>=750){
							faca[i][j].para();
							faca[i][j].setVisible(false);
							stopping++;
						}

					}
				}//Fecha For NumFacas

			}

		}
		else if(again){
			//Recorde Alvo
			int meuIndice = 0;
			if(p.getInt("recordealvo")<pontos[meuIndice]){
				p.set("recordealvo",pontos[meuIndice]);
				recorde.setTexto(p.get("recordealvo"));
			}
		
			return gerenciaJogarDenovo();
		}
		else{
			gerenciaRecomendacoes();	
		}


		if(stopping==ninjas*numFacas){
			again = true;
		}

		return id;
	}

	@Override
	public void draw(Grafico g) {

		fundo.draw(g);
		
		suporte.draw(g);
		
		manivelaCima.draw(g);
		manivelaBaixo.draw(g);
		
		alvo.draw(g);
		
		//Desenha Ninja
		nn.getCamada().draw(g);


		for(int i=0;i<ninjas;i++){
			botaoNome[i].draw(g);

			nomeLabel[i].draw(g);

			pontosLabel[i].draw(g);
		}

		for(int j = 0;j<ninjas;j++){
			for(int i = 0;i<numFacas;i++){
				faca[i][j].draw(g);
			}
			for(int i = 0;i<numMiniFacas[j];i++){
				miniFaca[i][j].draw(g);
			}

			efeito[j].draw(g);
		}

		if(!ready){
			desenhaRecomendacoes(g);
		}
		if(again){
			desenhaJogarDenovo(g);
		}

	}

	public void mudaPontos(int pontos, int player){
		this.pontos[player] = pontos;
		pontosLabel[player] = new TextLayer(0,300,Integer.toString(pontos));
		pontosLabel[player].setColor(255, 255, 255);

		pontosLabel[player].setSize(26);
		pontosLabel[player].setX(550);
		pontosLabel[player].centralizaY(botaoNome[player]);
		//FX de pontos
	}

	private void carregaRecomendacoes(){

		titulo.centralizaX(0, w);
		titulo.setY(26);

		recomendacoes = new ImageLayer(diretorioFase+nomeFase+"init.png");
		recomendacoes.centraliza(fundo);

		useEspacoLabel = new ImageLayer(lang+"fase/alvo/useespaco.png");
		useEspacoLabel.setCoordenadas(440, 120);
		comandosLabel = new ImageLayer(lang+"fase/alvo/comandos.png");
		comandosLabel.setCoordenadas(190, 84);
		municaoLabel = new ImageLayer(lang+"fase/alvo/municao.png");
		municaoLabel.setCoordenadas(118,262);
		pontuacaoLabel = new ImageLayer(lang+"fase/alvo/pontuacao.png");
		pontuacaoLabel.setCoordenadas(554,204);
		pontosAcumuladosLabel = new ImageLayer(lang+"fase/alvo/pontosacumulados.png");
		pontosAcumuladosLabel.setCoordenadas(292, 282);

	}
	private void gerenciaRecomendacoes(){
		if(teclado.getTecla(Tecla.TSK_ENTER)||(mouse.getPressionado()==1)){
			ready = true;
			
			manivelaCima.anima();
			manivelaBaixo.anima();
		}
	}
	private void desenhaRecomendacoes(Grafico g){
		recomendacoes.draw(g);
		titulo.draw(g);
		useEspacoLabel.draw(g);
		comandosLabel.draw(g);
		municaoLabel.draw(g);
		pontuacaoLabel.draw(g);
		pontosAcumuladosLabel.draw(g);
	}

	private void carregaJogarDenovo(){

		borda = new ImageLayer("gui/bordafase.png");
		g.centralizaX(borda);
		

		jogarNovamente = new ImageLayer(lang+"novamente.png");
		g.centralizaX(jogarNovamente);
		jogarNovamente.setY(270);
		
		novoRecorde = new TextLayer("");
		novoRecorde.setSize(32);
		novoRecorde.setColor(new Color(p.getInt("red"),p.getInt("green"),p.getInt("blue")));
		
		if(pontos[0]>p.getInt("recordealvo"))
			novoRecorde.setText("Seu novo recorde: ");
		else
			novoRecorde.setText("Seu recorde: ");
		
		g.centralizaX(novoRecorde);
		novoRecorde.setY(120);
	
		recorde = new TextLayer("");
		recorde.setSize(38);
		recorde.setColor(new Color(p.getInt("red"),p.getInt("green"),p.getInt("blue")));
		recorde.setTexto(p.get("recordealvo"));
		recorde.centralizaX(0, w);
		recorde.setY(novoRecorde.getY()+40);
		

		simLabel = new ImageLabel(lang+"sim.png");
		naoLabel = new ImageLabel(lang+"nao.png");

		botaoRedondo = new ImageLayer("gui/redondomini.png");
		botaoRedondoOnm = new ImageLayer("gui/redondominionm.png");

		int offset = 125;
		sim = new ImageButton(offset,310,botaoRedondo,botaoRedondoOnm);
		sim.setLabel(simLabel);
		simLabel.setOffsetX(34);
		simLabel.setOffsetY(34);
		nao = new ImageButton(-offset+680,310,botaoRedondo,botaoRedondoOnm);
		nao.setLabel(naoLabel);
		naoLabel.setOffsetX(34);
		naoLabel.setOffsetY(34);
		//680 = 0 (offsetNegativo)

	}
	private int gerenciaJogarDenovo(){

		sim.gerencia();
		nao.gerencia();

		if(sim.getAcionado()>0){
			recomeca();
			return id;
		}
		else if(nao.getAcionado()>0){
			return 10;
		}		

	}
	private void desenhaJogarDenovo(Grafico g){

		borda.draw(g);
		titulo.draw(g);	
		jogarNovamente.draw(g);
		novoRecorde.draw(g);
		recorde.draw(g);

		//Bot�es
		sim.desenha();
		nao.desenha();

	}


	private void arremessoNinja(int player){

		if((!outra[player])&&(facaVez[player]<numFacas)){
			outra[player] = true;
			gone[player] = false;
		}
	}

}
