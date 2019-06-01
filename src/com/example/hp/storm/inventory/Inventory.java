package com.example.hp.storm.inventory;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.example.hp.storm.Handler;
import com.example.hp.storm.items.Item;

public class Inventory {

	private Handler handler;
	private boolean active;
	private ArrayList<Item> inventoryItems;

	public Inventory(Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<>();
	}

	public void tick() {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
			active = !active;
		}
		if (!active) {
			return;
		}
		System.out.println("Inventory: ");
		for (Item i : inventoryItems) {
			System.out.println(i.getName() + " : " + i.getCount());
		}
	}

	public void render(Graphics g) {
		if (!active) {
			return;
		}
	}

	// Inventory Methods

	public void addItem(Item item) {
		for (Item i : inventoryItems) {
			if (i.getId() == item.getId()) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}

}