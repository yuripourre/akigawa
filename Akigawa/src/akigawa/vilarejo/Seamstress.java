package akigawa.vilarejo;

import java.awt.Color;

import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.core.video.Grafico;
import br.com.etyllica.gui.button.ImageButton;
import br.com.etyllica.layer.BufferedLayer;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.StaticLayer;
import br.com.etyllica.layer.TextLayer;


public class Seamstress extends Estabelecimento{
	
	private ImageLayer fundo;
		
	private ImageButton botaoSalvar;
	private StaticLayer salvar;
	private StaticLayer salvarOnm;
	
	//private Camada ninjaGlobe;
	private ImageLayer ninjaGlobe;
	
	//Ninja
	private ImageLayer pele;
	private BufferedLayer kimono;
	private BufferedLayer sombra;
	
	private BufferedLayer seta;
	
	//Barras
	private ImageLayer barraVermelha;
	private ImageLayer barraVerde;
	private ImageLayer barraAzul;

	//Indicadores
	private ImageLayer indicadorV;
	private ImageLayer indicadorD;
	private ImageLayer indicadorA;

	//Coisas de Loja
	private ImageLayer build;
	private TextLayer aviso;
	
	private TextLayer target;
	
	private int red;
	private int green;
	private int blue;
	
	public Seamstress(int w, int h, Village village) {
		super(w,h,village);		
	}
	
	@Override
	public void load(){
		
		fundo = new ImageLayer("npc/rouparia.png");
				
		salvar = new StaticLayer("gui/salvarmini.png");
		salvarOnm = new StaticLayer("gui/salvarminionm.png");
		
		botaoSalvar = new ImageButton(w-72-10,350,salvar,salvarOnm);
		add(botaoSalvar);
		
		//NinjaGlobe
		ninjaGlobe = new ImageLayer("perfil/ninjaglobe.png");
		ninjaGlobe.centralizaX(x,w);
		ninjaGlobe.setY(40);
		
		//Ninja
		/*red = Integer.parseInt(p.get("red"));
		green = Integer.parseInt(p.get("green"));
		blue = Integer.parseInt(p.get("blue"));*/
		
		red = 200;
		green = 0; 
		blue = 0;
		 

		kimono = new BufferedLayer("perfil/kimono.png");
		kimono.centraliza(ninjaGlobe);
		
		sombra = new BufferedLayer("perfil/sombra.png");
		sombra.centraliza(ninjaGlobe);
		
		pele = new ImageLayer("perfil/pele.png");
		pele.centraliza(ninjaGlobe);
		
		//Update Color
		kimono.offsetRGB(red, green, blue);
		sombra.offsetRGB(red/2, green/2, blue/2);
		
		loading = 20;
		seta = new BufferedLayer("cursor.png");
		
		//Barras
		
		loading = 30;
		barraVermelha = new ImageLayer("perfil/barrav.png");
		barraVermelha.centralizaX(x,w);
		barraVermelha.setY(210);
		
		loading = 40;
		indicadorV = new ImageLayer("perfil/miniv.png");
		indicadorV.centralizaY(barraVermelha);
		indicadorV.setX(barraVermelha.getX()-indicadorV.getW()/2+red);		
		
		loading = 50;
		barraVerde = new ImageLayer("perfil/barrad.png");
		barraVerde.centralizaX(x,w);
		barraVerde.setY(260);
		
		loading = 60;
		indicadorD = new ImageLayer("perfil/minid.png");
		indicadorD.centralizaY(barraVerde);
		indicadorD.setX(barraVerde.getX()-indicadorD.getW()/2+green);
		
		loading = 70;
		barraAzul = new ImageLayer("perfil/barraa.png");
		barraAzul.centralizaX(x,w);
		barraAzul.setY(310);
		
		loading = 80;
		indicadorA = new ImageLayer("perfil/minia.png");
		indicadorA.centralizaY(barraAzul);
		indicadorA.setX(barraVerde.getX()-indicadorA.getW()/2+blue);
		
		
		loading = 90;
		build = new ImageLayer("fase/vilainicial/loja/build.png");
		build.centralizaX(x,w);
		build.setY(350);
		
		aviso = new TextLayer(build.getX()+30,build.getY()+46,"Você pode trocar a cor da sua roupa.");
		aviso.setBorder(true);
		aviso.setBorderColor(new Color(0x0,0x0,0x0));
		aviso.setColor(0xff,0xff,0xff);
		aviso.setSize(22f);
		
		target = new TextLayer("");
		target.setColor(0xff,0xff,0xff);
		target.setSize(22);
		
		loading = 100;
	}
	
	/*
	public int gerencia(){
		
		botaoVoltar.gerencia();
		if(mouse.sobMouse(botaoVoltar)){
			target.setTexto("Voltar");
			target.centralizaX(mouse);
			target.setY(mouse.getY()-30);
			target.setAparecendo(true);
		}
		else if(mouse.sobMouse(botaoSalvar)){
			target.setTexto("Salvar");
			target.centralizaX(mouse);
			target.setY(mouse.getY()-30);
			target.setAparecendo(true);
		}
		else{
			target.setAparecendo(false);			
		}
		if(botaoVoltar.getAcionado()>0){
			return vila;
		}
		
		botaoSalvar.gerencia();
		if(botaoSalvar.getAcionado()>0){
			
			
			seta.mudaVermelho(red);
			seta.mudaAzul(blue);
			seta.mudaVerde(green);
			
			mouse.mudaRGB(red,green,blue);
			
			aviso.setTexto("As Cores foram salvas.");
			aviso.centraliza(build);
			
			p.set("red", Integer.toString(red));
			p.set("green", Integer.toString(green));
			p.set("blue", Integer.toString(blue));
		}
		

		gerenciaBarras();
		
		return id;
	}
	*/
	
	@Override
	public GUIEvent updateMouse(PointerEvent event){
		
		updateBars(event);
		
		return GUIEvent.NONE;
		
	}
	
	private void updateBars(PointerEvent event){
		
		if(event.getPressed(MouseButton.MOUSE_BUTTON_LEFT)){
			if(barraVermelha.onMouse(event)){
				indicadorV.setX(event.getX()-indicadorV.getW()/2);
				
				int fatorX = 1;
				red = event.getX()-barraVermelha.getX()/fatorX;
				
				kimono.offsetRGB(red, green, blue);
				sombra.offsetRGB(red/2, green/2, blue/2);
				
			}else if(barraVerde.onMouse(event)){
				indicadorD.setX(event.getX()-indicadorD.getW()/2);
				
				int fatorX = 1;
				green = event.getX()-barraVerde.getX()/fatorX;
				
				kimono.offsetRGB(red, green, blue);
				sombra.offsetRGB(red/2, green/2, blue/2);
				
			}else if(barraAzul.onMouse(event)){
				indicadorA.setX(event.getX()-indicadorA.getW()/2);
				
				int fatorX = 1;
				blue = event.getX()-barraAzul.getX()/fatorX;
				
				kimono.offsetRGB(red, green, blue);
				sombra.offsetRGB(red/2, green/2, blue/2);
				
			}
		}
	}
	
	
	@Override
	public void draw(Grafico g){

		fundo.draw(g);
		ninjaGlobe.draw(g);
		
		sombra.draw(g);
		kimono.draw(g);
		pele.draw(g);
		
		barraVermelha.draw(g);
		barraVerde.draw(g);
		barraAzul.draw(g);
		
		indicadorV.draw(g);
		indicadorD.draw(g);
		indicadorA.draw(g);
		
		build.draw(g);
		aviso.draw(g);
		
		target.draw(g);
	}
	
}
