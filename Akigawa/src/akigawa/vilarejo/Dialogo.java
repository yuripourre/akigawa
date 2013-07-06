package akigawa.vilarejo;

import java.util.ArrayList;

import etyllica.camada.Camada;
import etyllica.camada.CamadaEstatica;
import etyllica.camada.CamadaTexto;
import etyllica.gui.Botao;
import etyllica.nucleo.Gerenciador;


import akigawa.vilarejo.dialogo.Pergunta;

public class Dialogo extends Estabelecimento{

	private Camada fundo;
	private Camada npc;
	private int idNpc;

	private int pAtual;

	ArrayList<Pergunta> l;

	//Gui
	private Camada rect;
	private Botao botao[];
	private CamadaEstatica botaoDialogo;
	private CamadaEstatica botaoDialogoOnm;
	private CamadaEstatica botaoDialogoClk;

	private CamadaTexto ask;
	private CamadaTexto answer[];
	
	private String requerido;
	private int escape;
	private int stay;

	public Dialogo(Gerenciador g, int id,int vila, int idNpc) {
		super(g, id, vila);

		this.idNpc = idNpc;
		l = new ArrayList<Pergunta>();
		pAtual = 0;
	}

	public void carrega(){
		fundo = new Camada();
		npc = new Camada();

		rect = new Camada(g.carregaCamada("imagens/npc/rect.png"));
		rect.setCoordenadas(25, 200);
		carregando = 20;
		botaoDialogo = new CamadaEstatica(g.carregaCamada("imagens/npc/botao.png"));
		botaoDialogoOnm = new CamadaEstatica(g.carregaCamada("imagens/npc/botaoonm.png"));
		botaoDialogoClk = new CamadaEstatica(g.carregaCamada("imagens/npc/botaoclk.png"));

		botao = new Botao[3];
		for(int i=0;i<3;i++){
			botao[i] = new Botao(g,118, 255+50*i,botaoDialogo,botaoDialogoOnm,botaoDialogoClk);
		}

		carregando = 50;

		setAparencia(idNpc);
		carregando = 70;
		setDialogo(idNpc);
		carregando = 90;

		//Frase
		answer = new CamadaTexto[3];
		answer[0] = new CamadaTexto("");
		answer[0].setCorDifusa(0xff,0xff,0xff);
		answer[1] = new CamadaTexto("");
		answer[1].setCorDifusa(0xff,0xff,0xff);
		answer[2] = new CamadaTexto("");
		answer[2].setCorDifusa(0xff,0xff,0xff);


		ask = new CamadaTexto(l.get(pAtual).getFala());
		ask.setCorDifusa(0xff,0xff,0xff);
		ask.setY(205);
		mudaPergunta();


		carregando = 100;
	}
	public void desenha(){
		g.desenha(fundo);
		g.desenha(npc);

		g.desenha(rect);
		botao[0].desenha();
		botao[1].desenha();
		botao[2].desenha();

		g.desenha(ask);
		g.desenha(answer[0]);
		g.desenha(answer[1]);
		g.desenha(answer[2]);

	}	
	private void setAparencia(int idNpc){
		switch(idNpc){
		case 1:
			fundo.igualaImagem(g.carregaCamada("imagens/fase/vilainicial/porta.png"));
			npc.igualaImagem(g.carregaCamada("imagens/fase/vilainicial/samurai.png"));
			npc.setCoordenadas(550, 90);
			break;
		}
	}
	private void setDialogo(int idNpc){
		switch(idNpc){
		case 1:
			novoDialogo("Posso ajudar?","Eu gostaria de sair da cidade.",1,"Sai da frente e me deixe passar!",3,"Não.",-1);
			novoDialogo("Eu não posso deixar ninguém desarmado passar.","Mas eu tenho armas!",-2,"Tudo bem, deixa pra lá.",-1,"Tchau!",-1);
			novoDialogo("Desculpe-me mas essas facas de treino nem são afiadas.","Tem certeza?",4,"Como você sabe?",4,"Não sabia, tchau!",-1);
			novoDialogo("Como disse?","Nada.",-1,"Deixa pra lá.",-1,"Tchau!",-1);
			novoDialogo("Eu já tive a sua idade, e acredite eu entendo de lâminas.","Obrigado mesmo assim.",-1,"Vou indo, tchau.",-1,"Até logo.",-1);
			requerido = "Kunai Simples";
			escape = 17;
			stay = 2;
			break;

		case 2:
			novoDialogo("Para onde gostaria de ir?","Nishijima.",0,"Colheita do Mal",0,"Aqui está bom",-1);

			break;
		}
	}
	public int gerencia(){

		botao[0].gerencia();
		if(botao[0].getAcionado()>0){

			int retorno = l.get(pAtual).getRetorno(0);

			if(retorno==-1){
				return vila;
			}
			//-2 = verificar se tem o Item requerido
			//Ou a quest completa
			else if(retorno==-2){
				if(p.getInt(requerido)!=0){
					return escape;	
				}
				//else if(p.getQuest(quest)==true)
				//return escape
				else{
					pAtual = stay;
					mudaPergunta();
				}
			}
			else if(retorno<-3){
				return retorno*-1;
			}
			else{
				pAtual = retorno;
				mudaPergunta();
			}
			mouse.desPressiona();
		}

		botao[1].gerencia();
		if(botao[1].getAcionado()>0){
			int retorno = l.get(pAtual).getRetorno(1);

			if(retorno==-1){
				return vila;
			}
			else if(retorno==-2){
				if(p.getInt("Kunai Simples")>0){
					//p.setPodeSair(true); 
				}
			}
			else if(retorno<-2)
				return retorno*-1;
			else{
				pAtual = retorno;
				mudaPergunta();
			}
			mouse.desPressiona();
		}

		botao[2].gerencia();
		if(botao[2].getAcionado()>0){
			int retorno = l.get(pAtual).getRetorno(2);

			if(retorno==-1){
				return vila;
			}
			else if(retorno<-1)
				return retorno*-1;
			else{
				pAtual = retorno;
				mudaPergunta();
			}
			mouse.desPressiona();
		}


		return id;
	}
	private void novoDialogo(String pergunta, String resposta0,int r0, String resposta1,int r1, String resposta2, int r2){
		l.add(new Pergunta(pergunta,l.size(),resposta0,r0,resposta1,r1,resposta2,r2));
	}
	private void mudaPergunta(){
		ask.setTexto(l.get(pAtual).getFala());
		ask.centralizaX(rect);

		for(int i=0;i<3;i++){
			answer[i].setTexto(l.get(pAtual).getResposta(i));
			answer[i].centraliza(botao[i]);
		}
	}

}
