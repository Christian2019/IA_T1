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
	public static final int HEIGHT = 108;
	public final static int TILE_SIZE = 32;
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

	public static BufferedImage background;

	public static int MX;
	public static int MY;
	public static boolean mouseClicked;

	public static void iniciar() {
		Sound.fundo2.loop();
		entities.clear();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		world = new World();
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
		try {
			background = ImageIO.read(getClass().getResource("/background2.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		// frame.setIconImage(icon);
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
	//	 SCALE = 5;
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
		ui.tick();
		world.depth(entities);
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
		}
	}

	int load_frames = 0;
	boolean ft = true;

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = image.getGraphics();
		Graphics g2 = World.mapa.getGraphics();
		g.dispose();
		g = bs.getDrawGraphics();

		g.setColor(new Color(0, 0, 0));
		// g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
		g.drawImage(background, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		world.render(g2);
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g2);
		}
		ui.render(g);
		if (ui.state.equals("astar")) {
			g.drawImage(World.mapa, Proporcoes.porcentagem(Proporcoes.X_Total, 25),
					0, HEIGHT * SCALE, HEIGHT * SCALE,null);
		}else if (ui.state.equals("ag2")) {
			g.drawImage(World.mapa, Proporcoes.porcentagem(Proporcoes.X_Total, 0),
					0, HEIGHT * SCALE, HEIGHT * SCALE,null);
		}
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
		if (ui.state.equals("ag1") && !(ui.ag1_selecionado == 0)) {
			if (e.getKeyCode() == KeyEvent.VK_0) {
				Alphabet.vk_0 = true;
			} else if (e.getKeyCode() == KeyEvent.VK_1) {
				Alphabet.vk_1 = true;
			} else if (e.getKeyCode() == KeyEvent.VK_2) {
				Alphabet.vk_2 = true;
			} else if (e.getKeyCode() == KeyEvent.VK_3) {
				Alphabet.vk_3 = true;
			} else if (e.getKeyCode() == KeyEvent.VK_4) {
				Alphabet.vk_4 = true;
			} else if (e.getKeyCode() == KeyEvent.VK_5) {
				Alphabet.vk_5 = true;
			} else if (e.getKeyCode() == KeyEvent.VK_6) {
				Alphabet.vk_6 = true;
			} else if (e.getKeyCode() == KeyEvent.VK_7) {
				Alphabet.vk_7 = true;
			} else if (e.getKeyCode() == KeyEvent.VK_8) {
				Alphabet.vk_8 = true;
			} else if (e.getKeyCode() == KeyEvent.VK_9) {
				Alphabet.vk_9 = true;
			} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				Alphabet.vk_backspace = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

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
		MX = e.getX();
		MY = e.getY();
		System.out.println("X: " + MX);
		System.out.println("Y: " + MY);
		mouseClicked = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}