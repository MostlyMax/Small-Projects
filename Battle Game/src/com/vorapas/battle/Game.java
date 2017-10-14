package com.vorapas.battle;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Game extends JComponent implements KeyListener {
	Player player;
	JFrame f;
	static boolean running = true;
	
	public Game(JFrame f) {
		this.f = f;
	}
	
	public void gameLoop() {
		while(running) {
			gameIO();
			repaint();
			
			try {Thread.sleep(10);} 
			catch (InterruptedException e) {e.printStackTrace();}
		}
	}

	public void initGame() {
		Player player = new Player();
		this.player = player;
		
		repaint();
		gameLoop();
		
		
	}
	
	public void paintComponent(Graphics g) {
		player.drawPlayer(g);
	}
	
	public void gameIO() {
		player.move();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			player.up = true;
			break;
		case KeyEvent.VK_DOWN:
			player.down = true;
			break;
		case KeyEvent.VK_RIGHT:
			player.right = true;
			break;
		case KeyEvent.VK_LEFT:
			player.left = true;
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			player.up = false;
			break;
		case KeyEvent.VK_DOWN:
			player.down = false;
			break;
		case KeyEvent.VK_RIGHT:
			player.right = false;
			break;
		case KeyEvent.VK_LEFT:
			player.left = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
}
