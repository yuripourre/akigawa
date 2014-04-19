package akigawa.vilarejo;

import br.com.etyllica.layer.StaticLayer;


public class Item {
	private String nome;
	private String nomePlural;
	
	private String tipo;
	//Para agrupar
	private int quantidade;
	private int maxQuantidade;

	private int reqQuantidade;
	private String reqTipo;
	
	private StaticLayer miniatura;
	
	//public Item(Objetos o){
	public Item(Objetos o, StaticLayer miniatura){

		this.miniatura = miniatura;
		
		quantidade = 10;
		maxQuantidade = 100;
		
		switch (o){
		case KUNAI_OSSO:
			nome = "Kunai de Ossos";
			nomePlural = "Kunais de Ossos";
			quantidade = 10;
			maxQuantidade = 100;
			tipo = "t";//Throwing Weapon
			reqQuantidade = 10;
			reqTipo = "Ossos";		
			break;
		case KUNAI_SIMPLES:
			nome = "Kunai Simples";
			nomePlural = "Kunais Simples";
			quantidade = -1;
			tipo = "t";//Throwing Weapon
			reqQuantidade = 30;
			reqTipo = "Pontos";		
			break;
		case KUNAI_TREINO:
			nome = "Kunai de Treino";
			nomePlural = "Kunais de Treino";
			quantidade = -1;
			tipo = "t";//Throwing Weapon
			reqQuantidade = 30;
			reqTipo = "";		
			break;
		case POCAO_VIDA:
			nome = "Po��o de Vida";
			tipo = "l";//Life
			reqQuantidade = 0;
			reqTipo = "";		
			break;
		case POCAO_ENERGIA:
			nome = "Po��o de Energia";
			tipo = "l";//Life
			reqQuantidade = 0;
			reqTipo = "";		
			break;
		}
	}
	
	public void setQuantidade(int quantidade){
		this.quantidade = quantidade;
	}
	public String getNome(){
		return nome;
	}
	public String getNomePlural(){
		return nomePlural;
	}
	public int getQuantidade(){
		return quantidade;
	}
	public int getMaxQuantidade(){
		return maxQuantidade;
	}
	
	public int getReqQuantidade(){
		return reqQuantidade;
	}
	public String getReqTipo(){
		return reqTipo;
	}
	
	public StaticLayer getMiniatura(){
		return miniatura;
	}
	
	public enum Objetos {
		//Kunais
	    KUNAI_SIMPLES,
	    KUNAI_TREINO,
	    KUNAI_OSSO,
	    //M�gicos
	    POCAO_VIDA,
	    POCAO_ENERGIA;
	}

	
}
