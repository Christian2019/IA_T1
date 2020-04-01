package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import AStarAlg.Node;
import main.Game;
import world.Camera;

public class Entity {
	public static BufferedImage[] PORTAL_BLACK = {
			Game.spritesheet.getSprite(0 * Game.TILE_SIZE, 4 * Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE),
			Game.spritesheet.getSprite(5 * Game.TILE_SIZE, 4 * Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE) };
	
	public boolean left,right,up,down;
	public double x;
	public double y;
	protected int width, height;
	public double speed;
	protected BufferedImage sprite;
	public int depth;
	protected List<Node> path;

	public Entity(double x, double y, int width, int height, BufferedImage sprite, double speed, int depth) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		this.speed = speed;
		this.depth = depth;
	}

	public double getX() {
		return x;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public double getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(double novoX) {
		this.x = novoX;
	}

	public void setY(double novoY) {
		this.y = novoY;
	}

	public void setSprite(BufferedImage novoSprite) {
		this.sprite = novoSprite;
	}

	public void render(Graphics g) {
		if (Game.z == 1) {
			// Otimizando com a camera
			double xstart = Camera.x - Game.TILE_SIZE;
			double ystart = Camera.y - Game.TILE_SIZE;

			double xfinal = xstart + (Game.WIDTH * Game.SCALE);
			double yfinal = ystart + (Game.HEIGHT * Game.SCALE);

			boolean ver = false;
			if (x >= xstart && x <= xfinal && y >= ystart && y <= yfinal) {
				ver = true;
			}

			if (ver) {
				g.drawImage(sprite, (int) (x - Camera.x), (int) (y - Camera.y),width,height, null);
			}
		} else {
			g.drawImage(sprite, (int) x, (int) y, width, height, null);
		}
	}

	public void tick() {

	}

	public static boolean isColliding(Entity e1, Entity e2) {
		Rectangle r1 = new Rectangle((int) e1.getX(), (int) e1.getY(), e1.getWidth(), e1.getHeight());
		Rectangle r2 = new Rectangle((int) e2.getX(), (int) e2.getY(), e2.getWidth(), e2.getHeight());

		if (r1.intersects(r2)) {
			return true;
		}
		return false;
	}

	public static double distancia(double x, double y, double x2, double y2) {

		return Math.pow((Math.pow(x - x2, 2) + Math.pow(y - y2, 2)), 0.5);

	}
}
