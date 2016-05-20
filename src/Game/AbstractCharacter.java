package Game;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class AbstractCharacter {
	
	protected int MAX_HP,now_HP;
	protected String name;
	public float x,y;
	protected float velocityForDirectionX=0; //unit: unit_length/second
	protected float velocityForDirectionY=0;
	protected float gravity=5;
	protected Map map;
	public boolean isWalk=false;
	protected PImage chaImage;
	protected PApplet parent;
	protected GameStage gs;
	public String direction = "";
	abstract public void move();
	abstract public void move(String direction);
	abstract public void attack();
	
    public PImage getImage(){
    	return this.chaImage;
    }
    
    public Map getMap(){
    	return this.map;
    }

}
