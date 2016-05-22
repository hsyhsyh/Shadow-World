package Game;

import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PImage;

public class Character extends AbstractCharacter implements Runnable{
	
	public Character(PApplet parent, PImage chaImage, String name, float x, float y , int HP, GameStage gs,Map map){
		this.gs = gs;
		this.map = map;
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
	
	//the effect of gravity that make character fall down
    public void fallDown(){
    	if(!this.map.IsGround(this))
    		Ani.to(this,1,"velocityForDirectionY",velocityForDirectionY-gravity);
    	else if(this.map.IsGround(this) && velocityForDirectionY<0)
    	{   this.map.setToGround(this);
    		velocityForDirectionY=0;
    	}
    		
    	//because the coordinate y become small when "move up", if velocityForDirectionY>0, y-velocityForDirectionY is "move up"
    	Ani.to(this,1,"y",y-velocityForDirectionY);
    	
	}
	
    //always character move, stop when velocityForDirectionX=0, 
    @Override
    public void move(){
			Ani.to(this,1,"x",x+velocityForDirectionX);
	}
    
    @Override
    public void move(String direction){
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
	
	public void jump(){
		if(velocityForDirectionY<20)
			velocityForDirectionY += 20;
	}
	
	@Override
    public void attack(){
		
	}
    

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 1;
		while(true) {
			
			try {
				move();
				fallDown();
				Thread.sleep(100);
				if(direction.equals("right") && this.isWalk) {
					if(i%4 == 0)
						chaImage = gs.getImage(gs.man1);
					if(i%4 == 1)
						chaImage = gs.getImage(gs.man2);
					if(i%4 == 2)
						chaImage = gs.getImage(gs.man3);
					if(i%4 == 3)
						chaImage = gs.getImage(gs.man4);
				}
				else if(direction.equals("left") && this.isWalk) {
					if(i%4 == 0)
						chaImage = gs.getImage(gs.man5);
					if(i%4 == 1)
						chaImage = gs.getImage(gs.man6);
					if(i%4 == 2)
						chaImage = gs.getImage(gs.man7);
					if(i%4 == 3)
						chaImage = gs.getImage(gs.man8);
				}
				else if(map.IsGround(this))
					chaImage = gs.getImage(gs.man);
				//System.out.println(i);
				i ++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
