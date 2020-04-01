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
import som.Sound;

public class World {

	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;



	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());

			for (int x = 0; x < map.getWidth(); x++) {
				for (int y = 0; y < map.getHeight(); y++) {
					int pixelAtual = pixels[x + (y * map.getWidth())];
					// Chao
			//		tiles[x + (y * WIDTH)] = new Floor_Tile(x * Game.TILE_SIZE, y * Game.TILE_SIZE, Tile.TILE_FLOOR);
					if (pixelAtual == 0xFFB200FF) {
						// Parede
					//	tiles[x + (y * WIDTH)] = new Wall_Tile(x * Game.TILE_SIZE, y * Game.TILE_SIZE, Tile.TILE_WALL);

					}else if (pixelAtual == 0xFFFF0000) {
						// Brick
						//Brick brick = new Brick(x*Game.TILE_SIZE, y*Game.TILE_SIZE, 1*Game.TILE_SIZE, 1*Game.TILE_SIZE, null, 0, 2);
						//Game.entities.add(brick);

					}
 
						
					
					
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void creat_tabuleiro(Graphics g) {
		g.drawRect(91*Game.SCALE, 0, Game.WIDTH*Game.SCALE - 1, Game.HEIGHT*Game.SCALE - 1);
		for (int i=0;i<9;i++) {
			g.fillRect((91*Game.SCALE)+(i*(Game.HEIGHT*Game.SCALE/9)), 0, 1, (Game.HEIGHT*Game.SCALE));
		}
		for (int i=0;i<9;i++) {
			g.fillRect((91*Game.SCALE), (i*(Game.HEIGHT*Game.SCALE/9)), (Game.WIDTH*Game.SCALE), 1);
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
