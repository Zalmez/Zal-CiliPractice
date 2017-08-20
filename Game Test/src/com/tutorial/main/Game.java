package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
	
	/**
	 * I have no idea what this is.
	 * Explanation: noen scripts trenger en "id" for � fungere/kommunisere
	 */
	private static final long serialVersionUID = 1550691097823471818L;
	
	//This is the variables we will use to set screen size.
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	
	
	public Game(){
		new Window(WIDTH, HEIGHT, "Ree's Realm", this);
		
		handler = new Handler();
		
		handler.addObject(new Player(100, 100, ID.Player));
		handler.addObject(new Player(200, 200, ID.Player));
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();	
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// This is the Game Loop
	public void run(){
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				//update here
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick(){
		handler.tick();
	}
	
	
	private void render(){
		BufferStrategy bs= this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	
	public static void main(String[] args){
		new Game();
	}
	

}