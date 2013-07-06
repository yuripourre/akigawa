package akigawa.fase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

import etyllica.camada.Camada;
import etyllica.camada.CamadaEstatica;
import etyllica.nucleo.Gerenciador;
import etyllica.nucleo.TSe.Tecla;

import akigawa.jogador.Jogador;
import akigawa.jogador.Jogador.Direcao;
import akigawa.objetos.Plataforma;



public class FaseAgua extends Fase{

	private Camada fundo;

	private Plataforma plataforma[];
	private int numPlataformas;

	private Plataforma ativa;

	private int ninjas = 1;
	private int alturaPulo;
	private int gravidade;

	private Jogador[] ninja;
	private Camada pe;

	private boolean pulando;

	public FaseAgua(Gerenciador app, int id) {
		super(app, id);
		fundo = new Camada(diretorioFase+"agua/fundo.png");
		fundo.setY(-1200);
		ninja = new Jogador[ninjas];


		for(int i=0;i<ninjas;i++){
			ninja[i] = new Jogador(64+64*i,290,75,125);
			ninja[i].setVelocidade(20);
			//ninja[i].setArremessando(app.getArremessando(200,10,10));
			Random r = new Random();
			Random g = new Random();
			Random b = new Random();
			ninja[i].carregaCorrendo(app.getCorrendo(r.nextInt(200),g.nextInt(20),b.nextInt(200)));
		}

		pe = new Camada(diretorioFase+"agua/pe.png");
		pe.setY(ninja[0].getY()+ninja[0].getYTile()-1);
		pe.setAparecendo(false);

		numPlataformas = 50;
		alturaPulo = 0;

		CamadaEstatica plataImagem = new CamadaEstatica(diretorioFase+"agua/plataforma.png" );
		plataforma = new Plataforma[numPlataformas];

		for(int p=0;p<numPlataformas;p++){
			plataforma[p] = new Plataforma(plataImagem);
		}
		ativa = plataforma[0];
		//int offsetL = 1500;

		plataforma[0].setCoordenadas(90,340);
		plataforma[1].setCoordenadas(465,270);
		plataforma[2].setCoordenadas(245,200);
		plataforma[3].setCoordenadas(512,100);
		plataforma[4].setCoordenadas(512,-45);
		plataforma[5].setCoordenadas(235,-160);
		plataforma[6].setCoordenadas(308,-379);
		plataforma[7].setCoordenadas(515,-315);
		plataforma[8].setCoordenadas(585,-709);
		plataforma[9].setCoordenadas(21,-515);
		plataforma[10].setCoordenadas(332,-611);
		plataforma[11].setCoordenadas(236,-783);
		plataforma[12].setCoordenadas(550,-917);
		plataforma[13].setCoordenadas(195,-987);
		plataforma[14].setCoordenadas(462,-1118);
		plataforma[15].setCoordenadas(91,-1193);
		plataforma[16].setCoordenadas(451,-1279);
		plataforma[17].setCoordenadas(571,-1424);
		plataforma[18].setCoordenadas(304,-1555);
		plataforma[19].setCoordenadas(36,-1712);
		plataforma[20].setCoordenadas(589,-1654);
		plataforma[21].setCoordenadas(397,-1809);
		plataforma[22].setCoordenadas(133,-1956);
		plataforma[23].setCoordenadas(531,-2067);
		plataforma[24].setCoordenadas(128,-2065);
		plataforma[25].setCoordenadas(526,-2221);
		plataforma[26].setCoordenadas(213,-2344);
		plataforma[27].setCoordenadas(541,-2445);
		plataforma[28].setCoordenadas(177,-2511);
		plataforma[29].setCoordenadas(374,-2665);
		plataforma[30].setCoordenadas(304,-2826);
		plataforma[31].setCoordenadas(459,-2909);
		plataforma[32].setCoordenadas(140,-3004);
		plataforma[33].setCoordenadas(444,-3085);
		plataforma[34].setCoordenadas(556,-3222);
		plataforma[35].setCoordenadas(228,-3278);
		plataforma[36].setCoordenadas(244,-3450);
		plataforma[37].setCoordenadas(547,-3404);
		plataforma[38].setCoordenadas(527,-3579);
		plataforma[39].setCoordenadas(70,-3616);
		plataforma[40].setCoordenadas(525,-3735);
		plataforma[41].setCoordenadas(304,-3871);
		plataforma[42].setCoordenadas(77,-4026);
		plataforma[43].setCoordenadas(425,-4057);
		plataforma[44].setCoordenadas(187,-4206);
		plataforma[45].setCoordenadas(538,-4299);
		plataforma[46].setCoordenadas(209,-4382);
		plataforma[47].setCoordenadas(29,-4545);
		plataforma[48].setCoordenadas(398,-4612);
		plataforma[49].setCoordenadas(293,-4735);
		
		/*
		plataforma[0].setCoordenadas(90,350);
		plataforma[1].setCoordenadas(465,280);
		plataforma[2].setCoordenadas(245,210);
		plataforma[3].setCoordenadas(512,110);
		plataforma[4].setCoordenadas(512,-35);
		plataforma[5].setCoordenadas(235,-150);
		plataforma[6].setCoordenadas(308,-369);
		plataforma[7].setCoordenadas(515,-305);
		plataforma[8].setCoordenadas(585,-699);
		plataforma[9].setCoordenadas(21,-505);
		plataforma[10].setCoordenadas(332,-601);
		plataforma[11].setCoordenadas(219,-789);
		plataforma[12].setCoordenadas(550,-907);
		plataforma[13].setCoordenadas(195,-977);
		plataforma[14].setCoordenadas(462,-1108);
		plataforma[15].setCoordenadas(91,-1183);
		plataforma[16].setCoordenadas(451,-1269);
		plataforma[17].setCoordenadas(571,-1414);
		plataforma[18].setCoordenadas(304,-1545);
		plataforma[19].setCoordenadas(36,-1702);
		plataforma[20].setCoordenadas(589,-1644);
		plataforma[21].setCoordenadas(397,-1799);
		plataforma[22].setCoordenadas(133,-1946);
		plataforma[23].setCoordenadas(531,-2057);
		plataforma[24].setCoordenadas(128,-2055);
		plataforma[25].setCoordenadas(526,-2211);
		plataforma[26].setCoordenadas(213,-2334);
		plataforma[27].setCoordenadas(541,-2435);
		plataforma[28].setCoordenadas(154,-2512);
		plataforma[29].setCoordenadas(374,-2655);
		plataforma[30].setCoordenadas(304,-2825);
		plataforma[31].setCoordenadas(304,-2925);
		plataforma[32].setCoordenadas(304,-3025);
		plataforma[33].setCoordenadas(304,-3125);
		plataforma[34].setCoordenadas(304,-3225);
		plataforma[35].setCoordenadas(304,-3325);
		plataforma[36].setCoordenadas(304,-3425);
		plataforma[37].setCoordenadas(304,-3525);
		plataforma[38].setCoordenadas(304,-3625);
		plataforma[39].setCoordenadas(304,-3725);
		plataforma[40].setCoordenadas(304,-3825);
		plataforma[41].setCoordenadas(304,-3925);
		plataforma[42].setCoordenadas(304,-4025);
		plataforma[43].setCoordenadas(304,-4125);
		plataforma[44].setCoordenadas(304,-4225);
		plataforma[45].setCoordenadas(304,-4325);
		plataforma[46].setCoordenadas(304,-4425);
		plataforma[47].setCoordenadas(304,-4525);
		plataforma[48].setCoordenadas(304,-4625);
		plataforma[49].setCoordenadas(304,-4725);
		*/
		

		
		
		
		
		//plataforma[0].setCoordenadas(90,1745-offsetL);
		//plataforma[1].setCoordenadas(490,1685-offsetL);
		//plataforma[2].setCoordenadas(280,1540-offsetL);
		//plataforma[3].setCoordenadas(545,1445-offsetL);
		//plataforma[4].setCoordenadas(135,1368-offsetL);
		//plataforma[5].setCoordenadas(375,1205-offsetL);
		//plataforma[6].setCoordenadas(90,1070-offsetL);

		/*
		plataforma[0].setCoordenadas(280,350);
		plataforma[1].setCoordenadas(510,260);
		plataforma[2].setCoordenadas(170,190);
		plataforma[3].setCoordenadas(50,100);
		plataforma[4].setCoordenadas(350,0);

		//Random rand = new Random().nextInt(500);

		for(int i=5;i<10;i++){
			plataforma[i].setCoordenadas(new Random().nextInt(500),new Random().nextInt(300)-plataforma[i-1].getY()-90);
			//plataforma[6].setCoordenadas(new Random().nextInt(500),new Random().nextInt(90)-plataforma[5].getY());
			//plataforma[7].setCoordenadas(new Random().nextInt(500),new Random().nextInt(90)-plataforma[6].getY());
		}
		 */


		pulando = false;

		gravidade = 10;

	}


	public void desenha(){
		g.desenha(fundo);

		for(int i=0;i<ninjas;i++){
			//g.desenha(ninja[i].getSombra());
			//g.desenha(ninja[i].getKimono());
			g.desenha(ninja[i].getPele());
		}

		for(int i=0;i<numPlataformas;i++){
			g.desenha(plataforma[i]);
		}

		//Pé
		g.desenha(pe);

	}

	public int gerencia(){

		if((teclado.getTecla(Tecla.TSK_D)||
				(teclado.getTecla(Tecla.TSK_SETA_DIREITA)))){
			ninja[0].direciona(Direcao.LESTE);
		}
		else if((teclado.getTecla(Tecla.TSK_A)||
				(teclado.getTecla(Tecla.TSK_SETA_ESQUERDA)))){
			ninja[0].direciona(Direcao.OESTE);
		}
		else{
			if((!teclado.getTecla(Tecla.TSK_D)||
					(!teclado.getTecla(Tecla.TSK_SETA_DIREITA))))
				ninja[0].direciona(Direcao.CENTROX);
		}


		if(teclado.getTecla(Tecla.TSK_ESPAÇO)){
			//ninja[0].direciona(Direcao.NORTE);
			if(!pulando){
				if(alturaPulo==0)
					alturaPulo = ninja[0].getY()-150;

				pulando = true;
				//ninja[0].se

			}
		}
		if(!teclado.getTecla(Tecla.TSK_ESPAÇO)){
			pulando = false;
		}

		if(mouse.getPressionado()==1){
			for(int i=0;i<numPlataformas;i++){
				if(mouse.sobMouse(plataforma[i])){
					ativa = plataforma[i];
				}
			}
		}

		if(teclado.getTecla(Tecla.TSK_L)){
			ativa.setOffsetX(1);
		}
		if(teclado.getTecla(Tecla.TSK_J)){
			ativa.setOffsetX(-1);
		}
		if(teclado.getTecla(Tecla.TSK_K)){
			ativa.setOffsetY(1);
		}
		if(teclado.getTecla(Tecla.TSK_I)){
			ativa.setOffsetY(-1);
		}
		
		if(teclado.getTecla(Tecla.TSK_M)){
			try{
			    // Create file 
			    FileWriter fstream = new FileWriter("out.txt");
			    BufferedWriter out = new BufferedWriter(fstream);
			    for(int i=0;i<numPlataformas;i++){
			    	out.write("plataforma["+i+"].setCoordenadas("+plataforma[i].getX()+","+plataforma[i].getY()+");\n");
			    }
			    //Close the output stream
			    out.close();
			    }catch (Exception e){//Catch exception if any
			      System.err.println("Error: " + e.getMessage());
			    }
		}
		
		/*
		if(teclado.getTecla(Tecla.TSK_W)){
			ninja[0].direciona(Direcao.NORTE);
		}
		else if(teclado.getTecla(Tecla.TSK_S)){
			ninja[0].direciona(Direcao.SUL);
		}
		else{
			ninja[0].direciona(Direcao.CENTROY);
		}
		 */

		if((!teclado.getTecla(Tecla.TSK_D)&&
				(!teclado.getTecla(Tecla.TSK_SETA_DIREITA)))&&
				(!teclado.getTecla(Tecla.TSK_A)&&
						(!teclado.getTecla(Tecla.TSK_SETA_ESQUERDA)))){
			//(!teclado.getTecla(Tecla.TSK_W))&&
			//(!teclado.getTecla(Tecla.TSK_S))){
			ninja[0].desAnima();
		}

		if(alturaPulo>0){
			if(ninja[0].getY()>alturaPulo){
				ninja[0].setOffsetY(-10);
			}
			else{
				alturaPulo = 0;
			}
		}

		//pe.setCoordenadas(x, y);
		//pe.centralizaX(ninja[0]);
		pe.setX(ninja[0].getX()+8);
		pe.setY(ninja[0].getY()+ninja[0].getYTile()-1);

		gerenciaColisaoNinja();

		return id;
	}

	public void gerenciaColisaoNinja(){

		boolean colide = false;

		if(alturaPulo==0){

			int offset = 220-ninja[0].getY();
			for(int i=0;i<numPlataformas;i++){
				//if pé do ninja colideRetangular!
				//if(ninja[0].getY()+ninja[0].getYTile()<=plataforma[i].getY()){
				if(!plataforma[i].foiPassada()){

					if((pe.getY()>=plataforma[i].getY())&&(pe.colideRetangular(plataforma[i]))){

						for(int j=0;j<numPlataformas;j++){
							plataforma[j].setOffsetY(offset);
							if(plataforma[j].getY()>420){
								//plataforma[j].setPassada(true);
							}
						}
						fundo.setOffsetY(offset);

						ninja[0].setOffsetY(offset);
						colide = true;

						break;
					}
					//if(j==numPlataformas){}
				}

			}

			if(!colide){
				if((ninja[0].getY()>0)&&
						(ninja[0].getY()+ninja[0].getYTile()<=altura)){
					ninja[0].setOffsetY(+gravidade);
				}
				if(ninja[0].getY()>altura-200){
					if(plataforma[0].getY()>350){
						for(int p=0;p<numPlataformas;p++){
							plataforma[p].setOffsetY(-gravidade);
							//Morreu
							//plataforma[p].setOffsetY(-gravidade);
						}
						fundo.setOffsetY(-gravidade);
					}

					//sobe com as plataformas denovo
					//Para dar efeito de queda
				}
			}
		}
	}

}