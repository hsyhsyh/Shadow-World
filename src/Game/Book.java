package Game;

import processing.core.PImage;

public class Book extends AbstractItem{

	private boolean isspecial, isdone;
	private String[] text3;
	private boolean[] mantalk3;
	public Book(PImage image ,int x, int y, GameStage parent, String[] text1, boolean[] m1,
			String[] text2, boolean[] m2, String[] text3, boolean[] m3, boolean special)
	{
		this.image = image;
		this.x = x;
		this.y = y;
		width = image.width * 2;
		height = image.height * 2;
		this.parent = parent;
		this.text1 = text1;
		mantalk1 = m1;
		this.text2 = text2;
		mantalk2 = m2;
		this.text3 = text3;
		mantalk3 = m3;
		isspecial = special;
		isdone = false;
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		parent.image(image, x, y, 150, 150);
	}

	@Override
	public void dialog_event() {
		// TODO Auto-generated method stub
		if(isspecial && !isdone && parent.stage_5_box)
		{
//			parent.stage_5_floor = true;
			isdone = true;
			parent.opendialog(text2, mantalk2);

//			return null;
			
		}
		else if(isspecial && isdone)
		{
			parent.opendialog(text3,mantalk3);

			
//	 		return null;
		}
		else
		{
			parent.opendialog(text1,mantalk1);
		}
		
	}

}