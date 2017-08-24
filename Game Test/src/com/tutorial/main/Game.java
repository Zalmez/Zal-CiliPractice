package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	/**
	 * Some scripts require an ID to communicate / work.
	 */
	private static final long serialVersionUID = 1550691097823471818L;
	
	//These are the variables we will use to set the size of the screen.
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	
	public enum STATE{
		Menu,
		Help,
		Game,
		Pause,
		Death;
	}
	
	public STATE gameState = STATE.Menu;
	
	
	public Game(){
		
		handler = new Handler();
		menu = new Menu(this, handler);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		new Window(WIDTH, HEIGHT, "Demonica", this);
		
		hud = new HUD();
		spawner = new Spawn(handler, hud);
		menu = new Menu(this, handler);
		r = new Random();
		
		
		if(gameState == STATE.Game){
			//handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
			//handler.addObject(new SeekerEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SeekerEnemy, handler));
		}
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
		if(gameState == STATE.Game){
			hud.tick();
			spawner.tick();
		}else if(gameState == STATE.Menu){
			menu.tick();
		}
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
		
		if(gameState == STATE.Game){
			hud.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.Help){
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
		
	}
	
	public static float clamp(float var, float min, float max){
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	
	public static void main(String[] args){
		new Game();
	}

}
