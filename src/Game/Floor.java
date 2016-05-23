package Game;

import processing.core.PImage;

public class Floor {
	
	public float x,y;
	private PImage image;
	
	public Floor(float x, float y, PImage image){
		this.x=x;
		this.y=y;
		this.image=image;
	}

}
