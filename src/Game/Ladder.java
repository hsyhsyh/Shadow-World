package Game;

import processing.core.PApplet;
import processing.core.PImage;

public class Ladder {
	public int x, y, width, height;
	private int size;
	private PImage image;
	private PApplet parent;
//	private
	public Ladder(int x, int y, int size, PImage image, PApplet parent)
	{
		this.x = x;
		this.y = y;
		this.size = size;
		this.image = image;
		this.parent = parent;
		this.width = image.width;
		this.height = image.height * size;
	}
	
	public void display()
	{
		for(int i = 0; i < size; ++i)
		{
			parent.image(image,x,y + i * image.height);
		}
	}

	public PImage getImage()
	{
		return image;
	}
	
	public boolean isLadder(AbstractCharacter ch){
		if(ch.x<this.x+this.image.width && ch.x+ch.chaImage.width>this.x && ch.y<this.y+this.image.height && ch.y+ch.chaImage.height>this.y)
			return true;
		else 
			return false;
	}
	
	public void vanish(){
		this.x=10000;
		this.y=10000;
	}
}
