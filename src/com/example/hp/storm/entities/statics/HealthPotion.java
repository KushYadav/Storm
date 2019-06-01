package com.example.hp.storm.entities.statics;

import java.awt.Graphics;

import com.example.hp.storm.Handler;
import com.example.hp.storm.entities.creatures.Creature;
import com.example.hp.storm.entities.creatures.Player;
import com.example.hp.storm.gfx.Assets;
import com.example.hp.storm.input.KeyManager;

public class HealthPotion extends StaticEntity {

	private int potionNo;

	public HealthPotion(Handler handler, float x, float y, int potionNo) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH * 3 / 2, Creature.DEFAULT_CREATURE_HEIGHT * 3 / 2);
		this.potionNo = potionNo;
		bounds.x = width / 4;
		bounds.y = width / 4;
		bounds.width = width / 2;
		bounds.height = width / 2;
	}

	public void tick() {
		if (handler.getPlayer().getInteractionBounds().intersects(this.getInteractionBounds()))
			if (KeyManager.F) {
				consume();
			}
	}

	public void render(Graphics g) {
		g.drawImage(Assets.potion[potionNo], (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		showCollisionBound(g);
		showInteractionBound(g);
	}

	public void consume() {
		Player player = handler.getWorld().getPlayer();
		if (player.getInteractionBounds().intersects(this.getInteractionBounds())) {
			player.setHealth(Math.min(player.getHealth() + healthPoints(getPotionNo()), 100));
		}
	}

	public int healthPoints(int potionNo) {
		if (potionNo == 0) {
			return 40;
		} else if (potionNo == 1) {
			return 50;
		} else if (potionNo == 2) {
			return 60;
		} else {
			return 70;
		}
	}

	@Override
	public boolean isConsumable() {
		return true;
	}

	public int getPotionNo() {
		return potionNo;
	}

	public void setPotionNo(int potionNo) {
		this.potionNo = potionNo;
	}
}
