package AStarAlg;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import entities.Entity;
import main.Game;
import world.Tile;
import world.Wall_Tile;
import world.World;

public class AStar {

	public static double lastTime = System.currentTimeMillis();
	public static Comparator<Node> nodeSorter = new Comparator<Node>() {

		@Override
		public int compare(Node n0, Node n1) {
			if (n1.fcost < n0.fcost) {
				return +1;
			}
			if (n1.fcost > n0.fcost) {
				return -1;
			}
			return 0;
		}

	};

	public static boolean clear() {
		if (System.currentTimeMillis() - lastTime >= 1000) {
			return true;
		}
		return false;
	}

	private static double getDistance(Vector2i tile, Vector2i goal) {
		double dx = tile.x - goal.x;
		double dy = tile.y - goal.y;
		return Math.sqrt(dx * dx + dy * dy);

	}

	private static boolean vecInList(List<Node> list, Vector2i vector) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).tile.equals(vector)) {
				return true;
			}
		}
		return false;
	}

	public static List<Node> findPath(World world, Vector2i start, Vector2i end) {
		lastTime = System.currentTimeMillis();
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();

		Node current = new Node(start, null, 0, getDistance(start, end));

		openList.add(current);

		while (openList.size() > 0) {

			Collections.sort(openList, nodeSorter);
			current = openList.get(0);
			if (current.tile.equals(end)) {
				// chegamos no ponto final
				// basta retornar o valor
				List<Node> path = new ArrayList<Node>();
				while (current.parent != null) {
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			for (int i = 0; i < 9; i++) {
				if (i == 4) {
					continue;
				}
				int x = current.tile.x;
				int y = current.tile.y;
				int xi = (i % 3) - 1;
				int yi = (i / 3) - 1;
				Tile tile = null;
				try {
					tile = World.tiles[x + xi + ((y + yi) * World.WIDTH)];
				} catch (ArrayIndexOutOfBoundsException e) {
					tile = null;
				}
				if (x >= World.WIDTH || x < 0) {
					continue;
				}
				if (tile == null)
					continue;
				if (tile instanceof Wall_Tile) {
					continue;
				}
				if (i == 0 || i == 2 || i == 6 || i == 8) {
					continue;
				}

				Vector2i a = new Vector2i(x + xi, y + yi);

				double gCost = current.gcost + getDistance(current.tile, a);
				double hCost = getDistance(a, end);
				Node node = new Node(a, current, gCost, hCost);

				if (vecInList(closedList, a) && gCost >= current.gcost)
					continue;

				if (!vecInList(openList, a)) {
					openList.add(node);
				} else if (gCost < current.gcost) {

					openList.remove(current);
					openList.add(node);
				}
			}
		}
		closedList.clear();
		return null;
	}

	public static void followPath(List<Node> path, Entity e) {

		if (path != null) {

			if (path.size() > 0) {
				Vector2i target = path.get(path.size() - 1).tile;

				if (e.x < target.x * Game.TILE_SIZE) {

					e.right = true;
					e.left = false;

					if (target.x * Game.TILE_SIZE > e.x + e.speed) {
						e.x += e.speed;
					} else {
						e.x = target.x * Game.TILE_SIZE;
					}

				} else if (e.x > target.x * Game.TILE_SIZE) {

					e.right = false;
					e.left = true;
					if (target.x * Game.TILE_SIZE < e.x - e.speed) {
						e.x -= e.speed;
					} else {
						e.x = target.x * Game.TILE_SIZE;
					}

				}
				if (e.y < target.y * Game.TILE_SIZE) {

					if (target.y * Game.TILE_SIZE > e.y + e.speed) {
						e.y += e.speed;
					} else {
						e.y = target.y * Game.TILE_SIZE;
					}

				} else if (e.y > target.y * Game.TILE_SIZE) {

					if (target.y * Game.TILE_SIZE < e.y - e.speed) {
						e.y -= e.speed;
					} else {
						e.y = target.y * Game.TILE_SIZE;
					}

				}

				if (e.x == target.x * Game.TILE_SIZE && e.y == target.y * Game.TILE_SIZE) {
					path.remove(path.size() - 1);
				}

			}
		}
	}
}
