package Game;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class AbstractCharacter {
	
	protected int MAX_HP,now_HP;
	protected String name;
	public float x,y;
	protected float velocityForDirectionX=0; //unit: unit_length/second
	protected float velocityForDirectionY=0;
	protected float gravity=5;
	protected ArrayList<Floor> floors;
	public boolean isWalk=false;
	public boolean isGround=false;
	protected PImage chaImage;
	protected PApplet parent;
	protected GameStage gs;
	public String direction = "";
	public String UpDown = "";
	abstract public void move();
	abstract public void move(String direction);
	abstract public void attack();
	
    public PImage getImage(){
    	return this.chaImage;
    }
    

}
