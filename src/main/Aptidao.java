package main;

public class Aptidao {
	//v1= Numero de casas do tipo chao pisadas e nao repetidas, antes da primeira dead zone
	public int v1;
	//v2= Numero de casas repetidas pisadas, antes da primeira dead zone
	public int v2;
	
	public Aptidao(int v1, int v2) {
		this.v1=v1;
		this.v2=v2;
		
	}

	@Override
	public String toString() {
		return "Aptidao [v1=" + v1 + ", v2=" + v2 + "]";
	}
	
}
