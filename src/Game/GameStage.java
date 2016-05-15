package Game;

import processing.core.PApplet;
import processing.core.PImage;

public class GameStage extends PApplet {
	
	private static final long serialVersionUID = 1L;
	private final static int width = 1000, height = 500;
	
	private PImage man2;
	
	public void setup() {
		
		size(width, height);
		smooth();
		this.man2 = loadImage("man2.jpg");
		
	}
	
	public void draw() {
		
		background(255);
        image(this.man2, 100, 100);
        stroke(0);
        fill(0);
        this.rect(0, 0, 1000, 50);
        this.rect(0, 420, 1000, 50);
        this.rect(0, 50, 50, 370);
        this.rect(950, 50, 50, 370);
        
	}
}