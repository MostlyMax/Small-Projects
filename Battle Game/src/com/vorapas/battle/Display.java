package com.vorapas.battle;

import javax.swing.JFrame;

public class Display {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "Test Alpha 0.01";

	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		Game game = new Game(frame);
		
		frame.add(game);
		frame.addKeyListener(game);
		frame.setTitle(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
	
		System.out.println("Running...");	
		game.initGame();
	}
}
