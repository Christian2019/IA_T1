package graficos;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import entities.Entity;
import main.Game;
import world.Floor_Tile;
import world.Tile;
import world.Wall_Tile;
import world.World;

public class PerfectColliding {
	public static ArrayList<Ponto> getPoints(BufferedImage image) {
		ArrayList<Ponto> pontos = new ArrayList<Ponto>();

		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

		for (int x = 0; x < image.getWidth(); x++) {

			for (int y = 0; y < image.getHeight(); y++) {
				int pixelAtual = pixels[x + (y * image.getWidth())];

				if (pixelAtual != 0) {
					if (isEdge(x, y, pixels, image)) {
						Ponto ponto = new Ponto(x,y);
						pontos.add(ponto);
						
						
					}
				}
			}
		}
		return pontos;
	}

	public static boolean isEdge(int x, int y, int[] pixels, BufferedImage image) {
		int pixelteste;
		if (x == 0 || y == 0 || x == image.getWidth() - 1 || y == image.getHeight() - 1) {
			return true;
		}
		// 8 casos

		// 1
		pixelteste = pixels[(x - 1) + ((y - 1) * image.getWidth())];
		if (pixelteste == 0) {
			return true;
		}
		// 2
		pixelteste = pixels[(x) + ((y - 1) * image.getWidth())];
		if (pixelteste == 0) {
			return true;
		}
		// 3
		pixelteste = pixels[(x + 1) + ((y - 1) * image.getWidth())];
		if (pixelteste == 0) {
			return true;
		}
		// 4
		pixelteste = pixels[(x - 1) + ((y) * image.getWidth())];
		if (pixelteste == 0) {
			return true;
		} // 5
		pixelteste = pixels[(x + 1) + ((y) * image.getWidth())];
		if (pixelteste == 0) {
			return true;
		} // 6
		pixelteste = pixels[(x - 1) + ((y + 1) * image.getWidth())];
		if (pixelteste == 0) {
			return true;
		} // 7
		pixelteste = pixels[(x) + ((y + 1) * image.getWidth())];
		if (pixelteste == 0) {
			return true;
		} // 8
		pixelteste = pixels[(x + 1) + ((y + 1) * image.getWidth())];
		if (pixelteste == 0) {
			return true;
		}

		return false;
	}
	public static boolean isPerfectColliding(Entity e1, Entity e2) {
		ArrayList<Ponto> pontos = getPoints(e1.getSprite());
		int x1=0;
		int y1=0;
		for (int i=0;i<pontos.size();i++) {
		x1=(int)e1.getX()+pontos.get(i).x;
		y1=(int)e1.getY()+pontos.get(i).y;
		
		Rectangle r1 = new Rectangle(x1, y1, 1, 1);
		Rectangle r2 = new Rectangle((int) e2.getX(), (int) e2.getY(), e2.getWidth(), e2.getHeight());

		if (r1.intersects(r2)) {
			return true;
		}
		
		}
		return false;
	}

	public static boolean perfectIsFree(double x, double y, BufferedImage sprite) {
		ArrayList<Ponto> pontos = getPoints(sprite);
		int x1=0;
		int y1=0;
		for (int i=0;i<pontos.size();i++) {
		x1=(int)(x+pontos.get(i).x);
		y1=(int)(y+pontos.get(i).y);
		x1=(int)(x1/Game.TILE_SIZE);
		y1=(int)(y1/Game.TILE_SIZE);
		if (x1<0||x1>World.WIDTH-1||y1<0||y1>World.HEIGHT-1) {
			return false;
		}
		if (World.tiles[x1+(y1*World.WIDTH)] instanceof Wall_Tile) {
			return false;
		}
		}
		return true;
	}
}
