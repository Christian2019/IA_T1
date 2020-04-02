package main;

import java.util.ArrayList;
import java.util.Random;

import graficos.Ponto;
import world.World;

public class AlgGen {

	// Trocavel
	int populacao = 7; // Precisa ser impar
	int tamanho_cromossomo = 10; // Precisa ser par
	int limitegeracao = 40;
	//
	int geracao = 0;
	Random random = new Random();
	// Movimentos 0=NW 1=N 2=NE 3=E 4=SE 5=S 6=SW 7=W
	ArrayList<Aptidao> matriz_Atual_Aptidoes = new ArrayList<Aptidao>();
	boolean solucao_encontrada=false;
	int solucao_x;
	int[] solucao;
	public void callAG() {

		int[][] matrizA = new int[populacao][tamanho_cromossomo];
		int[][] matrizI = new int[populacao][tamanho_cromossomo];

		populaMatriz(matrizA);

		// playGod( matrizI, matrizA);
		for (int x = 0; x < populacao; x++) {
			System.out.println("Filho " + x);
			calculaAptidao(matrizA, x);
		}
		System.out.println(this.matriz_Atual_Aptidoes);
	}

	public void playGod(int[][] matrizI, int[][] matrizA) {
		this.matriz_Atual_Aptidoes.clear();
		geracao = geracao + 1;

		System.out.println();

		System.out.println("Geracao: " + geracao);

		System.out.println();

		for (int x = 0; x < populacao; x++) {
			calculaAptidao(matrizA, x);
		}
		if (this.solucao_encontrada) {
		solucao= new int [this.tamanho_cromossomo];
		for (int i=0;i<this.tamanho_cromossomo;i++) {
			solucao[i]=matrizA[this.solucao_x][i];
			
			System.out.println("solucao encontrada!");
			System.out.println(solucao);
			return;
		}
		
		System.out.println();
		}
		System.out.println();
		System.out.println("Elitísmo: " + linhaElite);
		System.out.println();

		for (int x = 0; x < populacao; x++) {
			crossover(matrizI, matrizA, i);

		}

		matrizI = mutacao(matrizI);

		for (int i = 0; i < matrizI.length; i++) {
			calculaAptidao(matrizI[i], inicial);

		}

		imprimeMatriz(matrizI);

		if (elite == 0) {
			System.out.println("Apt 0 encontrada! ");

		} else {
			int[][] matrizInext = new int[populacao][tamanho_cromossomo + 1];
			if (limitegeracao > geracao) {

				playGod(matrizInext, matrizI);
			}
		}

	}

	public int[][] mutacao(int[][] matrizI) {
		Random r = new Random();
		int i = 0;
		while (i == 0) {
			i = r.nextInt(populacao);
		}
		int j = r.nextInt(tamanho_cromossomo - 1);

//		 System.out.println("i: "+i);
		// System.out.println("j: "+j);
		// System.out.println("Antes da mutacao: "+matrizI[i][j]);

		if (matrizI[i][j] == 0) {
			matrizI[i][j] = 1;
		} else {
			matrizI[i][j] = 0;
		}
//		System.out.println("Depois da mutacao: "+matrizI[i][j]);
		return matrizI;
	}

	public void populaMatriz(int[][] matriz) {

		Random r = new Random();

		for (int i = 0; i < populacao; i++) {
			for (int j = 0; j < tamanho_cromossomo; j++) {
				matriz[i][j] = r.nextInt(8);
			}
		}

		// imprimeMatriz(matriz);

	}

	public void imprimeMatriz(int[][] matriz) {
		for (int x = 0; x < populacao; x++) {
			for (int y = 0; y < tamanho_cromossomo; y++) {
				System.out.print(matriz[x][y] + "");
			}
			System.out.println();
		}
	}

	public void calculaAptidao(int[][] matriz, int x) {
		String cromossomo = "";
		for (int y = 0; y < tamanho_cromossomo; y++) {
			cromossomo = cromossomo + matriz[x][y];
		}
		int x_atual = 0;
		int y_atual = 0;

		ArrayList<Ponto> casas = new ArrayList<Ponto>();
		Ponto entrada = new Ponto(x_atual, y_atual);
		casas.add(entrada);
//Movimentos 0=NW 1=N 2=NE 3=E  4=SE 5=S 6=SW 7=W
		for (int i = 0; i < cromossomo.length(); i++) {
			String movimento_atual = cromossomo.substring(i, i + 1);
			if (movimento_atual.equals("0")) {
				x_atual--;
				y_atual--;
			} else if (movimento_atual.equals("1")) {
				y_atual--;
			} else if (movimento_atual.equals("2")) {
				x_atual++;
				y_atual--;
			} else if (movimento_atual.equals("3")) {
				x_atual++;
			} else if (movimento_atual.equals("4")) {
				x_atual++;
				y_atual++;
			} else if (movimento_atual.equals("5")) {
				y_atual++;
			} else if (movimento_atual.equals("6")) {
				x_atual--;
				y_atual++;
			} else if (movimento_atual.equals("7")) {
				x_atual--;
			}
			Ponto ponto = new Ponto(x_atual, y_atual);
			casas.add(ponto);

		}
		System.out.println(cromossomo);
		System.out.println(casas);

		boolean vivo = true;
		ArrayList<Ponto> caminho_vivo = new ArrayList<Ponto>();
//0=chao 1=parede 2=buraco 3=S 4=entrada
		for (int i = 0; i < casas.size(); i++) {
			if (vivo) {
				try {
					if (World.labirinto[casas.get(i).x][casas.get(i).y] == 0
							|| World.labirinto[casas.get(i).x][casas.get(i).y] == 3
							|| World.labirinto[casas.get(i).x][casas.get(i).y] == 4) {
						Ponto p_vivo = new Ponto(casas.get(i).x, casas.get(i).y);
						caminho_vivo.add(p_vivo);
						if (World.labirinto[casas.get(i).x][casas.get(i).y] == 3) {
							this.solucao_encontrada=true;
							this.solucao_x=x;
						}
					} else {
						vivo = false;
					}
				} catch (IndexOutOfBoundsException e) {
					vivo = false;
				}
			} else {
				break;
			}
		}

		/*
		 * Teste caminho_vivo.clear(); Ponto p1 = new Ponto(0, 0); Ponto p2 = new
		 * Ponto(1, 0); Ponto p3 = new Ponto(2, 0); Ponto p4 = new Ponto(2, 1); Ponto p5
		 * = new Ponto(2, 0); Ponto p6 = new Ponto(1, 1); Ponto p7 = new Ponto(2, 1);
		 * caminho_vivo.add(p1); caminho_vivo.add(p2); caminho_vivo.add(p3);
		 * caminho_vivo.add(p4); caminho_vivo.add(p5); caminho_vivo.add(p6);
		 * caminho_vivo.add(p7);
		 */
		System.out.println(caminho_vivo);
		int val1 = -1;
		int val2 = 0;
		for (int i = 0; i < caminho_vivo.size(); i++) {
			int repetidas = 0;
			for (int j = 0; j < i; j++) {
				if (caminho_vivo.get(i).x == caminho_vivo.get(j).x && caminho_vivo.get(i).y == caminho_vivo.get(j).y) {
					repetidas++;
				}

			}
			if (repetidas == 0) {
				val1++;
			} else {
				val2 += repetidas;
			}

		}
		System.out.println(val1);
		System.out.println(val2);

		Aptidao aptidao = new Aptidao(val1, val2);
		this.matriz_Atual_Aptidoes.add(aptidao);
	}

	public int torneio(int matrizA[][]) {
		Random r = new Random();
		int larissa = r.nextInt(matrizA.length);
		int gabriel = r.nextInt(matrizA.length);

		if (matrizA[larissa][tamanho_cromossomo] < matrizA[gabriel][tamanho_cromossomo]) {
			return larissa;
		} else {
			return gabriel;
		}

	}

	public void crossover(int[][] matrizI, int[][] matrizA, int posicao) {
		int pai;
		int mae;
		int[] filho1 = new int[tamanho_cromossomo + 1];
		int[] filho2 = new int[tamanho_cromossomo + 1];

		pai = torneio(matrizA);
		mae = torneio(matrizA);
		while (pai == mae) {
			mae = torneio(matrizA);
		}
		System.out.println("Linhas " + posicao + " e " + (posicao + 1));
		System.out.println("Pai: " + pai);
		System.out.println("Mae: " + mae);

		// filho 1
		for (int i = 0; i < tamanho_cromossomo / 2; i++) {
			filho1[i] = matrizA[pai][i];
		}
		for (int i = tamanho_cromossomo / 2; i < tamanho_cromossomo; i++) {
			filho1[i] = matrizA[mae][i];
		}
		// filho 2
		for (int i = 0; i < tamanho_cromossomo / 2; i++) {
			filho2[i] = matrizA[mae][i];
		}
		for (int i = tamanho_cromossomo / 2; i < tamanho_cromossomo; i++) {
			filho2[i] = matrizA[pai][i];
		}

		for (int i = 0; i < tamanho_cromossomo; i++) {
			matrizI[posicao][i] = filho1[i];
		}
		for (int i = 0; i < tamanho_cromossomo; i++) {
			matrizI[posicao + 1][i] = filho2[i];
		}

	}
}
