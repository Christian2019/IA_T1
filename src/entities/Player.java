package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import AStarAlg.AStar;
import AStarAlg.Vector2i;
import graficos.Ponto;
import main.Game;
import world.World;

public class Player extends Entity {
	boolean vivo=true;
	boolean fim=false;
	String ag_path;
	Ponto atual;
	boolean elite;
	int r,g,b;
	double x_end;
	double y_end;
	public Player(double x, double y, int width, int height, BufferedImage sprite, double speed, int depth,String ag_path,boolean elite) {
		super(x, y, width, height, sprite, speed, depth);
		this.ag_path=ag_path;
		this.atual = new Ponto(x,y);
		this.elite=elite;
		this.r=Game.rand.nextInt(256);
		this.g=Game.rand.nextInt(256);
		this.b=Game.rand.nextInt(256);
		if (elite) {
			this.r=255;
			this.g=255;
			this.b=255;
		}
		Vector2i start = new Vector2i((int) (x / Game.TILE_SIZE), (int) (y / Game.TILE_SIZE));
		x_end = 0;
		 y_end = 0;
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
	double time=0;
	double max_time=60*1;
	public void tick() {
		if (Game.ui.state.equals("astar")) {
		if (Game.ui.astar_ligado) {
			AStar.followPath(path, this);
			
		}
		}
		else if (Game.ui.state.equals("ag2")) {
			if (elite&&!vivo) {
				if (Game.ui.ag2_comecarde) {
				time++;
				if (time==max_time) {
					time=0;
					Game.ui.elite_morto=true;
					int ga=Integer.parseInt(Game.ui.ag2_geracao_atual_valor_str);
					ga++;
					Game.ui.ag2_geracao_atual_valor_str=ga+"";
				}
				}
			}
			
			if (vivo&&!fim) {
			if (ag_path.length()>0) {
				
			Ponto proximo_paco=proximoPaco(ag_path);
		
			if (proximo_paco.xd==x&&proximo_paco.yd==y) {
						atual.xd=x;
				atual.yd=y;
				ag_path=ag_path.substring(1, ag_path.length());
			if (x==this.x_end&&y==this.y_end) {
				fim=true;
				return;
			}
				if (x<0||y<0||x/Game.TILE_SIZE>(World.WIDTH-1)||y/Game.TILE_SIZE>(World.HEIGHT-1)) {
				vivo=false;
			}
			else if	(!World.isFree(x, y)) {
				vivo=false;
			}
				
			}else {
				if (proximo_paco.xd<x) {
					x-=speed;
					left=true;
					right=false;
				}else if (proximo_paco.xd>x) {
					x+=speed;
					right=true;
					left=false;
				}
				if (proximo_paco.yd<y) {
					y-=speed;
				}else if (proximo_paco.yd>y) {
					y+=speed;
				}
			}
			}
			
			}
		}

	}
	double frames=0;
	double max_frames=20;
	public void render(Graphics g) {
		
		if (Game.ui.astar_ligado||(Game.ui.state.equals("ag2"))) {
		
		frames++;
		
		if (frames==max_frames) {
			frames=0;
		}
		if (vivo) {
		if (!elite) {
		if (frames<(max_frames/2)&&right) {
			sprite=Entity.PLAYER[0];
			
			
		}else if (frames>max_frames/2&&right) {
			sprite=Entity.PLAYER[1];
		}else if (frames<max_frames/2&&left) {
			sprite=Entity.PLAYER[2];
		}else if (frames>max_frames/2&&left) {
			sprite=Entity.PLAYER[3];
		}
		
			g.setColor(new Color(this.r,this.g,this.b,100));
			g.fillRect((int)x, (int)y, Game.TILE_SIZE, Game.TILE_SIZE);
		}else {
			if (frames<(max_frames/2)&&right) {
				sprite=Entity.ELITE[0];
				
				
			}else if (frames>max_frames/2&&right) {
				sprite=Entity.ELITE[1];
			}else if (frames<max_frames/2&&left) {
				sprite=Entity.ELITE[2];
			}else if (frames>max_frames/2&&left) {
				sprite=Entity.ELITE[3];
			}
			g.setColor(new Color(this.r,this.g,this.b,100));
			g.fillRect((int)x, (int)y, Game.TILE_SIZE, Game.TILE_SIZE);
		}
		
		}else {
			if (frames<(max_frames/2)&&right) {
				sprite=Entity.GHOST[0];
				
				
			}else if (frames>max_frames/2&&right) {
				sprite=Entity.GHOST[1];
			}else if (frames<max_frames/2&&left) {
				sprite=Entity.GHOST[2];
			}else if (frames>max_frames/2&&left) {
				sprite=Entity.GHOST[3];
			}
			g.setColor(new Color(this.r,this.g,this.b,100));
			g.fillRect((int)x, (int)y, Game.TILE_SIZE, Game.TILE_SIZE);
		}
		}
		
		super.render(g);
	}

	public Ponto proximoPaco(String ag_path) {
		String pp= ag_path.substring(0, 1);
		Ponto p=new Ponto(0.0,0.0);
		if (pp.equals("0")) {
			p.xd=atual.xd-Game.TILE_SIZE;
			p.yd=atual.yd-Game.TILE_SIZE;
		}else if (pp.equals("1")) {
			p.xd=atual.xd;
			p.yd=atual.yd-Game.TILE_SIZE;
		}else if (pp.equals("2")) {
			p.xd=atual.xd+Game.TILE_SIZE;
			p.yd=atual.yd-Game.TILE_SIZE;
		}else if (pp.equals("3")) {
			p.xd=atual.xd+Game.TILE_SIZE;
			p.yd=atual.yd;
		}else if (pp.equals("4")) {
			p.xd=atual.xd+Game.TILE_SIZE;
			p.yd=atual.yd+Game.TILE_SIZE;
		}else if (pp.equals("5")) {
			p.xd=atual.xd;
			p.yd=atual.yd+Game.TILE_SIZE;
		}else if (pp.equals("6")) {
			p.xd=atual.xd-Game.TILE_SIZE;
			p.yd=atual.yd+Game.TILE_SIZE;
		}else if (pp.equals("7")) {
			p.xd=atual.xd-Game.TILE_SIZE;
			p.yd=atual.yd;
		}
		
		return p;
		
	}

}
