package Game;

import processing.core.PImage;

public class Bloodletter extends AbstractItem {

	public Bloodletter(PImage image ,int x, int y, GameStage parent, String[] text1, 
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
			width = 100;
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

	}

	@Override
	public void dialog_event() {
		// TODO Auto-generated method stub
		System.out.println(text1[0]);
		parent.stage_8_bookcase_1 = true;
		parent.opendialog(text1, mantalk1);
	}

}
