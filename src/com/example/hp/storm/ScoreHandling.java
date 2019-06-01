package com.example.hp.storm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.example.hp.storm.utils.Utils;

public class ScoreHandling {
	public static int score = 0;
	public static int highScore;
	public static int health = 100;
	private Handler handler;

	public ScoreHandling(Handler handler) {
		this.handler = handler;
//		try {
//			BufferedReader br = new BufferedReader(new FileReader("res/data/temp.txt"));
//			String str=br.readLine();
//			int phs = Integer.parseInt(br.readLine());
//			highScore = phs;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		highScore = HighScoreState.highScore;
	}

	public void tick() {

	}

	public static void setHighScore() {
		BufferedReader br;
//		try {
//			br = new BufferedReader(new FileReader("res/data/temp.txt"));
//			int phs = Integer.parseInt(br.readLine());
//			if (ScoreHandling.score > phs) {
//				Utils.writeStringInFile(String.valueOf(ScoreHandling.score), "res/scoreHandling/HighestScore.txt");
//			}
//			Utils.writeStringInFile("Hello", "res/data/temp.txt");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public void render(Graphics g) {
		g.setFont(new Font("Comic Sans MS", 500, 20));
		g.setColor(Color.decode("#FFFFFF"));
		g.drawString("Health: " + handler.getPlayer().getHealth(), 20, 25);
		g.drawString("Score: " + score, 1050, 25);
	}

}
