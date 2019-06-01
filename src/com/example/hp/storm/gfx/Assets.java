package com.example.hp.storm.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	public static final int WIDTH = 32, HEIGHT = 32;
	public static BufferedImage player_still, wood;
	public static BufferedImage[] player_down, player_up, player_left, player_right, player_attack, spider, gemOre, gem,
			start, potion, wall, tile, tree;

	public static void init() {
		SpriteSheet buttons = new SpriteSheet(ImageLoader.loadImage("/textures/buttons.png"));
		SpriteSheet player = new SpriteSheet(ImageLoader.loadImage("/textures/Player.png"));
		SpriteSheet inventory = new SpriteSheet(ImageLoader.loadImage("/textures/inventory.png"));
		SpriteSheet stuff = new SpriteSheet(ImageLoader.loadImage("/textures/Stuff.png"));
		SpriteSheet walls = new SpriteSheet(ImageLoader.loadImage("/textures/Wall.png"));
		SpriteSheet tiles = new SpriteSheet(ImageLoader.loadImage("/textures/Tile.png"));

		player_down = new BufferedImage[8];
		player_up = new BufferedImage[8];
		player_left = new BufferedImage[8];
		player_right = new BufferedImage[8];
		player_attack = new BufferedImage[5];
		gemOre = new BufferedImage[8];
		spider = new BufferedImage[8];
		start = new BufferedImage[2];
		gem = new BufferedImage[8];
		potion = new BufferedImage[4];
		wall = new BufferedImage[4];
		tree = new BufferedImage[4];
		tile = new BufferedImage[16];

		start[1] = buttons.crop(0, 0, WIDTH * 8, HEIGHT * 2);
		start[0] = buttons.crop(0, HEIGHT * 2, WIDTH * 8, HEIGHT * 2);

		player_still = player.crop(0, 0, WIDTH * 4, HEIGHT * 4);
		wood = inventory.crop(0, 0, WIDTH, HEIGHT);

		for (int i = 0; i < 16; i++) {
			tile[i] = tiles.crop(WIDTH * i, 0, WIDTH, HEIGHT);
			if (i < 8) {
				player_down[i] = player.crop(WIDTH * 4 * (i + 1), 0, WIDTH * 4, HEIGHT * 4);
				player_up[i] = player.crop(WIDTH * 4 * (i + 1), HEIGHT * 4, WIDTH * 4, HEIGHT * 4);
				player_right[i] = player.crop(WIDTH * 4 * i, HEIGHT * 8, WIDTH * 4, HEIGHT * 4);
				player_left[i] = player.crop(WIDTH * 4 * i, HEIGHT * 12, WIDTH * 4, HEIGHT * 4);
				spider[i] = stuff.crop(WIDTH * 2 * i, HEIGHT * 2, WIDTH * 2, HEIGHT * 2);
				gemOre[i] = stuff.crop(WIDTH * i, 0, WIDTH, HEIGHT);
				gem[i] = stuff.crop(WIDTH * i, HEIGHT, WIDTH, HEIGHT);
			}
			if (i < 5) {
				player_attack[i] = player.crop(WIDTH * 4 * i, HEIGHT * 16, WIDTH * 4, HEIGHT * 4);
			}
			if (i < 4) {
				potion[i] = stuff.crop(WIDTH * i, HEIGHT * 4, WIDTH, HEIGHT);
				wall[i] = walls.crop(WIDTH * i, 0, WIDTH, HEIGHT);
				tree[i] = stuff.crop(WIDTH * 2 * i, HEIGHT * 5, WIDTH * 2, HEIGHT * 2);
			}

		}
	}
}
