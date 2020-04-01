package world;

import main.Game;

public class Camera {
public static double x;
public static double y;

public static double clamp(double Atual, double min, double max) {
	if (Atual< min) {
		Atual = min;
	}
	if (Atual>max) {
		Atual = max;
	}
	
	return Atual;
}

public static double max_Camera_x() {
	double xm=World.WIDTH*Game.TILE_SIZE;
	return clamp(xm - Game.image.getWidth() / 2, 0, World.WIDTH * Game.TILE_SIZE - Game.image.getWidth());
}
public static double max_Camera_y() {
	double ym=World.HEIGHT*Game.TILE_SIZE;
	return clamp(ym - Game.image.getHeight() / 2, 0,
			World.HEIGHT * Game.TILE_SIZE - Game.image.getHeight());
}
}
