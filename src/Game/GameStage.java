package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;


public class GameStage extends PApplet implements KeyListener{
	
	private static final long serialVersionUID = 1L;
	private final static int width = 1000, height = 500;
	
	public PImage man, books, monster, strike, box, bed, lader, door1, man1, man2, man3, man4
	              , man5, man6, man7, man8;
	private Character mainCharacter; 
	private Map map;
	private ArrayList<Character> characters;
	public boolean isLoading = true;
	
	public void setup() {
		
		size(width, height);
		smooth();
		this.books = loadImage("books.png");
		this.man = loadImage("man2.png");
		this.man1 = loadImage("man_run1.png");
		this.man2 = loadImage("man_run2.png");
		this.man3 = loadImage("man_run3.png");
		this.man4 = loadImage("man_run4.png");
		this.man5 = loadImage("man_run5.png");
		this.man6 = loadImage("man_run6.png");
		this.man7 = loadImage("man_run7.png");
		this.man8 = loadImage("man_run8.png");
		this.monster = loadImage("monster.png");
		this.strike = loadImage("strike.png");
		this.box = loadImage("box.png");
		this.bed = loadImage("bed.png");
		this.lader = loadImage("lader.png");
		this.door1 = loadImage("opendoor.png");
		map=new Map(50,420,950,50);
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
		image(this.door1, 80, 300);
		image(this.mainCharacter.getImage(), mainCharacter.x, mainCharacter.y);
		
        stroke(0);
        fill(0);
        this.rect(0, 0, 1000, map.getSupHeight());
        this.rect(0, map.getInfHeight(), 1000, 500-map.getInfHeight());
        this.rect(0, 0, map.getInfWidth(), 500);
        this.rect(map.getSupWidth(),0,1000-map.getSupWidth(), 500);
        
	}
	
	private void loadData(){
		
		mainCharacter=new Character(this,man,"none",120,320,100,this,map);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			mainCharacter.direction = "left";
			mainCharacter.move("left");
			mainCharacter.isWalk=true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
	    	mainCharacter.direction = "right";
			mainCharacter.move("right");
			mainCharacter.isWalk=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP && mainCharacter.getMap().IsGround(mainCharacter)){
			mainCharacter.jump();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_RIGHT){
			mainCharacter.isWalk=false;
			mainCharacter.move("stop");
			mainCharacter.direction = "";
		}
			
	}
	
	public PImage getImage(PImage image) {
		return image;
	}

	
}