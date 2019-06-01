package com.example.hp.storm;

import java.util.Random;

import javax.swing.JFrame;

import com.example.hp.storm.entities.creatures.Player;
import com.example.hp.storm.gfx.GameCamera;
import com.example.hp.storm.input.KeyManager;
import com.example.hp.storm.input.MouseManager;
import com.example.hp.storm.worlds.World;

public class Handler {

	private Game game;
	private World world;
	private Random random;

	public Handler(Game game) {
		this.game = game;
		random = new Random();
	}

	// Getters & Settters

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public int getWidth() {
		return game.getWidth();
	}

	public int getHeight() {
		return game.getHeight();
	}

	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}

	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}

	public Player getPlayer() {
		return world.getPlayer();
	}

	public void setPlayer(Player player) {
		world.setPlayer(player);
	}

	public JFrame getFrame() {
		return game.getDisplay().getFrame();
	}

	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}

	public Random random() {
		return random;
	}
}
