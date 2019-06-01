package com.example.hp.storm.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.example.hp.storm.gfx.Assets;

public class Tile {

	public static Tile[] tiles = new Tile[256];

	public static Tile skinTile = new Tile(Assets.tile[0], 0);
	public static Tile greenTile = new Tile(Assets.tile[1], 1);
	public static Tile redTile = new Tile(Assets.tile[2], 2);
	public static Tile darkBlueTile = new Tile(Assets.tile[3], 3);
	public static Tile greyTile = new Tile(Assets.tile[4], 4);
	public static Tile darkPinkTile = new Tile(Assets.tile[5], 5);
	public static Tile rustTile = new Tile(Assets.tile[6], 6);
	public static Tile mintTile = new Tile(Assets.tile[7], 7);
	public static Tile lightBlueTile = new Tile(Assets.tile[8], 8);
	public static Tile lightPurpleTile = new Tile(Assets.tile[9], 9);
	public static Tile cyanTile = new Tile(Assets.tile[10], 10);
	public static Tile parrotGreenTile = new Tile(Assets.tile[11], 11);
	public static Tile maroonTile = new Tile(Assets.tile[12], 12);
	public static Tile yellowTile = new Tile(Assets.tile[13], 13);
	public static Tile lightGreenTile = new Tile(Assets.tile[14], 14);
	public static Tile lightPinkTile = new Tile(Assets.tile[15], 15);
	public static Tile blackWallTile = new Tile(Assets.wall[0], 16);
	public static Tile blueWallTile = new Tile(Assets.wall[1], 17);
	public static Tile lightBlueWallTile = new Tile(Assets.wall[2], 18);
	public static Tile redWallTile = new Tile(Assets.wall[3], 19);

	public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;

	protected BufferedImage texture;
	protected final int id;

	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		tiles[id] = this;
	}

	public void tick() {

	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}

	public boolean isSolid() {
		if (this == redWallTile || this == blackWallTile || this == blueWallTile) {
			return true;
		} else {
			return false;
		}
	}

	public int getId() {
		return id;
	}

}
