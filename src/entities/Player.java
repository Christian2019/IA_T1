package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import AStarAlg.AStar;
import AStarAlg.Vector2i;
import main.Game;
import world.World;

public class Player extends Entity {


	
	public Player(double x, double y, int width, int height, BufferedImage sprite, double speed, int depth) {
		super(x, y, width, height, sprite, speed, depth);
		Vector2i start = new Vector2i((int) (x / Game.TILE_SIZE), (int) (y / Game.TILE_SIZE));
		double x_end = 0;
		double y_end = 0;
		for (int i=0;i<Game.entities.size();i++) {
			if (Game.entities.get(i) instanceof Saida) {
				x_end=Game.entities.get(i).x;
				y_end=Game.entities.get(i).y;
				break;
			}
		}
		
		Vector2i end = new Vector2i((int) (x_end / Game.TILE_SIZE), (int) (y_end / Game.TILE_SIZE));
		path = AStar.findPath(Game.world, start, end);
	
	}
	public void tick() {
		if (Game.ui.astar_ligado) {
			AStar.followPath(path, this);
			
		}

	}
	double frames=0;
	double max_frames=20;
	public void render(Graphics g) {
		if (Game.ui.astar_ligado) {
		frames++;
		
		if (frames==max_frames) {
			frames=0;
		}
		if (frames<(max_frames/2)&&right) {
			sprite=Entity.PLAYER[0];
			
			
		}else if (frames>max_frames/2&&right) {
			sprite=Entity.PLAYER[1];
		}else if (frames<max_frames/2&&left) {
			sprite=Entity.PLAYER[2];
		}else if (frames>max_frames/2&&left) {
			sprite=Entity.PLAYER[3];
		}
		}
		
		super.render(g);
	}

	

}
