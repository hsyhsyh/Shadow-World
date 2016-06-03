package Game;

import processing.core.PImage;

public class Bed extends AbstractItem{

	public Bed(PImage image ,int x, int y, GameStage parent, String[] text1, String[] text2)
	{
		this.image = image;
		this.x = x;
		this.y = y;
		width = image.width;
		height = image.height;
		this.parent = parent;
		this.text1 = text1;
		this.text2 = text2;
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		parent.image(image,x,y);
	}

	@Override
	public String[] dialog_event() {
		// TODO Auto-generated method stub
		return text1;
	}

}
