package com.example.hp.storm.gfx;

import com.example.hp.storm.Game;
import com.example.hp.storm.Handler;
import com.example.hp.storm.entities.Entity;
import com.example.hp.storm.tiles.Tile;

public class GameCamera {

	private Handler handler;
	private float xOffset, yOffset;

	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpace();
	}

	public void checkBlankSpace() {
		if (xOffset < 0) {
			xOffset = 0;
		} else if (xOffset > handler.getWorld().getwWidth() * Tile.TILE_WIDTH - handler.getWidth()) {
			xOffset = handler.getWorld().getwWidth() * Tile.TILE_WIDTH - handler.getWidth();
		}
		if (yOffset <= 0) {
			yOffset = 0;
		}
		if (yOffset > handler.getWorld().getwHeight() * Tile.TILE_HEIGHT - handler.getHeight()) {
			yOffset = handler.getWorld().getwHeight() * Tile.TILE_HEIGHT - handler.getHeight();
		}

	}

	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
	}

	// Getters and Setters

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}
