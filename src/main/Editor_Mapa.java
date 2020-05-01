package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import AStarAlg.AStar;
import AStarAlg.Node;
import AStarAlg.Vector2i;
import entities.Entity;
import entities.Entrada;
import entities.Saida;
import world.Tile;
import world.World;

public class Editor_Mapa {
	public Font font2 = new Font("Arial", Font.BOLD, Proporcoes.porcentagem(Proporcoes.X_Total, 2));
	int em_size_image = Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
	// image chao
	BufferedImage em_chao_image = Tile.TILE_FLOOR;
	int em_chao_x = Proporcoes.porcentagem(Proporcoes.X_Total, 73);
	int em_chao_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
	// image buraco
	BufferedImage em_buraco_image = Tile.TILE_HOLE;
	int em_buraco_x = Proporcoes.porcentagem(Proporcoes.X_Total, 73);
	int em_buraco_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 22);
	// image parede
	BufferedImage em_parede_image = Tile.TILE_WALL;
	int em_parede_x = Proporcoes.porcentagem(Proporcoes.X_Total, 73);
	int em_parede_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 34);
	// image entrada
	BufferedImage em_entrada_image = Entity.ENTRADA;
	int em_entrada_x = Proporcoes.porcentagem(Proporcoes.X_Total, 73);
	int em_entrada_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 46);
	// image saida
	BufferedImage em_saida_image = Entity.SAIDA;
	int em_saida_x = Proporcoes.porcentagem(Proporcoes.X_Total, 73);
	int em_saida_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 58);
	// botao salvar
	int em_botao_salvar_x = Proporcoes.porcentagem(Proporcoes.X_Total, 62);
	int em_botao_salvar_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 83);
	int em_botao_salvar_width = Proporcoes.porcentagem(Proporcoes.X_Total, 10);
	int em_botao_salvar_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 7);
	String em_botao_salvar_string_str = "Salvar";
	int em_botao_salvar_string_x = Proporcoes.porcentagem(Proporcoes.X_Total, 63.5);
	int em_botao_salvar_string_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 87.5);
	// botao voltar
	int em_botao_voltar_x = Proporcoes.porcentagem(Proporcoes.X_Total, 80);
	int em_botao_voltar_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 83);
	int em_botao_voltar_width = Proporcoes.porcentagem(Proporcoes.X_Total, 10);
	int em_botao_voltar_height = Proporcoes.porcentagem(Proporcoes.Y_Total, 7);
	String em_botao_voltar_string_str = "Voltar";
	int em_botao_voltar_string_x = Proporcoes.porcentagem(Proporcoes.X_Total, 82);
	int em_botao_voltar_string_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 87.5);
	// feedback
	String em_feedback_srt = "";
	int em_feedback_x = Proporcoes.porcentagem(Proporcoes.X_Total, 58);
	int em_feedback_y = Proporcoes.porcentagem(Proporcoes.Y_Total, 77.5);

	int selecionado = 0;

	public void tick() {
		if (Game.mouseClicked) {
			Game.mouseClicked = false;
			// Botao Voltar
			if ((Game.MX > em_botao_voltar_x && Game.MX < this.em_botao_voltar_width + em_botao_voltar_x)
					&& (Game.MY > em_botao_voltar_y && Game.MY < this.em_botao_voltar_height + em_botao_voltar_y)) {
				System.out.println("Botao voltar");
				Game.world = new World(Game.path);
				selecionado=0;
				Game.ui.state = "Menu principal";
			}
			// Botao Salvar
			else if ((Game.MX > em_botao_salvar_x && Game.MX < this.em_botao_salvar_width + em_botao_salvar_x)
					&& (Game.MY > em_botao_salvar_y && Game.MY < this.em_botao_salvar_height + em_botao_salvar_y)) {
				System.out.println("Botao salvar");
				selecionado=0;
				if (validado()) {
					Save_Game.save_mapa();
					em_feedback_srt= "Mapa salvo!";
				}
			}
			// Image Chao
			else if ((Game.MX > em_chao_x && Game.MX < this.em_size_image + em_chao_x)
					&& (Game.MY > em_chao_y && Game.MY < this.em_size_image + em_chao_y)) {
				System.out.println("Image Chao");
				selecionado = 1;
			}
			// Image Buraco
			else if ((Game.MX > em_buraco_x && Game.MX < this.em_size_image + em_buraco_x)
					&& (Game.MY > em_buraco_y && Game.MY < this.em_size_image + em_buraco_y)) {
				System.out.println("Image buraco");
				selecionado = 2;
			}
			// Image Parede
			else if ((Game.MX > em_parede_x && Game.MX < this.em_size_image + em_parede_x)
					&& (Game.MY > em_parede_y && Game.MY < this.em_size_image + em_parede_y)) {
				System.out.println("Image parede");
				selecionado = 3;
			}
			// Image Entrada
			else if ((Game.MX > em_entrada_x && Game.MX < this.em_size_image + em_entrada_x)
					&& (Game.MY > em_entrada_y && Game.MY < this.em_size_image + em_entrada_y)) {
				System.out.println("Image entrada");
				selecionado = 4;
			}
			// Image saida
			else if ((Game.MX > em_saida_x && Game.MX < this.em_size_image + em_saida_x)
					&& (Game.MY > em_saida_y && Game.MY < this.em_size_image + em_saida_y)) {
				System.out.println("Image saida");
				selecionado = 5;
			}
			//Clicar no mapa
		
			else if ((Game.MX > 0 && Game.MX < Game.HEIGHT * Game.SCALE)
					&& (Game.MY > 0 && Game.MY < Game.HEIGHT * Game.SCALE)) {
				System.out.println("Mapa");
				double px=Game.MX/(Game.SCALE);
				double py=Game.MY/(Game.SCALE);
				int tabuleiro_x=0;
				int tabuleiro_y=0;
				for (int x=0;x<10;x++) {
					if (px>x*10.8&&px<(x+1)*10.8) {
						tabuleiro_x=x;
						break;
					}
				}
				for (int y=0;y<10;y++) {
					if (py>y*10.8&&py<(y+1)*10.8) {
						tabuleiro_y=y;
						break;
					}
				}
			//chao	
			if (selecionado ==1) {
				World.labirinto[tabuleiro_x][tabuleiro_y]=0;
				Game.world.editar_mapa();
				em_feedback_srt= "Chão criado!";
			}
			//buraco
			else if (selecionado ==2) {
				World.labirinto[tabuleiro_x][tabuleiro_y]=2;
				Game.world.editar_mapa();
				em_feedback_srt= "Buraco criado!";
			}
			//parede
			else if (selecionado ==3) {
				World.labirinto[tabuleiro_x][tabuleiro_y]=1;
				Game.world.editar_mapa();
				em_feedback_srt= "Parede criada!";
			}
			//entrada
			else if (selecionado ==4) {
				int cont=0;
				for (int i=0;i<Game.entities.size();i++) {
					if (Game.entities.get(i) instanceof Entrada) {
						cont++;
					}
				}
				if (cont==0) {
				World.labirinto[tabuleiro_x][tabuleiro_y]=4;
				Game.world.editar_mapa();
				em_feedback_srt= "Entrada criada!";
				}else {
					em_feedback_srt= "Apenas uma entrada!";
				}
			}
			//saida
			else if (selecionado ==5) {
				int cont=0;
				for (int i=0;i<Game.entities.size();i++) {
					if (Game.entities.get(i) instanceof Saida) {
						cont++;
					}
				}
				if (cont==0) {
				World.labirinto[tabuleiro_x][tabuleiro_y]=3;
				Game.world.editar_mapa();
				em_feedback_srt= "Saída criada!";
				}else {
					em_feedback_srt= "Apenas uma saída!";
				}
			}
				
				
			}
		}
	}

	private boolean validado() {
		
		//entradas e saidas =1
		int cont_entradas=0;
		int cont_saidas=0;
		for (int i=0;i<Game.entities.size();i++) {
			if (Game.entities.get(i) instanceof Entrada) {
				cont_entradas++;
			}else {
				if (Game.entities.get(i) instanceof Saida) {
					cont_saidas++;
				}	
			}
		}
		if (cont_entradas!=1||cont_saidas!=1) {
			em_feedback_srt= "Precisa de 1 entrada e 1 saída!";
			return false;
		}
		//caminho impossivel
		double x_start = 0;
		double y_start = 0;
		double x_end = 0;
		double y_end = 0;
		
		for (int i = 0; i < Game.entities.size(); i++) {
			if (Game.entities.get(i) instanceof Entrada) {
				x_start = Game.entities.get(i).x;
				y_start = Game.entities.get(i).y;
			
			}
			else if (Game.entities.get(i) instanceof Saida) {
				x_end = Game.entities.get(i).x;
				y_end = Game.entities.get(i).y;
				
			}
		}
		Vector2i start = new Vector2i((int) (x_start / Game.TILE_SIZE), (int) (y_start / Game.TILE_SIZE));
		Vector2i end = new Vector2i((int) (x_end / Game.TILE_SIZE), (int) (y_end / Game.TILE_SIZE));
		List<Node> path = AStar.findPath(start, end);
		
		if (path==null) {
			em_feedback_srt= "Caminho impossível!";
			return false;
		}
		
		return true;
	}

	public void render(Graphics g) {
		g.setFont(font2);
		// image chao
		g.drawImage(em_chao_image, em_chao_x, em_chao_y, em_size_image, em_size_image, null);
		if (selecionado == 1) {
			g.setColor(Color.RED);
			g.drawRect(em_chao_x, em_chao_y, em_size_image, em_size_image);
		}
		// image buraco
		g.drawImage(em_buraco_image, em_buraco_x, em_buraco_y, em_size_image, em_size_image, null);
		if (selecionado == 2) {
			g.setColor(Color.RED);
			g.drawRect(em_buraco_x, em_buraco_y, em_size_image, em_size_image);
		}
		// image parede
		g.drawImage(em_parede_image, em_parede_x, em_parede_y, em_size_image, em_size_image, null);
		if (selecionado == 3) {
			g.setColor(Color.RED);
			g.drawRect(em_parede_x, em_parede_y, em_size_image, em_size_image);
		}
		// image entrada
		g.drawImage(em_entrada_image, em_entrada_x, em_entrada_y, em_size_image, em_size_image, null);
		if (selecionado == 4) {
			g.setColor(Color.RED);
			g.drawRect(em_entrada_x, em_entrada_y, em_size_image, em_size_image);
		}
		// image saida
		g.drawImage(em_saida_image, em_saida_x, em_saida_y, em_size_image, em_size_image, null);
		if (selecionado == 5) {
			g.setColor(Color.RED);
			g.drawRect(em_saida_x, em_saida_y, em_size_image, em_size_image);
		}
		// botao salvar
		g.setColor(Color.RED);
		g.fillRect(em_botao_salvar_x, em_botao_salvar_y, em_botao_salvar_width, em_botao_salvar_height);
		g.setColor(Color.WHITE);
		g.drawString(em_botao_salvar_string_str, em_botao_salvar_string_x, em_botao_salvar_string_y);
		// botao voltar
		g.setColor(Color.BLUE);
		g.fillRect(em_botao_voltar_x, em_botao_voltar_y, em_botao_voltar_width, em_botao_voltar_height);
		g.setColor(Color.WHITE);
		g.drawString(em_botao_voltar_string_str, em_botao_voltar_string_x, em_botao_voltar_string_y);
		// feeback
		g.setColor(Color.RED);
		g.drawString(em_feedback_srt, em_feedback_x, em_feedback_y);
	}
}
