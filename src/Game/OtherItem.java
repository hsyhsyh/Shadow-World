package Game;

import processing.core.PImage;

public class OtherItem extends AbstractItem {

	public OtherItem(PImage image ,int x, int y, GameStage parent, String[] text1, 
			 boolean[] m1,String[] text2, boolean[] m2)
	{
		this.image = image;
		this.x = x;
		this.y = y;
		if(image != null)
		{
			width = image.width;
			height = image.height;
		}
		else
		{
			width = 50;
			height = 100;
		}
		this.parent = parent;
		this.text1 = text1;
		mantalk1 = m1;
		this.text2 = text2;
		mantalk2 = m2;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		if(image != null)
		{
			parent.image(image,x,y);
		}
		else
		{
			parent.fill(255,0,0);
			parent.rect(x, y, width, height);
		}

	}

	@Override
	public void dialog_event() {
		// TODO Auto-generated method stub
		parent.opendialog(text1, mantalk1);
	}

}
