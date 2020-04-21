package entities;

import java.awt.image.BufferedImage;

import main.AlgGen;
import main.Game;

public class Spawnner_Player {
	public static void limparPlayer() {
		for (int i = 0; i < Game.entities.size(); i++) {
			if (Game.entities.get(i) instanceof Player) {
				Game.entities.remove(i);
				i--;
			}
		}
	}

	public static void populaGeracao(AlgGen ag, int geracao_atual, double speed) {
		double entrance_x = 0;
		double entrance_y = 0;
		for (int i = 0; i < Game.entities.size(); i++) {
			if (Game.entities.get(i) instanceof Entrada) {
				entrance_x = Game.entities.get(i).x;
				entrance_y = Game.entities.get(i).y;
			}
		}
		for (int i = 0; i < ag.Geracoes.get(geracao_atual).populacao.size(); i++) {
			Player player;
			if (ag.Geracoes.get(geracao_atual).populacao.get(i).equals(ag.Geracoes.get(geracao_atual).elite)) {
				player = new Player(entrance_x, entrance_y, Game.TILE_SIZE, Game.TILE_SIZE, Entity.PLAYER[0], speed, 1,
						ag.Geracoes.get(geracao_atual).populacao.get(i), true);
			}
			else {
			player = new Player(entrance_x, entrance_y, Game.TILE_SIZE, Game.TILE_SIZE, Entity.PLAYER[0], speed, 2,
					ag.Geracoes.get(geracao_atual).populacao.get(i), false);
			}
			Game.entities.add(player);

		}
		

	}
}
