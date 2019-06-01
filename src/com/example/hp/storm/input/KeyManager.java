package com.example.hp.storm.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys, justPressed, cantPressed;
	public static boolean up, down, left, right, space, F;

	public KeyManager() {
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPressed = new boolean[keys.length];
	}

	public void tick() {
		for (int i = 0; i < keys.length; i++) {
			if (cantPressed[i] && !keys[i]) {
				cantPressed[i] = false;
			} else if (justPressed[i]) {
				cantPressed[i] = true;
				justPressed[i] = false;
			}
			if (!cantPressed[i] && keys[i]) {
				justPressed[i] = true;
			}
		}

		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		space = keys[KeyEvent.VK_SPACE];
		F = keys[KeyEvent.VK_F];
	}

	public boolean keyJustPressed(int vkF) {
		if (justPressed[vkF]) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
			return;
		}
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
			return;
		}
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
