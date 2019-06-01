package com.example.hp.storm.worlds;

import java.awt.Graphics;

import com.example.hp.storm.Handler;
import com.example.hp.storm.entities.EntityManager;
import com.example.hp.storm.entities.creatures.Player;
import com.example.hp.storm.entities.creatures.Spider;
import com.example.hp.storm.entities.statics.GemOre;
import com.example.hp.storm.entities.statics.HealthPotion;
import com.example.hp.storm.entities.statics.Tree;
import com.example.hp.storm.items.ItemManager;
import com.example.hp.storm.tiles.Tile;
import com.example.hp.storm.utils.Utils;

public class World {

	private int[][] tiles;
	private int wWidth, wHeight;
	private int spawnX, spawnY;
	private Handler handler;
	private EntityManager entityManager;
	private ItemManager itemManager;
	private Player player;
	private int worldKey;

	public World(Handler handler, int worldKey, String path) {
		this.handler = handler;
		this.worldKey = worldKey;
		loadWorld(path);
		entityManager = new EntityManager(handler);
		itemManager = new ItemManager(handler);
		entityManager.addEntity(player);
		if (worldKey == 1) {
			initEntities(50, 25, 5, 3);
		} else if (worldKey == 2) {
			initEntities(50, 25, 5, 4);
		} else if (worldKey == 3) {
			initEntities(150, 100, 10, 5);
		}
	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		wWidth = Utils.parseInt(tokens[0]);
		wHeight = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		player = new Player(handler, spawnX, spawnY);

		tiles = new int[wWidth][wHeight];
		for (int y = 0; y < wHeight; y++) {
			for (int x = 0; x < wWidth; x++) {
				tiles[x][y] = Utils.parseInt(tokens[x + y * wWidth + 4]);
			}
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || x >= wWidth || y < 0 || y >= wHeight) {
			return Tile.greenTile;
		}
		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null) {
			return Tile.greenTile;
		}
		return t;
	}

	public void tick() {
		entityManager.tick();
		itemManager.tick();
	}

	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(wWidth,
				(handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(wHeight,
				(handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		entityManager.render(g);
		itemManager.render(g);
	}

	private boolean canBePlaced(int placeX, int placeY) {
		if (!getTile(placeX, placeY).isSolid() && !getTile(placeX + 1, placeY).isSolid()
				&& !getTile(placeX, placeY + 1).isSolid() && !getTile(placeX + 1, placeY + 1).isSolid()) {
			return true;
		}
		return false;
	}

	public void initEntities(int trees, int spiders, int gemOres, int HealthPotions) {
		for (int i = 0; i < trees; i++) {
			int placeX = handler.random().nextInt(wWidth);
			int placeY = handler.random().nextInt(wHeight);
			if (canBePlaced(placeX, placeY)) {
				entityManager.addEntity(new Tree(handler, placeX * 32, placeY * 32, 1));
			}
		}
		for (int i = 0; i < spiders; i++) {
			int placeX = handler.random().nextInt(wWidth);
			int placeY = handler.random().nextInt(wHeight);
			if (canBePlaced(placeX, placeY)) {
				entityManager.addEntity(new Spider(handler, placeX * 32, placeY * 32, worldKey));
			}
		}

		for (int i = 0; i < gemOres; i++) {
			int placeX = handler.random().nextInt(wWidth);
			int placeY = handler.random().nextInt(wHeight);
			if (canBePlaced(placeX, placeY)) {
				entityManager.addEntity(new GemOre(handler, placeX * 32, placeY * 32, handler.random().nextInt(8)));
			}
		}

		for (int i = 0; i < HealthPotions; i++) {
			int placeX = handler.random().nextInt(wWidth);
			int placeY = handler.random().nextInt(wHeight);
			if (canBePlaced(placeX, placeY)) {
				entityManager
						.addEntity(new HealthPotion(handler, placeX * 32, placeY * 32, handler.random().nextInt(4)));
			}
		}
	}

	// Getters and Setters

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getwWidth() {
		return wWidth;
	}

	public void setwWidth(int wWidth) {
		this.wWidth = wWidth;
	}

	public int getwHeight() {
		return wHeight;
	}

	public void setwHeight(int wHeight) {
		this.wHeight = wHeight;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public int getKey() {
		return worldKey;
	}

}
