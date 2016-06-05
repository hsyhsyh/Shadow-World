package Game;

import processing.core.PImage;

public class Box extends AbstractItem{

	private boolean isspecial;
	private String[] text3;
	private boolean[] mantalk3;
	public Box(PImage image ,int x, int y, GameStage parent, String[] text1, boolean[] m1,
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
//		isspecial = special;
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		parent.image(image, x + image.width/2, y);
		parent.image(image, x, y + image.height);
		parent.image(image, x + image.width, y + image.height);
	}

	@Override
	public void dialog_event() {
		// TODO Auto-generated method stub
//		System.out.println(isspecial + " " + isdone);
		if(isspecial && !parent.stage_5_box_2 && parent.stage_5_box_1)
		{
//			parent.stage_5_floor = true;
			parent.stage_5_box_2 = true;
			parent.opendialog(text2, mantalk2);
			Thread s = new Thread(new Runnable(){
				public void run()
				{
					while(true)
					{
						System.out.print(parent.hasdialog);
						if(!parent.hasdialog)
						{
//							System.out.println(parent.hasdialog);
							parent.goalX = 847;
							parent.goalY = 97;
							parent.transport(6);
							break;
						}
					}
				}
			});
			s.start(); 
//			return null;
			
		}
		else if(isspecial && parent.stage_5_box_2)
		{
			parent.opendialog(text3,mantalk3);
			Thread s = new Thread(new Runnable(){
				public void run()
				{
					while(true)
					{
						System.out.print(parent.hasdialog);
						if(!parent.hasdialog)
						{
							
							parent.goalX = 847;
							parent.goalY = 97;
							parent.transport(6);
							break;
						}
					}
				}
			});
			s.start(); 
			
//	 		return null;
		}
		else
		{
			parent.opendialog(text1,mantalk1);
		}
		
	}

}