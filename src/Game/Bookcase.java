package Game;

import processing.core.PApplet;
import processing.core.PImage;

public class Bookcase extends AbstractItem{

	public Bookcase(int x, int y, GameStage parent, String[] text1, String[] text2)
	{
//		this.image = image;
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
//		parent.image(image,x,y);
	}

	@Override
	public String[] dialog_event(boolean switchs) {
		// TODO Auto-generated method stub
		if(switchs)
		{
			parent.stage_5_floor = true;
			return text2;
		}
		
		return text1;
	}

}
