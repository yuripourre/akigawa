package akigawa.vilarejo.dialogo;

public class Pergunta {
	private String fala;
	private int idPergunta;
	
	private Resposta resposta[];
	
	
	public Pergunta(String fala, int idPergunta, String resposta0, int r0, String resposta1, int r1, String resposta2, int r2){
		this.fala = fala;
		this.idPergunta = idPergunta;
		
		resposta = new Resposta[3];
		resposta[0] = new Resposta(resposta0,r0);
		resposta[1] = new Resposta(resposta1,r1);
		resposta[2] = new Resposta(resposta2,r2);
	}
	
	public String getFala() {
		return fala;
	}
	public int getIdPergunta() {
		return idPergunta;
	}
	public int getRetorno(int r) {
		return resposta[r].getRetorno();
	}
	public String getResposta(int r) {
		return resposta[r].getFala();
	}
}
