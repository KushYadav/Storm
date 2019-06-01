package com.example.hp.storm.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.example.hp.storm.Handler;

public class GameCompleteState extends State {

	public GameCompleteState(Handler handler) {
		super(handler);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.clearRect(0, 0, handler.getWidth(), handler.getHeight());

		g.setColor(Color.decode("#009688"));
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());

		g.setFont(new Font("Comic Sans MS", 100, 80));
		g.setColor(Color.decode("#34485E"));
		g.drawString("GameCompleted!", 300, 350);
	}

}
