package Game;

import processing.core.PApplet;
import processing.core.PImage;

public class Box extends AbstractItem{

	private boolean isspecial;
	public Box(PImage image ,int x, int y, GameStage parent, String[] text1, String[] text2, boolean special)
	{
		this.image = image;
		this.x = x;
		this.y = y;
		width = image.width * 2;
		height = image.height * 2;
		this.parent = parent;
		this.text1 = text1;
		this.text2 = text2;
		isspecial = special;
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		parent.image(image, x + image.width/2, y);
		parent.image(image, x, y + image.height);
		parent.image(image, x + image.width, y + image.height);
	}

	@Override
	public String[] dialog_event(boolean switchs) {
		// TODO Auto-generated method stub
		if(switchs && isspecial)
		{
			parent.goalX = 847;
			parent.goalY = 93;
			parent.transport(6);
		}
		return text1;
	}

}