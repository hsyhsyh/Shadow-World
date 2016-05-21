package Game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PImage;


public class GameStage extends PApplet{
	
	private static final long serialVersionUID = 1L;
	private final static int width = 1000, height = 500;
	
	public PImage man, books, monster, strike, box, bed, lader, door1, man1, man2, man3, man4
	              , man5, man6, man7, man8;
	private Character mainCharacter; 
	private Map map;
	private ArrayList<Monster> monsters;
	private Dialog dialog;
	private int stage_num;
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
		monsters = new ArrayList<Monster>();
		loadData();
		//this.addKeyListener(this);
		isLoading = false;
		stage_num = 0;
		
	}
	
	public void draw() 
	{
		switch(stage_num)
		{
		case 0:
			test_stage();
			break;
		case 1:
			stage_1();
			break;
		case 2:
			stage_2();
			break;
		case 3:
			stage_3();
			break;
		case 4:
			stage_4();
			break;
		case 5:
			stage_5();
			break;
		case 6:
			stage_6();
			break;
		case 7:
			stage_7();
			break;
		case 8:
			stage_8();
			break;
		}     
	}
	
	private void loadData(){
		
		mainCharacter = new Character(this,man,"none",120,320,100,this,map);
		Monster mons;
		mons = new Monster(this,monster,"none",400,320,100,this,map);
		monsters.add(mons);
	}
	
	public void keyPressed() {
		// TODO Auto-generated method stub
		//WARNING: 需切換至英文輸入法才可以正常運作
		switch(keyCode)
		{
		case KeyEvent.VK_LEFT:
			mainCharacter.direction = "left";
			mainCharacter.move("left");
			mainCharacter.isWalk=true;
			break;
		case KeyEvent.VK_RIGHT:
			mainCharacter.direction = "right";
			mainCharacter.move("right");
			mainCharacter.isWalk=true;
			break;
		case KeyEvent.VK_UP://jump or up to ladder
			if(mainCharacter.getMap().IsGround(mainCharacter))
			{
				mainCharacter.jump();
				//mainCharacter.isWalk=false;
			}
			break;
		case KeyEvent.VK_DOWN://down to ladder
			break;
		case KeyEvent.VK_SPACE://find
			
			if(true)
			{
				have_dialog();
				dialog.open();
				System.out.println("AAA");
				while(dialog.isopen)
				{
					dialog.display();
				}
				
			}
			break;
		}
	}

	public void keyReleased() {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.VK_LEFT||keyCode==KeyEvent.VK_RIGHT){
			//mainCharacter.isWalk=false;
			mainCharacter.move("stop");
			mainCharacter.direction = "";
		}
			
	}
	
	public PImage getImage(PImage image) {
		return image;
	}

	private void stage_1()
	{
		;
	}
	
	private void stage_2()
	{
		;
	}
	
	private void stage_3()
	{
		;
	}
	
	private void stage_4()
	{
		;
	}
	
	private void stage_5()
	{
		;
	}
	
	private void stage_6()
	{
		;
	}
	
	private void stage_7()
	{
		;
	}
	
	private void stage_8()
	{
		;
	}
	
	private void test_stage()
	{
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
		for(Monster monster : monsters){
			image(monster.getImage(),monster.x, monster.y);
		}
		
        stroke(0);
        fill(0);
        this.rect(0, 0, 1000, map.getSupHeight());
        this.rect(0, map.getInfHeight(), 1000, 500-map.getInfHeight());
        this.rect(0, 0, map.getInfWidth(), 500);
        this.rect(map.getSupWidth(),0,1000-map.getSupWidth(), 500);
	}
	
	private void have_dialog()//character will use this to talk
	{
		dialog = new Dialog(this);
		dialog.display();
	}
	
	class Dialog
	{
		public boolean isopen;
//		public boolean opening;
		public boolean canclosed;
		private PApplet parent;
		private int wide;
		private int high;
		public Dialog(PApplet p)
		{
			parent = p;
			wide = 1;
			high = 1;
			isopen = true;
//			opening = true;
			canclosed = false;
		}
		public void display()
		{
			strokeWeight(10);
			fill(255,0,0);
			parent.rect(500,300,wide,high);
		}
		public void open()
		{
			Ani.to(this, 3.0f, "wide", 200);
			Ani.to(this, 3.0f, "high", 200);
			isopen = true;
			canclosed = true;
		}
		public void closed()
		{
			Ani.to(this, 0.5f, "wide", 0);
			Ani.to(this, 0.5f, "high", 0);
		}
	}
	
}