package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.Game;

public class Tile {
	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0 * Game.TILE_SIZE, 1*Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE);
	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(0 * Game.TILE_SIZE, 3*Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE);
	public static BufferedImage TILE_HOLE = Game.spritesheet.getSprite(0 * Game.TILE_SIZE, 2*Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE);
	
	protected BufferedImage sprite;
	protected int x, y;

	public Tile(int x, int y, BufferedImage sprite) {

		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}

	public static void ChangeTile(int Nivel) {
		/*
		 * if (Nivel % 7 == 0) { // Grama/Pedra TILE_FLOOR =
		 * Game.spritesheet.getSprite(0, 0, 16, 16); TILE_WALL =
		 * Game.spritesheet.getSprite(16 * 2, 0, 16, 16);
		 * 
		 * }
		 */
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void render(Graphics g) {
		
		g.drawImage(sprite, (int) (x), (int) (y), null);
		
	}

}
