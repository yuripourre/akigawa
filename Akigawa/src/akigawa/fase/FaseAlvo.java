package akigawa.fase;

import java.awt.Color;

import etyllica.camada.Camada;
import etyllica.camada.CamadaAnimacao;
import etyllica.camada.CamadaEstatica;
import etyllica.camada.CamadaManipulavel;
import etyllica.camada.CamadaTexto;
import etyllica.camada.Efeito;
import etyllica.gui.Botao;
import etyllica.nucleo.Gerenciador;
import etyllica.nucleo.TSe.Tecla;


import akigawa.armas.Arremessavel;
import akigawa.jogador.Ninja;

public class FaseAlvo extends Fase{

	private String nomeFase;

	private Camada fundo;
	private Camada alvo;

	private Camada suporte;

	private CamadaEstatica kunai;
	private CamadaEstatica miniKunai;

	//Pergaminho
	private boolean ready;
	private boolean again;

	private Camada titulo;	
	private Camada borda;

	//Recomendações
	private Camada recomendacoes;

	private Camada useEspacoLabel;
	private Camada comandosLabel;
	private Camada municaoLabel;
	private Camada pontuacaoLabel;
	private Camada pontosAcumuladosLabel;


	//JogarNovamente
	private Camada jogarNovamente;
	private Botao sim;
	private Botao nao;

	private Camada simLabel;
	private Camada naoLabel;
	private CamadaTexto recorde;
	private CamadaTexto novoRecorde;

	private CamadaEstatica botaoRedondo;
	private CamadaEstatica botaoRedondoOnm;


	//Novo Ninja
	private Camada[] botaoNome;

	private Camada[][] miniFaca;

	private Arremessavel[][] faca;

	private int numFacas;
	private int[] facaVez;

	private boolean[] outra;
	private boolean[] gone;

	private int[] numMiniFacas;

	private int alvoVelocidade;
	private boolean alvoSobe = false;

	//private SomWav somArremesso;

	private CamadaTexto pontosLabel[];
	private CamadaTexto nomeLabel[];

	private int ninjas = 1;

	private int pontos[];

	//private Jogador[] ninja;
	private CamadaManipulavel sombra;
	private CamadaManipulavel kimono;
	private CamadaManipulavel pele;

	private int stopping;


	private CamadaEstatica ponto1;
	private CamadaEstatica ponto2;
	private CamadaEstatica ponto3;
	private CamadaEstatica ponto5;

	private Efeito[] efeito;

	private CamadaAnimacao manivelaCima;
	private CamadaAnimacao manivelaBaixo;
	
	Ninja nn;

	public FaseAlvo(Gerenciador app, int id) {
		super(app, id);
	}
	@Override
	public void carrega(){

		nomeFase = "alvo/";
		numFacas = 10;
		alvoVelocidade = 4;

		titulo = new Camada(g.carregaCamada(lang+"fase/"+nomeFase+"titulo.png"));
		carregando = 10;

		fundo = new Camada(g.carregaCamada(diretorioFase+nomeFase+"chao.png"));
		carregando = 15;
		alvo = new Camada(g.carregaCamada(diretorioFase+nomeFase+"alvo.png"));
		alvo.setCoordenadas(650, 20);
		carregando = 20;


		suporte = g.novaCamada(diretorioFase+nomeFase+"suporte.png");
		suporte.setCoordenadas(666, 12);
		carregando = 30;
		
		
		manivelaCima = new CamadaAnimacao(suporte.getX()+20, suporte.getY()+20, 27,30);
		manivelaCima.igualaImagem(g.carregaCamada(diretorioFase+nomeFase+"manivela.png"));
		manivelaCima.setNumeroFrames(4);
		manivelaCima.setAnimaEmX(false);
		manivelaCima.setVelocidadeAnimacao(130);
		carregando = 40;
		
		
		manivelaBaixo = new CamadaAnimacao(suporte.getX()+20, suporte.getY()+suporte.getYLimite()-40,27,30);
		manivelaBaixo.igualaImagem(g.carregaCamada(diretorioFase+nomeFase+"manivela.png"));
		manivelaBaixo.setNumeroFrames(4);
		manivelaBaixo.setAnimaEmX(false);
		manivelaBaixo.setVelocidadeAnimacao(130);
		carregando = 50;

		kunai = new CamadaEstatica(g.carregaCamada(diretorioFase+"kunai.png"));
		carregando = 55;
		
		miniKunai = new CamadaEstatica(g.carregaCamada(diretorioFase+"minikunai.png"));
		carregando = 60;


		botaoNome = new Camada[ninjas];
		nomeLabel = new CamadaTexto[ninjas];
		pontosLabel = new CamadaTexto[ninjas];

		pontos = new int[ninjas];
		numMiniFacas = new int[ninjas];

		//ninja = new Jogador[ninjas];
		facaVez = new int[ninjas];

		outra = new boolean[ninjas];
		gone = new boolean[ninjas];

		//Efeitos Especiais

		efeito = new Efeito[ninjas];
		ponto1 = new CamadaEstatica(g.carregaCamada(diretorioFase+nomeFase+"ponto1.png"));
		carregando = 62;
		ponto2 = new CamadaEstatica(g.carregaCamada(diretorioFase+nomeFase+"ponto2.png"));
		carregando = 64;
		ponto3 = new CamadaEstatica(g.carregaCamada(diretorioFase+nomeFase+"ponto3.png"));
		carregando = 67;
		ponto5 = new CamadaEstatica(g.carregaCamada(diretorioFase+nomeFase+"ponto5.png"));
		carregando = 70;

		//somArremesso = new SomWav("throw.wav");

		miniFaca = new Camada[numFacas][ninjas];
		faca = new Arremessavel[numFacas][ninjas];
		carregando = 75;
		
		
		sombra = new CamadaManipulavel(g.carregaImagem("imagens/jogador/arremesso/sombra.png"));
		sombra.mudaRGB(p.getInt("red")/2, p.getInt("green")/2, p.getInt("blue")/2);
		
		kimono = new CamadaManipulavel(g.carregaImagem("imagens/jogador/arremesso/kimono.png"));
		kimono.mudaRGB(p.getInt("red"), p.getInt("green"), p.getInt("blue"));
		
		pele = new CamadaManipulavel(g.carregaImagem("imagens/jogador/arremesso/pele.png"));
		
		nn = new Ninja(10,160);
		//g.centralizaY(nn);
		nn.setNinja(sombra, kimono, pele);
		
		for(int i=0;i<ninjas;i++){

			//Ninjas
			//ninja[i] = new Jogador(90,10+55*i,75,125);
			//ninja[i].carregaArremessando(getArremessando(200,10,10));
			//ninja[i].centralizaY(fundo);
			//ninja[i].setY(fundo);

			botaoNome[i] = g.novaCamada(diretorio+"gui/botaotransp.png");
			g.centralizaX(botaoNome[i]);
			g.centralizaY(botaoNome[i]);
			//botaoNome[i].setY(20+50*i);
			//botaoNome[i].centralizaY(nn);

			//nomeLabel[i] = new CamadaTexto(0,300,p.getNome());
			//Ninja 1
			//nomeLabel[i] = new CamadaTexto(0,300,"Ninja "+(i+1));
			//nomeLabel.setCorDifusa(p.getCor());
			nomeLabel[i] = new CamadaTexto(200,300,"NINJA");
			//nomeLabel[i].setCorDifusa(20,20,20);
			nomeLabel[i].setTamanhoFonte(26);
			nomeLabel[i].centraliza(botaoNome[i]);

			efeito[i] = new Efeito(alvo.getX(),nn.getY(),85,86);
			efeito[i].setNumeroFrames(4);
			efeito[i].setVelocidadeAnimacao(120);
			//efeito[i].setSom(somBatendo);
		}
		carregando = 80;
		//Começa
		recomeca();
		//Centralizar os Botões.
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
		
		nomeLabel[0].setCorDifusa(p.getInt("red"),p.getInt("green"),p.getInt("blue"));
		nomeLabel[0].setTexto("NINJA");
		carregando = 88;
		carregaRecomendacoes();
		carregando = 90;
		carregaJogarDenovo();
		carregando = 100;
		
	}

	private void recomeca(){

		for(int i=0;i<ninjas;i++){

			numMiniFacas[i] = numFacas;

			facaVez[i] = 0;
			outra[i] = false;
			gone[i] = true;

			mudaPontos(0,i);

			for(int j = 0;j<numMiniFacas[i];j++){
				faca[j][i] = new Arremessavel(kunai);
				faca[j][i].setCoordenadas(20, nn.getY()+40);
				faca[j][i].setVelocidade(20);
				faca[j][i].para();

				//MiniFacas
				miniFaca[j][i] = new Camada(g.carregaCamada(miniKunai.getCaminho()));
				miniFaca[j][i].setX(botaoNome[i].getX()+10+(10*j));
				//miniFaca[j][i].centralizaY(botaoNome[i]);
				g.centralizaY(miniFaca[j][i]);
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

			if(teclado.getTeclaOnce(Tecla.TSK_ESPAÇO)){
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

			//Movimentação do Alvo em Y;
			if(alvoSobe){
				alvo.setOffsetY(-alvoVelocidade);
				if(alvo.getY()<0){
					alvoSobe = false;
				}
			}
			else{
				alvo.setOffsetY(alvoVelocidade);
				if(alvo.getY()>altura-alvo.getYLimite()){
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
								efeito[j].igualaImagem(ponto1.getCaminho());
							}
							else if(faca[i][j].getY()<alvo.getY()+8+11){
								mudaPontos(pontos[j]+2,j);
								efeito[j].igualaImagem(ponto2.getCaminho());							
							}
							else if(faca[i][j].getY()<alvo.getY()+8+11+11){
								mudaPontos(pontos[j]+3,j);
								efeito[j].igualaImagem(ponto3.getCaminho());
							}
							//Meio
							else if(faca[i][j].getY()<alvo.getY()+8+11+11+19){
								mudaPontos(pontos[j]+5,j);
								efeito[j].igualaImagem(ponto5.getCaminho());
							}

							else if(faca[i][j].getY()<alvo.getY()+30+19+11){
								mudaPontos(pontos[j]+3,j);
								efeito[j].igualaImagem(ponto3.getCaminho());			
							}
							else if(faca[i][j].getY()<alvo.getY()+60+10){
								mudaPontos(pontos[j]+2,j);
								efeito[j].igualaImagem(ponto2.getCaminho());
							}
							else{
								mudaPontos(pontos[j]+1,j);
								efeito[j].igualaImagem(ponto1.getCaminho());
							}

							faca[i][j].para();
							faca[i][j].setAparecendo(false);
							stopping++;
						}
						else if(faca[i][j].getX()>=750){
							faca[i][j].para();
							faca[i][j].setAparecendo(false);
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
	public void desenha() {

		g.desenha(fundo);
		
		g.desenha(suporte);
		
		g.desenha(manivelaCima);
		g.desenha(manivelaBaixo);
		
		g.desenha(alvo);
		
		//Desenha Ninja
		g.desenha(nn.getCamada());


		for(int i=0;i<ninjas;i++){
			g.desenha(botaoNome[i]);

			g.desenha(nomeLabel[i]);

			g.desenha(pontosLabel[i]);
		}

		for(int j = 0;j<ninjas;j++){
			for(int i = 0;i<numFacas;i++){
				g.desenha(faca[i][j]);
			}
			for(int i = 0;i<numMiniFacas[j];i++){
				g.desenha(miniFaca[i][j]);
			}

			g.desenha(efeito[j]);
		}

		if(!ready){
			desenhaRecomendacoes();
		}
		if(again){
			desenhaJogarDenovo();
		}

	}

	public void mudaPontos(int pontos, int player){
		this.pontos[player] = pontos;
		pontosLabel[player] = new CamadaTexto(0,300,Integer.toString(pontos));
		pontosLabel[player].setCorDifusa(255, 255, 255);

		pontosLabel[player].setTamanhoFonte(26);
		pontosLabel[player].setX(550);
		pontosLabel[player].centralizaY(botaoNome[player]);
		//FX de pontos
	}

	private void carregaRecomendacoes(){

		g.centralizaX(titulo);
		titulo.setY(26);

		recomendacoes = new Camada(g.carregaCamada(diretorioFase+nomeFase+"init.png"));
		recomendacoes.centraliza(fundo);

		useEspacoLabel = new Camada(g.carregaCamada(lang+"fase/alvo/useespaco.png"));
		useEspacoLabel.setCoordenadas(440, 120);
		comandosLabel = new Camada(g.carregaCamada(lang+"fase/alvo/comandos.png"));
		comandosLabel.setCoordenadas(190, 84);
		municaoLabel = new Camada(g.carregaCamada(lang+"fase/alvo/municao.png"));
		municaoLabel.setCoordenadas(118,262);
		pontuacaoLabel = new Camada(g.carregaCamada(lang+"fase/alvo/pontuacao.png"));
		pontuacaoLabel.setCoordenadas(554,204);
		pontosAcumuladosLabel = new Camada(g.carregaCamada(lang+"fase/alvo/pontosacumulados.png"));
		pontosAcumuladosLabel.setCoordenadas(292, 282);

	}
	private void gerenciaRecomendacoes(){
		if(teclado.getTecla(Tecla.TSK_ENTER)||(mouse.getPressionado()==1)){
			ready = true;
			
			manivelaCima.anima();
			manivelaBaixo.anima();
		}
	}
	private void desenhaRecomendacoes(){
		g.desenha(recomendacoes);
		g.desenha(titulo);
		g.desenha(useEspacoLabel);
		g.desenha(comandosLabel);
		g.desenha(municaoLabel);
		g.desenha(pontuacaoLabel);
		g.desenha(pontosAcumuladosLabel);
	}

	private void carregaJogarDenovo(){

		borda = new Camada(g.carregaCamada(diretorio+"gui/bordafase.png"));
		g.centralizaX(borda);
		

		jogarNovamente = new Camada(g.carregaCamada(lang+"novamente.png"));
		g.centralizaX(jogarNovamente);
		jogarNovamente.setY(270);
		
		novoRecorde = new CamadaTexto("");
		novoRecorde.setTamanhoFonte(32);
		novoRecorde.setCorDifusa(new Color(p.getInt("red"),p.getInt("green"),p.getInt("blue")));
		
		if(pontos[0]>p.getInt("recordealvo"))
			novoRecorde.setTexto("Seu novo recorde é: ");
		else
			novoRecorde.setTexto("Seu recorde é: ");
		
		g.centralizaX(novoRecorde);
		novoRecorde.setY(120);
	
		recorde = new CamadaTexto("");
		recorde.setTamanhoFonte(38);
		recorde.setCorDifusa(new Color(p.getInt("red"),p.getInt("green"),p.getInt("blue")));
		recorde.setTexto(p.get("recordealvo"));
		g.centralizaX(recorde);
		recorde.setY(novoRecorde.getY()+40);
		

		simLabel = new Camada(g.carregaCamada(lang+"sim.png"));
		naoLabel = new Camada(g.carregaCamada(lang+"nao.png"));

		botaoRedondo = new Camada(g.carregaCamada(diretorio+"gui/redondomini.png"));
		botaoRedondoOnm = new Camada(g.carregaCamada(diretorio+"gui/redondominionm.png"));

		int offset = 125;
		sim = new Botao(g,offset,310,botaoRedondo,botaoRedondoOnm);
		sim.setLabel(simLabel);
		simLabel.setOffsetX(34);
		simLabel.setOffsetY(34);
		nao = new Botao(g,-offset+680,310,botaoRedondo,botaoRedondoOnm);
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

		return id;
	}
	private void desenhaJogarDenovo(){

		g.desenha(borda);
		g.desenha(titulo);	
		g.desenha(jogarNovamente);
		g.desenha(novoRecorde);
		g.desenha(recorde);
		

		
		//Botões
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
