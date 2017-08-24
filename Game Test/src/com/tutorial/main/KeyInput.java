package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.tutorial.main.Game;
import com.tutorial.main.Game.STATE;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	private Game.STATE gameState;
	
	
	public KeyInput(Handler handler){
		this.handler = handler;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			
			if(tempObject.getId() == ID.Player){
				//Key Events for Player1
				
				if(key == KeyEvent.VK_W) { tempObject.setVelY(-5); keyDown[0] = true; }
				if(key == KeyEvent.VK_S) { tempObject.setVelY(5); keyDown[1] = true; }
				if(key == KeyEvent.VK_A) { tempObject.setVelX(-5); keyDown[2] = true; }
				if(key == KeyEvent.VK_D) { tempObject.setVelX(5); keyDown[3] = true; }
				if(key == KeyEvent.VK_SPACE) { tempObject.setVelX(0); tempObject.setVelY(0); keyDown[4] = true; }
			}
			
			if(tempObject.getId() == ID.Player2){
				//Key Events for Player2
				//If you want Player2 to work properly, add "keyDown" functions
				
				if(key == KeyEvent.VK_UP) tempObject.setVelY(-5);
				if(key == KeyEvent.VK_DOWN) tempObject.setVelY(5);
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(-5);
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(5);
				if(key == KeyEvent.VK_NUMPAD0) { tempObject.setVelX(0); tempObject.setVelY(0);}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) gameState = STATE.Pause;
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				//Key Events for Player1 when key is released
				
				if(key == KeyEvent.VK_W) keyDown[0] = false; //tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) keyDown[1] = false; //tempObject.setVelY(0);
				if(key == KeyEvent.VK_A) keyDown[2] = false; //tempObject.setVelX(0);
				if(key == KeyEvent.VK_D) keyDown[3] = false; //tempObject.setVelX(0);
				
				//Vertical Movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				//Horizontal Movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
			}
			
			if(tempObject.getId() == ID.Player2){
				//Key Events for Player2 when key is released
				
				if(key == KeyEvent.VK_UP) keyDown[0] = false; //tempObject.setVelY(0);
				if(key == KeyEvent.VK_DOWN) keyDown[1] = false; //tempObject.setVelY(0);
				if(key == KeyEvent.VK_LEFT) keyDown[2] = false; //tempObject.setVelX(0);
				if(key == KeyEvent.VK_RIGHT) keyDown[3] = false; //tempObject.setVelX(0);
			}
		}
	}
}