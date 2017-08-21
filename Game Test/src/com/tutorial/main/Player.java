package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {
	
	Random r = new Random();
	Handler handler;
	
	

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, 32, 32);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 36);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		
		handler.addObject(new BasicTrail(x, y, ID.BasicTrail, Color.cyan, 32, 32, 0.2f, handler));
		
		collision();
		
	}
	
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Enemy){	//tempObject is now Enemy
				if(getBounds().intersects(tempObject.getBounds())){
					//Collision Code
					HUD.HEALTH -= 2;
				}
			}
		}
	}
	
	

	public void render(Graphics g) {
		
		if(id == ID.Player){g.setColor(Color.cyan);}
		else if(id == ID.Player2){g.setColor(Color.MAGENTA);}
		g.fillRect(x, y, 32, 32);
		
	}

}
