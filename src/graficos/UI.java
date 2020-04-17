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
	public Font font2 = new Font("Arial", Font.BOLD, Proporcoes.porcentagem(Proporcoes.X_Total, 2));
	// Menu principal
	int menuprincipal_botao_editarmapa_x = Proporcoes.porcentagem(Proporcoes.X_Total, 71);
	int menuprincipal_botao_editarmapa_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 17.5);
	int menuprincipal_botao_editarmapa_width = Proporcoes.porcentagem(Proporcoes.X_Total, 18);
	int menuprincipal_botao_editarmapa_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
	String menuprincipal_string_editarmapa_str = "Editar Mapa";
	int menuprincipal_string_editarmapa_x = Proporcoes.porcentagem(Proporcoes.X_Total, 71.5);
	int menuprincipal_string_editarmapa_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 24);
	// Botao A*
	int menuprincipal_botao_astar_x = Proporcoes.porcentagem(Proporcoes.X_Total, 71);
	int menuprincipal_botao_astar_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 45);
	int menuprincipal_botao_astar_width = Proporcoes.porcentagem(Proporcoes.X_Total, 18);
	int menuprincipal_botao_astar_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
	String menuprincipal_string_astar_str = "A*";
	int menuprincipal_string_astar_x = Proporcoes.porcentagem(Proporcoes.X_Total, 78);
	int menuprincipal_string_astar_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 51.5);
	// Botao AG
	int menuprincipal_botao_ag_x = Proporcoes.porcentagem(Proporcoes.X_Total, 66);
	int menuprincipal_botao_ag_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 72.5);
	int menuprincipal_botao_ag_width = Proporcoes.porcentagem(Proporcoes.X_Total, 28);
	int menuprincipal_botao_ag_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
	String menuprincipal_string_ag_str = "Algoritmo Genético";
	int menuprincipal_string_ag_x = Proporcoes.porcentagem(Proporcoes.X_Total, 66.5);
	int menuprincipal_string_ag_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 79);
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
	// Algoritmo Genetico ag1
	// painel
	int ag1_painel_x = Proporcoes.porcentagem(Proporcoes.X_Total, 68);
	int ag1_painel_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 0);
	int ag1_painel_width = Proporcoes.porcentagem(Proporcoes.X_Total, 32);
	int ag1_painel_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 100);
	// painel2
	int ag1_painel2_x = Proporcoes.porcentagem(Proporcoes.X_Total, 0);
	int ag1_painel2_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 0);
	int ag1_painel2_width = Proporcoes.porcentagem(Proporcoes.X_Total, 25);
	int ag1_painel2_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 100);
	// texto feedback
	String ag1_fb_str = "texto de feedback!";
	int ag1_fb_x = Proporcoes.porcentagem(Proporcoes.X_Total, 4);
	int ag1_fb_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 50);
	// botao populacao
	int ag1_botao_populacao_x = Proporcoes.porcentagem(Proporcoes.X_Total, 85);
	int ag1_botao_populacao_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 3.75);
	int ag1_botao_populacao_width = Proporcoes.porcentagem(Proporcoes.X_Total, 10);
	int ag1_botao_populacao_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
	// botao tamanho_cromossomo
	int ag1_botao_tamanho_cromossomo_x = Proporcoes.porcentagem(Proporcoes.X_Total, 85);
	int ag1_botao_tamanho_cromossomo_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 17.5);
	int ag1_botao_tamanho_cromossomo_width = Proporcoes.porcentagem(Proporcoes.X_Total, 10);
	int ag1_botao_tamanho_cromossomo_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
	// botao limite_populacao
	int ag1_botao_limite_populacao_x = Proporcoes.porcentagem(Proporcoes.X_Total, 85);
	int ag1_botao_limite_populacao_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 31.25);
	int ag1_botao_limite_populacao_width = Proporcoes.porcentagem(Proporcoes.X_Total, 10);
	int ag1_botao_limite_populacao_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
	// botao taxa_mutacao
	int ag1_botao_taxa_mutacao_x = Proporcoes.porcentagem(Proporcoes.X_Total, 85);
	int ag1_botao_taxa_mutacao_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 45);
	int ag1_botao_taxa_mutacao_width = Proporcoes.porcentagem(Proporcoes.X_Total, 10);
	int ag1_botao_taxa_mutacao_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
	// botao gerar
	int ag1_botao_gerar_x = Proporcoes.porcentagem(Proporcoes.X_Total, 85);
	int ag1_botao_gerar_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 58.75);
	int ag1_botao_gerar_width = Proporcoes.porcentagem(Proporcoes.X_Total, 10);
	int ag1_botao_gerar_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
	// botao voltar
	int ag1_botao_voltar_x = Proporcoes.porcentagem(Proporcoes.X_Total, 85);
	int ag1_botao_voltar_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 72.5);
	int ag1_botao_voltar_width = Proporcoes.porcentagem(Proporcoes.X_Total, 10);
	int ag1_botao_voltar_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
	// botao comecar
	int ag1_botao_comecar_x = Proporcoes.porcentagem(Proporcoes.X_Total, 85);
	int ag1_botao_comecar_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 86.25);
	int ag1_botao_comecar_width = Proporcoes.porcentagem(Proporcoes.X_Total, 10);
	int ag1_botao_comecar_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
	// string populacao
	String ag1_string_populacao_str = "População:";
	int ag1_string_populacao_x = Proporcoes.porcentagem(Proporcoes.X_Total, 70);
	int ag1_string_populacao_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 9.75);
	// string populacao_valor
	String ag1_string_populacao_valor_str = "1001";
	int ag1_string_populacao_valor_x = Proporcoes.porcentagem(Proporcoes.X_Total, 85.5);
	int ag1_string_populacao_valor_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 9.75);
	// string tamanho_cromossomo1
	String ag1_string_tamanho_cromossomo_str = "Tamanho do";
	int ag1_string_tamanho_cromossomo_x = Proporcoes.porcentagem(Proporcoes.X_Total, 70);
	int ag1_string_tamanho_cromossomo_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 21.5);
	// string tamanho_cromossomo2
	String ag1_string_tamanho_cromossomo2_str = "cromossomo:";
	int ag1_string_tamanho_cromossomo2_x = Proporcoes.porcentagem(Proporcoes.X_Total, 70);
	int ag1_string_tamanho_cromossomo2_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 26.5);
	// string tamanho_cromossomo_valor
	String ag1_string_tamanho_cromossomo_valor_str = "50";
	int ag1_string_tamanho_cromossomo_valor_x = Proporcoes.porcentagem(Proporcoes.X_Total, 85.5);
	int ag1_string_tamanho_cromossomo_valor_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 23.5);
	// string limite_populacao
	String ag1_string_limite_populacao_str = "Limite de";
	int ag1_string_limite_populacao_x = Proporcoes.porcentagem(Proporcoes.X_Total, 70);
	int ag1_string_limite_populacao_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 35.25);
	// string limite_populacao2
	String ag1_string_limite_populacao2_str = "população:";
	int ag1_string_limite_populacao2_x = Proporcoes.porcentagem(Proporcoes.X_Total, 70);
	int ag1_string_limite_populacao2_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 40.25);
	// string limite_populacao_valor
	String ag1_string_limite_populacao_valor_str = "1000";
	int ag1_string_limite_populacao_valor_x = Proporcoes.porcentagem(Proporcoes.X_Total, 85.5);
	int ag1_string_limite_populacao_valor_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 37.25);
	// string taxa_mutacao
	String ag1_string_taxa_mutacao_str = "Taxa de";
	int ag1_string_taxa_mutacao_x = Proporcoes.porcentagem(Proporcoes.X_Total, 70);
	int ag1_string_taxa_mutacao_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 49);
	// string taxa_mutacao2
	String ag1_string_taxa_mutacao2_str = "mutação:";
	int ag1_string_taxa_mutacao2_x = Proporcoes.porcentagem(Proporcoes.X_Total, 70);
	int ag1_string_taxa_mutacao2_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 54);
	// string taxa_mutacao_valor
	String ag1_string_taxa_mutacao_valor_str = "2";
	int ag1_string_taxa_mutacao_valor_x = Proporcoes.porcentagem(Proporcoes.X_Total, 85.5);
	int ag1_string_taxa_mutacao_valor_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 51);
	// string gerar
	String ag1_string_gerar_str = "Gerar";
	int ag1_string_gerar_x = Proporcoes.porcentagem(Proporcoes.X_Total, 87);
	int ag1_string_gerar_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 64.75);
	// string voltar
	String ag1_string_voltar_str = "Voltar";
	int ag1_string_voltar_x = Proporcoes.porcentagem(Proporcoes.X_Total, 87);
	int ag1_string_voltar_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 78.5);
	// string comecar
	String ag1_string_comecar_str = "Começar";
	int ag1_string_comecar_x = Proporcoes.porcentagem(Proporcoes.X_Total, 85.5);
	int ag1_string_comecar_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 92.25);

	public static String state = "Menu principal";
	boolean background = false;
	public boolean astar_ligado = false;

	public void tick() {
		if (state.equals("Menu principal")) {
			if (Game.mouseClicked) {
				Game.mouseClicked = false;
				// Botao ag
				if ((Game.MX > menuprincipal_botao_ag_x
						&& Game.MX < this.menuprincipal_botao_ag_width + menuprincipal_botao_ag_x)
						&& (Game.MY > menuprincipal_botao_ag_y
								&& Game.MY < this.menuprincipal_botao_ag_height + menuprincipal_botao_ag_y)) {
					System.out.println("Botao AG");
					state = "ag1";
					background = false;
				}
				// Botao editar mapa
				else if ((Game.MX > menuprincipal_botao_editarmapa_x
						&& Game.MX < this.menuprincipal_botao_editarmapa_width + menuprincipal_botao_editarmapa_x)
						&& (Game.MY > menuprincipal_botao_editarmapa_y
								&& Game.MY < this.menuprincipal_botao_editarmapa_height
										+ menuprincipal_botao_editarmapa_y)) {
					System.out.println("Editar mapa");
				}
				// Bota a*
				else if ((Game.MX > menuprincipal_botao_astar_x
						&& Game.MX < this.menuprincipal_botao_astar_width + menuprincipal_botao_astar_x)
						&& (Game.MY > menuprincipal_botao_astar_y
								&& Game.MY < this.menuprincipal_botao_astar_height + menuprincipal_botao_astar_y)) {
					System.out.println("A*");
					state = "astar";
					background = false;
				}
			}
		} else if (state.equals("astar")) {
			if (Game.mouseClicked) {
				Game.mouseClicked = false;
				// botao play
				if ((Game.MX > this.astar_botao_play_x
						&& Game.MX < this.astar_botao_play_width + this.astar_botao_play_x)
						&& (Game.MY > this.astar_botao_play_y
								&& Game.MY < this.astar_botao_play_height + this.astar_botao_play_y)) {
					System.out.println("Botao Play");
					if (this.astar_ligado == false) {
						this.astar_ligado = true;
						World.clearPlayers();
						for (int i = 0; i < Game.entities.size(); i++) {
							if (Game.entities.get(i) instanceof Entrada) {
								Player player = new Player(Game.entities.get(i).x, Game.entities.get(i).y,
										Game.TILE_SIZE, Game.TILE_SIZE, Entity.PLAYER[0], 1, 1);
								Game.entities.add(player);
								break;

							}
						}
					} else {
						this.astar_ligado = false;
					}
				}
				// botao voltar
				else if ((Game.MX > this.astar_botao_voltar_x
						&& Game.MX < this.astar_botao_voltar_width + this.astar_botao_voltar_x)
						&& (Game.MY > this.astar_botao_voltar_y
								&& Game.MY < this.astar_botao_voltar_height + this.astar_botao_voltar_y)) {
					System.out.println("Botao Voltar");
					this.astar_ligado = false;
					state = "Menu principal";
					background = false;
				}
			}
		} else if (state.equals("ag1")) {
			if (Game.mouseClicked) {
				Game.mouseClicked = false;
				// Botao voltar
				if ((Game.MX > ag1_botao_voltar_x
						&& Game.MX < this.ag1_botao_voltar_width + ag1_botao_voltar_x)
						&& (Game.MY > ag1_botao_voltar_y
								&& Game.MY < this.ag1_botao_voltar_height + ag1_botao_voltar_y)) {
					System.out.println("Botao voltar");
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
		} else if (state.equals("ag1")) {
			ag1(g);
		}

	}

	private void ag1(Graphics g) {
		if (!background) {
			background = true;
			try {
				Game.background = ImageIO.read(getClass().getResource("/background2.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		g.setColor(Color.black);
		g.setFont(font2);
		// painel1
		g.fillRect(ag1_painel_x, ag1_painel_y, ag1_painel_width, ag1_painel_height);
		// painel2
		g.fillRect(ag1_painel2_x, ag1_painel2_y, ag1_painel2_width, ag1_painel2_height);
		g.setColor(Color.white);
		// texto feedback
		g.drawString(ag1_fb_str, ag1_fb_x, ag1_fb_y);
		// botao populacao
		g.setColor(Color.white);
		g.fillRect(ag1_botao_populacao_x, ag1_botao_populacao_y, ag1_botao_populacao_width, ag1_botao_populacao_height);
		g.setColor(Color.BLUE);
		g.drawRect(ag1_botao_populacao_x, ag1_botao_populacao_y, ag1_botao_populacao_width, ag1_botao_populacao_height);
		g.setColor(Color.BLUE);
		g.drawString(ag1_string_populacao_str, ag1_string_populacao_x, ag1_string_populacao_y);
		g.setColor(Color.RED);
		g.drawString(ag1_string_populacao_valor_str, ag1_string_populacao_valor_x, ag1_string_populacao_valor_y);
		// botao tamanho_cromossomo
		g.setColor(Color.white);
		g.fillRect(ag1_botao_tamanho_cromossomo_x, ag1_botao_tamanho_cromossomo_y, ag1_botao_tamanho_cromossomo_width,
				ag1_botao_tamanho_cromossomo_height);
		g.setColor(Color.BLUE);
		g.drawRect(ag1_botao_tamanho_cromossomo_x, ag1_botao_tamanho_cromossomo_y, ag1_botao_tamanho_cromossomo_width,
				ag1_botao_tamanho_cromossomo_height);
		g.setColor(Color.BLUE);
		g.drawString(ag1_string_tamanho_cromossomo_str, ag1_string_tamanho_cromossomo_x,
				ag1_string_tamanho_cromossomo_y);
		g.drawString(ag1_string_tamanho_cromossomo2_str, ag1_string_tamanho_cromossomo2_x,
				ag1_string_tamanho_cromossomo2_y);
		g.setColor(Color.RED);
		g.drawString(ag1_string_tamanho_cromossomo_valor_str, ag1_string_tamanho_cromossomo_valor_x,
				ag1_string_tamanho_cromossomo_valor_y);
		// botao limite_populacao
		g.setColor(Color.white);
		g.fillRect(ag1_botao_limite_populacao_x, ag1_botao_limite_populacao_y, ag1_botao_limite_populacao_width,
				ag1_botao_limite_populacao_height);
		g.setColor(Color.BLUE);
		g.drawRect(ag1_botao_limite_populacao_x, ag1_botao_limite_populacao_y, ag1_botao_limite_populacao_width,
				ag1_botao_limite_populacao_height);
		g.setColor(Color.BLUE);
		g.drawString(ag1_string_limite_populacao_str, ag1_string_limite_populacao_x, ag1_string_limite_populacao_y);
		g.drawString(ag1_string_limite_populacao2_str, ag1_string_limite_populacao2_x, ag1_string_limite_populacao2_y);
		g.setColor(Color.RED);
		g.drawString(ag1_string_limite_populacao_valor_str, ag1_string_limite_populacao_valor_x,
				ag1_string_limite_populacao_valor_y);
		// botao taxa_mutacao
		g.setColor(Color.white);
		g.fillRect(ag1_botao_taxa_mutacao_x, ag1_botao_taxa_mutacao_y, ag1_botao_taxa_mutacao_width,
				ag1_botao_taxa_mutacao_height);
		g.setColor(Color.BLUE);
		g.drawRect(ag1_botao_taxa_mutacao_x, ag1_botao_taxa_mutacao_y, ag1_botao_taxa_mutacao_width,
				ag1_botao_taxa_mutacao_height);
		g.setColor(Color.BLUE);
		g.drawString(ag1_string_taxa_mutacao_str, ag1_string_taxa_mutacao_x, ag1_string_taxa_mutacao_y);
		g.drawString(ag1_string_taxa_mutacao2_str, ag1_string_taxa_mutacao2_x, ag1_string_taxa_mutacao2_y);
		g.setColor(Color.RED);
		g.drawString(ag1_string_taxa_mutacao_valor_str, ag1_string_taxa_mutacao_valor_x,
				ag1_string_taxa_mutacao_valor_y);
		// botao gerar
		g.setColor(Color.BLUE);
		g.fillRect(ag1_botao_gerar_x, ag1_botao_gerar_y, ag1_botao_gerar_width, ag1_botao_gerar_height);
		g.setColor(Color.white);
		g.drawString(ag1_string_gerar_str, ag1_string_gerar_x, ag1_string_gerar_y);
		// botao voltar
		g.setColor(Color.BLUE);
		g.fillRect(ag1_botao_voltar_x, ag1_botao_voltar_y, ag1_botao_voltar_width, ag1_botao_voltar_height);
		g.setColor(Color.white);
		g.drawString(ag1_string_voltar_str, ag1_string_voltar_x, ag1_string_voltar_y);
		// botao comecar
		g.setColor(Color.BLUE);
		g.fillRect(ag1_botao_comecar_x, ag1_botao_comecar_y, ag1_botao_comecar_width, ag1_botao_comecar_height);
		g.setColor(Color.white);
		g.drawString(ag1_string_comecar_str, ag1_string_comecar_x, ag1_string_comecar_y);

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
		g.fillRect(menuprincipal_botao_editarmapa_x, menuprincipal_botao_editarmapa_y,
				menuprincipal_botao_editarmapa_width, menuprincipal_botao_editarmapa_height);
		g.setColor(Color.white);
		g.setFont(font1);
		g.drawString(menuprincipal_string_editarmapa_str, menuprincipal_string_editarmapa_x,
				menuprincipal_string_editarmapa_y);

		// Botao A*
		g.setColor(Color.red);
		g.fillRect(menuprincipal_botao_astar_x, menuprincipal_botao_astar_y, menuprincipal_botao_astar_width,
				menuprincipal_botao_astar_height);
		g.setColor(Color.white);
		g.setFont(font1);
		g.drawString(menuprincipal_string_astar_str, menuprincipal_string_astar_x, menuprincipal_string_astar_y);

		// Botao AG
		g.setColor(Color.GREEN);
		g.fillRect(menuprincipal_botao_ag_x, menuprincipal_botao_ag_y, menuprincipal_botao_ag_width,
				menuprincipal_botao_ag_height);
		g.setColor(Color.white);
		g.setFont(font1);
		g.drawString(menuprincipal_string_ag_str, menuprincipal_string_ag_x, menuprincipal_string_ag_y);

	}

}
