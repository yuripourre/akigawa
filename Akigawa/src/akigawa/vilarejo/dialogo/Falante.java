package akigawa.vilarejo.dialogo;

import java.util.ArrayList;

import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.StaticLayer;


public class Falante {
	
	private String nome;
	private ImageLayer fundo;
	private ImageLayer aparencia;
	
	private ArrayList<Pergunta> p;
	
	public Falante(String nome, StaticLayer fundo, StaticLayer npc){
		this.nome = nome;
		
		this.fundo = new ImageLayer(0,0);
		this.aparencia = new ImageLayer(200,100);
		
		p = new ArrayList<Pergunta>();
		
		this.fundo.cloneLayer(fundo.getPath());
		this.aparencia.cloneLayer(npc.getPath());
	}
	
	public ArrayList<Pergunta> getFalas(){
		return p;
	}
	
	public String getNome(){
		return nome;
	}
	
	public ImageLayer getFundo(){
		return fundo;
	}
	public ImageLayer getAparencia(){
		return aparencia;
	}
	
	public void novoDialogo(String pergunta, String resposta0,int r0, String resposta1,int r1, String resposta2, int r2){
		p.add(new Pergunta(pergunta,p.size(),resposta0,r0,resposta1,r1,resposta2,r2));
	}
	

}
