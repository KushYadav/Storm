package com.example.hp.storm.entities.statics;

import java.awt.Graphics;

import com.example.hp.storm.Handler;
import com.example.hp.storm.entities.creatures.Creature;
import com.example.hp.storm.gfx.Assets;

public class Tree extends StaticEntity {

	private int treeId;

	public Tree(Handler handler, float x, float y, int treeId) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH * 2, Creature.DEFAULT_CREATURE_HEIGHT * 2);
		this.treeId = treeId;
		bounds.x = 20;
		bounds.y = 32;
		bounds.width = 24;
		bounds.height = 24;
	}

	public void tick() {
		handleCombat();
	}

	public void render(Graphics g) {
		showHealth(g);
		g.drawImage(Assets.tree[treeId], (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), null);
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
		return "Tree";
	}
}
