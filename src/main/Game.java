package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import entities.Entity;
import entities.Player;
import graficos.ImageResizer;
import graficos.PerfectColliding;
import graficos.Spritesheet;
import graficos.UI;
import som.Sound;
import world.Camera;
import world.Tile;
import world.World;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	// Janela
	public static JFrame frame;
	public static final int WIDTH = 190;
	public static final int HEIGHT = 99;
	public final static int TILE_SIZE = 11;
	public static int SCALE = 1;
	// Thread1
	private static boolean isRunning;
	public static Thread thread;
	// Render
	public static BufferedImage image;
	public static Spritesheet spritesheet;
	public static ArrayList<Entity> entities;
	public static Random rand;
	public static UI ui;
	public static String estado = "Game";
	public static int Nivel = 0;
	public static boolean restart = false;
	static Game game;
	public static int frames;
	public static World world;
	public static Player player;
	boolean pausedClicked = false;
	public static double proximidadeCamera = 3;
	boolean zoom = false;
	public static int z = 0;

	
	static BufferedImage background;
	public static int MX;
	public static int MY;
	
	public static boolean mouseClicked;
	

	public static void iniciar() {
		Sound.fundo.loop();
		entities.clear();
	
	//	world = new World("/maps/m" + Game.Nivel + ".png");

		image = new BufferedImage(9 * Game.TILE_SIZE, 9 * Game.TILE_SIZE,
				BufferedImage.TYPE_INT_RGB);
		// image = new BufferedImage(14, 19, BufferedImage.TYPE_INT_RGB);

	}

	public Game() {
		Game.rand = new Random();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		this.setFocusable(true);
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		initFrame();
		// Inicializando objetos.
		entities = new ArrayList<Entity>();
		ui = new UI();
		
		
		iniciar();
	}

	public void initFrame() {
		frame = new JFrame("IA_T1");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		spritesheet = new Spritesheet("/spritesheet.png");
		
		// Icone Mouse
		// Toolkit toolkit = Toolkit.getDefaultToolkit();
		// Cursor c = toolkit.createCustomCursor(icon, new Point(0, 0), "img");
		// frame.setCursor(c);
	//	 frame.setIconImage(icon);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void autoScale() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		int x = WIDTH * SCALE;
		int y = HEIGHT * SCALE;
		while (x < width && y < height) {
			SCALE++;
			x = WIDTH * SCALE;
			y = HEIGHT * SCALE;
		}
		SCALE--;
	}

	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		autoScale();
		
		// SCALE = 5;
		game = new Game();
		game.start();
	}

	public synchronized void start() throws InterruptedException {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public static synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	

	public void tick() {
		
		if (estado.equals("Paused")) {
			if (this.pausedClicked) {
				this.pausedClicked = false;
				Sound.fundo.loop();
				estado = "Game";
			}
		}
		if (estado.equals("Game")) {
			if (this.pausedClicked) {
				this.pausedClicked = false;
				Sound.fundo.pause();
				estado = "Paused";
			}

			if (restart) {
				restart = false;
				iniciar();
			}
		
		
		}
	}

	
int load_frames=0;
boolean ft=true;
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = image.getGraphics();
		
		// world.render(g);
		// Inicio da Renderizacao
	
		// Fim da Renderizacao
		g.dispose();
		g = bs.getDrawGraphics();
		g.setColor(new Color(0, 0, 0));
		//g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		
		
		 ui.render(g);
		
		bs.show();
		
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		frames = 0;
		double timer = System.currentTimeMillis();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
	 if (e.getKeyCode() == KeyEvent.VK_P) {
			this.pausedClicked = true;
		}
		if (estado.equals("Game")) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				// estado="Menu";
			}
			if (e.getKeyCode() == KeyEvent.VK_R) {
				restart = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				player.right = true;

			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				player.left = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {

			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			}

		}
	}

	
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		/*
		 * double proporcaoX = (double) (World.WIDTH * this.TILE_SIZE) / (double) (WIDTH
		 * * SCALE); double proporcaoY = (double) (World.HEIGHT * this.TILE_SIZE) /
		 * (double) (HEIGHT * SCALE); double correctionX = 1; double correctionY = 1;
		 * double correction2X = 0; double correction2Y = 0; // Para mapas que usam a
		 * camera if (z == 1) { correctionX = World.WIDTH * Game.TILE_SIZE /
		 * (World.WIDTH * Game.TILE_SIZE - Camera.max_Camera_x()); correctionY =
		 * World.HEIGHT * Game.TILE_SIZE / (World.HEIGHT * Game.TILE_SIZE -
		 * Camera.max_Camera_y()); correction2X = Camera.x; correction2Y = Camera.y; }
		 * // // UI.MouseX=correction2X+e.getX()*proporcaoX/correctionX; //
		 * UI.MouseY=correction2Y+e.getY()*proporcaoY/correctionY;
		 */

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		MX = e.getX() / SCALE;
		MY = e.getY() / SCALE;
		System.out.println("X: " + MX);
		System.out.println("Y: " + MY);
		mouseClicked = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}