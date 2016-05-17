package Game;

import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PImage;

public class Character {
	
	private int MAX_HP,now_HP;
	private String name;
	public float x,y;
	private int velocityForDirectionX=20; //unit: unit_length/second
	private int velocityForDirectionY=0;
	private int gravity;
	private PImage chaImage;
	private PApplet parent;
	
	public Character(PApplet parent, PImage chaImage, String name, float x, float y , int HP){
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
		
	}
	
    public void attack(){
		
	}
    
    public PImage getImage(){
    	return this.chaImage;
    }
	

}
