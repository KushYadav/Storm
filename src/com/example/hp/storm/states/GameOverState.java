package com.example.hp.storm.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;

import com.example.hp.storm.Handler;
import com.example.hp.storm.ScoreHandling;

public class GameOverState extends State {

	private ScoreHandling sh;
	private JButton b;

	public GameOverState(Handler handler) {
		super(handler);
		sh = new ScoreHandling(handler);
		ScoreHandling.setHighScore();
		b = new JButton("Main Menu");
	}

	@Override
	public void tick() {
		b.setBounds(50, 50, 1000, 1000);
		handler.getFrame().add(b);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.clearRect(0, 0, handler.getWidth(), handler.getHeight());

		g.setColor(Color.decode("#009688"));
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
//			
		g.setFont(new Font("Comic Sans MS", 100, 80));
		g.setColor(Color.decode("#34485E"));
		g.drawString("GameOVER!", 400, 350);
//
//		g.setColor(Color.decode("#FFFF00"));
//		g.setFont(new Font("Comic Sans MS", 1000, 20));
//		g.drawString("Score: " + sh.score, 550, 400);
//

	}

}
