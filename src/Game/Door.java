package Game;

import processing.core.PImage;

public class Door {
	
	public int x,y, goalX, goalY;
	private PImage image;
	private boolean isOpen;
	private int goal;
	public Door(int x, int y, PImage image, int goal, int goalX, int goalY){
		this.x=x;
		this.y=y;
		this.image=image;
		this.goal = goal;
		this.goalX = goalX;
		this.goalY = goalY;
	}
	
	public int getgoal()
	{
		return goal;
	}
	
    public PImage getImage(){
    	return this.image;
    }

}
