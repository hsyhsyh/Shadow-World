package Game;

import processing.core.PApplet;
import processing.core.PImage;

public class GameStage extends PApplet {
	
	private static final long serialVersionUID = 1L;
	private final static int width = 1000, height = 500;
	
	private PImage man, books, monster, strike, box, bed, lader, door1;
	
	public void setup() {
		
		size(width, height);
		smooth();
		this.books = loadImage("books.jpg");
		this.man = loadImage("man2.jpg");
		this.monster = loadImage("monster.jpg");
		this.strike = loadImage("strike.png");
		this.box = loadImage("box.png");
		this.bed = loadImage("bed.jpg");
		this.lader = loadImage("lader.png");
		this.door1 = loadImage("opendoor.png");
		
	}
	
	public void draw() {
		
		background(255);
        image(this.books, 200, 100);
        image(this.bed, 600, 50);
        image(this.lader, 600, 100);
        image(this.strike, 800, 330);
        image(this.box, 200, 330);
        image(this.man, 600, 300);
        image(this.monster, 650, 300);
        image(this.door1, 80, 300);
        stroke(0);
        fill(0);
        this.rect(0, 0, 1000, 50);
        this.rect(0, 420, 1000, 50);
        this.rect(0, 50, 50, 370);
        this.rect(950, 50, 50, 370);
        
	}
}