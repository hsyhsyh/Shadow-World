package Game;

import processing.core.PImage;

public class Door {
	
	public float x,y;
	private PImage image;
	private boolean isOpen;
	
	public Door(float x, float y, PImage image){
		this.x=x;
		this.y=y;
		this.image=image;
	}
	
    public PImage getImage(){
    	return this.image;
    }

}
