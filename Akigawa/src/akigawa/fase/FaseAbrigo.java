package akigawa.fase;

import java.util.Random;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.effects.Effect;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.StaticLayer;
import etyllica.camada.Efeito;
import etyllica.nucleo.Gerenciador;
import akigawa.GerenciadorAkigawa;
import akigawa.objetos.Caido;
import akigawa.jogador.Jogador;
import akigawa.jogador.Jogador.Direcao;



public class FaseAbrigo extends Fase{

	private ImageLayer fundo;

	private int ninjas = 1;
	private Jogador[] ninja;
	private Effect[] efeito;

	private StaticLayer estrelaBoa;
	private StaticLayer estrelaRuim;

	private ImageLayer tronco;

	private StaticLayer cannonballImage;
	private Caido cannonball[];
	private int cannons;

	private StaticLayer folhaImage;
	private Caido folha[];
	private int folhas;

	private StaticLayer galhoImage;
	private Caido galho[];
	private int galhos;


	public FaseAbrigo(int w, int h) {
		super(w, h);				
	}
	@Override
	public void load() {
		
		fundo = new ImageLayer(diretorioFase+"abrigo/fundo.png");
		loading = 15;
		
		ninja = new Jogador[ninjas];
		efeito = new Effect[ninjas];

		cannons = 8;
		cannonball = new Caido[cannons];
		folhas = 3;
		folha = new Caido[folhas];

		galhos = 3;
		galho = new Caido[galhos];

		estrelaBoa = new StaticLayer(diretorioFase+"abrigo/starfx.png");
		loading = 18;
		estrelaRuim = new StaticLayer(diretorioFase+"abrigo/redstar.png");
		loading = 20;

		for(int i=0;i<ninjas;i++){
			ninja[i] = new Jogador(64+64*i,270,75,125);
			//ninja[i].setArremessando(app.getArremessando(200,10,10));
			Random r = new Random();
			Random g = new Random();
			Random b = new Random();
			//Problema
			//esse getCorrendo precisa ficar em uma SessaoAkigawa/Fase
			ninja[i].carregaCorrendo(app.getCorrendo(r.nextInt(200),g.nextInt(20),b.nextInt(200)));

			efeito[i] = new Effect(0,0,86,88);
			efeito[i].setAnimateHorizontally(true);
			efeito[i].setFrames(4);
			efeito[i].setSpeed(120);

		}
		loading = 25;

		tronco = new ImageLayer(378,245, diretorioFase+"abrigo/rect.png");
		loading = 35;

		//Fallen Objects
		cannonballImage = new StaticLayer(diretorioFase+"abrigo/cannonball.png");
		loading = 50;
		for(int c=0;c<cannons;c++){
			cannonball[c] = new Caido(cannonballImage);
			cannonball[c].para();
		}

		folhaImage = new StaticLayer(url, diretorioFase+"abrigo/folha.png");
		loading = 70;
		for(int f=0;f<folhas;f++){
			folha[f] = new Caido(folhaImage);
			folha[f].para();
		}
		galhoImage = new StaticLayer(url, diretorioFase+"abrigo/galho.png");
		loading = 90;
		for(int g=0;g<galhos;g++){
			galho[g] = new Caido(galhoImage);
			galho[g].para();
		}
		loading = 100;
	}

	public void draw(Graphic g) {
		fundo.draw(g);

		for(int i=0;i<ninjas;i++){
			//app.desenha(ninja[i].getSombra());
			//app.desenha(ninja[i].getKimono());
			ninja[i].getSombra().draw(g);
			ninja[i].getKimono().draw(g);
			ninja[i].getPele().draw(g);

			efeito[i].draw(g);
		}
		
		//app.desenha(tronco);

		for(int c=0;c<cannons;c++){
			cannonball[c].draw(g);
		}
		for(int f=0;f<folhas;f++){
			folha[f].draw(g);	
		}
		for(int b = 0; b < galhos; b++) {
			galho[b].draw(g);
		}

	}

	public GUIEvent updateKeyboard(KeyEvent event){

		if(teclado.getTecla(Tecla.TSK_D)){
			ninja[0].direciona(Direcao.LESTE);
		}
		else if(teclado.getTecla(Tecla.TSK_A)){
			ninja[0].direciona(Direcao.OESTE);
		}
		else{
			if(!teclado.getTecla(Tecla.TSK_D))
				ninja[0].direciona(Direcao.CENTROX);
		}

		if(teclado.getTecla(Tecla.TSK_W)){
			ninja[0].direciona(Direcao.NORTE);
		}
		else if(teclado.getTecla(Tecla.TSK_S)){
			ninja[0].direciona(Direcao.SUL);
		}
		else{
			ninja[0].direciona(Direcao.CENTROY);
		}

		if((!teclado.getTecla(Tecla.TSK_D))&&
				(!teclado.getTecla(Tecla.TSK_A))&&
				(!teclado.getTecla(Tecla.TSK_W))&&
				(!teclado.getTecla(Tecla.TSK_S))){
			ninja[0].desAnima();
		}

		gerenciaColisaoNinja();
		gerenciaObjetos();

		return id;
	}

	private void gerenciaObjetos(){

		//cannonball.setOffsetY(1);

		for(int c=0;c<cannons;c++){
			cannonball[c].gerencia();

			for(int i=0;i<ninjas;i++){
				if(ninja[i].colideRetangular(cannonball[c])){
					//Estrela Ruim

					efeito[i].setCoordenadas(ninja[i].getX(), ninja[i].getY()-70);
					efeito[i].copy(estrelaRuim);
					efeito[i].anima();
					cannonball[c].para();
				}
			}

			if(cannonball[c].getParado()){
				cannonball[c].setCoordenadas(new Random().nextInt(700),-10-new Random().nextInt(20));
				cannonball[c].setVelocidade(2+(new Random().nextInt(6)));
				cannonball[c].cai();
			}
		}

		for(int f=0;f<folhas;f++){
			folha[f].gerencia();

			for(int i=0;i<ninjas;i++){
				if(ninja[i].colideRetangular(folha[f])){
					//Estrela boa

					efeito[i].setCoordenadas(ninja[i].getX(), ninja[i].getY()-70);
					efeito[i].copy(estrelaBoa);
					efeito[i].anima();
					folha[f].para();
				}
			}

			if(folha[f].getParado()){
				folha[f].setCoordenadas(new Random().nextInt(700),-10-new Random().nextInt(20));
				folha[f].setVelocidade(1+(new Random().nextInt(7)));
				folha[f].cai();
			}
		}

		for(int g=0;g<galhos;g++){
			galho[g].gerencia();

			for(int i=0;i<ninjas;i++){
				if(ninja[i].colideRetangular(galho[g])){
					//Estrela boa

					efeito[i].setCoordenadas(ninja[i].getX(), ninja[i].getY()-70);
					efeito[i].copy(estrelaBoa);
					efeito[i].anima();
					galho[g].para();
				}
			}

			if(galho[g].getParado()){
				galho[g].setCoordenadas(new Random().nextInt(700),-10-new Random().nextInt(20));
				galho[g].setVelocidade(1+(new Random().nextInt(7)));
				galho[g].cai();
			}
		}

	}

	private void gerenciaColisaoNinja(){
		if(ninja[0].getX()+ninja[0].getXTile()+ninja[0].getIncrementoX()>fundo.getXLimite()){
			ninja[0].setIncrementoX(0);
		}

		if(ninja[0].getX()<0){
			ninja[0].setIncrementoX(0);
			ninja[0].setX(0);
		}
		if(ninja[0].getY()<260){
			ninja[0].setIncrementoY(0);
			ninja[0].setY(260);
		}
		else if(ninja[0].getY()+ninja[0].getYTile()>423){
			ninja[0].setIncrementoY(0);
			ninja[0].setY(423-ninja[0].getYTile());
		}

		if(ninja[0].getIncrementoY()<0){
			if(ninja[0].colideRetangular(tronco)){
				if((ninja[0].getY()<=tronco.getY()+tronco.getYLimite())
					//&&(ninja[0].getX()<tronco.getX())
					){
					ninja[0].setY(tronco.getY()+tronco.getYLimite()+ninja[0].getVelocidade());
					ninja[0].setIncrementoY(0);
				}
			}
		}

		if(ninja[0].getIncrementoX()>0){
			if(ninja[0].colideRetangular(tronco)){
				if((ninja[0].getX()<tronco.getX())&&
					(ninja[0].getY()<tronco.getY()+tronco.getYLimite())){
					ninja[0].setX(tronco.getX()-ninja[0].getXTile());
					ninja[0].setIncrementoX(0);
				}
			}
		}
		else if(ninja[0].getIncrementoX()<0){
			if(ninja[0].colideRetangular(tronco)){
				if((ninja[0].getX()>tronco.getX())&&
					(ninja[0].getY()<tronco.getY()+tronco.getYLimite())){
					ninja[0].setX(tronco.getX()+tronco.getXLimite());
					ninja[0].setIncrementoX(0);
				}
			}
		}
		
		/*
		if(ninja[0].colideRetangular(tronco)){
			//if(ninja[0].getX()>=tronco.getX()&&ninja[0].getX()<tronco.getX()+tronco.getXLimite()){
			//ninja[0].setIncrementoX(0);
			//}
			
			if(ninja[0].getX()+ninja[0].getXTile()<=tronco.getX()){
				if(ninja[0].getDirecao()==Direcao.LESTE){
					ninja[0].setX(tronco.getX()-ninja[0].getXTile());
				}
			}

			if(ninja[0].getDirecao()==Direcao.OESTE){
				if(ninja[0].getX()>tronco.getX()+tronco.getXLimite())
					ninja[0].setX(tronco.getX()+tronco.getXLimite());
			}
			else{
				ninja[0].setIncrementoX(+ninja[0].getVelocidade());
			}

		}*/

	}

	/*
	private void gerenciaColisaoNinja(){

		//incremento ninja ou Ninja[0].getVelocidade();
		int velocidade = 8;


		//Andando para Direita "D"
		if(ninja[0].getIncrementoX()>0){

			//Se for sair da tela
			if(ninja[0].getX()+ninja[0].getXTile()+ninja[0].getIncrementoX()>fundo.getXLimite()){
				ninja[0].setIncrementoX(0);
			}

			else{

				if(ninja[0].colideRetangular(tronco)){
					if(ninja[0].getX()>=tronco.getX()+tronco.getXLimite()){
						//Colidiu na hora de sair.
						ninja[0].setIncrementoX(+velocidade);
					}
					else{
						ninja[0].setIncrementoX(0);
						ninja[0].setX(tronco.getX()-ninja[0].getXTile()-1);
					}
				}
			}
		}
		//Andando para esquerda "A"
		else if(ninja[0].getIncrementoX()<0){
			if(ninja[0].getX()-velocidade<0){
				ninja[0].setIncrementoX(0);	
			}
			else{
				if(ninja[0].colideRetangular(tronco)){
					if(ninja[0].getX()+ninja[0].getXTile()<=tronco.getX()){
						//Colidiu na hora de sair.
						ninja[0].setIncrementoX(-velocidade);
					}
					else{
						ninja[0].setIncrementoX(0);
						ninja[0].setX(tronco.getX()+tronco.getXLimite()+1);
					}
				}
				//else{ninja[0].setIncrementoX(-velocidade);}
			}
		}
		//Anda pra baixo "W"
		if(ninja[0].getIncrementoY()<0){
			if(ninja[0].getY()>=250){
				if(ninja[0].colideRetangular(tronco)){
					ninja[0].setY(tronco.getY()+tronco.getYLimite()+1);
				}
			}
			else{
				ninja[0].setIncrementoY(0);
			}
		}
		else if(ninja[0].getIncrementoY()>0){
			if(ninja[0].getY()>fundo.getYLimite()-ninja[0].getYTile()-velocidade){
				ninja[0].setIncrementoY(0);
			}
		}
	}
	 */



}
