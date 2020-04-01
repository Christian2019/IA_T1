package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
	/*
		int cont_line = 1;
		int nivel = 0;
		int p = 0;
		String line = "";
		File file = new File("save.txt");
		if (file.exists()) {
			String singleLine = null;
			try {
				BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
				try {
					while ((singleLine = reader.readLine()) != null) {
						if ((cont_line - nivel) - (Score.scores.get(0).size() * nivel) == 1) {
							nivel++;
							p = 0;
						} else {
							String[] score = singleLine.split(":");
							line = score[0];
							Score.scores.get(nivel - 1).get(p - 1).name = line;
							line = score[1];
							Score.scores.get(nivel - 1).get(p - 1).strokes = line;

						}
						cont_line++;
						p++;
					}
				} catch (IOException e) {

					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
		}
*/
	}

}
