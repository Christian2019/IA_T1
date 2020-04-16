package graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Entity;
import entities.Entrada;
import entities.Player;
import main.Game;
import main.Proporcoes;

import world.Camera;
import world.World;

public class UI {

	public Font font1 = new Font("Arial", Font.BOLD, Proporcoes.porcentagem(Proporcoes.X_Total, 3));
	// Menu principal
	int menuprincipal_botao_editarmapa_x = Proporcoes.porcentagem(Proporcoes.X_Total, 71);
	int menuprincipal_botao_editarmapa_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 2);
	int menuprincipal_botao_editarmapa_width = Proporcoes.porcentagem(Proporcoes.X_Total, 18);
	int menuprincipal_botao_editarmapa_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
	String menuprincipal_string_editarmapa_str = "Editar Mapa";
	int menuprincipal_string_editarmapa_x = Proporcoes.porcentagem(Proporcoes.X_Total, 71.5);
	int menuprincipal_string_editarmapa_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 8.5);
	// Botao A*
	int menuprincipal_botao_astar_x = Proporcoes.porcentagem(Proporcoes.X_Total, 71);
	int menuprincipal_botao_astar_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 30);
	int menuprincipal_botao_astar_width = Proporcoes.porcentagem(Proporcoes.X_Total, 18);
	int menuprincipal_botao_astar_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
	String menuprincipal_string_astar_str = "A*";
	int menuprincipal_string_astar_x = Proporcoes.porcentagem(Proporcoes.X_Total, 78);
	int menuprincipal_string_astar_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 37);
	// Botao AG
	int menuprincipal_botao_ag_x = Proporcoes.porcentagem(Proporcoes.X_Total, 66);
	int menuprincipal_botao_ag_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 58);
	int menuprincipal_botao_ag_width = Proporcoes.porcentagem(Proporcoes.X_Total, 28);
	int menuprincipal_botao_ag_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
	String menuprincipal_string_ag_str = "Algoritmo Genético";
	int menuprincipal_string_ag_x = Proporcoes.porcentagem(Proporcoes.X_Total, 66.5);
	int menuprincipal_string_ag_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 64.5);
	// A*
	// painel
	int astar_painel_x = Proporcoes.porcentagem(Proporcoes.X_Total, 80);
	int astar_painel_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 0);
	public int astar_painel_width = Proporcoes.porcentagem(Proporcoes.X_Total, 20);
	int astar_painel_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 100);
	// botao play
	int astar_botao_play_x = Proporcoes.porcentagem(Proporcoes.X_Total, 85);
	int astar_botao_play_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 35);
	int astar_botao_play_width = Proporcoes.porcentagem(Proporcoes.X_Total, 10);
	int astar_botao_play_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
	// botao voltar
	int astar_botao_voltar_x = Proporcoes.porcentagem(Proporcoes.X_Total, 85);
	int astar_botao_voltar_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 55);
	int astar_botao_voltar_width = Proporcoes.porcentagem(Proporcoes.X_Total, 10);
	int astar_botao_voltar_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
	// string play
	String astar_string_play = "Play";
	int astar_string_play_x = Proporcoes.porcentagem(Proporcoes.X_Total, 87);
	int astar_string_play_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 41.5);
	// string voltar
	String astar_string_voltar = "Voltar";
	int astar_string_voltar_x = Proporcoes.porcentagem(Proporcoes.X_Total, 86);
	int astar_string_voltar_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 62);

	public static String state = "Menu principal";
	boolean background = false;
	public boolean astar_ligado=false;

	public void tick() {
		if (state.equals("Menu principal")) {
			if (Game.mouseClicked) {
				Game.mouseClicked = false;
				// Botao ag
				if ((Game.MX > menuprincipal_botao_ag_x && Game.MX < this.menuprincipal_botao_ag_width + menuprincipal_botao_ag_x)
						&& (Game.MY > menuprincipal_botao_ag_y && Game.MY < this.menuprincipal_botao_ag_height + menuprincipal_botao_ag_y)) {
					System.out.println("Botao AG");
				}
				// Botao editar mapa
				else if ((Game.MX > menuprincipal_botao_editarmapa_x && Game.MX < this.menuprincipal_botao_editarmapa_width + menuprincipal_botao_editarmapa_x)
						&& (Game.MY > menuprincipal_botao_editarmapa_y
								&& Game.MY < this.menuprincipal_botao_editarmapa_height + menuprincipal_botao_editarmapa_y)) {
					System.out.println("Editar mapa");
				}
				// Bota a*
				else if ((Game.MX > menuprincipal_botao_astar_x && Game.MX < this.menuprincipal_botao_astar_width + menuprincipal_botao_astar_x)
						&& (Game.MY > menuprincipal_botao_astar_y && Game.MY < this.menuprincipal_botao_astar_height + menuprincipal_botao_astar_y)) {
					System.out.println("A*");
					state = "astar";
					background = false;
				}
			}
		}else if (state.equals("astar")) {
			if (Game.mouseClicked) {
				Game.mouseClicked = false;
				//botao play
				if ((Game.MX > this.astar_botao_play_x && Game.MX < this.astar_botao_play_width + this.astar_botao_play_x)
						&& (Game.MY > this.astar_botao_play_y && Game.MY < this.astar_botao_play_height + this.astar_botao_play_y)) {
					System.out.println("Botao Play");
					if (this.astar_ligado==false) {
					this.astar_ligado=true;
					World.clearPlayers();
					for (int i=0;i<Game.entities.size();i++) {
						if (Game.entities.get(i) instanceof Entrada) {
							Player player = new Player(Game.entities.get(i).x,Game.entities.get(i).y, Game.TILE_SIZE, Game.TILE_SIZE, Entity.PLAYER[0], 1, 1);	
							Game.entities.add(player);
							break;
							
						}
					}
					}else {
						this.astar_ligado=false;
					}
				}
				//botao voltar
				else if ((Game.MX > this.astar_botao_voltar_x && Game.MX < this.astar_botao_voltar_width + this.astar_botao_voltar_x)
						&& (Game.MY > this.astar_botao_voltar_y && Game.MY < this.astar_botao_voltar_height + this.astar_botao_voltar_y)) {
					System.out.println("Botao Voltar");
					this.astar_ligado=false;
					state = "Menu principal";
					background = false;
				}
			}
		}
	}

	public void render(Graphics g) {
		if (state.equals("Menu principal")) {
			menu_principal(g);
		} else if (state.equals("astar")) {
			astar(g);
		}

	}

	private void astar(Graphics g) {
		if (!background) {
			background = true;
			try {
				Game.background = ImageIO.read(getClass().getResource("/background.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// painel
		g.setColor(Color.BLACK);
		g.fillRect(astar_painel_x, astar_painel_y, astar_painel_width, astar_painel_height);
		// botao play
		g.setColor(Color.BLUE);
		g.fillRect(astar_botao_play_x, astar_botao_play_y, astar_botao_play_width, astar_botao_play_height);
		// botao voltar
		g.setColor(Color.BLUE);
		g.fillRect(astar_botao_voltar_x, astar_botao_voltar_y, astar_botao_voltar_width, astar_botao_voltar_height);
		g.setFont(font1);
		// string play
		g.setColor(Color.WHITE);
		g.drawString(astar_string_play, astar_string_play_x, astar_string_play_y);
		// string voltar
		g.setColor(Color.WHITE);
		g.drawString(astar_string_voltar, astar_string_voltar_x, astar_string_voltar_y);

	}

	private void menu_principal(Graphics g) {
				
		if (!background) {
			background = true;
			try {
				Game.background = ImageIO.read(getClass().getResource("/background3.jpeg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Botao editar mapa
		g.setColor(Color.BLUE);
		g.fillRect(menuprincipal_botao_editarmapa_x, menuprincipal_botao_editarmapa_y, menuprincipal_botao_editarmapa_width, menuprincipal_botao_editarmapa_height);
		g.setColor(Color.white);
		g.setFont(font1);
		g.drawString(menuprincipal_string_editarmapa_str, menuprincipal_string_editarmapa_x, menuprincipal_string_editarmapa_y);

		// Botao A*
		g.setColor(Color.red);
		g.fillRect(menuprincipal_botao_astar_x, menuprincipal_botao_astar_y, menuprincipal_botao_astar_width, menuprincipal_botao_astar_height);
		g.setColor(Color.white);
		g.setFont(font1);
		g.drawString(menuprincipal_string_astar_str, menuprincipal_string_astar_x, menuprincipal_string_astar_y);

		// Botao AG
		g.setColor(Color.GREEN);
		g.fillRect(menuprincipal_botao_ag_x, menuprincipal_botao_ag_y, menuprincipal_botao_ag_width, menuprincipal_botao_ag_height);
		g.setColor(Color.white);
		g.setFont(font1);
		g.drawString(menuprincipal_string_ag_str, menuprincipal_string_ag_x, menuprincipal_string_ag_y);

	}

}
