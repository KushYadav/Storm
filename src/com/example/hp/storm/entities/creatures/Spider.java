package com.example.hp.storm.entities.creatures;

import java.awt.Graphics;

import com.example.hp.storm.Handler;
import com.example.hp.storm.gfx.Assets;

public class Spider extends Creature {

	private int id;

	public Spider(Handler handler, float x, float y, int id) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		this.id = id;
		speed = 2f;
		bounds.x = 5;
		bounds.y = 5;
		bounds.width = 22;
		bounds.height = 22;
		health = 50;
	}

	@Override
	public void tick() {
		move();
	}

	@Override
	public boolean isEnemy() {
		return true;
	}

	@Override
	public void render(Graphics g) {
		if (id == 1) {
			g.drawImage(Assets.spider[0], (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), 32, 32, null);
		} else if (id == 2) {
			g.drawImage(Assets.spider[1], (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), 32, 32, null);
		} else if (id == 3) {
			g.drawImage(Assets.spider[2], (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), 32, 32, null);
		}
		showCollisionBound(g);
		showInteractionBound(g);
		showHealth(g);
	}

}
