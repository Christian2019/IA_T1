package main;
import java.util.Random;
public class AlgGen {
	


	//Trocavel
	static	int populacao = 7; // Precisa ser impar
	static 	int tamanho = 10; //Precisa ser par
	static int[] inicial = {5,10,15,3,10,5,2,16,9,7};
	static int limitegeracao=40;
	//


	static int elite=100;
	static int geracao=0;
	static Random random = new Random();
	public static void populacaoInicial() {
		for (int i=0;i<5;i++) {
			System.out.println("Cromossomo: "+i);
			System.out.println();
			for (int j=0;j<8;j++) {
				int r =random.nextInt(8);
				System.out.println(r);
				}
			
		}
		
	}

	 public static void callAG() {
			
			
			
			
		    int[][] matrizA = new int[populacao][tamanho+1];
			int[][] matrizI = new int[populacao][tamanho+1];

			populaMatriz(matrizA);
			
			playGod( matrizI, matrizA);
		
			
		}
	 
	 public static void playGod(int[][] matrizI, int[][]matrizA) {
		
		 geracao=geracao+1;
		 
		 System.out.println();
		 
		 System.out.println("Geracao: "+geracao);	
		 
		 System.out.println();
		 
		 for (int i=0; i<matrizA.length;i++) {
				calculaAptidao(matrizA[i], inicial);
				
			}
			
			 elite = matrizA[0][tamanho];
			int linhaElite =0;
			
			for (int i=1;i<matrizA.length;i++) {
				if (matrizA[i][tamanho]<elite) {
					elite = matrizA[i][tamanho];
					linhaElite=i;
				}
			}
			
			for (int j=0;j<tamanho;j++) {
				matrizI[0][j]=matrizA[linhaElite][j];
			}
			
			imprimeMatriz(matrizA);
			System.out.println();
			System.out.println("Elitísmo: "+linhaElite);
			System.out.println();
			
			for (int i=1;i<populacao-1;i=i+2) {
			crossover(matrizI,matrizA, i);
			
			}
			
			matrizI=mutacao(matrizI);
			
			for (int i=0; i<matrizI.length;i++) {
				calculaAptidao(matrizI[i], inicial);
				
			}
			
			imprimeMatriz(matrizI);
			
			if (elite==0) {
				System.out.println("Apt 0 encontrada! ");
				
			}
			else {
			int[][] matrizInext = new int[populacao][tamanho+1];
			if (limitegeracao>geracao) {
				
			playGod(matrizInext, matrizI);
			}
			}
	 }

	 public static int[][] mutacao(int[][] matrizI) {
		 Random r = new Random();
		 int i=0;
		 while (i==0) {
		  i=r.nextInt(populacao);
		 }
		 int j=r.nextInt(tamanho-1);
		 
//		 System.out.println("i: "+i);
		// System.out.println("j: "+j);
		// System.out.println("Antes da mutacao: "+matrizI[i][j]);
		 
		if (matrizI[i][j]==0) {
			matrizI[i][j]=1;
		}
		else {
			matrizI[i][j]=0;
		}
//		System.out.println("Depois da mutacao: "+matrizI[i][j]);
		 return matrizI;
	 }
	 
		public static void populaMatriz(int[][] matriz) {
			
			Random r = new Random();

			for (int i=0;i<matriz.length;i++) {
				for (int j=0;j<matriz[i].length-1;j++) {
					matriz[i][j]=r.nextInt(2);
				}
			}

			
			
		}
		
		public static void imprimeMatriz(int[][] matriz) {
			for (int i=0;i<matriz.length;i++) {
				int j=0;
				for ( j=0;j<matriz[i].length-1;j++) {
					System.out.print(matriz[i][j]+" ");
						}
			
				System.out.println("\tApt: " + matriz[i][j]);
			}
				}

	public static void calculaAptidao(int[] linha, int[] carga) {
		int a1=0;
		int a2=0;
		
		for (int i=0; i<carga.length;i++) {
		
	if (linha[i]==0) {
		a1+=carga[i];
		}else {
			a2+=carga[i];
		}
		
		}

	linha[carga.length]=Math.abs(a2-a1);

	}

	public static int torneio(int matrizA[][]) {
		Random r = new Random();
		int larissa= r.nextInt(matrizA.length);
		int gabriel=r.nextInt(matrizA.length);
		
		if (matrizA[larissa][tamanho]<matrizA[gabriel][tamanho]) {
			return larissa;
		}
		else {
			return gabriel;
		}

	}

	public static void crossover(int[][] matrizI, int[][]matrizA, int posicao) {
		int pai;
		int mae;
		int[] filho1 = new int[tamanho+1];
		int[] filho2= new int[tamanho+1];
		
		pai= torneio(matrizA);
		mae = torneio(matrizA);
		while(pai==mae){
			mae = torneio(matrizA);
		}
		System.out.println("Linhas "+posicao+" e "+(posicao+1));
		System.out.println("Pai: "+pai);
		System.out.println("Mae: "+mae);
		
		//filho 1
		for (int i=0;i<tamanho/2;i++) {
			filho1[i]=matrizA[pai][i];
		}
		for (int i=tamanho/2;i<tamanho;i++) {
			filho1[i]=matrizA[mae][i];
		}
		//filho 2
			for (int i=0;i<tamanho/2;i++) {
				filho2[i]=matrizA[mae][i];
			}
			for (int i=tamanho/2;i<tamanho;i++) {
				filho2[i]=matrizA[pai][i];
			}
			
			for (int i=0;i<tamanho;i++) {
				matrizI[posicao][i]=filho1[i];
			}
			for (int i=0;i<tamanho;i++) {
				matrizI[posicao+1][i]=filho2[i];
			}

	}
	}



