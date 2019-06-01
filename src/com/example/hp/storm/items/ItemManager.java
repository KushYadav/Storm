package com.example.hp.storm.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import com.example.hp.storm.Handler;

public class ItemManager {

	public Handler handler;
	private ArrayList<Item> items;

	public ItemManager(Handler handler) {
		this.handler = handler;
		items = new ArrayList<>();

	}

	public void tick() {
		Iterator<Item> it = items.iterator();
		while (it.hasNext()) {
			Item i = it.next();
			i.tick();
			if (i.isPickedUp()) {
				it.remove();
			}
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getCollected(g)) {
				items.remove(i);
				return;
			}
			items.get(i).render(g);
		}
	}

	public void addItem(Item i) {
		i.setHandler(handler);
		items.add(i);
	}

	// Getters and setters
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

}
