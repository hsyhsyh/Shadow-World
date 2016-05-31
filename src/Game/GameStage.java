package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PFont;
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
	private ArrayList<AbstractItem> items;
	//private ArrayList<Bullet> bullets;
//	private ArrayList<strike> traps;
	private Dialog dialog;
	private boolean hasdialog;
	private int stage_num;
	public boolean isLoading = true;
	private boolean is_transport;
	private boolean is_hurt;
	private boolean stage_3_door,stage_5_floor, stage_5_box;
	private int alpha;
	private PFont cnFont;
	
	public void setup() {
		
		cnFont = new PFont(new Font
				(this.getClass().getResource("/NotoSansHantRegular.otf").getPath()
						, Font.PLAIN, 30), true);
		
		size(width, height);
		smooth();
		
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
		int i;
		for(i = 1; i <= 8; i++){
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
		items = new ArrayList<AbstractItem>();
		//bullets = new ArrayList<Bullet>();
		dialog = new Dialog();
		
		stage_num = 4;
		
		loadData();
		isLoading = false;
		hasdialog = false;
		is_transport = false;
		stage_3_door = false;
		stage_5_floor = false;
		stage_5_box = false;
		
		
		
	}
	
	public void draw() 
	{
		
		background(255);
		stroke(0);
        fill(0);
        strokeWeight(1);

		
		if(!monsters.isEmpty())
		{
			for(Monster monster : monsters){
				image(monster.getImage(),monster.x, monster.y);
			}
		}
		
		if(!items.isEmpty())
		{
			for(AbstractItem i : items){
				image(i.getImage(),i.x, i.y);
			}
		}
        
        if(!floors.isEmpty())
        {
        	for(Floor floor : floors){
            	this.rect(floor.getX(),floor.getY(),floor.getWidth(),floor.getHeight());
            }
        }
        
        if(!doors.isEmpty())
        {
        	for(Door door : doors){
        		image(door.getImage(),door.x, door.y);
            }
        }
        
        image(this.mainCharacter.getImage(), mainCharacter.x, mainCharacter.y);
        for(Bullet bullet: mainCharacter.getBullet()){
        	image(bullet.getImage(),bullet.x,bullet.y);
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
        		Ani.to(this, (float)1.5, "alpha", 0);
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
		case 0://test stage
			mainCharacter.deleteFloor();
			mainCharacter.x = 120;
			mainCharacter.y = 320;
			monsters.add(new Monster(this,monster,"none",400,320,100,this,350,450) );
			clearplace();
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			floors.add(new Floor(300,380,100, 20));
			floors.add(new Floor(420,340,100, 20));
			floors.add(new Floor(540,300,100, 20));
			floors.add(new Floor(660,360,100, 20));
			
			doors.add(new Door( (float)80, (float)300, door2, 0));
			doors.add(new Door( (float)300, (float)100, door2, 0));
			
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
			floors.add(new Floor(0, 0, 1000, 40));
			floors.add(new Floor(0, 430, 1000, 40));
			floors.add(new Floor(0, 0, 40, 500));
			floors.add(new Floor(960,0,40, 500));
			floors.add(new Floor(0,225,450, 15));
			floors.add(new Floor(550,315,450, 15));
			floors.add(new Floor(550,160,450, 15));
		
			doors.add(new Door( (float)50, (float)330, door2, 1));
			doors.add(new Door( (float)860, (float)330, door2, 3));
			doors.add(new Door( (float)860, (float)60, door2, 4));
			monsters.add(new Monster(this,monster,"none",400,300,100,this,350,450));
			monsters.add(new Monster(this,monster,"none",430,300,100,this,350,450));
			monsters.add(new Monster(this,monster,"none",480,210,100,this,460,520));
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
			doors.add(new Door( (float)50, (float)220, door2, 2));
			doors.add(new Door( (float)800, (float)220, door1, 5));
			monsters.add(new Monster(this,monster,"none",400,220,100,this,350,450));
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
			floors.add(new Floor(750,305,250, 15));
			floors.add(new Floor(50,200,200, 15));
			floors.add(new Floor(400,250,100, 15));//maybe can move
			doors.add(new Door( (float)50, (float)320, door2, 3));
			doors.add(new Door( (float)850, (float)320, door1, 5));
			monsters.add(new Monster(this,monster,"none",200,220,100,this,150,250));
			monsters.add(new Monster(this,monster,"none",400,220,100,this,350,450));
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
	}
	
	public void keyPressed() {
		// TODO Auto-generated method stub
		//WARNING: 需切換至英文輸入法
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
		case KeyEvent.VK_SPACE://jump
			if(mainCharacter.isGround)
			{
				mainCharacter.jump();
				//mainCharacter.isWalk=false;
			}
			break;
		case KeyEvent.VK_DOWN://down to ladder
			break;
		case KeyEvent.VK_UP://up to ladder
			for(Door d : doors)
			{
				if( whereisch(d) )
				{
					transport(d.getgoal());
//					isnotdone = false;
					break;
				}
			}
			break;
		case KeyEvent.VK_A:
			if(!hasdialog)
			{
				boolean isnotdone = true;
				
				
				for(AbstractItem i : items)
				{
//					String text[] = i.gettext();
					String text[] = new String[2];
					textFont(cnFont);
					text[0] = "測試";
					text[1] = "test";
					if( isnotdone && whereisch(i) && (text.length != 0) )//if need dialog
					{
						hasdialog = true;
						dialog.open();
						dialog.settext(text);
						dialog.showtext();
					}
				}
			}
			else
			{
				dialog.dosomething();
			}
			break;
		case KeyEvent.VK_Z://find
			//attack
			mainCharacter.isAttack = true;
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
		Ani.to(this, (float)1.0, "alpha", 255);

		stage_num = num;
		
		
	}
	
	public boolean whereisch(Object thing)
	{		
		if(thing.getClass().getName().equals("Game.Door"))
		{
			Door d = (Door)thing;
			if( (d.x - 10 < mainCharacter.x) && (d.x + door1.width + 10 > mainCharacter.x)
					&& (d.y - 10 < mainCharacter.y + man1.height) 
						&& (d.y + door1.height + 10 > mainCharacter.y + man1.height) )
			{//if charater is at the door
				return true;
			}
			else
			{
				return false;
			}
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
		items.clear();
	}

	public Character getCharacter() {
		// TODO Auto-generated method stub
		return this.mainCharacter;
	}
}