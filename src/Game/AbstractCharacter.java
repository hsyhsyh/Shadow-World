package Game;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class AbstractCharacter {
	
	public int MAX_HP, now_HP;
	protected String name;
	public float x,y;
	protected float velocityForDirectionX=0; //unit: unit_length/second
	protected float velocityForDirectionY=0;
	protected float gravity=5;
	protected ArrayList<Floor> floors;
	protected ArrayList<Ladder> ladders;
	public boolean isWalk=false;
	public boolean isGround=false;
	public boolean isAttack=false;
	public boolean canAttack=true; //make sure character not continuously attack without waiting time
	protected PImage chaImage;
	protected PApplet parent;
	protected GameStage gs;
	public String direction = "";
	public String bulletDirection = "right";
	public String UpDown = "";
	abstract public void move();
	abstract public void move(String direction);
	abstract public void attack();
	
    public PImage getImage(){
    	return this.chaImage;
    }
    

}
