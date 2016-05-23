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
	private boolean hasdialog;
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
		map = new Map(50,420,950,50);
		monsters = new ArrayList<Monster>();
		dialog = new Dialog();
		loadData();
		//this.addKeyListener(this);
		isLoading = false;
		stage_num = 0;
		hasdialog = false;
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
			mainCharacter.isWalk = true;
			break;
		case KeyEvent.VK_RIGHT:
			mainCharacter.direction = "right";
			mainCharacter.move("right");
			mainCharacter.isWalk = true;
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
			if(true)//if need dialog
			{
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
			}
			else if(false)//open door
			{
				;
			}
			else//attack
			{
				;
			}
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
        if(hasdialog)
        {
        	dialog.display();
//			Ani.to(dialog, (float)3.0, "wide", 970);
//			Ani.to(dialog, (float)3.0, "high", 170);
        }
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
	
}