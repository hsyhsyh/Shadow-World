package Game;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PImage;


public class GameStage extends PApplet{
	
	private static final long serialVersionUID = 1L;
	private final static int width = 1000, height = 500;
	
	public PImage man, bullet, books, book, bloodletter, diamand, phone, skull, monster, monster2, strike, box, bed, lader, door1, door2, man1, man2, man3, man4
	              , man5, man6, man7, man8;
	public PImage[] man_a = new PImage[10];
	private Character mainCharacter; 
	private ArrayList<Monster> monsters;
	private ArrayList<Floor> floors;
	private ArrayList<Door> doors;
	//private ArrayList<Bullet> bullets;
//	private ArrayList<strike> traps;
	private Dialog dialog;
	private boolean hasdialog;
	private int stage_num;
	public boolean isLoading = true;
	private boolean is_transport;
	private boolean is_hurt;
	private int alpha;
	
	public void setup() {
		
		size(width, height);
		smooth();
		int i;
		this.books = loadImage("books.png");
		this.bullet = loadImage("bullet.png");
		this.book = loadImage("book.png");
		this.bloodletter = loadImage("bloodletter.png");
		this.phone = loadImage("phone.png");
		this.diamand = loadImage("diamand.png");
		this.skull = loadImage("skull.png");
		this.man = loadImage("man2.png");
		this.man1 = loadImage("man_run1.png");
		this.man2 = loadImage("man_run2.png");
		this.man3 = loadImage("man_run3.png");
		this.man4 = loadImage("man_run4.png");
		this.man5 = loadImage("man_run5.png");
		this.man6 = loadImage("man_run6.png");
		this.man7 = loadImage("man_run7.png");
		this.man8 = loadImage("man_run8.png");
		for(i=1;i<=8;i++){
			this.man_a[i] = loadImage("man_gun"+Integer.toString(i)+".png");
		}
		this.monster = loadImage("monster.png");
		this.monster2 = loadImage("monster2.png");
		this.strike = loadImage("strike.png");
		this.box = loadImage("box.png");
		this.bed = loadImage("bed.png");
		this.lader = loadImage("lader.png");
		this.door1 = loadImage("closedoor.png");
		this.door2 = loadImage("opendoor.png");
		mainCharacter = new Character(this,man,"none",0,0,100,this);
		monsters = new ArrayList<Monster>();
		floors = new ArrayList<Floor>();
		doors = new ArrayList<Door>();
		//bullets = new ArrayList<Bullet>();
		dialog = new Dialog();
		
		stage_num = 2;
		
		loadData();
		isLoading = false;
		hasdialog = false;
		is_transport = false;
		
		
	}
	
	public void draw() 
	{
		
		background(255);

        //image(this.book, 200, 200);

        image(this.door1, 80, 320);
		image(this.door2, 800, 320);
		image(this.mainCharacter.getImage(), mainCharacter.x, mainCharacter.y);
		if(!monsters.isEmpty())
		{
			for(Monster monster : monsters){
				image(monster.getImage(),monster.x, monster.y);
			}
		}
		
		
        stroke(0);
        fill(0);
        if(!floors.isEmpty())
        {
        	for(Floor floor : floors){
            	this.rect(floor.getX(),floor.getY(),floor.getWidth(),floor.getHeight());
            }
        }
        
        if(!doors.isEmpty())
        {
        	for(Door door : doors){
//        		image(door.getImage(),door.x, door.y);
            }
        }
        

        if(!mainCharacter.getBullet().isEmpty()){
        	for(Bullet bullet: mainCharacter.getBullet()){
        		image(bullet.getImage(),bullet.x,bullet.y);
        		}
        }
        
        if(hasdialog)
        {
        	dialog.display();
        } 
		
        if(is_hurt)
        {
			fill(255,0,0,alpha);
        	rect(0,0,width,height);
        	if(alpha == 0)
        	{
        		is_hurt = false;
        	}
        }
        
		if(is_transport)
        {
			is_hurt = false;
			fill(0,0,0,alpha);
        	rect(0,0,width,height);
        	if(alpha == 255)
        	{
        		loadData();
        		Ani.to(this, (float)3.0, "alpha", 0);
        	}
        	if(alpha == 0)
        	{
        		is_transport = false;
        	}
        }
	}
	
	private void loadData(){
		switch(stage_num)
		{
		case 0:
			mainCharacter.deleteFloor();
			mainCharacter.x = 120;
			mainCharacter.y = 320;
			monsters.add(new Monster(this,monster,"none",400,320,100,this) );
			clearplace();
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			floors.add(new Floor(300,380,100, 20));
			floors.add(new Floor(420,340,100, 20));
			floors.add(new Floor(540,300,100, 20));
			floors.add(new Floor(660,360,100, 20));
			
			doors.add(new Door( (float)80, (float)300, door2));
			doors.add(new Door( (float)300, (float)100, door2));
			
			mainCharacter.addFloor(floors);
			break;
		case 1:
			mainCharacter.deleteFloor();
			clearplace();
			mainCharacter.x = 120;
			mainCharacter.y = 320;
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			mainCharacter.addFloor(floors);
			break;
		case 2:
			mainCharacter.deleteFloor();
			clearplace();
			mainCharacter.x = 120;
			mainCharacter.y = 320;
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			floors.add(new Floor(0,220,450, 20));
			floors.add(new Floor(550,320,450, 20));
			floors.add(new Floor(550,120,450, 20));
			monsters.add(new Monster(this,monster,"none",400,300,100,this));
			monsters.add(new Monster(this,monster,"none",430,300,100,this));
			monsters.add(new Monster(this,monster,"none",480,210,100,this));
			mainCharacter.addFloor(floors);
			break;
		case 3:
			mainCharacter.deleteFloor();
			clearplace();
			mainCharacter.x = 120;
			mainCharacter.y = 220;
			floors.add(new Floor(0, 0, 1000, 150));
			floors.add(new Floor(0, 320, 1000, 180));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			doors.add(new Door( (float)80, (float)100, door2));
			doors.add(new Door( (float)300, (float)100, door2));
			monsters.add(new Monster(this,monster,"none",400,220,100,this));
			mainCharacter.addFloor(floors);
			break;
		case 4:
			mainCharacter.deleteFloor();
			clearplace();
			mainCharacter.x = 120;
			mainCharacter.y = 320;
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			mainCharacter.addFloor(floors);
			break;
		case 5:
			mainCharacter.deleteFloor();
			clearplace();
			mainCharacter.x = 120;
			mainCharacter.y = 320;
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			mainCharacter.addFloor(floors);
			break;
		case 6:
			mainCharacter.deleteFloor();
			clearplace();
			mainCharacter.x = 120;
			mainCharacter.y = 320;
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			mainCharacter.addFloor(floors);
			break;
		case 7:
			mainCharacter.deleteFloor();
			clearplace();
			mainCharacter.x = 120;
			mainCharacter.y = 320;
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			mainCharacter.addFloor(floors);
			break;
		case 8:
			mainCharacter.deleteFloor();
			clearplace();
			mainCharacter.x = 120;
			mainCharacter.y = 320;
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			mainCharacter.addFloor(floors);
			break;
		case 9:
			mainCharacter.deleteFloor();
			clearplace();
			mainCharacter.x = 120;
			mainCharacter.y = 320;
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			mainCharacter.addFloor(floors);
			break;
		case 10:
			mainCharacter.deleteFloor();
			clearplace();
			mainCharacter.x = 120;
			mainCharacter.y = 320;
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			mainCharacter.addFloor(floors);
			break;
		}
		//mainCharacter.deleteFloor();
		//mainCharacter.addFloor(floors);
	}
	
	public void keyPressed() {
		// TODO Auto-generated method stub
		//WARNING: 需切換至英文輸入法才可以正常運作
		switch(keyCode)
		{
		case KeyEvent.VK_LEFT:
			mainCharacter.direction = "left";
			mainCharacter.bulletDirection="left";
			mainCharacter.move("left");
			mainCharacter.isWalk = true;
			break;
		case KeyEvent.VK_RIGHT:
			mainCharacter.direction = "right";
			mainCharacter.bulletDirection="right";
			mainCharacter.move("right");
			mainCharacter.isWalk = true;
			break;
		case KeyEvent.VK_SPACE://jump or up to ladder
			if(mainCharacter.isGround)
			{
				mainCharacter.jump();
				//mainCharacter.isWalk=false;
			}
			break;
		case KeyEvent.VK_DOWN://down to ladder
			break;
		case KeyEvent.VK_A:
			
			for(Door d : doors)
			{
				if( whereisch(d) )
				{
//					transport(d.goal);
				}
			}
			
//			for(Item i : items)
//			{
//				if( whereisch(i) )//if need dialog
//				{
					if(!hasdialog)
					{
						have_dialog();
						dialog.open();
						String text[] = new String[2];
						text[0] = "just";
						text[1] = "test";
						dialog.settext(text);
						dialog.showtext();
					}
					else
					{
						dialog.dosomething();
					}
//				}
//			}
			break;
		case KeyEvent.VK_Z://find
			//attack
			mainCharacter.isAttack = true;
			//transport(8);
			break;
			
		}
	}

	public void keyReleased() {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT)
		{
			//mainCharacter.isWalk=false;
			mainCharacter.move("stop");
			mainCharacter.direction = "";
		}
		else if(keyCode == KeyEvent.VK_Z){
			mainCharacter.isAttack = false;
		}
			
	}
	
	public PImage getImage(PImage image) {
		return image;
	}


	
	private void have_dialog()//character will use this to talk
	{
		hasdialog = true;
		
	}
	
	class Dialog
	{
		public int wide;
		public int high;
		private int manX, manY;
		private String text[];
		private int textnum;
		private int textpagenum;
		private int now_textpagenum;
		public Dialog()
		{
			wide = 1;
			high = 1;
			hasdialog = true;
			textpagenum = 0;
			now_textpagenum = 0;
			textnum = 0;//one row 45 letters, at most 3 column
//			text = "1234567890\n1234567890\n1234567890";
			manX = -200;
			manY = 160;
		}
		public void display()
		{
			image(man, manX, manY, 130, 350);
			stroke(125);
			strokeWeight(10);
			fill(255);
			rect(22,300,wide,high);
			if(dialog.wide == 1)
			{
				hasdialog = false;
			}
			fill(0);
			textSize(32);
			text(text[now_textpagenum].substring(0, textnum), 40, 340);
			
			
		}
		public void dosomething()
		{
			if(textnum == text[now_textpagenum].length() && now_textpagenum < textpagenum)
			{
				now_textpagenum++;
				textnum = 0;
				Ani.to(this, (float)(text[now_textpagenum].length()*0.3),
						"textnum", text[now_textpagenum].length(), Ani.LINEAR);
			}
			else if (textnum == text[now_textpagenum].length() && now_textpagenum == textpagenum)
			{
				closed();
			}
		}
		public void open()
		{
			Ani.to(this, (float)1.0, "wide", 950, Ani.SINE_OUT);
			Ani.to(this, (float)1.0, "high", 150, Ani.SINE_OUT);
			Ani.to(this, (float)0.3, "manX", 20, Ani.SINE_OUT);
		}
		private void closed()
		{
			textnum = 0;
			textpagenum = 0;
			now_textpagenum = 0;
			Ani.to(this, (float)0.5, "wide", 1, Ani.SINE_OUT);
			Ani.to(this, (float)0.5, "high", 1, Ani.SINE_OUT);
			Ani.to(this, (float)0.3, "manX", -200, Ani.SINE_OUT);
		}
		public void settext(String[] t)
		{
			textpagenum = t.length - 1;
			text = new String[textpagenum];
			now_textpagenum = 0;
			text = t;
			textnum = 0;
		}
		public void showtext()
		{
			Ani.to(this, (float)(text[now_textpagenum].length()*0.3),
							"textnum", text[now_textpagenum].length(), Ani.LINEAR);
		}
	}
	
	public void transport(int num)
	{
		is_transport = true;
		alpha = 1;
		Ani.to(this, (float)3.0, "alpha", 255);

		stage_num = num;
		
		
	}
	
	public boolean whereisch(Object thing)
	{		
		if(thing.getClass().getName().equals("Game.Door"))
		{
			Door d = (Door)thing;
//			if( (d.x < mainCharacter.x) && (d.x + d.width > mainCharacter.x)
//					&& (d.y < mainCharacter.y + mainCharacter.height) 
//						&& (d.y + d.height > mainCharacter.y + mainCharacter.height) )
//			{//if charater is at the door
//				return true;
//			}
//			else
//			{
//				return false;
//			}
		}
//		else if(thing.getClass().getName().equals("Game.Trap"))
//		{
//			Trap t = (Trap)thing;
//			if( (t.x < mainCharacter.x) && (t.x + t.width > mainCharacter.x)
//					&& (t.y < mainCharacter.y + mainCharacter.height) 
//						&& (t.y + t.height > mainCharacter.y + mainCharacter.height) )
//			{//if charater is at the trap
//				return true;
//			}
//			else
//			{
//				return false;
//			}
//		}
		
		return false;
	}
	
	public void hurt()
	{
		is_hurt = true;
		alpha = 255;
		Ani.to(this, (float)0.5, "alpha", 0);
	}
	
	private void clearplace()
	{
		floors.clear();
		doors.clear();
		monsters.clear();
//		items.clear();
	}

	public Character getCharacter() {
		// TODO Auto-generated method stub
		return this.mainCharacter;
	}
}