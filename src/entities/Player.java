package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import world.World;

public class Player extends Entity {
public boolean right,left;
	
	public Player(double x, double y, int width, int height, BufferedImage sprite, double speed, int depth) {
		super(x, y, width, height, sprite, speed, depth);
	
	}
	public void tick() {

	
	}

	public void render(Graphics g) {
		g.setColor(new Color(216, 132, 70));
		g.fillRect((int)x, (int)y, width, height);
	}

}
