package graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import main.Proporcoes;

import world.Camera;

public class UI {

	public Font font1 = new Font("Arial", Font.BOLD, Proporcoes.porcentagem(Proporcoes.X_Total, 3));
//	int x=Proporcoes.porcentagem(Proporcoes.X_Total, 30);
	
public String state = "Menu principal"; 
	public void render(Graphics g) {
	if (state.equals("Menu principal")) {
		menu_principal(g);
	}
		
		
	}
	private void menu_principal(Graphics g) {
		//Botao editar mapa
		g.setColor(Color.BLUE);
		int botao_editarmapa_x=Proporcoes.porcentagem(Proporcoes.X_Total, 41);
		int botao_editarmapa_y=Proporcoes.porcentagem(Proporcoes.Y_Total, 2);
		int botao_editarmapa_width=Proporcoes.porcentagem(Proporcoes.X_Total, 18);
		int botao_editarmapa_height=Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
		g.fillRect(botao_editarmapa_x, botao_editarmapa_y, botao_editarmapa_width, botao_editarmapa_height);
		g.setColor(Color.white);
		String string_editarmapa_str="Editar Mapa";
		int string_editarmapa_x=Proporcoes.porcentagem(Proporcoes.X_Total, 41.5);
		int string_editarmapa_y=Proporcoes.porcentagem(Proporcoes.Y_Total, 8.5);
		g.setFont(font1);
		g.drawString(string_editarmapa_str, string_editarmapa_x, string_editarmapa_y);
		
		//Botao A*
		g.setColor(Color.red);
		int botao_astar_x=Proporcoes.porcentagem(Proporcoes.X_Total, 66);
		int botao_astar_y=Proporcoes.porcentagem(Proporcoes.Y_Total, 50);
		int botao_astar_width=Proporcoes.porcentagem(Proporcoes.X_Total, 18);
		int botao_astar_height=Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
		g.fillRect(botao_astar_x, botao_astar_y, botao_astar_width, botao_astar_height);
		g.setColor(Color.white);
		String string_astar_str="A*";
		int string_astar_x=Proporcoes.porcentagem(Proporcoes.X_Total, 74);
		int string_astar_y=Proporcoes.porcentagem(Proporcoes.Y_Total, 57);
		g.setFont(font1);
		g.drawString(string_astar_str, string_astar_x, string_astar_y);
		
		//Botao AG
				g.setColor(Color.GREEN);
				int botao_ag_x=Proporcoes.porcentagem(Proporcoes.X_Total, 2);
				int botao_ag_y=Proporcoes.porcentagem(Proporcoes.Y_Total, 50);
				int botao_ag_width=Proporcoes.porcentagem(Proporcoes.X_Total, 28);
				int botao_ag_height=Proporcoes.porcentagem(Proporcoes.Y_Total, 10);
				g.fillRect(botao_ag_x, botao_ag_y, botao_ag_width, botao_ag_height);
				g.setColor(Color.white);
				String string_ag_str="Algoritmo Genético";
				int string_ag_x=Proporcoes.porcentagem(Proporcoes.X_Total, 2);
				int string_ag_y=Proporcoes.porcentagem(Proporcoes.Y_Total, 57);
				g.setFont(font1);
				g.drawString(string_ag_str, string_ag_x, string_ag_y);
		
	}


}
