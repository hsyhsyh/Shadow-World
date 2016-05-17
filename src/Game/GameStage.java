package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;


public class GameStage extends PApplet implements KeyListener{
	
	private static final long serialVersionUID = 1L;
	private final static int width = 1000, height = 500;
	
	public PImage man, books, monster, strike, box, bed, lader, door1, man1, man2, man3, man4;
	private Character mainCharacter; 
	private Map map;
	private ArrayList<Character> characters;
	public boolean isLoading = true;
	
	public void setup() {
		
		size(width, height);
		smooth();
		this.books = loadImage("books.jpg");
		this.man = loadImage("man2.jpg");
		this.man1 = loadImage("man_run1.jpg");
		this.man2 = loadImage("man_run2.jpg");
		this.man3 = loadImage("man_run3.jpg");
		this.man4 = loadImage("man_run4.jpg");
		this.monster = loadImage("monster.jpg");
		this.strike = loadImage("strike.png");
		this.box = loadImage("box.png");
		this.bed = loadImage("bed.jpg");
		this.lader = loadImage("lader.png");
		this.door1 = loadImage("opendoor.png");
		characters = new ArrayList<Character>();
		loadData();
		this.addKeyListener(this);
		isLoading = false;
		
	}
	
	public void draw() {
		
		background(255);
        /*image(this.books, 200, 100);
        image(this.bed, 600, 50);
        image(this.lader, 600, 100);
        image(this.strike, 800, 330);
        image(this.box, 200, 330);
        image(this.man, 600, 300);
        image(this.monster, 650, 300);
        image(this.door1, 80, 300);*/
		image(this.mainCharacter.getImage(), mainCharacter.x, mainCharacter.y);
		
        stroke(0);
        fill(0);
        this.rect(0, 0, 1000, 50);
        this.rect(0, 420, 1000, 50);
        this.rect(0, 50, 50, 370);
        this.rect(950, 50, 50, 370);
        
	}
	
	private void loadData(){
		
		mainCharacter=new Character(this,man,"none",120,320,100,this);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			mainCharacter.move("left");
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			mainCharacter.move("right");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(userEnter);
	}
	
	public PImage getImage(PImage image) {
		return image;
	}

	
}