package Game;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class Monster extends AbstractCharacter implements Runnable{
	
	private boolean isboom = false;
	private int leftBound, rightBound;
	
	public Monster(PApplet parent, PImage chaImage, String name, float x, float y , int HP, GameStage gs, int leftBound, int rightBound){
		
		
		this.gs = gs;
		Thread ms = new Thread(this);
		ms.start();
		this.x=x;
		this.y=y;
		this.name=name;
		this.parent=parent;
		this.MAX_HP=HP;
		this.now_HP=HP;
		this.chaImage=chaImage;
		this.leftBound = leftBound;
		this.rightBound = rightBound;
		this.direction="right";
	}


	@Override
	public void move() {
		// TODO Auto-generated method stub
		x+=velocityForDirectionX/20;
	}

	@Override
	public void move(String direction) {
		// TODO Auto-generated method stub
		
		if(direction.equals("left")){
			velocityForDirectionX=-15;
		}
		else if(direction.equals("right")){
			velocityForDirectionX=15;
		}
		else if(direction.equals("stop")){
			velocityForDirectionX=0;
		}
	}
	
	private void RandomMove(){
		Random random=new Random();
		int a=random.nextInt(100);
		if(this.x<this.leftBound)
			direction="right";
		else if(this.x>this.rightBound)
			direction="left";
			
		move(direction);
			
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void beAttacked(Character ch) {
		
			for(Bullet bullet: ch.getBullet()){
					if(bullet.x>=this.x+20 && bullet.x<=this.x+this.chaImage.width-20 && bullet.y>=this.y+10 && bullet.y<=this.y+this.chaImage.height-10){
						bullet.vanish();
						isBoomed();
						this.now_HP-=20;
					}
			}
	}
	
	public boolean isDead() {
		
		if(this.now_HP<=0)
			return true;
		else 
			return false;
	}
	
	public void vanish(){
		this.x=10000;
		this.y=10000;
	}
	
	int i = 0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				RandomMove();
				beAttacked(gs.getCharacter());
				if(this.isDead())
					vanish();
				if(this.isboom == true) i ++;
				if(i == 25) {
					i = 0;
					this.chaImage=gs.monster;
					this.isboom = false;
				}
				move();
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void isBoomed() {
		this.chaImage=gs.monster2;
		this.isboom = true;
	}

}
