package Game;

import processing.core.PImage;

public class Door {
	
	public int x,y, goalX, goalY;
	private PImage image;
	private int goal;
	private boolean isopen;
	public Door(int x, int y, PImage image, int goal, int goalX, int goalY, boolean open){
		this.x=x;
		this.y=y;
		this.image=image;
		this.goal = goal;
		this.goalX = goalX;
		this.goalY = goalY;
		isopen = open;
	}
	
	public int getgoal()
	{
		return goal;
	}
	
    public PImage getImage(){
    	return this.image;
    }

    public boolean isopen()
    {
    	return isopen;
    }
}
