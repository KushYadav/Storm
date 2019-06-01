package com.example.hp.storm;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JButton;

import com.example.hp.storm.display.Display;
import com.example.hp.storm.gfx.Assets;
import com.example.hp.storm.gfx.GameCamera;
import com.example.hp.storm.input.KeyManager;
import com.example.hp.storm.input.MouseManager;
import com.example.hp.storm.states.CurrentState;
import com.example.hp.storm.states.GameOverState;
import com.example.hp.storm.states.GameState;
import com.example.hp.storm.states.MenuState;
import com.example.hp.storm.states.SettingState;

public class Game implements Runnable {
	private Display display;
	private String title;
	private int width;
	private int height;

	private boolean running;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;

	private GameState gameState;
	private MenuState menuState;
	private GameOverState gameOverState;
	private SettingState settingState;

	// Input Manager
	private KeyManager keyManager;
	private MouseManager mouseManager;

	private GameCamera gameCamera;

	// Handler
	private Handler handler;

	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}

	private void init() {
		Assets.init();
		display = new Display(title, width, height);
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		settingState = new SettingState(handler);
		CurrentState.setCurrentState(menuState);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		display.getFrame().addKeyListener(keyManager);
	}

	private void tick() {
		keyManager.tick();
		if (CurrentState.getCurrentState() != null) {
			CurrentState.getCurrentState().tick();
		}
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		// Draw
		if (CurrentState.getCurrentState() != null) {
			CurrentState.getCurrentState().render(g);
		}

		// End
		bs.show();
		g.dispose();
	}

	@Override
	public void run() {
		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		long now;
		long lastTime = System.nanoTime();
		int ticks = 0;
		long timer = 0;
		double delta = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				delta = 0;
				tick();
				render();
				ticks++;
			}
			if (timer >= 1000000000) {
				System.out.println("Ticks: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}

	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public void setKeyManager(KeyManager keyManager) {
		this.keyManager = keyManager;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public void setMouseManager(MouseManager mouseManager) {
		this.mouseManager = mouseManager;
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public BufferStrategy getBs() {
		return bs;
	}

	public void setBs(BufferStrategy bs) {
		this.bs = bs;
	}

	public Graphics getG() {
		return g;
	}

	public void setG(Graphics g) {
		this.g = g;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public MenuState getMenuState() {
		return menuState;
	}

	public void setMenuState(MenuState menuState) {
		this.menuState = menuState;
	}

	public SettingState getSettingState() {
		return settingState;
	}

	public void setSettingState(SettingState settingState) {
		this.settingState = settingState;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public void setGameCamera(GameCamera gameCamera) {
		this.gameCamera = gameCamera;
	}

	public GameOverState getGameOverState() {
		return gameOverState;
	}

	public void setGameOverState(GameOverState gameOverState) {
		this.gameOverState = gameOverState;
	}
}
