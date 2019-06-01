package com.example.hp.storm.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import com.example.hp.storm.Handler;
import com.example.hp.storm.ScoreHandling;
import com.example.hp.storm.entities.creatures.Player;
import com.example.hp.storm.entities.statics.GemOre;
import com.example.hp.storm.entities.statics.Tree;
import com.example.hp.storm.input.KeyManager;
import com.example.hp.storm.items.Item;

public class EntityManager {

	private Handler handler;
	private ScoreHandling sh;
	private ArrayList<Entity> entities;
	private boolean rendered;

	public EntityManager(Handler handler) {
		this.handler = handler;
		sh = new ScoreHandling(handler);
		entities = new ArrayList<>();
	}

	public void tick() {
		for (Entity e : entities) {
			e.tick();
		}
		sh.tick();
	}

	public void render(Graphics g) {
		rendered = false;
		Player p = handler.getPlayer();

		for (int i = 1; i < entities.size(); i++) {
			Entity e = entities.get(i);

			if (p.getInteractionBounds().intersects(e.getInteractionBounds())
					&& p.getInteractionBounds().y < e.getInteractionBounds().y && e.isStatic()) {
				rendered = true;
				entities.get(0).render(g);
			}

			if (e.health <= 0) {
				if (e.staticEntityType().equals("GemOre")) {
					GemOre go = (GemOre) e;
					handler.getWorld().getItemManager().addItem(Item.items[go.getId()]
							.createNew((int) (e.getX() + e.getWidth() / 4), (int) (e.getY() + e.getHeight() / 4)));
				} else if (e.staticEntityType().equals("Tree")) {
					handler.getWorld().getItemManager().addItem(Item.wood.createNew((int) (e.getX() + e.getWidth() / 4),
							(int) (e.getY() + e.getHeight() / 4)));
				}
				entities.remove(i);
			}

			if (e.isConsumable()) {
				if (p.getInteractionBounds().intersects(e.getInteractionBounds()))
					if (KeyManager.F) {
						entities.remove(i);
					}
			}
			e.render(g);
		}
		sh.render(g);
		if (!rendered) {
			entities.get(0).render(g);
		}
	}

	public void renderList(ArrayList<Entity> list, Graphics g) {
		for (int j = 0; j < list.size(); j++) {
			list.get(j).render(g);
		}
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}
}
