package Game;

import java.io.IOException;

import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PImage;

public class Character implements Runnable{
	
	private int MAX_HP,now_HP;
	private String name;
	public float x,y;
	private float velocityForDirectionX=20; //unit: unit_length/second
	private float velocityForDirectionY=0;
	private float gravity=2;
	private PImage chaImage;
	private PApplet parent;
	GameStage gs;
	public String direction = "";
	
	public Character(PApplet parent, PImage chaImage, String name, float x, float y , int HP, GameStage gs){
		this.gs = gs;
		Thread ch = new Thread(this);
		ch.start();
		this.x=x;
		this.y=y;
		this.name=name;
		this.parent=parent;
		this.MAX_HP=HP;
		this.now_HP=HP;
		this.chaImage=chaImage;
		Ani.init(parent);
	}
	
	//the effect of gravity that make character falldown
    public void fallDown(){
    	if(this.y<320)
    		Ani.to(this,1,"velocityForDirectionY",velocityForDirectionY-gravity);
    	else if(this.y>=320 && velocityForDirectionY<0)
    	{   this.y=320;
    		velocityForDirectionY=0;
    	}
    		
    	//because the coordinate y become small when "move up", if velocityForDirectionY>0, y-velocityForDirectionY is "move up"
    	Ani.to(this,1,"y",y-velocityForDirectionY);
    	
	}
	
    public void move(String direction){
		if(direction.equals("left")){
			Ani.to(this,1,"x",x-velocityForDirectionX);
		}
		else if(direction.equals("right")){
			Ani.to(this,1,"x",x+velocityForDirectionX);
		}
	}
	
	public void jump(){
		velocityForDirectionY += 10;
	}
	
    public void attack(){
		
	}
    
    public PImage getImage(){
    	return this.chaImage;
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 1;
		while(true) {
			
			try {
				fallDown();
				Thread.sleep(100);
				if(direction.equals("right")) {
					if(i%4 == 0)
						chaImage = gs.getImage(gs.man1);
					if(i%4 == 1)
						chaImage = gs.getImage(gs.man2);
					if(i%4 == 2)
						chaImage = gs.getImage(gs.man3);
					if(i%4 == 3)
						chaImage = gs.getImage(gs.man4);
				}
				if(direction.equals("left")) {
					if(i%4 == 0)
						chaImage = gs.getImage(gs.man5);
					if(i%4 == 1)
						chaImage = gs.getImage(gs.man6);
					if(i%4 == 2)
						chaImage = gs.getImage(gs.man7);
					if(i%4 == 3)
						chaImage = gs.getImage(gs.man8);
				}
				//System.out.println(i);
				i ++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
