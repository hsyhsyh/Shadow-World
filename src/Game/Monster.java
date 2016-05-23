package Game;

import java.util.Random;

import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PImage;

public class Monster extends AbstractCharacter implements Runnable{
	
	public Monster(PApplet parent, PImage chaImage, String name, float x, float y , int HP, GameStage gs,Map map){
		this.gs = gs;
		this.map = map;
		Thread ms = new Thread(this);
		ms.start();
		this.x=x;
		this.y=y;
		this.name=name;
		this.parent=parent;
		this.MAX_HP=HP;
		this.now_HP=HP;
		this.chaImage=chaImage;
		Ani.init(parent);
	}


	@Override
	public void move() {
		// TODO Auto-generated method stub
		Ani.to(this,1,"x",x+velocityForDirectionX);
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
		if(a%100<40)
			direction="left";
		else if(a%100>=59)
			direction="right";
		else 
			direction="stop";
		move(direction);
			
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		while(true) {
			try {
				RandomMove();
				if((direction.equals("left")||direction.equals("right"))&& map.BoundFor(direction , this))
					move();
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
