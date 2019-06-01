package com.example.hp.storm.entities.statics;

import java.awt.Graphics;

import com.example.hp.storm.Handler;
import com.example.hp.storm.entities.creatures.Creature;
import com.example.hp.storm.gfx.Assets;

public class GemOre extends StaticEntity {

	private int id;

	public GemOre(Handler handler, float x, float y, int id) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH * 3 / 2, Creature.DEFAULT_CREATURE_HEIGHT * 3 / 2);
		this.id = id;
		bounds.x = width / 3;
		bounds.y = height / 3;
		bounds.width = width / 3;
		bounds.height = width / 2;
	}

	public void tick() {
		handleCombat();
	}

	public void render(Graphics g) {
		showHealth(g);
		g.drawImage(Assets.gemOre[id], (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		showCollisionBound(g);
		showInteractionBound(g);
	}

	@Override
	public boolean containsItem() {
		return true;
	}

	@Override
	public boolean isStatic() {
		return true;
	}

	@Override
	public String staticEntityType() {
		return "GemOre";
	}

	public int getId() {
		return id;
	}
}
