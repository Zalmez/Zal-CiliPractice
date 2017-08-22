package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	/**
	 * Some scripts needs an ID to communicate / work.
	 */
	private static final long serialVersionUID = 1550691097823471818L;
	
	//This is the variables we will use to set the size of the screen.
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Random rand;
	private Handler handler;
	private HUD hud;
	
	
	public Game(){
		
		new Window(WIDTH, HEIGHT, "Demonica", this);
		hud = new HUD();
		
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		rand = new Random();
		
		handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
		//handler.addObject(new Player(WIDTH/2 + 64, HEIGHT / 2 - 32, ID.Player2));
		//for(int i = 0; i < 8; i++)
		handler.addObject(new Enemy(rand.nextInt(WIDTH), rand.nextInt(HEIGHT), ID.Enemy, handler));
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
		this.requestFocus();	//This code focuses the game (You don't have to click the game to move)
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
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;		//Added frame limiter 'cus fuk 1k frames Kappa
			if(frames >= 120) frames = 120;
			
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
		hud.tick();
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
		hud.render(g);
		
		g.dispose();
		bs.show();
		
	}
	
	public static int clamp(int val, int min, int max){
		if(val >= max)
			return val = max;
		else if(val <= min)
			return val = min;
		else
			return val;
	}
	
	
	public static void main(String[] args){
		new Game();
	}

}
