package main;

public class Aptidao {
	//v1= Numero de casas do tipo chao pisadas e nao repetidas, antes da primeira dead zone
	public int v1;
	//v2= Numero de casas repetidas pisadas, antes da primeira dead zone
	public int v2;
	//v3= Numero de casas pisadas fora do jogo
	public int v3;
	
	public Aptidao(int v1, int v2, int v3) {
		this.v1=v1;
		this.v2=v2;
		this.v3=v3;
	}
}
