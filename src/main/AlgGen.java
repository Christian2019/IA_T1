package main;

import java.util.ArrayList;
import java.util.Random;

import graficos.Ponto;
import world.World;

public class AlgGen {

	// Trocavel
	int populacao = 1001; // Precisa ser impar Ideal 1001
	int tamanho_cromossomo = 50; // Ideal 50
	int limitegeracao = 1000;
	double taxa_mutacao = 2; // % de ocorrer. Valor(entre 0.1-100) Ideal 2
	//
	int geracao = 0;
	Random random = new Random();
	// Movimentos 0=NW 1=N 2=NE 3=E 4=SE 5=S 6=SW 7=W
	ArrayList<Aptidao> matriz_Atual_Aptidoes = new ArrayList<Aptidao>();
	boolean solucao_encontrada = false;
	int solucao_x;
	int[] solucao;
	int[] elite;

	public void callAG() {

		int[][] matrizA = new int[populacao][tamanho_cromossomo];
//Populacao inicial
		populaMatriz(matrizA);
//Comeca a brincadeira
		playGod(matrizA);
	}

	public void playGod(int[][] matrizA) {

		this.matriz_Atual_Aptidoes.clear();
		geracao = geracao + 1;

		System.out.println();

		System.out.println("Geracao: " + geracao);

		System.out.println();

		// Defini a aptidao de cada pessoa da populacao
		for (int x = 0; x < populacao; x++) {
			calculaAptidao(matrizA, x);
		}
		// Solucao encontrada para de rodar
		if (this.solucao_encontrada) {
			solucao = new int[this.tamanho_cromossomo];
			for (int i = 0; i < this.tamanho_cromossomo; i++) {
				solucao[i] = matrizA[this.solucao_x][i];

			}

			this.imprimeVetor(solucao);
			System.out.println();
			ArrayList<Ponto> casas = new ArrayList<Ponto>();
			casas = this.movimentos_pontos(solucao);
			for (int i = 0; i < casas.size(); i++) {
				System.out.print(casas.get(i));
			}
			System.out.println("solucao encontrada!");
			return;
		}

		System.out.println("Populacao atual:");
	//	this.imprimeMatriz(matrizA);
		System.out.println("Aptidoes:");
		for (int i=0;i<this.matriz_Atual_Aptidoes.size();i++) {
		System.out.print(this.matriz_Atual_Aptidoes.get(i));
		}
System.out.println();
		// Define o melhor da populacao e joga na nova geracao
		int x_elite = this.elitismo();
		elite = new int[this.tamanho_cromossomo];
		for (int i = 0; i < this.tamanho_cromossomo; i++) {
			elite[i] = matrizA[x_elite][i];
		}

		System.out.println("elite:");
		for (int i = 0; i < this.tamanho_cromossomo; i++) {
			System.out.print(elite[i]);

		}
		System.out.println();
		System.out.println("Aptidao elite: " + this.matriz_Atual_Aptidoes.get(x_elite));

		int[][] matrizI = new int[populacao][tamanho_cromossomo];
		for (int i = 0; i < this.tamanho_cromossomo; i++) {
			matrizI[0][i] = elite[i];
		}

		// Torneio;

		ArrayList<int[]> filhos = new ArrayList<int[]>();

		for (int i = 0; i < populacao - 1; i += 2) {
			boolean iguais = true;
			int pai = 0;
			int mae = 0;
			while (iguais) {
				pai = torneio();
				mae = torneio();
				if (pai != mae) {
					iguais = false;
				}
			}
			// Crossover
			crossover(pai, mae, filhos, matrizA);
		}
		/*
		 * for (int i=0;i<filhos.size();i++) { System.out.println();
		 * this.imprimeVetor(filhos.get(i)); } System.out.println();
		 */
		// Mutacao
		mutacao(filhos);

		// nova geracao pronta
		for (int x = 1; x < this.populacao; x++) {
			for (int y = 0; y < this.tamanho_cromossomo; y++) {
				matrizI[x][y] = filhos.get(x - 1)[y];
			}

		}
		if (this.geracao == this.limitegeracao) {
			System.out.println("Limite de geracao");
			return;
		}
		playGod(matrizI);

	}

	public int elitismo() {
		int elite = 0;
		int melhor_v1 = 0;
		int melhor_v2 = 100;
		for (int i = 0; i < this.matriz_Atual_Aptidoes.size(); i++) {
			if (this.matriz_Atual_Aptidoes.get(i).v1 > melhor_v1) {
				melhor_v1 = this.matriz_Atual_Aptidoes.get(i).v1;
				elite = i;
			} else if (this.matriz_Atual_Aptidoes.get(i).v1 == melhor_v1) {
				if (this.matriz_Atual_Aptidoes.get(i).v2 < melhor_v2) {
					melhor_v2 = this.matriz_Atual_Aptidoes.get(i).v2;
					elite = i;
				}
			}

		}
		return elite;
	}

	public void mutacao(ArrayList<int[]> filhos) {
		Random random = new Random();
		int chance = (int) (this.taxa_mutacao * 10);

		for (int i = 0; i < filhos.size(); i++) {
			for (int j = 0; j < filhos.get(i).length; j++) {
				if (random.nextInt(1000) <= chance) {
				//	System.out.println("MUTACAO!");
					int novo_movimento = random.nextInt(8);
					filhos.get(i)[j] = novo_movimento;
				}

			}
		}

	}

	public void populaMatriz(int[][] matriz) {

		Random r = new Random();

		for (int i = 0; i < populacao; i++) {
			for (int j = 0; j < tamanho_cromossomo; j++) {
				matriz[i][j] = r.nextInt(8);
			}
		}
		for (int x = 0; x < populacao; x++) {
			calculaAptidao(matriz, x);
		}

		// Restarta se o comeco for ruim
		int x_elite = this.elitismo();
		if (this.matriz_Atual_Aptidoes.get(x_elite).v1 < 1) {
			populaMatriz(matriz);
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

	public ArrayList<Ponto> movimentos_pontos(int[] vetor) {
		String cromossomo = "";
		for (int y = 0; y < tamanho_cromossomo; y++) {
			cromossomo = cromossomo + vetor[y];
		}
		int x_atual = World.x_inicial;
		int y_atual = World.y_inicial;

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
		return casas;
	}

	public void calculaAptidao(int[][] matriz, int x) {
		String cromossomo = "";
		for (int y = 0; y < tamanho_cromossomo; y++) {
			cromossomo = cromossomo + matriz[x][y];
		}
		int x_atual = World.x_inicial;
		int y_atual = World.y_inicial;

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
		// System.out.println(cromossomo);
		// System.out.println(casas);

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
							this.solucao_encontrada = true;
							this.solucao_x = x;
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
		// System.out.println(caminho_vivo);
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
		// System.out.println(val1);
		// System.out.println(val2);

		Aptidao aptidao = new Aptidao(val1, val2);
		this.matriz_Atual_Aptidoes.add(aptidao);
	}

	public int torneio() {
		Random random = new Random();
		int vencedor = 0;
		int c1 = random.nextInt(populacao);
		int c2 = random.nextInt(populacao);
		while (c1 == c2) {
			c1 = random.nextInt(populacao);
			c2 = random.nextInt(populacao);
		}
		if (this.matriz_Atual_Aptidoes.get(c1).v1 > this.matriz_Atual_Aptidoes.get(c2).v1) {
			vencedor = c1;
		} else if (this.matriz_Atual_Aptidoes.get(c1).v1 < this.matriz_Atual_Aptidoes.get(c2).v1) {
			vencedor = c2;
		} else if (this.matriz_Atual_Aptidoes.get(c1).v1 == this.matriz_Atual_Aptidoes.get(c2).v1) {
			if (this.matriz_Atual_Aptidoes.get(c1).v2 <= this.matriz_Atual_Aptidoes.get(c2).v2) {
				vencedor = c1;
			} else {
				vencedor = c2;
			}
		}

		return vencedor;
	}

	public void crossover(int pai, int mae, ArrayList<int[]> filhos, int[][] matrizA) {

		int[] filho1 = new int[tamanho_cromossomo];
		int[] filho2 = new int[tamanho_cromossomo];
		// ponto de corte
		int pc1 = 0;
		if (this.matriz_Atual_Aptidoes.get(pai).v1 >= this.matriz_Atual_Aptidoes.get(mae).v1) {
			pc1 = this.matriz_Atual_Aptidoes.get(pai).v1;
		} else {
			pc1 = this.matriz_Atual_Aptidoes.get(mae).v1;
		}
		Random random = new Random();
		int pc2 = 0;
		if (pc1 >= this.tamanho_cromossomo - 1) {
			pc2 = pc1;
		} else {
			pc2 = random.nextInt(this.tamanho_cromossomo - pc1 - 1) + pc1 + 1;
		}

		int[] pai_A = new int[pc2 - pc1];
		int[] pai_B = new int[tamanho_cromossomo - pc2];
		int[] mae_A = new int[pc2 - pc1];
		int[] mae_B = new int[tamanho_cromossomo - pc2];

		for (int i = 0; i < pc2 - pc1; i++) {
			pai_A[i] = matrizA[pai][pc1 + i];
		}
		for (int i = 0; i < this.tamanho_cromossomo - pc2; i++) {
			pai_B[i] = matrizA[pai][pc2 + i];
		}
		for (int i = 0; i < pc2 - pc1; i++) {
			mae_A[i] = matrizA[mae][pc1 + i];
		}
		for (int i = 0; i < this.tamanho_cromossomo - pc2; i++) {
			mae_B[i] = matrizA[mae][pc2 + i];
		}

		// filho 1
		for (int i = 0; i < pc1; i++) {
			filho1[i] = matrizA[pai][i];
		}
		for (int i = 0; i < mae_B.length; i++) {
			filho1[i + pc1] = mae_B[i];
		}
		for (int i = 0; i < pai_A.length; i++) {
			filho1[i + pc1 + mae_B.length] = pai_A[i];
		}
		// filho 2
		for (int i = 0; i < pc1; i++) {
			filho2[i] = matrizA[mae][i];
		}
		for (int i = 0; i < pai_B.length; i++) {
			filho2[i + pc1] = pai_B[i];
		}
		for (int i = 0; i < mae_A.length; i++) {
			filho2[i + pc1 + pai_B.length] = mae_A[i];
		}
		filhos.add(filho1);
		filhos.add(filho2);
		/*
		 * System.out.println("matriz"); this.imprimeMatriz(matrizA);
		 * System.out.println("pc1: "+pc1); System.out.println("pc2: "+pc2);
		 * System.out.println("Pai: "+pai); System.out.println("Mae: "+mae);
		 * System.out.println("Filho1:"); imprimeVetor(filho1); System.out.println();
		 * System.out.println("Filho2:"); imprimeVetor(filho2); System.out.println();
		 */
	}

	public void imprimeVetor(int[] vetor) {
		for (int i = 0; i < vetor.length; i++) {
			System.out.print(vetor[i] + "");
		}

	}
}
