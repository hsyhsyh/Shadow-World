package Game;

import processing.core.PImage;

public class Door {
	
	public float x,y;
	private PImage image;
	private boolean isOpen;
	private int goal;
	public Door(float x, float y, PImage image, int goal){
		this.x=x;
		this.y=y;
		this.image=image;
		this.goal = goal;
	}
	
	public int getgoal()
	{
		return goal;
	}
	
    public PImage getImage(){
    	return this.image;
    }

}
