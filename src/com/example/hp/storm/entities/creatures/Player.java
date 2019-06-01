package com.example.hp.storm.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.example.hp.storm.Handler;
import com.example.hp.storm.gfx.Animation;
import com.example.hp.storm.gfx.Assets;
import com.example.hp.storm.input.KeyManager;
import com.example.hp.storm.inventory.Inventory;

public class Player extends Creature {

	// Aninmation
	private Animation animDown, animUp, animLeft, animRight, animAttack;

	// Inventory
	private Inventory inventory;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH * 5, Creature.DEFAULT_CREATURE_HEIGHT * 5);
		animDown = new Animation(100, Assets.player_down);
		animUp = new Animation(100, Assets.player_up);
		animLeft = new Animation(100, Assets.player_left);
		animRight = new Animation(100, Assets.player_right);
		animAttack = new Animation(100, Assets.player_attack);
		bounds.x = width * 2 / 5;
		bounds.y = width * 2 / 5;
		bounds.width = width / 5;
		bounds.height = width / 4;
		crop.x = 64;
		crop.y = 48;
		crop.width = 128;
		crop.height = 96;
		attack = 5;
		speed = 2.5f;
		inventory = new Inventory(handler);
	}

	@Override
	public void tick() {
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		animAttack.tick();
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		inventory.tick();
	}

	public void getInput() {
		xMove = 0;
		yMove = 0;

		if (KeyManager.up) {
			yMove -= speed;
		}
		if (KeyManager.down) {
			yMove += speed;
		}
		if (KeyManager.left) {
			xMove -= speed;
		}
		if (KeyManager.right) {
			xMove += speed;
		}
	}

	@Override
	public void render(Graphics g) {
		inventory.render(g);
		if (KeyManager.space) {
			g.drawImage(animAttack.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		} else {
			g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		}
		showCollisionBound(g);
		showInteractionBound(g);
	}

	public BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0) {
			return animLeft.getCurrentFrame();
		} else if (xMove > 0) {
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			return animUp.getCurrentFrame();
		} else if (yMove > 0) {
			return animDown.getCurrentFrame();
		} else {
			return Assets.player_still;
		}
	}

	@Override
	public boolean isEnemy() {
		return false;
	}

	public int getBoundX() {
		return bounds.x;
	}

	public int getBoundY() {
		return bounds.y;
	}

	public int getBoundWidth() {
		return bounds.width;
	}

	public int getBoundHeight() {
		return bounds.height;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Player getPlayer() {
		return this;
	}
}
