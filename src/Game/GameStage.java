package Game;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;



public class GameStage extends PApplet{
	private static final long serialVersionUID = 1L;
	private final static int width = 1000, height = 500;
	private GameStage self;
	private Main m;
	private Gamestart gs;
	public PImage man, bullet, books, book, bloodletter, diamand, phone, skull, monster, 
				monster2, strike, box, bed, ladder, door1, door2, man1, man2, man3, man4
                , man5, man6, man7, man8, man_c1, man_c2, man_s1, man_s2,dead_man, 
                kidnap, kidnap2, fireBall1, fireBall2, cellphone;

	public PImage[] man_a = new PImage[10];
	private Character mainCharacter; 
	private ArrayList<Monster> monsters;
	private ArrayList<Floor> floors;
	private ArrayList<Door> doors;
	private ArrayList<AbstractItem> items;
	private ArrayList<Ladder> ladders;
	private Dialog dialog;
	public boolean hasdialog;
	private int stage_num;
	public boolean isLoading = true;
	private boolean is_transport;
	public boolean firststart,stage_2_door,stage_3_door,stage_5_floor, stage_5_box_1
			,stage_5_box_2, stage_8_bookcase_1, stage_8_bookcase_2,be_end,the_end
			,end_dialog, is_voting, change;
	private int alpha, hurt_alpha;
	public int goalX,goalY;
	private PFont cnFont;
	public float GameOver_x = 350,GameOver_y = 0;
	public int GameOver_color = 250, GameOver_color2 = 0;
	Minim minim;
	AudioPlayer song;
	public AudioPlayer effect[]=new AudioPlayer[10];
	
	public GameStage(Main m)
	{
		this.m = m;
	}
	
	public void setup() {
		
		cnFont = new PFont(new Font
				(this.getClass().getResource("/NotoSansHantRegular.otf").getPath()
						, Font.PLAIN, 30), true);
		
		size(width, height);
		smooth();
		self = this;
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
		this.man_s1 = loadImage("man_sit1.png");
		this.man_s2 = loadImage("man_sit2.png");
		this.dead_man = loadImage("man_die.png");
		this.kidnap = loadImage("kidnap.png");
		this.kidnap2 = loadImage("kidnap2.png");
		this.cellphone = loadImage("phone.png");
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
		this.fireBall1 = loadImage("fireball.png");
		this.fireBall2 = loadImage("fireball2.png");
		mainCharacter = new Character(this,man,"none",0,0,100,this);
		monsters = new ArrayList<Monster>();
		floors = new ArrayList<Floor>();
		doors = new ArrayList<Door>();
		items = new ArrayList<AbstractItem>();
		ladders = new ArrayList<Ladder>();
		dialog = new Dialog();
		
		stage_num = 1;
		
		
		isLoading  = false;
		hasdialog = false;
		is_transport = false;
		stage_2_door = stage_3_door = stage_5_floor = stage_5_box_1 
				= stage_5_box_2 = stage_8_bookcase_1 = stage_8_bookcase_2 = be_end 
				= the_end = end_dialog = is_voting = change = false;
		firststart = true;
		loadData();
		minim = new Minim(this);
		song=minim.loadFile("shadow.wav");
		song.loop();
		effect[0]=minim.loadFile("shoot.wav");
		effect[1]=minim.loadFile("levelUp.wav");
   		Thread ch = new Thread(new Runnable(){
			public void run(){
				while(true)
				{
					System.out.print(change);
					if(change)
					{
						m.change_into_applet(gs);
						break;
					}
				}
				
			}
		});
		ch.start();
	}
	
	public void draw() 
	{
		
		background(255);
        strokeWeight(1);
        //blood
        if(stage_num == 3 || stage_num == 7) {
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
        //level
        if(stage_num == 3 || stage_num == 7) {
        this.textFont(createFont("Arial", 12), 20);
        this.text("LEVEL  "+mainCharacter.level , 200, 190);
        }
        else {
        	this.textFont(createFont("Arial", 12), 20);
            this.text("LEVEL  "+mainCharacter.level , 200, 80);
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
        		ladder.display();
            }
        }
        
        image(this.mainCharacter.getImage(), mainCharacter.x, mainCharacter.y);
        for(Bullet bullet: mainCharacter.getBullet()){
        	image(bullet.getImage(),bullet.x,bullet.y);
        	}
        
        for(Monster monster: monsters)
        	for(Bullet bullet: monster.getBullet()){
        	image(bullet.getImage(),bullet.x,bullet.y);
        	}
   
        if(end_dialog)
		{
			fill(0,0,0,alpha);
        	rect(0,0,width,height);
        	if(alpha == 255 && !hasdialog && !change)
        	{
        		String[] en = new String[8];
        		en[0] = "喂！你睡一整天了耶！\n身為你的室友我真的看不下去了，你是要不要起床啊？";
        		en[1] = "我......回來了？我沒死？";
        		en[2] = "你在說什麼啊？\n喂我跟你說，今天老師說他期末考有一題改錯了\n所以幾乎全班都有加到分";
        		en[3] = "所以說......";
        		en[4] = "所以說你沒有被二一";
        		en[5] = "喔喔喔太好啦！";
        		en[6] = "喂高興就高興，不要鬼吼鬼叫的吵死啦！";
        		en[7] = "管你的哈哈哈！";
        		boolean[] as = new boolean[8];
        		as[0] = as[1] = as[2] = as[3] = as[4] = as[5] =as[6] = false;
        		as[7] = false;
        		opendialog(en,as);
//        		===================the end=====================
        		Thread end = new Thread(new Runnable(){
        			public void run(){
        				try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        				while(true)
        				{
        					System.out.println(hasdialog);
        					if(!hasdialog)
        					{
        						change = true;
        						break;
        					}
        				}
        			}
        		});
        		end.start();
        	}
		}
        
        if(hasdialog)
        {
        	dialog.display();
        } 
		
		fill(255,0,0,hurt_alpha);
        rect(0,0,width,height);
        hurt_alpha-=10;
        if(hurt_alpha < 0)
        {
        	hurt_alpha = 0;
        }
        
		if(is_transport)
        {
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
		
		
		
		//Game over
		if(mainCharacter.now_HP <= 0) {
			fill(this.GameOver_color,GameOver_color2,0);
			this.textFont(createFont("Arial", 12), 50);
	        this.text("Game over", this.GameOver_x, this.GameOver_y);
	        fill(0);
		}
			
		
	}
	
	private void loadData(){
		
		clearplace();
		mainCharacter.deleteFloor();
		mainCharacter.deleteLadder();
		System.out.println(stage_num);
		String[] s,t,u;
		boolean[] a,b,c;
		switch(stage_num)
		{
		case 0://test stage
			mainCharacter.x = 120;
			mainCharacter.y = 320;
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			floors.add(new Floor(300,380,100, 20));
			floors.add(new Floor(420,340,100, 20));
			floors.add(new Floor(540,300,100, 20));
			floors.add(new Floor(660,360,100, 20));
			doors.add(new Door( 80, 300, door2, 0, 120, 320,true));
			doors.add(new Door( 300, 100, door2, 0, 120, 320,true));
			s = new String[2];
			s[0] = "不知道是誰遺落在這裡的手機，似乎是摔壞了\n手機只能開啟某個APP";
			s[1] = "要開啟投票系統嗎？\nY鍵確定，N鍵......A鍵返回";
			a = new boolean[2];
			a[0] = false;
			a[1] = false;
			t = new String[4];
			t[0] = "不知道是誰遺落在這裡的手機，似乎是摔壞了\n手機只能看簡訊或開啟某個APP";
			t[1] = "要做什麼呢？\nY鍵開啟投票系統，A鍵看簡訊，N鍵返回";
			t[2] = "我終於知道是誰偷我的錢了，明明小偷還跟我炫耀過......\n可笑的是我居然反而懷疑你"
					+ "\n如果我們能活著出去的話，我一定要跟你說聲對不起......";
			t[3] = "手機裡只有這封簡訊能看......";
			b = new boolean[4];
			b[0] = false;
			b[1] = false;
			b[2] = false;
			b[3] = true;
			items.add(new Cellphone(cellphone,200,350,this,s,a,t,b,true));
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
			doors.add(new Door( 860, 320, door2, 2, 50, 330,true));
			ladders.add(new Ladder(280, 160, 4, ladder, this));
			ladders.add(new Ladder(280, 365, 1, ladder, this));
			s = new String[2];
			s[0] = "雖然失去了色彩，但還是看的出來這就是自己的位置。\n在枕頭和書桌的正中央各貼著一張紙條。";
			s[1] = "左右方向鍵行走，上方向鍵可以開門以及爬梯子，下方向鍵可以蹲下\n以及下梯子，A鍵可以調查事物，Z鍵可以發射子彈。";
			a = new boolean[2];
			a[0] = false;
			a[1] = false;
			items.add(new OtherItem(bed, 60, 110, this ,s,a, null,null));
			mainCharacter.addFloor(floors);
			mainCharacter.addLadder(ladders);
			if(firststart)
			{
				String[] start = new String[6];
				start[0] = "煩耶！誰一直吵我睡覺啦！\n......紙條？";
				start[1] = "左右方向鍵行走，上方向鍵可以開門以及爬梯子，下方向鍵可以蹲下\n以及下梯子，A鍵可以調查事物，Z鍵可以發射子彈。";
				start[2] = "什麼鬼東西啊？扔掉算了！\n......嗯？";
				start[3] = "這裡是......宿舍？為什麼都是黑白的？\n到底......發生什麼事了？";
				start[4] = "呃？在我枕頭底下這個難道是......槍？\n為什麼我會有這種東西？";
				start[5] = "......\n不管了，先到處看看再說。";
				a = new boolean[6];
				a[0] = a[1] = a[2] = a[3] = a[4] = a[5] = true;
				opendialog(start,a);
				firststart = false;
			}
			if(the_end)
			{
				u = new String[4];
				u[0] = "最後，還是回到這裡了嗎......\n看來好像出不去了呢......";
				u[1] = "昨天我還因為成績而覺得沮喪\n沒想到一覺醒來之後就再也回不去了......";
				u[2] = "哈哈......\n成績不好又怎樣？被二一了又怎樣？只要還活著就還有機會啊！";
				u[3] = "我現在才終於了解，人總是要失去了才懂得珍惜啊......";
				c = new boolean[4];
				c[0] = true;
				c[1] = true;
				c[2] = true;
				c[3] = true;
				opendialog(u,c);
				Thread e = new Thread(new Runnable(){
					public void run()
					{
						while(true)
						{
							System.out.println(hasdialog);
							if(!hasdialog)
							{
								end_dialog = true;
								Ani.to(self, (float)1.5, "alpha", 255);
								break;
							}
						}
					}
				});
				e.start();
				the_end = false;
			}
			break;
		case 2:
			
			floors.add(new Floor(0, 0, 1000, 40));
			floors.add(new Floor(0, 430, 1000, 40));
			floors.add(new Floor(0, 0, 40, 500));
			floors.add(new Floor(960,0,40, 500));
			floors.add(new Floor(0,225,450, 15));
			floors.add(new Floor(550,315,450, 15));
			floors.add(new Floor(550,160,450, 15));
			doors.add(new Door( 50, 330, door2, 1, 860, 320,true));
			doors.add(new Door( 860, 330, door2, 3, 50, 220,true));
			if(stage_2_door && !stage_5_floor)
			{
				doors.add(new Door( 860, 60, door2, 4, 50 ,320,true));
			}
			else
			{
				doors.add(new Door( 860, 60, door1, 4, 50 ,320,false));
			}
			
			s = new String[2];
			s[0] = "不知道是誰遺落在這裡的大螢幕手機，似乎是摔壞了\n手機只能開啟某個APP";
			s[1] = "要開啟投票系統嗎？\nY鍵確定，N鍵......A鍵返回";
			a = new boolean[2];
			a[0] = false;
			a[1] = false;
			t = new String[4];
			t[0] = "不知道是誰遺落在這裡的大螢幕手機，似乎是摔壞了\n手機只能看簡訊或開啟某個APP";
			t[1] = "要做什麼呢？\nY鍵開啟投票系統，A鍵看簡訊，N鍵返回";
			t[2] = "我終於知道是誰偷我的錢了，明明小偷還跟我炫耀過......\n可笑的是我居然反而懷疑你"
					+ "\n如果我們能活著出去的話，我一定要跟你說聲對不起......";
			t[3] = "手機裡只有這封簡訊能看......";
			b = new boolean[4];
			b[0] = false;
			b[1] = false;
			b[2] = false;
			b[3] = true;
			items.add(new Cellphone(cellphone,60,170,this,s,a,t,b,false));
			mainCharacter.addFloor(floors);
			break;
		case 3:
			floors.add(new Floor(0, 0, 1000, 150));
			floors.add(new Floor(0, 320, 1000, 180));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			doors.add(new Door( 50, 220, door2, 2, 860, 330,true));
			doors.add(new Door( 800, 220, door1, 5, 50, 100,false));
			s = new String[4];
			s[0] = "喂！你還好嗎？沒事吧？\n......你說什麼？太小聲我聽不清楚？";
			s[1] = "喂？......喂！\n「沒想到我最後一次跟我媽說話竟然是跟她吵架」？\n不要隨便替自己立flag啊！";
			s[2] = "喂！什麼叫「我把希望交給你了」，說的好像是你的遺言似的\n想點正面的東西對你的生命比較好不是嗎？";
			s[3] = "喂喂！\n......交給我這把鑰匙之後居然就真的死了\n嘖，這種被託付希望的感覺真不好受。";
			a = new boolean[4];
			a[0] = true;
			a[1] = true;
			a[2] = true;
			a[3] = true;
			t = new String[2];
			t[0] = "雖然找不到任何傷口，卻一動也不動的冰冷遺體。";
			t[1] = "嘖......\n我會連你的份一起活下去的，你就好好安息吧。";
			b = new boolean[2];
			b[0] = false;
			b[1] = true;
			if(!stage_5_floor)
			{
				items.add(new Deadman(kidnap,kidnap2,600,255,this,s, a,t,b));
			}
			if(!be_end)
			{
				monsters.add(new Monster(this,monster,"none",400,220,100,this,350,450));
			}
			mainCharacter.addFloor(floors);
			if(be_end)
			{
				u = new String[2];
				u[0] = "怎麼又回到這裡了......我開錯門了嗎？\n不對，門怎麼鎖住了回不去？";
				u[1] = "......\n算了，還是繼續看看其他地方吧";
				c = new boolean[2];
				c[0] = true;
				c[1] = true;
				opendialog(u,c);
				be_end = false;
				the_end = true;
			}
			break;
		case 4:
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			floors.add(new Floor(750,305,250, 15));
			floors.add(new Floor(50,200,200, 15));
			floors.add(new Floor(400,250,100, 15,300,600));//maybe can move(add left and right bound in the end)
			doors.add(new Door( 50, 320, door2, 2, 860, 60,true));
			doors.add(new Door( 850, 320, door2, 5, 50, 320,true));
			monsters.add(new Monster(this,monster,"none",200,220,100,this,150,250));
			monsters.add(new Monster(this,monster,"none",400,220,100,this,350,450));
			s = new String[2];
			s[0] = "宛如壁紙一般，側面看過去甚至看不見的箱子\n詭異的是，從正面伸手居然還摸的到木質般的實體";
			s[1] = "這是什麼鬼箱子啊......\n碰都不想碰......";
			a = new boolean[2];
			a[0] = false;
			a[1] = true;
			items.add(new Box(box, 250, 282, this ,s,a, null,null,null,null, false));
			t = new String[4];
			t[0] = "牆壁上貼著一張便利貼，似乎才剛貼上去不久。\n上面以潦草的字跡寫了一段話。";
			t[1] = "我是@%#，我那個爛男友正要去前面那個房間，\n說是在箱子中找到一扇門。\n如果你有看到的話，就往前走吧...";
			t[2] = "對不起，跟你分開之後，我想了一想，\n我不應該為了開冷氣這種小事跟你吵架的，\n如果可以從這裡出去，我們還會是好室友，好閨蜜口";
			t[3] = "嘖，最後一句話好像沒寫完，真令人在意......\n那個遮住名字的污漬也是......";
			b = new boolean[4];
			b[0] = false;
			b[1] = false;
			b[2] = false;
			b[3] = true;
			items.add(new Paper(bloodletter, 90, 140, this ,t,b,null, null));
			mainCharacter.addFloor(floors);
			break;
		case 5:
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			floors.add(new Floor(50,200,250, 15));
			floors.add(new Floor(750,305,250, 15));
			doors.add(new Door( 50, 100, door2, 3, 800, 220,true));
			if(stage_5_floor)
			{
				doors.add(new Door( 50, 320, door1, 4, 850, 320,false));
			}
			else
			{
				doors.add(new Door( 50, 320, door2, 4, 850, 320,true));
			}
			
			doors.add(new Door( 850, 320, door2, 7, 50, 220,true));
			s = new String[2];
			s[0] = "宛如壁紙一般，側面看過去甚至看不見的箱子\n詭異的是，從正面伸手居然還摸的到木質般的實體";
			s[1] = "這是什麼鬼箱子啊......\n碰都不想碰......";
			a = new boolean[2];
			a[0] = false;
			a[1] = true;
			t = new String[1];
			t[0] = "如果紙條寫的是對的話，那這裡應該會有門......\n咦咦還真的有！";
			b = new boolean[1];
			b[0] = true;
			u = new String[1];
			u[0] = "摸起來是箱子的東西居然用力推開會變成門......\n這到底是什麼鬼地方啊.......";
			c = new boolean[1];
			c[0] = true;
			items.add(new Box(box, 400, 282, this ,s,a,t,b, u,c, true));
			if(stage_5_floor)
			{
				floors.add(new Floor(450,250,150, 15));
			}
			mainCharacter.addFloor(floors);
			monsters.add(new Monster(this,monster,"none",600,320,100,this,600,750));
			monsters.add(new Monster(this,monster,"none",500,200,100,this,600,750));
			break;
		case 6:
			floors.add(new Floor(0, 0, 1000, 50));
			floors.add(new Floor(0, 420, 1000, 80));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			floors.add(new Floor(750,200,250, 20));
			doors.add(new Door( 850, 100, door2, 5, 450, 282,true));
			ladders.add(new Ladder(750, 190, 4, ladder, this));
			s = new String[4];
			s[0] = "地上寫著一段話\n雖然變成了深灰色，卻仍然可以讓人感覺出那是用血書寫而成的";
			s[1] = "對不起，@%#.......\n我居然懷疑你劈腿......\n如果有來世，能讓我繼續守護妳嗎......";
			s[2] = "我們系的$*&繼續往前走了，去跟他會合吧......\n然後，活下去......";
			s[3] = "......\n看來這裡也有一個人死了呢......";
			a = new boolean[4];
			a[0] = false;
			a[1] = false;
			a[2] = false;
			a[3] = true;
			t = new String[2];
			t[0] = "淺灰色的頭骨，似乎已經在這裡很長一段時間了\n僅存的手臂骨正指著前方不遠處的地板";
			t[1] = "......\n是人的骨頭嗎......";
			b = new boolean[2];
			b[0] = false;
			b[1] = true;
			items.add(new Bloodletter(null,570,320,this,s,a,null,null));
			items.add(new OtherItem(skull,680,340,this,t,b,null,null));
			mainCharacter.addFloor(floors);
			mainCharacter.addLadder(ladders);
			break;
		case 7:
			floors.add(new Floor(0, 0, 1000, 150));
			floors.add(new Floor(0, 320, 1000, 180));
			floors.add(new Floor(0, 0, 50, 500));
			floors.add(new Floor(950,0,50, 500));
			doors.add(new Door( 50, 220, door2, 5, 850, 320,true));
			doors.add(new Door( 850, 220, door2, 8, 50, 150,true));
			monsters.add(new Monster(this,monster,"none",400,200,100,this,350,450) );
			monsters.add(new Monster(this,monster,"none",500,200,100,this,350,550) );
			monsters.add(new Monster(this,monster,"none",600,200,100,this,350,650) );
			t = new String[2];
			t[0] = "雖然找不到任何傷口，卻一動也不動的冰冷遺體。";
			t[1] = "嘖......\n又是一個死人嗎.......";
			b = new boolean[2];
			b[0] = false;
			b[1] = true;
			items.add(new Deadman(dead_man,dead_man,600,265,this,null, null,t,b));
			s = new String[2];
			s[0] = "不知道是誰遺落在這裡的大螢幕手機，似乎是摔壞了\n手機只能開啟某個APP";
			s[1] = "要開啟投票系統嗎？\nY鍵確定，N鍵......A鍵返回";
			a = new boolean[2];
			a[0] = false;
			a[1] = false;
			t = new String[4];
			t[0] = "不知道是誰遺落在這裡的大螢幕手機，似乎是摔壞了\n手機只能看簡訊或開啟某個APP";
			t[1] = "要做什麼呢？\nY鍵開啟投票系統，A鍵看簡訊，N鍵返回";
			t[2] = "我終於知道是誰偷我的錢了，明明小偷還跟我炫耀過......\n可笑的是我居然反而懷疑你"
					+ "\n如果我們能活著出去的話，我一定要跟你說聲對不起......";
			t[3] = "手機裡只有這封簡訊能看......";
			b = new boolean[4];
			b[0] = false;
			b[1] = false;
			b[2] = false;
			b[3] = true;
			items.add(new Cellphone(cellphone,700,270,this,s,a,t,b,true));
			mainCharacter.addFloor(floors);
			break;
		case 8:
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
			doors.add(new Door( 50, 150, door2, 7, 850, 220,true));
			s = new String[2];
			s[0] = "彷彿處於2D空間，側面看過去甚至會消失的書櫃\n詭異的是，從正面伸手居然還摸的到各式各樣的書本";
			s[1] = "這什麼鬼東西啊......\n還是離遠一點好了......";
			a = new boolean[2];
			a[0] = false;
			a[1] = true;
			t = new String[2];
			t[0] = "彷彿處於2D空間，側面看過去甚至會消失的書櫃\n詭異的是，從正面伸手居然還摸的到各式各樣的書本";
			t[1] = "嗯？書本之間居然還有一根拉桿？剛剛明明就沒有的啊？\n嘖......反正也沒有路可以走了，就直接拉下去算了";
			b = new boolean[2];
			b[0] = false;
			b[1] = true;
			u = new String[2];
			u[0] = "彷彿處於2D空間，側面看過去甚至會消失的書櫃\n詭異的是，從正面伸手居然還摸的到各式各樣的書本";
			u[1] = "一個沒注意拉桿又不見了......\n我還是離它遠一點好了......";
			c = new boolean[2];
			c[0] = false;
			c[1] = true;
			items.add(new Book(books,450,100,this,s,a,t,b,u, c, true));
			s = new String[2];
			s[0] = "不知道是誰遺落在這裡的大螢幕手機，似乎是摔壞了\n手機只能開啟某個APP";
			s[1] = "要開啟投票系統嗎？\nY鍵確定，N鍵......A鍵返回";
			a = new boolean[2];
			a[0] = false;
			a[1] = false;
			t = new String[4];
			t[0] = "不知道是誰遺落在這裡的大螢幕手機，似乎是摔壞了\n手機只能看簡訊或開啟某個APP";
			t[1] = "要做什麼呢？\nY鍵開啟投票系統，A鍵看簡訊，N鍵返回";
			t[2] = "我終於知道是誰偷我的錢了，明明小偷還跟我炫耀過......\n可笑的是我居然反而懷疑你"
					+ "\n如果我們能活著出去的話，我一定要跟你說聲對不起......";
			t[3] = "手機裡只有這封簡訊能看......";
			b = new boolean[4];
			b[0] = false;
			b[1] = false;
			b[2] = false;
			b[3] = true;
			items.add(new Cellphone(cellphone,200,370,this,s,a,t,b,false));
			mainCharacter.addFloor(floors);
			mainCharacter.addLadder(ladders);
			monsters.add(new Monster(this,monster,"none",600,320,100,this,600,750));
			monsters.add(new Monster(this,monster,"none",600,320,100,this,600,750));
			monsters.add(new Monster(this,monster,"none",300,320,100,this,100,750));
			monsters.add(new Monster(this,monster,"none",700,50,100,this,600,750));
			break;
		}
	}
	
	public void keyPressed() {
		// TODO Auto-generated method stub
		//WARNING: 需切換至英文輸入法
		switch(keyCode)
		{
		case KeyEvent.VK_LEFT:
			if(!mainCharacter.isCrouch && mainCharacter.now_HP > 0 && !hasdialog) {
			if(mainCharacter.isOnLadder){
				mainCharacter.isOnLadder=false;
				mainCharacter.y-=20;
			}
			mainCharacter.direction = "left";
			mainCharacter.bulletDirection="left";
			mainCharacter.move("left");
			mainCharacter.isWalk = true;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(!mainCharacter.isCrouch && mainCharacter.now_HP > 0 && !hasdialog) {
			if(mainCharacter.isOnLadder){
				mainCharacter.isOnLadder=false;
				mainCharacter.y-=20;
			}
			mainCharacter.direction = "right";
			mainCharacter.bulletDirection="right";
			mainCharacter.move("right");
			mainCharacter.isWalk = true;
			}
			break;
		case KeyEvent.VK_SPACE://jump
			if(!mainCharacter.isOnLadder && !mainCharacter.isCrouch && 
					mainCharacter.isGround && mainCharacter.now_HP > 0 && !hasdialog)
			{
				mainCharacter.jump();
			}
			break;
		case KeyEvent.VK_DOWN://down to ladder
			if(mainCharacter.now_HP > 0 && !hasdialog) {
				for(Ladder ladder : ladders)
				{
					if(ladder.isLadder(mainCharacter)){
						if(!mainCharacter.isOnLadder)
							mainCharacter.y-=12;
						mainCharacter.isOnLadder=true;
						if(ladder.isOnTopLadder(mainCharacter))
							mainCharacter.isOnTopLadder=true;
						mainCharacter.velocityForDirectionY = -5;
					}
				}
				if(!mainCharacter.isOnLadder && mainCharacter.isGround){
					mainCharacter.crouch();
					mainCharacter.direction = "";
				}
			}
			break;
		case KeyEvent.VK_UP://up to ladder
			if(!mainCharacter.isCrouch && mainCharacter.now_HP > 0 && !hasdialog) {
			for(Door d : doors)
			{
				if( whereisch(d) )
				{
					if(d.isopen())
					{
						clearItem();
						transport(d.getgoal());
						goalX = d.goalX - 3;
						goalY = d.goalY - 3;
					}
					else
					{
						String[] s = new String[2];
						s[0] = "不知道是用什麼材質做成的淺灰色門板，就算用腳踹門也不會發出任何\n聲音。";
						s[1] = "打不開啊.......\n是少了鑰匙嗎？";
						boolean[] a = new boolean[2];
						a[0] = false;
						a[1] = true;
						opendialog(s,a);
					}
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
			}
			break;
		case KeyEvent.VK_A:
			if(mainCharacter.now_HP > 0) {
			if(!hasdialog)
			{
				for(AbstractItem i : items)
				{
					if(whereisch(i))
					{
						i.dialog_event();
					}
				}
			}
			else
			{
				dialog.dosomething();
			}
			}
			break;
		case KeyEvent.VK_Z:
			//attack
			if(mainCharacter.now_HP > 0 && !hasdialog) {
			if(mainCharacter.isOnLadder){
				mainCharacter.isOnLadder=false;
				mainCharacter.y-=20;
			}
			mainCharacter.isAttack = true;
			}
			break;
		case KeyEvent.VK_Y:
			if(hasdialog && is_voting)
			{
				is_voting = false;
				dialog.closed();
				
				m.addapplet();
				//投票系統
			}
			break;
		case KeyEvent.VK_N:
			if(is_voting)
			{
				is_voting = false;
				dialog.closed();
			}
			break;
		case KeyEvent.VK_D:
			mainCharacter.isForDemo=true;
			String d[] = new String[1];
			d[0] = "(Demo Mode On)";
			boolean b[] = new boolean[1];
			b[0] = true;
			opendialog(d,b);
			break;
		}
	}

	public void keyReleased() {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT)
		{
			mainCharacter.move("stop");
			mainCharacter.direction = "";
		}
		else if(keyCode == KeyEvent.VK_Z){
			mainCharacter.isAttack = false;
		}
		
		if(keyCode == KeyEvent.VK_DOWN && mainCharacter.isCrouch){
			mainCharacter.unCrouch();
		}
			
	}
	public void opendialog(String[] text, boolean[] m)
	{
		if(text != null)
		{
			hasdialog = true;
			
			dialog.settext(text,m);
			dialog.open();
			dialog.showtext();
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
		private boolean[] mantalk;
		private boolean speed;
		public Dialog()
		{
			wide = 1;
			high = 1;
			hasdialog = true;
			textpagenum = 0;
			now_textpagenum = 0;
			textnum = 0;
			manX = -200;
			manY = 160;
			speed = false;
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
			if(now_textpagenum >= 3)
			{
				is_voting = false;
			}
			if(textnum == text[now_textpagenum].length() && now_textpagenum < textpagenum)
			{
				now_textpagenum++;
				textnum = 0;
				showtext();
				showman();
			}
			else if (textnum == text[now_textpagenum].length() && now_textpagenum == textpagenum)
			{
				closed();
			}
			else if (textnum < text[now_textpagenum].length() &&
					wide == 950 && high == 150 )
			{
				speed = true;
			}
		}
		public void open()
		{
			Ani.to(this, (float)1.0, "wide", 950, Ani.SINE_OUT);
			Ani.to(this, (float)1.0, "high", 150, Ani.SINE_OUT);
			showman();
		}
		public void closed()
		{
			textnum = 0;
			textpagenum = 0;
			now_textpagenum = 0;
			Ani.to(this, (float)0.5, "wide", 1, Ani.SINE_OUT);
			Ani.to(this, (float)0.5, "high", 1, Ani.SINE_OUT);
			Ani.to(this, (float)0.3, "manX", -200, Ani.SINE_OUT);
		}
		public void settext(String[] t, boolean[] m)
		{
			mantalk = m;
			textpagenum = t.length - 1;
			text = new String[textpagenum];
			now_textpagenum = 0;
			text = t;
			textnum = 0;
		}
		public void showtext()
		{
			Thread t = new Thread(new Runnable(){
				public void run(){
					while(textnum != text[now_textpagenum].length())
					{
						try {
							if(speed)
							{
								textnum = text[now_textpagenum].length();
								speed = false;
							}
							else
							{
								textnum++;
								Thread.sleep(100);
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			t.start();
		}
		private void showman()
		{
			if(mantalk[now_textpagenum])
			{
				Ani.to(this, (float)0.3, "manX", 20, Ani.SINE_OUT);
			}
			else
			{
				Ani.to(this, (float)0.3, "manX", -200, Ani.SINE_OUT);
			}
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
		else if(thing.getClass().getName().equals("Game.Deadman"))
		{
			Deadman b = (Deadman)thing;
			if( (b.x < mainCharacter.x) && (b.x + b.width > mainCharacter.x)
					&& (b.y - 3 < mainCharacter.y + man1.height) 
						&& (b.y + b.height + 50 > mainCharacter.y + man1.height) )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(thing.getClass().getName().equals("Game.Paper"))
		{
			
			Paper b = (Paper)thing;
			if( (b.x - 3 < mainCharacter.x) && (b.x + b.width + 3 > mainCharacter.x)
					&& (b.y - 50 < mainCharacter.y + man1.height) 
						&& (b.y + b.height + 50 > mainCharacter.y + man1.height) )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(thing.getClass().getName().equals("Game.OtherItem"))
		{
			
			OtherItem b = (OtherItem)thing;
			if( (b.x - 3 < mainCharacter.x) && (b.x + b.width + 3 > mainCharacter.x)
					&& (b.y - 8 < mainCharacter.y + man1.height) 
						&& (b.y + b.height + 8 > mainCharacter.y + man1.height) )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(thing.getClass().getName().equals("Game.Book"))
		{
			
			Book b = (Book)thing;
			if( (b.x - 3 < mainCharacter.x) && (b.x + b.width + 3 > mainCharacter.x)
					&& (b.y - 8 < mainCharacter.y + man1.height) 
						&& (b.y + b.height + 8 > mainCharacter.y + man1.height) )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(thing.getClass().getName().equals("Game.Cellphone"))
		{
			
			Cellphone b = (Cellphone)thing;
			if( (b.x - 3 < mainCharacter.x) && (b.x + b.width + 3 > mainCharacter.x)
					&& (b.y - 40 < mainCharacter.y + man1.height) 
						&& (b.y + b.height + 40 > mainCharacter.y + man1.height) )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(thing.getClass().getName().equals("Game.Bloodletter"))
		{
			
			Bloodletter b = (Bloodletter)thing;
			if( (b.x - 3 < mainCharacter.x) && (b.x + b.width + 3 > mainCharacter.x)
					&& (b.y - 5 < mainCharacter.y + man1.height) 
						&& (b.y + b.height + 5 > mainCharacter.y + man1.height) )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		return false;
	}
	
	public void hurt()
	{
		hurt_alpha = 255;
	}
	
	private void clearplace()
	{
		clearItem();
		floors.clear();
		doors.clear();
		monsters.clear();
		items.clear();
		ladders.clear();
	}
	private void clearItem() {
		for(Monster monster:monsters) {
			monster.vanish();
		}
		for(Ladder ladder : ladders){
			ladder.vanish();
		}
	}

	public void setgs(Gamestart s)
	{
		gs = s;
	}
	public Character getCharacter() {
		// TODO Auto-generated method stub
		return this.mainCharacter;
	}
}