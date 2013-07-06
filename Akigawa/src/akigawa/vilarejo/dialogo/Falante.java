package akigawa.vilarejo.dialogo;

import java.util.ArrayList;

import etyllica.camada.Camada;
import etyllica.camada.CamadaEstatica;


public class Falante {
	
	private String nome;
	private Camada fundo;
	private Camada aparencia;
	
	private ArrayList<Pergunta> p;
	
	public Falante(String nome, CamadaEstatica fundo, CamadaEstatica npc){
		this.nome = nome;
		
		this.fundo = new Camada(0,0);
		this.aparencia = new Camada(200,100);
		
		p = new ArrayList<Pergunta>();
		
		this.fundo.igualaImagem(fundo.getCaminho());
		this.aparencia.igualaImagem(npc.getCaminho());
	}
	
	public ArrayList<Pergunta> getFalas(){
		return p;
	}
	
	public String getNome(){
		return nome;
	}
	
	public Camada getFundo(){
		return fundo;
	}
	public Camada getAparencia(){
		return aparencia;
	}
	
	public void novoDialogo(String pergunta, String resposta0,int r0, String resposta1,int r1, String resposta2, int r2){
		p.add(new Pergunta(pergunta,p.size(),resposta0,r0,resposta1,r1,resposta2,r2));
	}
	

}
