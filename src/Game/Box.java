package Game;

import processing.core.PImage;

public class Box extends AbstractItem{

	private boolean isspecial, isdone;
	private String[] text3;
	public Box(PImage image ,int x, int y, GameStage parent, String[] text1,
									String[] text2, String[] text3, boolean special)
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
		parent.image(image, x + image.width/2, y);
		parent.image(image, x, y + image.height);
		parent.image(image, x + image.width, y + image.height);
	}

	@Override
	public String[] dialog_event() {
		// TODO Auto-generated method stub
		if(isspecial && !isdone && parent.stage_5_box)
		{
//			parent.stage_5_floor = true;
			isdone = true;
			parent.opendialog(text2);
			Thread s = new Thread(new Runnable(){
				public void run()
				{
					while(true)
					{
//						System.out.println(parent.hasdialog);
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
			return null;
			
		}
		else if(isspecial && isdone)
		{
			parent.opendialog(text3);
			Thread s = new Thread(new Runnable(){
				public void run()
				{
					while(true)
					{
						System.out.println(parent.hasdialog);
						if(!parent.hasdialog)
						{
							
							parent.goalX = 847;
							parent.goalX = 97;
							parent.transport(6);
							break;
						}
					}
				}
			});
			s.start(); 
			
	 		return null;
		}
		return text1;
	}

}