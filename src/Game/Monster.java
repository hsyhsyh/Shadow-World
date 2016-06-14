package Game;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class Monster extends AbstractCharacter implements Runnable{
	
	private Bullet bullets[]=new Bullet[20];
	public int waitAttackTime=0;
	private int bulletNumberLeft=0;
	private int bulletNumberRight=1;
	private boolean isboom = false;
	private boolean isAlive = true;
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
		for(int i=0;i<20;i+=2){
			bullets[i]= new Bullet(gs.fireBall1,10000,10000,0);
		}
		for(int i=1;i<20;i+=2){
			bullets[i]= new Bullet(gs.fireBall2,10000,10000,0);
		}
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

		if(this.x<this.leftBound)
			direction="right";
		else if(this.x>this.rightBound)
			direction="left";

		move(direction);
			
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		Random random=new Random();
		int a=random.nextInt(2);
		if(a==0)
			bulletDirection="right";
		else if(a==1)
			bulletDirection="left";
		if(bulletDirection.equals("right")){
			bullets[bulletNumberRight%20].x=this.x+40;
			bullets[bulletNumberRight%20].y=this.y+50;
			bullets[bulletNumberRight%20].velocity=20;
			bulletNumberRight+=2;
		}
		else if(bulletDirection.equals("left")){
			bullets[bulletNumberLeft%20].x=this.x-12;
			bullets[bulletNumberLeft%20].y=this.y+50;
			bullets[bulletNumberLeft%20].velocity=-20;
			bulletNumberLeft+=2;
		}
		
		
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
	
	public void CharacterBeAttacked(Character ch) {
		if(ch.isMonsterTouch == false)
			if(ch.x<this.x+this.chaImage.width && ch.x+ch.chaImage.width>this.x && ch.y<this.y+this.chaImage.height && ch.y+ch.chaImage.height>this.y){
				gs.hurt();
				if(!ch.isForDemo)
					ch.now_HP -=5;
				ch.isMonsterTouch = true;
			}
		

		for(Bullet bullet: bullets){
			if(bullet.x>=ch.x && bullet.x<=ch.x+ch.chaImage.width && bullet.y>=ch.y+5 && bullet.y<=ch.y+ch.chaImage.height-5){
				bullet.vanish();
				gs.hurt();
				if(!ch.isForDemo)
					ch.now_HP -=5;
				}
			}
		if(ch.now_HP < 0)
			ch.now_HP = 0;
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
		this.isAlive=false;
		for(int i=0;i<20;i++){
			bullets[i].x=10000;
			bullets[i].y=10000;
			bullets[i].velocity=0;
		}
	}
	
	public Bullet[] getBullet(){
		return bullets;
	}
	
	int i = 0,j = 0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				RandomMove();
				if(gs.getCharacter().now_HP > 0){
					beAttacked(gs.getCharacter());
					CharacterBeAttacked(gs.getCharacter());
				}
				if(waitAttackTime>0)
					waitAttackTime--;
				if(waitAttackTime==0){
					this.canAttack=true;
				}
				
				if(isAlive){
					if(this.isDead()){
						gs.getCharacter().experienceValue++;
						vanish();
					}
				}
				if(this.isboom == true) i ++;
				if(i == 25) {
					i = 0;
					this.chaImage=gs.monster;
					this.isboom = false;
				}
				if(this.canAttack){
					this.attack();
					Random random=new Random();
					int a=random.nextInt(100);
					this.waitAttackTime=200+a;
					this.canAttack = false;
				}
				if(gs.getCharacter().isMonsterTouch == true) j ++;
				if(j == 160) {
					j = 0;
					gs.getCharacter().isMonsterTouch = false;
				}
				move();
				for(Bullet bullet: bullets){
					bullet.move();
				}
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
