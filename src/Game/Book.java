package Game;

import processing.core.PApplet;
import processing.core.PImage;

public class Book extends AbstractItem{

	private boolean isspecial, isdone;
	private String[] text3;
	public Book(PImage image ,int x, int y, GameStage parent, String[] text1, String[] text2, String[] text3, boolean special)
	{
		this.image = image;
		this.x = x;
		this.y = y;
		width = image.width * 2;
		height = image.height * 2;
		this.parent = parent;
		this.text1 = text1;
		this.text2 = text2;
		this.text3 = text3;
		isspecial = special;
		isdone = false;
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		parent.image(image, x, y, 150, 150);
//		parent.image(image, x + image.width/2, y);
//		parent.image(image, x, y + image.height);
//		parent.image(image, x + image.width, y + image.height);
	}

	@Override
	public String[] dialog_event(boolean switchs) {
		// TODO Auto-generated method stub
		if(switchs && isspecial && !isdone)
		{
			parent.stage_5_floor = true;
			isdone = true;
			return text2;
		}
		else if(switchs && isspecial)
		{
			return text3;
		}
		return text1;
	}

}