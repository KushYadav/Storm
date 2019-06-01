package com.example.hp.storm.entities.creatures;

import com.example.hp.storm.Handler;
import com.example.hp.storm.entities.Entity;
import com.example.hp.storm.input.KeyManager;
import com.example.hp.storm.states.CurrentState;
import com.example.hp.storm.states.GameOverState;
import com.example.hp.storm.tiles.Tile;

public abstract class Creature extends Entity {

	public static final int DEFAULT_ATTACK = 0;
	public static final float DEFAULT_SPEED = 1.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 32;
	public static final int DEFAULT_CREATURE_HEIGHT = 32;

	protected int attack;
	protected float speed;
	protected float xMove;
	protected float yMove;
	private int countP = 0;
	private int countS = 0;

	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		attack = DEFAULT_ATTACK;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	public boolean isEnemy() {
		return false;
	}

	public void move() {
		if (isEnemy()) {
			handleEnemyFollowing();
		}
		if (!checkEntityCollision(xMove, 0f)) {
			moveX();
		}
		if (!checkEntityCollision(0f, yMove)) {
			moveY();
		}
	}

	protected void moveX() {
		if (xMove > 0) {
			int tx = (int) (x + bounds.x + bounds.width + xMove) / Tile.TILE_WIDTH;
			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT)
					&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
			}
		} else if (xMove < 0) {
			int tx = (int) (x + bounds.x + xMove) / Tile.TILE_WIDTH;
			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT)
					&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
			}
		}
	}

	protected void moveY() {
		if (yMove < 0) {
			int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.x;
			}
		} else if (yMove > 0) {
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
				y += yMove;
			} else {
				y = ty * Tile.TILE_HEIGHT - bounds.x - bounds.height - 1;
			}
		}
	}

	public void handleEnemyFollowing() {
		xMove = 0;
		yMove = 0;

		Player p = handler.getPlayer();
		int centerPx = (int) (p.getRangeBounds().x + p.getRangeBounds().width / 2);
		int centerPy = (int) (p.getRangeBounds().y + p.getRangeBounds().height / 2);
		int centerCx = (int) (getInteractionBounds().x + getInteractionBounds().width / 2);
		int centerCy = (int) (getInteractionBounds().y + getInteractionBounds().height / 2);

		if (p.getRangeBounds().intersects(getInteractionBounds())) {
			if (centerCx < centerPx) {
				xMove += speed;
			} else if (centerCx > centerPx) {
				xMove -= speed;
			}
			if (centerCy < centerPy) {
				yMove += speed;
			} else if (centerCy > centerPy) {
				yMove -= speed;
			}
		}

		if (p.getInteractionBounds().intersects(this.getInteractionBounds()) && this.isEnemy()) {
			countP++;
			if (countP >= 30) {
				countP = 0;
				p.setHealth(p.getHealth() - handler.random().nextInt(5));
				if (p.getHealth() <= 0) {
					CurrentState.setCurrentState(new GameOverState(handler));
				}
			}
		}
		if (p.getInteractionBounds().intersects(this.getInteractionBounds()) && this.isEnemy()) {
			handler.getKeyManager();
			if (KeyManager.space) {
				countS++;
				if (countS >= 30) {
					countS = 0;
					health -= p.attack + handler.random().nextInt(10);
				}
			}
		}
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	// Getters & Setters

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public static int getDefaultHealth() {
		return DEFAULT_HEALTH;
	}

	public static int getDefaultAttack() {
		return DEFAULT_ATTACK;
	}

	public static float getDefaultSpeed() {
		return DEFAULT_SPEED;
	}

	public static int getDefaultCreatureWidth() {
		return DEFAULT_CREATURE_WIDTH;
	}

	public static int getDefaultCreatureHeight() {
		return DEFAULT_CREATURE_HEIGHT;
	}

}
