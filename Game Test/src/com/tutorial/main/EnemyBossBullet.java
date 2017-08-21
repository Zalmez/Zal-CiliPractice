/*NOTE: Fienden kolliderer ikke ordentlig med veggene. Idk what's wrong FeelsBadMan
 * 
 */

package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject{
	
	private Handler handler;
	
	public EnemyBossBullet(int x, int y, ID id, Handler handler){
		super(x, y, id);
		
		this.handler = handler;
		Random r = new Random();
		
		velX = (r.nextInt(5 - -5) + -5);
		velY = 5;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void tick(){
		x += velX;
		y += velY;
		
		//if(x <= 0 || x >= Game.HEIGHT - 64) velX *= -1;
		//if(y <= 0 || y >= Game.WIDTH - 16) velY *= -1;
		
		if(y >= Game.HEIGHT) handler.removeObject(this);
		
		handler.addObject(new BasicTrail((int)x, (int)y, ID.BasicTrail, Color.red, 16, 16, 0.06f, handler));
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 16, 16);
	}
		
	

}
