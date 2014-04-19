package akigawa.vilarejo;

import java.util.ArrayList;

import akigawa.vilarejo.dialogo.Pergunta;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.button.ImageButton;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.StaticLayer;
import br.com.etyllica.layer.TextLayer;

public class Dialogo extends Estabelecimento {

	private ImageLayer fundo;
	private ImageLayer npc;
	private int idNpc;

	private int pAtual;

	ArrayList<Pergunta> l;

	//Gui
	private ImageLayer rect;
	
	private ImageButton botao[];
	
	private StaticLayer botaoDialogo;
	private StaticLayer botaoDialogoOnm;
	private StaticLayer botaoDialogoClk;

	private TextLayer ask;
	private TextLayer answer[];
	
	private String requerido;
	private int escape;
	private int stay;

	public Dialogo(int w, int h, Village village) {
		super(w, h, village);

		this.idNpc = idNpc;
		l = new ArrayList<Pergunta>();
		pAtual = 0;
	}

	public void load() {
		fundo = new ImageLayer();
		npc = new ImageLayer();

		rect = new ImageLayer("imagens/npc/rect.png");
		rect.setCoordinates(25, 200);
		loading = 20;
		botaoDialogo = new StaticLayer("imagens/npc/botao.png");
		botaoDialogoOnm = new StaticLayer("imagens/npc/botaoonm.png");
		botaoDialogoClk = new StaticLayer("imagens/npc/botaoclk.png");

		botao = new ImageButton[3];
		for(int i=0;i<3;i++) {
			botao[i] = new ImageButton(118, 255+50*i,botaoDialogo,botaoDialogoOnm,botaoDialogoClk);
			add(botao[i]);
		}

		loading = 50;

		setAparencia(idNpc);
		loading = 70;
		setDialogo(idNpc);
		loading = 90;

		//Frase
		answer = new TextLayer[3];
		answer[0] = new TextLayer("");
		answer[0].setColor(0xff,0xff,0xff);
		answer[1] = new TextLayer("");
		answer[1].setColor(0xff,0xff,0xff);
		answer[2] = new TextLayer("");
		answer[2].setColor(0xff,0xff,0xff);


		ask = new TextLayer(l.get(pAtual).getFala());
		ask.setColor(0xff,0xff,0xff);
		ask.setY(205);
		mudaPergunta();


		loading = 100;
	}
	
	public void draw(Graphic g) {
		fundo.draw(g);
		npc.draw(g);

		rect.draw(g);

		ask.draw(g);
		answer[0].draw(g);
		answer[1].draw(g);
		answer[2].draw(g);		

	}
	
	private void setAparencia(int idNpc) {
		switch(idNpc) {
		case 1:
			fundo.cloneLayer("imagens/fase/vilainicial/porta.png");
			npc.cloneLayer("imagens/fase/vilainicial/samurai.png");
			npc.setCoordinates(550, 90);
			break;
		}
	}
	private void setDialogo(int idNpc) {
		switch(idNpc) {
		case 1:
			novoDialogo("Posso ajudar?","Eu gostaria de sair da cidade.",1,"Sai da frente e me deixe passar!",3,"N�o.",-1);
			novoDialogo("Eu n�o posso deixar ningu�m desarmado passar.","Mas eu tenho armas!",-2,"Tudo bem, deixa pra l�.",-1,"Tchau!",-1);
			novoDialogo("Desculpe-me mas essas facas de treino nem s�o afiadas.","Tem certeza?",4,"Como voc� sabe?",4,"N�o sabia, tchau!",-1);
			novoDialogo("Como disse?","Nada.",-1,"Deixa pra l�.",-1,"Tchau!",-1);
			novoDialogo("Eu j� tive a sua idade, e acredite eu entendo de l�minas.","Obrigado mesmo assim.",-1,"Vou indo, tchau.",-1,"At� logo.",-1);
			requerido = "Kunai Simples";
			escape = 17;
			stay = 2;
			break;

		case 2:
			novoDialogo("Para onde gostaria de ir?","Nishijima.",0,"Colheita do Mal",0,"Aqui est� bom",-1);

			break;
		}
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {

		botao[0].gerencia();
		
		if(botao[0].getAcionado()>0) {

			int retorno = l.get(pAtual).getRetorno(0);

			if(retorno==-1) {
				return vila;
			}
			//-2 = verificar se tem o Item requerido
			//Ou a quest completa
			else if(retorno==-2) {
				if(p.getInt(requerido)!=0) {
					return escape;	
				}
				//else if(p.getQuest(quest)==true)
				//return escape
				else{
					pAtual = stay;
					mudaPergunta();
				}
			}
			else if(retorno<-3) {
				return retorno*-1;
			}
			else{
				pAtual = retorno;
				mudaPergunta();
			}
			mouse.desPressiona();
		}

		botao[1].gerencia();
		if(botao[1].getAcionado()>0) {
			int retorno = l.get(pAtual).getRetorno(1);

			if(retorno==-1) {
				return vila;
			}
			else if(retorno==-2) {
				if(p.getInt("Kunai Simples")>0) {
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
		if(botao[2].getAcionado()>0) {
			int retorno = l.get(pAtual).getRetorno(2);

			if(retorno==-1) {
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
	private void novoDialogo(String pergunta, String resposta0,int r0, String resposta1,int r1, String resposta2, int r2) {
		l.add(new Pergunta(pergunta,l.size(),resposta0,r0,resposta1,r1,resposta2,r2));
	}
	
	private void mudaPergunta() {
		ask.setText(l.get(pAtual).getFala());
		ask.centralizeX(rect);

		for(int i=0;i<3;i++) {
			answer[i].setText(l.get(pAtual).getResposta(i));
			answer[i].centralize(botao[i]);
		}
	}

}
