package main;
import java.util.ArrayList;
import java.util.Random;
public class AlgGen {
	
	//Trocavel
		int populacao = 7; // Precisa ser impar
	 	int tamanho_cromossomo = 10; //Precisa ser par
	 int limitegeracao=40;
	//
	 int geracao=0;
	 Random random = new Random();
	//Movimentos 0=NW 1=N 2=NE 3=E  4=SE 5=S 6=SW 7=W
	ArrayList<Aptidao> matriz_Atual_Aptidoes = new ArrayList<Aptidao>();
		 public  void callAG() {
				
		    int[][] matrizA = new int[populacao][tamanho_cromossomo];
			int[][] matrizI = new int[populacao][tamanho_cromossomo];

			populaMatriz(matrizA);
			
		//	playGod( matrizI, matrizA);
			 for (int x=0; x<populacao;x++) {
					calculaAptidao(matrizA, x);
					
				}
			
		}
	 
	 public  void playGod(int[][] matrizI, int[][]matrizA) {
		
		 geracao=geracao+1;
		 
		 System.out.println();
		 
		 System.out.println("Geracao: "+geracao);	
		 
		 System.out.println();
		 
		 for (int x=0; x<populacao;x++) {
				calculaAptidao(matrizA, x);
				
			}
			
			 elite = matrizA[0][tamanho_cromossomo];
			int linhaElite =0;
			
			for (int i=1;i<matrizA.length;i++) {
				if (matrizA[i][tamanho_cromossomo]<elite) {
					elite = matrizA[i][tamanho_cromossomo];
					linhaElite=i;
				}
			}
			
			for (int j=0;j<tamanho_cromossomo;j++) {
				matrizI[0][j]=matrizA[linhaElite][j];
			}
			
			imprimeMatriz(matrizA);
			System.out.println();
			System.out.println("Elitísmo: "+linhaElite);
			System.out.println();
			
			for (int x=0;x<populacao;x++) {
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
			int[][] matrizInext = new int[populacao][tamanho_cromossomo+1];
			if (limitegeracao>geracao) {
				
			playGod(matrizInext, matrizI);
			}
			}
			
	 }

	 public int[][] mutacao(int[][] matrizI) {
		 Random r = new Random();
		 int i=0;
		 while (i==0) {
		  i=r.nextInt(populacao);
		 }
		 int j=r.nextInt(tamanho_cromossomo-1);
		 
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
	 
		public  void populaMatriz(int[][] matriz) {
			
			Random r = new Random();

			for (int i=0;i<populacao;i++) {
				for (int j=0;j<tamanho_cromossomo;j++) {
					matriz[i][j]=r.nextInt(8);
				}
			}

			imprimeMatriz(matriz);
			
		}
		
		public  void imprimeMatriz(int[][] matriz) {
			for (int x=0;x<populacao;x++) {
				for (int y=0;y<tamanho_cromossomo;y++) {
					System.out.print(matriz[x][y]+"");
				}
				System.out.println();
			}
				}

	public  void calculaAptidao(int[][] matriz, int x) {
	String cromossomo = "";
	for (int y=0;y<tamanho_cromossomo;y++) {
		cromossomo=cromossomo+matriz[x][y];
	}
	System.out.println(cromossomo);
	}

	public int torneio(int matrizA[][]) {
		Random r = new Random();
		int larissa= r.nextInt(matrizA.length);
		int gabriel=r.nextInt(matrizA.length);
		
		if (matrizA[larissa][tamanho_cromossomo]<matrizA[gabriel][tamanho_cromossomo]) {
			return larissa;
		}
		else {
			return gabriel;
		}

	}

	public  void crossover(int[][] matrizI, int[][]matrizA, int posicao) {
		int pai;
		int mae;
		int[] filho1 = new int[tamanho_cromossomo+1];
		int[] filho2= new int[tamanho_cromossomo+1];
		
		pai= torneio(matrizA);
		mae = torneio(matrizA);
		while(pai==mae){
			mae = torneio(matrizA);
		}
		System.out.println("Linhas "+posicao+" e "+(posicao+1));
		System.out.println("Pai: "+pai);
		System.out.println("Mae: "+mae);
		
		//filho 1
		for (int i=0;i<tamanho_cromossomo/2;i++) {
			filho1[i]=matrizA[pai][i];
		}
		for (int i=tamanho_cromossomo/2;i<tamanho_cromossomo;i++) {
			filho1[i]=matrizA[mae][i];
		}
		//filho 2
			for (int i=0;i<tamanho_cromossomo/2;i++) {
				filho2[i]=matrizA[mae][i];
			}
			for (int i=tamanho_cromossomo/2;i<tamanho_cromossomo;i++) {
				filho2[i]=matrizA[pai][i];
			}
			
			for (int i=0;i<tamanho_cromossomo;i++) {
				matrizI[posicao][i]=filho1[i];
			}
			for (int i=0;i<tamanho_cromossomo;i++) {
				matrizI[posicao+1][i]=filho2[i];
			}

	}
	}



