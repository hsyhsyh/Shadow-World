package Game;

import processing.core.PImage;

public class Deadman extends AbstractItem{

	private boolean switchs;
	private PImage image2;
	public Deadman(PImage image1, PImage image2 ,int x, int y, GameStage parent, String[] text1, 
			 boolean[] m1,String[] text2, boolean[] m2)
	{
		this.image = image1;
		this.image2 = image2;
		this.x = x;
		this.y = y;
		width = image.width;
		height = image.height;
		this.parent = parent;
		this.text1 = text1;
		mantalk1 = m1;
		this.text2 = text2;
		mantalk2 = m2;
		switchs = false;
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		parent.image(image,x,y);
	}

	@Override
	public void dialog_event() {
		// TODO Auto-generated method stub
		if(this.switchs)
		{
			parent.opendialog(text2,mantalk2);
		}
		else
		{
			parent.stage_2_door = true;
			this.switchs = true;
			parent.opendialog(text1,mantalk1);
			Thread s = new Thread(new Runnable(){
				public void run()
				{
					while(true)
					{
						System.out.println(parent.hasdialog);
						if(!parent.hasdialog)
						{
							image = image2;
							break;
						}
					}
				}
			});
			s.start(); 
		}
	}
	public void getImage(PImage image) {
		this.image = image;
	}

}

