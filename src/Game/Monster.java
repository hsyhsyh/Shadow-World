package Game;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class Monster extends AbstractCharacter implements Runnable{
	
	public Monster(PApplet parent, PImage chaImage, String name, float x, float y , int HP, GameStage gs){
		
		
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
			velocityForDirectionX=-20;
		}
		else if(direction.equals("right")){
			velocityForDirectionX=20;
		}
		else if(direction.equals("stop")){
			velocityForDirectionX=0;
		}
	}
	
	private void RandomMove(){
		Random random=new Random();
		int a=random.nextInt(100);
		if(direction.equals("left")){
			if(a%100<90)
				direction="left";
			else if(a%100>=94)
				direction="right";
			else 
				direction="stop";
		}
		else if(direction.equals("right")){
			if(a%100<90)
				direction="right";
			else if(a%100>=94)
				direction="left";
			else 
				direction="stop";
		}
		else{
			if(a%100<50)
				direction="left";
			else if(a%100>=50)
				direction="right";
		}

		move(direction);
			
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void beAttacked(Character ch) {
		
		if(!ch.getBullet().isEmpty())
			for(Bullet bullet: ch.getBullet()){
					if(bullet.x>=this.x+20 && bullet.x<=this.x+this.chaImage.width-20 && bullet.y>=this.y+10 && bullet.y<=this.y+this.chaImage.height-10){
						bullet.vanish();
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
	
	private void vanish(){
		this.x=10000;
		this.y=10000;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				RandomMove();
				beAttacked(gs.getCharacter());
				if(this.isDead())
					vanish();
				move();
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
