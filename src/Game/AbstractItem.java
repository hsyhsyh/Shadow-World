package Game;

import processing.core.PImage;

abstract public class AbstractItem {

	public int x,y;
	protected int width,height;
	protected String[] text1, text2;
	protected boolean[] mantalk1,mantalk2;
	protected PImage image;
	protected GameStage parent;
	abstract public void display();
	abstract public void dialog_event();
}
