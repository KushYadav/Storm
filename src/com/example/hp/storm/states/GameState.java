package com.example.hp.storm.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.example.hp.storm.Handler;
import com.example.hp.storm.entities.creatures.Player;
import com.example.hp.storm.worlds.World;

public class GameState extends State {

	private World world;

	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, 1, "res/worlds/world_01.txt");
		handler.setWorld(world);
	}

	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
//		System.out.println(handler.getPlayer().getX() + "," + handler.getPlayer().getY());
		if (handler.getPlayer().getX() > 908.5 && handler.getPlayer().getX() > 908.5
				&& handler.getPlayer().getY() > 1250 && world.getKey() == 1) {
			Player temp = handler.getPlayer();
			world = new World(handler, 2, "res/worlds/world_02.txt");
			handler.setWorld(world);
			handler.getPlayer().setInventory(temp.getInventory());
			handler.getPlayer().setHealth(temp.getHealth());
		} else if (handler.getPlayer().getX() > 543 && handler.getPlayer().getX() < 620
				&& handler.getPlayer().getY() >= 640 && handler.getPlayer().getY() <= 663 && world.getKey() == 2) {
			Player temp = handler.getPlayer();
			world = new World(handler, 3, "res/worlds/world_03.txt");
			handler.setWorld(world);
			handler.getPlayer().setInventory(temp.getInventory());
			handler.getPlayer().setHealth(temp.getHealth());
		} else if (handler.getPlayer().getX() >= 2048 && handler.getPlayer().getX() <= 2111
				&& handler.getPlayer().getY() > 1475 && handler.getPlayer().getY() < 1568 && world.getKey() == 3) {
			CurrentState.setCurrentState(new GameCompleteState(handler));
//			Player temp = handler.getPlayer();
//			world = new World(handler, 3, "res/worlds/world_03.txt");
//			handler.setWorld(world);
//			handler.getPlayer().setInventory(temp.getInventory());

		} else if (handler.getPlayer().getX() >= 928 && handler.getPlayer().getX() <= 991
				&& handler.getPlayer().getY() > 1431 && handler.getPlayer().getY() < 1525 && world.getKey() == 3) {
			System.out.println("Hello");
			g.setColor(Color.WHITE);
			g.setFont(new Font("Comic Sans MS", 1000, 30));
			g.drawString("Gate Locked", 550, 400);
//			Player temp = handler.getPlayer();
//			world = new World(handler, 3, "res/worlds/world_03.txt");
//			handler.setWorld(world);
//			handler.getPlayer().setInventory(temp.getInventory());
		}
	}
}
