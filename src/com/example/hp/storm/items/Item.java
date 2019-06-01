package com.example.hp.storm.items;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.example.hp.storm.Handler;
import com.example.hp.storm.entities.creatures.Player;
import com.example.hp.storm.gfx.Assets;
import com.example.hp.storm.input.KeyManager;

public class Item {

	// Item Handler

	public static Item[] items = new Item[256];
	public static Item greenGem = new Item(Assets.gem[0], "Green Gem", 0);
	public static Item blueGem = new Item(Assets.gem[1], "Blue Gem", 1);
	public static Item yellowGem = new Item(Assets.gem[2], "Yellow Gem", 2);
	public static Item redGem = new Item(Assets.gem[3], "Red Gem", 3);
	public static Item pinkGem = new Item(Assets.gem[4], "Pink Gem", 4);
	public static Item orangeGem = new Item(Assets.gem[5], "Orange Gem", 5);
	public static Item darkBlueGem = new Item(Assets.gem[6], "Dark Blue Gem", 6);
	public static Item purpleGem = new Item(Assets.gem[7], "Purple Gem", 7);
	public static Item wood = new Item(Assets.wood, "Wood", 8);

	// Class

	public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected final int id;
	protected Rectangle bounds;
	protected boolean pickedUp = false;

	protected int x, y, count;

	public Item(BufferedImage texture, String name, int id) {
		this.texture = texture;
		this.name = name;
		this.id = id;
		count = 1;
		items[id] = this;
		bounds = new Rectangle(0, 0, ITEMWIDTH, ITEMHEIGHT);
	}

	public void tick() {
	}

	public void render(Graphics g) {
		if (handler == null) {
			return;
		}

		render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
//		g.drawRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),
//				bounds.width, bounds.height);
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
	}

	public Item createNew(int x, int y) {
		Item i = new Item(texture, name, id);
		i.setPosition(x, y);
		return i;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}

	public boolean getCollected(Graphics g) {
		Player p = handler.getWorld().getPlayer();
		if (p.getInteractionBounds().intersects(getCollisonBounds())) {
			g.setColor(Color.decode("#FFFF00"));
			g.setFont(new Font("Comic Sans MS", 1000, 20));
			g.drawString("Press F to Pick Item", 550, 400);
			if (KeyManager.F) {
				handler.getPlayer().getInventory().addItem(this);
				pickedUp = true;
				return true;
			}
		}
		return false;
	}

	public Rectangle getCollisonBounds() {
		return new Rectangle((int) (bounds.x - handler.getGameCamera().getxOffset()),
				(int) (bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);

	}

	// Getters and Setters

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
