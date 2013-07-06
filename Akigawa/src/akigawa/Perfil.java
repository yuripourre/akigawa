package akigawa;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Perfil {

	//Opções de configuração
	private String uid = "0";
	private String idioma = "br";

	private boolean som = true;
	private boolean fullscreen = false;

	//Detalhes sobre o Personagem.	
	private String nome = "Ninja";
	private int corR = 0;
	private int corG = 0;
	private int corB = 0;	
	
	private int medalhasF = 0;
	private int medalhasM = 0;
	private int medalhasD = 0;
	
	private String[] fase = new String[3];
	
	private ArrayList<Perfil> amigos = new ArrayList<Perfil>();
	
	Map<String, Integer> inventario;
	//Map<String, Integer> recorde;
	
	//Recordes
	private int recordeAlvo;
	public int getRecordeAlvo(){
		return recordeAlvo;
	}
	public void setRecordeAlvo(int recordeAlvo){
		this.recordeAlvo = recordeAlvo;
	}
	
	public int getQuantidadeItem(String nome){
		String nomeItem = nome.toLowerCase();
		return inventario.get(nomeItem);
	}
	public void setQuantidadeItem(String nome, int quantidade){
		String nomeItem = nome.toLowerCase();
		inventario.put(nomeItem,quantidade);
	}
	public Perfil(String uid){
		this.uid = uid;
		
		recordeAlvo = 0;
		
		inventario = new HashMap<String, Integer>();
		setQuantidadeItem("Ossos", 100);
		setQuantidadeItem("Ferros", 10);
		setQuantidadeItem("Kunai Simples", 0);
		setQuantidadeItem("Kunai de Treino", 0);
		
		//faz uma busca no banco de dados.
		recarregaPerfil(uid);
	}
	public void recarregaPerfil(String uid){
		//faz uma busca no banco de dados.
	}
	
	public void adicionaAmigo(Perfil p){
		//Sabendo que ele existe
		//Carrega do Banco de dados
		amigos.add(p);
	}
	public ArrayList<Perfil> getAmigos(){
		return amigos; 
	}
	public Perfil getAmigo(int index){
		return amigos.get(index); 
	}

	public void setNome(String nome){
		this.nome = nome;
		//update BD
	}
	public void setCor(int r, int g, int b){
		corR = r;
		corG = g;
		corB = b;
	}
	public void setIdioma(String idioma){
		this.idioma = idioma;
		//update BD
	}
	public void setSom(char som){
		if(som == 'y'){
		this.som = true;
		}
		
		this.som = false;
		//update BD
	}
	public void setFullScreen(char fullscreen){
		if(fullscreen == 'y'){
			this.fullscreen = true;
			}
			
			this.fullscreen = false;
			//Atualiza//SetFullscreen
		//update BD
	}
	
	//Calcula medalhas
	public int getMedalhasF(){
		
		medalhasF = 0;

		for(int i=0; i<3;i++){
			for(int k=0; k<fase[i].length();k++){
				if('f'==fase[i].charAt(k)){
					medalhasF++;
				}
			}
		}
		return medalhasF;
	}
	public int getMedalhasM(){

		medalhasM = 0;
		
		for(int i=0; i<3;i++){
			for(int k=0; k<fase[i].length();k++){
				if('m'==fase[i].charAt(k)){
					medalhasM++;
				}
			}
		}

		return medalhasM;
	}
	public int getMedalhasD(){
		
		medalhasD = 0;

		for(int i=0; i<3;i++){
			for(int k=0; k<fase[i].length();k++){
				if('d'==fase[i].charAt(k)){
					medalhasD++;
				}
			}
		}

		return medalhasD;
	}

	public String getUid(){
		return uid;
	}

	public String getNome(){
		return nome;
	}

	public Color getCor(){
		Color c  = new Color(corR,corG,corB);
		
		return c;
	}
	public int getCorR(){		
		return corR;
	}
	public int getCorG(){		
		return corG;
	}
	public int getCorB(){		
		return corB;
	}
	
	public String getIdioma(){
		return idioma;
	}

	public int getMedalhas(){
		return medalhasF+medalhasM+medalhasD;
	}

}
