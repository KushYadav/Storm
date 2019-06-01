package com.example.hp.storm.states;

import java.awt.Graphics;

import com.example.hp.storm.Game;
import com.example.hp.storm.Handler;

public abstract class State {

	protected Handler handler;

	public State(Handler handler) {
		this.handler = handler;
	}

	public abstract void tick();

	public abstract void render(Graphics g);
}
