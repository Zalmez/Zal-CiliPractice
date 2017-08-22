package com.tutorial.main;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick(){
		scoreKeep++;
		
		if(scoreKeep >= 300){
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			
			if(hud.getLevel() == 2){
				handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
			}else if(hud.getLevel() == 3){
				handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
				handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
			}else if(hud.getLevel() == 4){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
			}else if(hud.getLevel() == 5){
				handler.addObject(new SeekerEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SeekerEnemy, handler));
			}else if(hud.getLevel() == 8){
				handler.addObject(new SeekerEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT), ID.SeekerEnemy, handler));
			}else if(hud.getLevel() == 10){
				handler.clearEnemies();
				handler.addObject(new EnemyBoss(272, -100, ID.EnemyBoss, handler));
			}
		}
		
	}

}
