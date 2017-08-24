package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
	}
	
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		//Play Button
		if(game.gameState == STATE.Menu && mouseOver(mx, my, 210, 180, 200, 64)){
			game.gameState = STATE.Game;
			handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
			handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));		
		}
		
		//Help Button
		if(game.gameState == STATE.Menu && mouseOver(mx, my, 210, 260, 200, 64)){
			game.gameState = STATE.Help;
		}
		
		//Back button for Help
		if(game.gameState == STATE.Help){
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				game.gameState = STATE.Menu;
				return;
			}
		}
		if(game.gameState == STATE.Menu){
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				System.exit(1);
			}
		}
		
		//PAUSE BUTTONS
		//Quit Button (Pause menu needs it's own quit button)
		if(game.gameState == STATE.Pause && mouseOver(mx, my, 210, 350, 200, 64)){
			System.exit(1);
		}
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else return false;
		}else return false;
	}
	
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		if(game.gameState == STATE.Menu){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt4 = new Font("arial", 1, 12);
			
			g.setFont(fnt);
			g.setColor(Color.RED);
			g.drawString("DEMONICA", 180, 70);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(210, 180, 200, 64);
			g.drawString("Play", 280, 220);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(210, 260, 200, 64);
			g.drawString("Help", 280, 300);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(210, 340, 200, 64);
			g.drawString("Quit", 280, 380);
			
			g.setFont(fnt4);
			g.setColor(Color.red);
			g.drawString("Alpha v.0.1", 280, 100);
			
			g.setFont(fnt4);
			g.setColor(Color.white);
			g.drawString("Made by:", 10, 410);
			g.drawString("Cilivarus & Zalmez", 10, 430);
			
			
		}else if(game.gameState == STATE.Help){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			Font fnt4 = new Font("arial", 1, 12);
			
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Help", 260, 70);
			
			g.setFont(fnt3);
			g.setColor(Color.WHITE);
			g.drawString("Dodge the incoming enemies!", 170, 120);
			g.drawString("Use the WASD keys to move around.", 140, 150);
			g.drawString("You can exit the game by pressing \"Escape\".", 120, 260);
			g.drawString("Thanks for playing!", 220, 300);
			
			g.setFont(fnt4);
			g.setColor(Color.white);
			g.drawString("Made by:", 10, 410);
			g.drawString("Cilivarus & Zalmez", 10, 430);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(210, 340, 200, 64);
			g.drawString("Back", 280, 380);
			
		}else if(game.gameState == STATE.Pause){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Pause", 240, 70);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(210, 180, 200, 64);
			g.drawString("Continue", 245, 220);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(210, 260, 200, 64);
			g.drawString("Help", 280, 300);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(210, 340, 200, 64);
			g.drawString("Quit", 280, 380);
		}
	}

}
