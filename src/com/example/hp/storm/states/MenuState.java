package com.example.hp.storm.states;

import java.awt.Color;
import java.awt.Graphics;

import com.example.hp.storm.Handler;
import com.example.hp.storm.gfx.Assets;
import com.example.hp.storm.ui.ClickListener;
import com.example.hp.storm.ui.UIImageButton;
import com.example.hp.storm.ui.UIManager;

public class MenuState extends State {

	private UIManager uiManager;

	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUiManager(uiManager);
		uiManager.addObject(new UIImageButton(handler.getWidth() / 2 - 128, handler.getHeight() / 2 - 32, 256, 64,
				Assets.start, new ClickListener() {

					@Override
					public void onClick() {
						handler.getMouseManager().setUiManager(null);
						CurrentState.setCurrentState(handler.getGame().getGameState());
					}
				}));
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		g.clearRect(0, 0, handler.getWidth(), handler.getHeight());
		g.setColor(Color.decode("#47c0ff"));
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		uiManager.render(g);
	}

}
