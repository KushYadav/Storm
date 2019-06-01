package com.example.hp.storm.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.example.hp.storm.Handler;

public abstract class Entity {

	public static final int DEFAULT_HEALTH = 100;
	protected float x, y;
	protected int width, height;
	protected Handler handler;
	protected Rectangle bounds;
	protected Rectangle crop;
	protected int health;

	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.health = DEFAULT_HEALTH;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(0, 0, width, height);
		crop = new Rectangle(0, 0, 0, 0);
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public void showHealth(Graphics g) {
		g.setFont(new Font("Comic Sans MS", 500, 10));
		g.setColor(Color.WHITE);
		g.drawString("" + health, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()));
	}

	public boolean checkEntityCollision(float xOffset, float yOffset) {
		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this)) {
				continue;
			}
			if (e.closeToCollision(0f, 0f).intersects(closeToCollision(xOffset, yOffset))) {
				return true;
			}
		}
		return false;
	}

	public Rectangle closeToCollision(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width,
				bounds.height);
	}

	public Rectangle getCollisonBounds() {
		return new Rectangle((int) (x - handler.getGameCamera().getxOffset()) + bounds.x,
				(int) (y - handler.getGameCamera().getyOffset() + bounds.y), bounds.width, bounds.height);
	}

	public Rectangle getInteractionBounds() {
		return new Rectangle((int) (x + crop.x - handler.getGameCamera().getxOffset()),
				(int) (y + crop.y - handler.getGameCamera().getyOffset()), width - crop.width, height - crop.height);
	}

	public void showInteractionBound(Graphics g) {
		g.setColor(Color.cyan);
		g.drawRect((int) (x + crop.x - handler.getGameCamera().getxOffset()),
				(int) (y + crop.y - handler.getGameCamera().getyOffset()), width - crop.width, height - crop.height);
	}

	public Rectangle getRangeBounds() {
		return new Rectangle((int) (x - width - handler.getGameCamera().getxOffset()),
				(int) (y - width - handler.getGameCamera().getyOffset()), width * 3, height * 3);
	}

	public void showRangeBound(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect((int) (x - width - handler.getGameCamera().getxOffset()),
				(int) (y - width - handler.getGameCamera().getyOffset()), width * 3, height * 3);
	}
//	public Rectangle getRangeBounds() {
//		return new Rectangle((int) (x - width / 2 - handler.getGameCamera().getxOffset()),
//				(int) (y - width / 2 - handler.getGameCamera().getyOffset()), width * 2, height * 2);
//	}
//	
//	public void showRangeBound(Graphics g) {
//		g.setColor(Color.BLACK);
//		g.drawRect((int) (x - width / 2 - handler.getGameCamera().getxOffset()),
//				(int) (y - width / 2 - handler.getGameCamera().getyOffset()), width * 2, height * 2);
//	}

	public void showCollisionBound(Graphics g) {
//		g.setColor(Color.WHITE);
//		g.drawRect((int) (x - handler.getGameCamera().getxOffset()) + bounds.x,
//				(int) (y - handler.getGameCamera().getyOffset() + bounds.y), bounds.width, bounds.height);
	}

	public boolean isStatic() {
		return false;
	}

	public boolean containsItem() {
		return false;
	}

	public boolean isEnemy() {
		return false;
	}

	public boolean isConsumable() {
		return false;
	}

	public String staticEntityType() {
		return "";
	}

//	 Getters and Setters

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
