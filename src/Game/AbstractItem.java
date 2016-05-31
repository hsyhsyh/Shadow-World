package Game;

import processing.core.PImage;

abstract public class AbstractItem {

	public float x,y;
	private int width,height;
	private String[] text;
	private PImage image;
	public String[] gettext()
	{
		return text;
	}
	public void settext(String[] s)
	{
		text = s;
	}
	public PImage getImage()
	{
		return image;
	}
}
