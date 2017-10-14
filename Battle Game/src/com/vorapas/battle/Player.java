package com.vorapas.battle;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	private int x;
	private int y;
	
	private int speedX;
	private int speedY;
	
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	
	public Player() {
		x = (Display.WIDTH-25)/2;
		y = (Display.HEIGHT-25)/2;
		
	}

	public void drawPlayer(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 25, 25);
		
	}

	public void move() {
		//up
		if (up&&speedY<5) speedY+=1;
		else if (!up&&speedY>0) speedY-=1;
		//down
		if (down&&speedY>-5) speedY-=1;
		else if (!down&&speedY<0) speedY+=1;
		//right
		if (right&&speedX<5) speedX+=1;
		else if (!right&&speedX>0) speedX-=1;
		//left
		if (left&&speedX>-5) speedX-=1;
		else if (!left&&speedX<0) speedX+=1;
		
		System.out.println(speedY);
		System.out.println(speedX);
		
		y+=-1*speedY;
		x+=speedX;
	}

}
