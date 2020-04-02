package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.imageio.ImageIO;
import com.sun.prism.paint.Color;
import entities.Entity;
import entities.Player;
import graficos.ImageResizer;
import main.Game;
import main.Save_Game;
import som.Sound;

public class World {

	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	//0=chao 1=parede 2=buraco 3=S 4=entrada
	public static int[][] labirinto;



	public World() {
		labirinto = new int[10][10];
		Save_Game.load();
		for (int y=0;y<10;y++) {
			for (int x=0;x<10;x++) {
				System.out.print(labirinto[x][y]+"");
			}
			System.out.println();
		}
	}


	public static boolean isFree(double x, double y) {
		int xnext = (int) x;
		int ynext = (int) y;

		int x1 = xnext / Game.TILE_SIZE;
		int y1 = ynext / Game.TILE_SIZE;

		int x2 = (xnext + Game.TILE_SIZE - 1) / Game.TILE_SIZE;
		int y2 = ynext / Game.TILE_SIZE;

		int x3 = xnext / Game.TILE_SIZE;
		int y3 = (ynext + Game.TILE_SIZE - 1) / Game.TILE_SIZE;

		int x4 = (xnext + Game.TILE_SIZE - 1) / Game.TILE_SIZE;
		int y4 = (ynext + Game.TILE_SIZE - 1) / Game.TILE_SIZE;

		return !(tiles[x1 + (y1 * World.WIDTH)] instanceof Wall_Tile
				|| tiles[x2 + (y2 * World.WIDTH)] instanceof Wall_Tile
				|| tiles[x3 + (y3 * World.WIDTH)] instanceof Wall_Tile
				|| tiles[x4 + (y4 * World.WIDTH)] instanceof Wall_Tile);

	}

	public void render(Graphics g) {

		for (int x = 0; x < World.WIDTH; x++) {
			for (int y = 0; y < World.HEIGHT; y++) {
				Tile tile = tiles[x + (y * WIDTH)];

				tile.render(g);
			}
		}

	}

	public static void depth(ArrayList<Entity> a) {
		// Crescente
		Collections.sort(a, listSorter);

		// Decrescente
		// Collections.sort(a, Collections.reverseOrder());

	}

	public static Comparator<Entity> listSorter = new Comparator<Entity>() {

		@Override
		public int compare(Entity n0, Entity n1) {
			if (n1.depth < n0.depth) {
				return -1;
			}
			if (n1.depth > n0.depth) {
				return +1;
			}
			return 0;
		}

	};

}
