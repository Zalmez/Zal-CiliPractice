/*NOTE: Fienden kolliderer ikke ordentlig med veggene. Idk what's wrong FeelsBadMan
 * 
 */

package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject{
	
	private Handler handler;
	Random r = new Random();
	
	private int timer = 80;
	private int timer2 = 50;
	
	public EnemyBoss(int x, int y, ID id, Handler handler){
		super(x, y, id);
		
		this.handler = handler;
		
		
		velX = 0;
		velY = 2;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void tick(){
		x += velX;
		y += velY;
		
		if(timer <= 0) velY = 0;
		else timer--;
		
		if(timer <= 0) timer2--;
		if(timer2 <= 0)
		{
			if(velX == 0) velX = 2;
			
			if(velX > 0)
			velX += 0.01f;
			else if(velX < 0)
			velX -= 0.01f;
			
			velX = Game.clamp(velX, -10, 10);
			
			int spawn = r.nextInt(10);
			if(spawn == 0) handler.addObject(new EnemyBossBullet((int)x, (int)y, ID.Enemy, handler));
		}
		
		if(x <= 0 || x >= Game.HEIGHT - 64) velX *= -1;
		//if(y <= 0 || y >= Game.WIDTH - 16) velY *= -1;
		
		handler.addObject(new BasicTrail((int)x, (int)y, ID.BasicTrail, Color.WHITE, 64, 64, 0.06f, handler));
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, 16, 16);
	}
		
	

}