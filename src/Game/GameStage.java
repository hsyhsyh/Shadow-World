package Game;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;


public class GameStage extends PApplet{
	
	private static final long serialVersionUID = 1L;
	private final static int width = 1000, height = 500;
	
	public PImage man, bullet, books, book, bloodletter, diamand, phone, skull, monster, monster2, strike, box, bed, ladder, door1, door2, man1, man2, man3, man4
	              , man5, man6, man7, man8, man_c1, man_c2;
	public PImage[] man_a = new PImage[10];
	private Character mainCharacter; 
	private ArrayList<Monster> monsters;
	private ArrayList<Floor> floors;
	private ArrayList<Door> doors;
	private ArrayList<AbstractItem> items;
	private ArrayList<Ladder> ladders;
	//private ArrayList<Bullet> bullets;
//	private ArrayList<strike> traps;
	private Dialog dialog;
	private boolean hasdialog;
	private int stage_num;
	public boolean isLoading = true;
	private boolean is_transport;
	private boolean is_hurt;
	public boolean firststart,stage_3_door,stage_5_floor, stage_5_box;
	private int alpha;
	public int goalX,goalY;
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
		this.man_c1 = loadImage("man_climb1.png");
		this.man_c2 = loadImage("man_climb2.png");
		int i;
		for(i = 1; i <= 8; i++){
			this.man_a[i] = loadImage("man_gun"+Integer.toString(i)+".png");
		}
		this.monster = loadImage("monster.png");
		this.monster2 = loadImage("monster2.png");
		this.strike = loadImage("strike.png");
		this.box = loadImage("box.png");
		this.bed = loadImage("bed.png");
		this.ladder = loadImage("lader.png");
		this.door1 = loadImage("closedoor.png");
		this.door2 = loadImage("opendoor.png");
		mainCharacter = new Character(this,man,"none",0,0,100,this);
		monsters = new ArrayList<Monster>();
		floors = new ArrayList<Floor>();
		doors = new ArrayList<Door>();
		items = new ArrayList<AbstractItem>();
		ladders = new ArrayList<Ladder>();
		//bullets = new ArrayList<Bullet>();
		dialog = new Dialog();
		
		stage_num = 8;
		
		
		isLoading = false;
		hasdialog = false;
		is_transport = false;
		stage_3_door = false;
		stage_5_floor = false;
		stage_5_box = false;
		firststart = true;
		loadData();
		
	}
	
	public void draw() 
	{
		
		background(255);
        strokeWeight(1);
//        textFont(cnFont);
        //blood
        if(stage_num == 3) {
	        fill(200);
	        stroke(200);
	        this.rect(80+mainCharacter.now_HP, 195, 100-mainCharacter.now_HP, 5);
	        fill(0);
	        stroke(0);
	        if(mainCharacter.now_HP > 0)
	        	this.rect(80, 195, mainCharacter.now_HP, 5);
	        this.textFont(createFont("Arial", 12), 20);
	        this.text("HP", 80, 190);
        }
        else {
	        fill(200);
	        stroke(200);
	        this.rect(70+mainCharacter.now_HP, 85, 100-mainCharacter.now_HP, 5);
	        fill(0);
	        stroke(0);
	        if(mainCharacter.now_HP > 0)
	        	this.rect(70, 85, mainCharacter.now_HP, 5);
	        this.textFont(createFont("Arial", 12), 20);
	        this.text("HP", 70, 80);
        }
        
		if(!monsters.isEmpty())
		{
			for(Monster monster : monsters){
				image(monster.getImage(),monster.x, monster.y);
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
        
		if(!items.isEmpty())
		{
			for(AbstractItem i : items){
				i.display();
			}
		}
        
        if(!ladders.isEmpty())
        {
        	for(Ladder ladder : ladders){
//        		image(ladder.getImage(),ladder.x, ladder.y);
        		ladder.display();
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
        		mainCharacter.x = goalX;
        		mainCharacter.y = goalY;
        	}
        	if(alpha == 0)
        	{
        		is_transport = false;
        	}
        }
		
	}
	
	private void loadData(){
		
		clearplace();
		mainCharacter.deleteFloor();
		mainCharacter.deleteLadder();
		System.out.println(stage_num);
		String[] s;
		switch(stage_num)
		{
		case 0://test stage
			mainCharacter.x = 120;
			mainCharacter.y = 320;
			monsters.add(new Monster(this,monster,"none",400,320,100,this,350,450) );
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			floors.add(new Floor(300,380,100, 20));
			floors.add(new Floor(420,340,100, 20));
			floors.add(new Floor(540,300,100, 20));
			floors.add(new Floor(660,360,100, 20));
			doors.add(new Door( 80, 300, door2, 0, 120, 320));
			doors.add(new Door( 300, 100, door2, 0, 120, 320));
			mainCharacter.addFloor(floors);
			break;
		case 1:
			mainCharacter.x = 120;
			mainCharacter.y = 20;
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			floors.add(new Floor(75,180,270, 10));
			doors.add(new Door( 860, 320, door2, 2, 50, 330));
			ladders.add(new Ladder(280, 160, 4, ladder, this));
			ladders.add(new Ladder(280, 365, 1, ladder, this));
			s = new String[2];
			s[0] = "雖然失去了色彩，但還是看的出來這就是自己的位置。\n在枕頭和書桌的正中央各貼著一張紙條。";
			s[1] = "左右方向鍵行走，上方向鍵可以開門以及爬梯子，下方向鍵可以蹲下\n以及下梯子，A鍵可以調查事物，Z鍵可以發射子彈。";
			items.add(new Bed(bed, 60, 110, this ,s, null));
			mainCharacter.addFloor(floors);
			mainCharacter.addLadder(ladders);
//			if(firststart)
//			{
//				String[] start = new String[6];
//				start[0] = "煩耶！誰一直吵我睡覺啦！\n......紙條？";
//				start[1] = "左右方向鍵行走，上方向鍵可以開門以及爬梯子，下方向鍵可以蹲下\n以及下梯子，A鍵可以調查事物，Z鍵可以發射子彈。";
//				start[2] = "什麼鬼東西啊？扔掉算了！\n......嗯？";
//				start[3] = "這裡是......宿舍？為什麼都是黑白的？\n到底......發生什麼事了？";
//				start[4] = "呃？在我枕頭底下這個難道是......槍？\n為什麼我會有這種東西？";
//				start[5] = "......\n不管了，先到處看看再說。";
//				hasdialog = true;
//				dialog.open();
//				dialog.settext(start);
//				dialog.showtext();
//				firststart = false;
//			}
			break;
		case 2:
//			mainCharacter.x = 120;
//			mainCharacter.y = 320;
			floors.add(new Floor(0, 0, 1000, 40));
			floors.add(new Floor(0, 430, 1000, 40));
			floors.add(new Floor(0, 0, 40, 500));
			floors.add(new Floor(960,0,40, 500));
			floors.add(new Floor(0,225,450, 15));
			floors.add(new Floor(550,315,450, 15));
			floors.add(new Floor(550,160,450, 15));
			doors.add(new Door( 50, 330, door2, 1, 860, 320));
			doors.add(new Door( 860, 330, door2, 3, 50, 220));
			doors.add(new Door( 860, 60, door2, 4, 50 ,320));
			monsters.add(new Monster(this,monster,"none",400,300,100,this,300,460));
			monsters.add(new Monster(this,monster,"none",430,300,100,this,300,460));
			monsters.add(new Monster(this,monster,"none",480,210,100,this,460,700));
			mainCharacter.addFloor(floors);
			break;
		case 3:
//			mainCharacter.x = 120;
//			mainCharacter.y = 220;
			floors.add(new Floor(0, 0, 1000, 150));
			floors.add(new Floor(0, 320, 1000, 180));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			doors.add(new Door( 50, 220, door2, 2, 860, 330));
			doors.add(new Door( 800, 220, door1, 5, 50, 100));
			monsters.add(new Monster(this,monster,"none",400,220,100,this,350,450));
			mainCharacter.addFloor(floors);
			break;
		case 4:
//			mainCharacter.x = 120;
//			mainCharacter.y = 320;
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			floors.add(new Floor(750,305,250, 15));
			floors.add(new Floor(50,200,200, 15));
			floors.add(new Floor(400,250,100, 15,300,550));//maybe can move(add left and right bound in the end)
			doors.add(new Door( 50, 320, door2, 2, 860, 60));
			doors.add(new Door( 850, 320, door1, 5, 50, 320));
			monsters.add(new Monster(this,monster,"none",200,220,100,this,150,250));
			monsters.add(new Monster(this,monster,"none",400,220,100,this,350,450));
//			s = new String[1];
			s = null;
//			s[0] = "雖然失去了色彩，但還是看的出來這就是自己的位置。\n在枕頭和書桌的正中央各貼著一張紙條。";
//			s[1] = "左右方向鍵行走，上方向鍵可以開門以及爬梯子，下方向鍵可以蹲下\n以及下梯子，A鍵可以調查事物，Z鍵可以發射子彈。";
			items.add(new Box(box, 250, 282, this ,s, null, false));
			mainCharacter.addFloor(floors);
			break;
		case 5:
//			mainCharacter.x = 120;
//			mainCharacter.y = 320;
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			floors.add(new Floor(50,200,250, 15));
			floors.add(new Floor(750,305,250, 15));
			doors.add(new Door( 50, 100, door2, 3, 800, 220));
			doors.add(new Door( 50, 320, door2, 4, 850, 320));
			doors.add(new Door( 850, 320, door2, 7, 120, 320));
			doors.add(new Door( 850, 320, door2, 8, 120, 320));
//			s = new String[1];
			s = null;
//			s[0] = "雖然失去了色彩，但還是看的出來這就是自己的位置。\n在枕頭和書桌的正中央各貼著一張紙條。";
//			s[1] = "左右方向鍵行走，上方向鍵可以開門以及爬梯子，下方向鍵可以蹲下\n以及下梯子，A鍵可以調查事物，Z鍵可以發射子彈。";
			items.add(new Box(box, 400, 282, this ,s, null, true));
			if(stage_5_floor)
			{
				floors.add(new Floor(450,250,150, 15));
			}
			mainCharacter.addFloor(floors);
			break;
		case 6:
//			mainCharacter.x = 120;
//			mainCharacter.y = 320;
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			floors.add(new Floor(750,200,250, 20));
			doors.add(new Door( 850, 100, door2, 5, 450, 282));
			ladders.add(new Ladder(750, 196, 4, ladder, this));
			mainCharacter.addFloor(floors);
			mainCharacter.addLadder(ladders);
			break;
		case 7:
//			mainCharacter.x = 120;
//			mainCharacter.y = 320;
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			doors.add(new Door( 50, 320, door2, 5, 850, 320));
			doors.add(new Door( 850, 320, door2, 8, 50, 150));
			mainCharacter.addFloor(floors);
			break;
		case 8:
//			mainCharacter.x = 120;
//			mainCharacter.y = 320;
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 50));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			floors.add(new Floor(50,250,100,20));
			floors.add(new Floor(850,350,100,100));
			floors.add(new Floor(700,150,300,20));
			floors.add(new Floor(450,250,150,20));
			ladders.add(new Ladder(85, 240, 2, ladder, this));
			ladders.add(new Ladder(85, 330, 1, ladder, this));
			ladders.add(new Ladder(85, 370, 1, ladder, this));
			ladders.add(new Ladder(880, 130, 4, ladder, this));
			doors.add(new Door( 50, 150, door2, 7, 850, 320));
			items.add(new Book(books,500,150,this,null,null, null, true));
			mainCharacter.addFloor(floors);
			mainCharacter.addLadder(ladders);
			break;
//		case 9:
//			mainCharacter.x = 120;
//			mainCharacter.y = 320;
//			floors.add(new Floor(0, 0, 1000, 50));
//			floors.add(new Floor(0, 420, 1000, 80));
//			floors.add(new Floor(0, 0, 50, 500));
//			floors.add(new Floor(950,0,50, 500));
//			mainCharacter.addFloor(floors);
//			break;
//		case 10:
//			mainCharacter.x = 120;
//			mainCharacter.y = 320;
//			floors.add(new Floor(0, 0, 1000, 50));
//			floors.add(new Floor(0, 420, 1000, 80));
//			floors.add(new Floor(0, 0, 50, 500));
//			floors.add(new Floor(950,0,50, 500));
//			mainCharacter.addFloor(floors);
//			break;
		}
	}
	
	public void keyPressed() {
		// TODO Auto-generated method stub
		//WARNING: 需切換至英文輸入法
		switch(keyCode)
		{
		case KeyEvent.VK_LEFT:
			if(mainCharacter.isOnLadder){
				mainCharacter.isOnLadder=false;
				mainCharacter.y-=20;
			}
			mainCharacter.direction = "left";
			mainCharacter.bulletDirection="left";
			mainCharacter.move("left");
			mainCharacter.isWalk = true;
			break;
		case KeyEvent.VK_RIGHT:
			if(mainCharacter.isOnLadder){
				mainCharacter.isOnLadder=false;
				mainCharacter.y-=20;
			}
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
					goalX = d.goalX - 3;
					goalY = d.goalY - 3;
//					isnotdone = false;
					break;
				}
			}
			for(Ladder ladder : ladders)
			{
				if(ladder.isLadder(mainCharacter)){
					mainCharacter.isOnLadder=true;
					mainCharacter.velocityForDirectionY=5;
				}
			}
			break;
		case KeyEvent.VK_A:
			if(!hasdialog)
			{
				boolean isnotdone = true;
				
				
				for(AbstractItem i : items)
				{
					String text[] = i.dialog_event(true);
//					String text[] = new String[2];
//					textFont(cnFont);
//					text[0] = "測試";
//					text[1] = "test";
					if( isnotdone && whereisch(i) && (text != null) )//if need dialog
					{
						hasdialog = true;
						dialog.open();
						dialog.settext(text);
						dialog.showtext();
						break;
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
			if(mainCharacter.isOnLadder){
				mainCharacter.isOnLadder=false;
				mainCharacter.y-=20;
			}
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
			textFont(cnFont);
			text(text[now_textpagenum].substring(0, textnum), 40, 340);
			if(wide == 950 && high == 150)
			{
				textSize(16);
				text("按A鍵繼續......", 860, 430);
			}
		}
		public void dosomething()
		{
			if(textnum == text[now_textpagenum].length() && now_textpagenum < textpagenum)
			{
				now_textpagenum++;
				textnum = 0;
				Ani.to(this, (float)(text[now_textpagenum].length()*0.1),
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
			Ani.to(this, (float)(text[now_textpagenum].length()*0.1),
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
			if( (d.x < mainCharacter.x) && (d.x + door1.width > mainCharacter.x)
					&& (d.y - 5 < mainCharacter.y + man1.height) 
						&& (d.y + door1.height + 5 > mainCharacter.y + man1.height) )
			{//if charater is at the door
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(thing.getClass().getName().equals("Game.Bed"))
		{
			Bed b = (Bed)thing;
			if( (b.x < mainCharacter.x) && (b.x + b.width > mainCharacter.x)
					&& (b.y - 5 < mainCharacter.y + man1.height) 
						&& (b.y + b.height + 5 > mainCharacter.y + man1.height) )
			{//if charater is at the bed
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(thing.getClass().getName().equals("Game.Box"))
		{
			Box b = (Box)thing;
			if( (b.x < mainCharacter.x) && (b.x + b.width > mainCharacter.x)
					&& (b.y - 5 < mainCharacter.y + man1.height) 
						&& (b.y + b.height + 5 > mainCharacter.y + man1.height) )
			{//if charater is at the bed
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
		for(Monster monster:monsters) {
			monster.vanish();
		}
		for(Ladder ladder : ladders){
			ladder.vanish();
		}
		floors.clear();
		doors.clear();
		monsters.clear();
		items.clear();
		ladders.clear();
	}

	public Character getCharacter() {
		// TODO Auto-generated method stub
		return this.mainCharacter;
	}
}