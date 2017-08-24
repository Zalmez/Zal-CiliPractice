package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{
	
	private Handler handler;
	
	public FastEnemy(int x, int y, ID id, Handler handler){
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 2;
		velY = 8;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void tick(){
		x += velX;
		y += velY;
		
		if(x <= 0 || x >= Game.HEIGHT - 64) velX *= -1;
		if(y <= 0 || y >= Game.WIDTH - 16) velY *= -1;
		
		handler.addObject(new BasicTrail(x, y, ID.BasicTrail, Color.MAGENTA, 16, 16, 0.06f, handler));
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.MAGENTA);
		g.fillRect((int)x, (int)y, 16, 16);
	}
		
	

}
