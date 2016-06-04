package Game;

import processing.core.PImage;

public class Paper extends AbstractItem{

//	private boolean switchs;
	public Paper(PImage image ,int x, int y, GameStage parent, String[] text1, 
			 boolean[] m1,String[] text2, boolean[] m2)
	{
		this.image = image;
		this.x = x;
		this.y = y;
		width = image.width;
		height = image.height;
		this.parent = parent;
		this.text1 = text1;
		mantalk1 = m1;
		this.text2 = text2;
		mantalk2 = m2;
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		parent.image(image,x,y);
	}

	@Override
	public void dialog_event() {
		// TODO Auto-generated method stub
//		if(this.switchs)
//		{
//			this.switchs = true;
			parent.stage_5_box = true;
//			return text2;
//		}
		parent.opendialog(text1, mantalk1);
//		return text1;
	}

}
