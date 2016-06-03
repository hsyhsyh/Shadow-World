package Game;

import processing.core.PImage;

abstract public class AbstractItem {

	public int x,y;
	protected int width,height;
	protected String[] text1, text2;
	protected PImage image;
	protected GameStage parent;
//	public void set(PImage simage ,int sx, int sy, PApplet sparent, String[] text1, String[] text2)
//	{
//		this.image = image;
//		this.x = x;
//		this.y = y;
//		this.parent = parent;
//		this.text1 = text1;
//		this.text2 = text2;
//	}
	abstract public void display();
	abstract public String[] dialog_event();
}
