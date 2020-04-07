package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import world.World;

public class Save_Game {
	public static boolean saveExist = false;
	public static boolean saveGame = false;

	public static void save() {
		BufferedWriter write = null;
		try {
			write = new BufferedWriter(new FileWriter("save.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		write = creat_String(write);
		try {
			write.flush();
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static BufferedWriter creat_String(BufferedWriter write) {
/*
		String current;
		for (int i = 0; i < Score.scores.size(); i++) {
			current = i + "";
			try {
				write.write(current);
				write.newLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			for (int j = 0; j < Score.scores.get(i).size(); j++) {
				current = Score.scores.get(i).get(j).name + ":" + Score.scores.get(i).get(j).strokes;
				try {
					write.write(current);
					if (i < Score.scores.size() - 1 || j < Score.scores.get(i).size() - 1) {
						write.newLine();
					}
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

		}
		*/
		return write;

	}

	public static void load() {
		String path="save.txt";
		int cont_line = 1;
		
		File file = new File(path);
		if (file.exists()) {
			String singleLine = null;
			try {
				BufferedReader reader = new BufferedReader(new FileReader(path));
				try {
					while ((singleLine = reader.readLine()) != null) {
						if (cont_line>1) {
							int y=cont_line-2;
							String [] posicoes= singleLine.split(" ");
						for (int x=0;x<10;x++) {
							String posicao_atual= posicoes[x];
							int tile=0;
							//0=chao 1=parede 2=buraco 3=S 4=entrada
							if (posicao_atual.equals("E")) {
								tile=4;
								World.x_inicial=x;
								World.y_inicial=y;
							}else if (posicao_atual.equals("0")) {
								tile=0;
							}else if (posicao_atual.equals("B")) {
								tile=2;
							}else if (posicao_atual.equals("1")) {
								tile=1;
							}else if (posicao_atual.equals("S")) {
								tile=3;
								}
							World.labirinto[x][y]=tile;
							
						}

						
						}
						cont_line++;
						
					}
				} catch (IOException e) {

					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
		}

	}

}
