package com.example.hp.storm.entities.statics;

import java.awt.Graphics;

import com.example.hp.storm.Handler;
import com.example.hp.storm.entities.Entity;
import com.example.hp.storm.entities.creatures.Player;
import com.example.hp.storm.input.KeyManager;

public class StaticEntity extends Entity {

	protected int countS, count = 0;

	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = width;
		bounds.height = height;
		health = 20;
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {

	}

	public void handleCombat() {
		Player player = handler.getWorld().getPlayer();
		if (player.getInteractionBounds().intersects(this.getInteractionBounds())) {
			if (KeyManager.space) {
				countS++;
				if (countS >= 30) {
					countS = 0;
					health -= player.getAttack() + handler.random().nextInt(10);
				}
			}
		}
	}
}
