package Game;

import java.util.ArrayList;

import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PImage;

public class Character extends AbstractCharacter implements Runnable{
	
	public Character(PApplet parent, PImage chaImage, String name, float x, float y , int HP, GameStage gs){
        Ani.init(parent);
		floors = new ArrayList<Floor>();
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
	}
	
	//the effect of gravity that make character fall down
    public void fallDown(){
    	isGround=false;
    	for(Floor floor : floors){
    		if(floor.IsOnGround(this))
    		{
    			isGround=true;
    			break;
    		}
    	}
    	for(Floor floor : floors){
    		if( floor.IsCeiling(this)&&velocityForDirectionY>0 ) 
    		{
    			velocityForDirectionY = 0;
    		}
    		else if(floor.IsGround(this) && velocityForDirectionY<0)
    		{
    			velocityForDirectionY=0;
    		}
    	}
       if(!isGround && velocityForDirectionY>=-15)
    	   velocityForDirectionY-=gravity/20;
    		
    	//because the coordinate y become small when "move up", if velocityForDirectionY>0, y-velocityForDirectionY is "move up"

       y-=velocityForDirectionY/20;
    	
	}
	
    //always character move, stop when velocityForDirectionX=0, 
    @Override
    public void move(){
    	
    	x+=velocityForDirectionX/20;

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
		if(velocityForDirectionY==0)
			velocityForDirectionY += 35;
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
				if(this.velocityForDirectionY>0)
					UpDown="Up";
				else if(this.velocityForDirectionY<0)
					UpDown="Down";
				if(direction.equals("left")||direction.equals("right")){
					int moving=1;
					for(Floor floor : floors){
						if(floor.IsFloor(this))
							moving=0;
						}
					if(moving==1)
						move();
				}
				fallDown();
				Thread.sleep(5);
				if(direction.equals("right") && this.isWalk) {
					if(i%80 == 0)
						chaImage = gs.getImage(gs.man1);
					if(i%80 == 20)
						chaImage = gs.getImage(gs.man2);
					if(i%80 == 40)
						chaImage = gs.getImage(gs.man3);
					if(i%80 == 60)
						chaImage = gs.getImage(gs.man4);
				}
				else if(direction.equals("left") && this.isWalk) {
					if(i%80 == 0)
						chaImage = gs.getImage(gs.man5);
					if(i%80 == 20)
						chaImage = gs.getImage(gs.man6);
					if(i%80 == 40)
						chaImage = gs.getImage(gs.man7);
					if(i%80 == 60)
						chaImage = gs.getImage(gs.man8);
				}
				else 
				{
					for(Floor floor : floors){
						if(floor.IsGround(this)){
							chaImage = gs.getImage(gs.man);
							break;
						}
					}
				}	
				//System.out.println(i);
				i ++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addFloor(ArrayList<Floor> floors)
	{
		this.floors = floors;
	}
	
	public void deleteFloor()
	{
		floors.clear();
	}

}
