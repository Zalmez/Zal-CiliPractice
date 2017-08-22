/*NOTE: Fienden kolliderer ikke ordentlig med veggene. Idk what's wrong FeelsBadMan
 * 
 */

package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SeekerEnemy extends GameObject{
	
	private Handler handler;
	private GameObject player;
	
	public SeekerEnemy(int x, int y, ID id, Handler handler){
		super(x, y, id);
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++){
			if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
		}
		
		
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void tick(){
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float) Math.sqrt((x-player.getX()*(x-player.getX())) + (y-player.getY())* (y-player.getY())); //MATH REEEE
		
		velX = (float) ((-1.0/distance) * diffX);
		velY = (float) ((-1.0/distance) * diffY);
		
		
		//if(x <= 0 || x >= Game.HEIGHT - 64) velX *= -1;
		//if(y <= 0 || y >= Game.WIDTH - 16) velY *= -1;
		
		handler.addObject(new BasicTrail((int)x, (int)y, ID.BasicTrail, Color.GREEN, 16, 16, 0.06f, handler));
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, 16, 16);
	}
		
	

}
