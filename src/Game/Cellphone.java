package Game;

import processing.core.PImage;

public class Cellphone extends AbstractItem {

	private boolean isspecial;
	public Cellphone(PImage image ,int x, int y, GameStage parent, String[] text1, 
			 boolean[] m1,String[] text2, boolean[] m2, boolean special)
	{
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = image.width;
		this.height = image.height;
		this.parent = parent;
		this.text1 = text1;
		this.mantalk1 = m1;
		this.text2 = text2;
		this.mantalk2 = m2;
		this.isspecial = special;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		parent.image(image,x,y);
	}

	@Override
	public void dialog_event() {
		// TODO Auto-generated method stub
		parent.is_voting = true;
		if(isspecial)
		{
			parent.opendialog(text2, mantalk2);
		}
		else
		{
			parent.opendialog(text1,mantalk1);
		}

	}

}
