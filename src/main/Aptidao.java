package main;

public class Aptidao {
	//v1= Numero de casas do tipo chao pisadas e nao repetidas, antes da primeira dead zone
	public int v1;
	//v2= Numero de casas repetidas pisadas, antes da primeira dead zone
	public int v2;
	//v3= Lugares novos
	public int v3;
	
	public Aptidao(int v1, int v2, int v3) {
		this.v1=v1;
		this.v2=v2;
		this.v3=v3;
		
	}

	@Override
	public String toString() {
		return "Aptidao [v1=" + v1 + ", v2=" + v2 + ", v3=" + v3 + "]";
	}


	
}
