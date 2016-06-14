package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import processing.core.PApplet;
import processing.core.PImage;


public class Gamestart extends PApplet implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final static int width = 1000, height = 500;
	private GameStage gs;
	private Main m;
	private PImage startbg;
	JButton start, stop, achievement, statis;
	Achieve achi;
	int index=0;   
    PImage [] imgs=new PImage[4];
    
    public Gamestart(Main main)
    {
    	m = main;
    	index = 5;
    }
    
	public void setup() {
		achi = new Achieve();
		for(int i=0;i<4;i++){  
        	this.imgs[i] = loadImage("start" + (i+1) + ".png"); 
        }
		size(width, height);
		smooth();
		startbg = loadImage("startbg.png");
		this.start = new JButton("Start");
		this.stop = new JButton("Exit");
		this.achievement = new JButton("achievement");
		this.statis = new JButton("statis");
		start.addActionListener(this);
		start.setActionCommand("start");
		stop.addActionListener(this);
		stop.setActionCommand("stop");
		achievement.addActionListener(this);
		achievement.setActionCommand("achievement");
		statis.addActionListener(this);
		statis.setActionCommand("statis");
		this.add(start);
		this.add(stop);
		this.add(achievement);
		this.add(statis);
		
		}
	public void draw() {
		this.setLayout(null);
		if(index<=4){
		if(index<4){
			image(imgs[index], 0, 0);
    		try{  
    			Thread.sleep(1500);  
            	}  
    			catch(Exception e){  
    				e.printStackTrace();  
            	}
			}
			else{
				
				try{  
					Thread.sleep(1500); m.change_into_applet(gs); 
            	}  
    			catch(Exception e){  
    				e.printStackTrace();  
            	}
			};
    		index=index+1;  
    	}
		else if(index == 5){
			image(this.startbg, -300, -300);
			index=index+1;
		}
		else if(index > 5){
			while(index > 5){
				this.start.setLocation(500,100);
	    		this.start.setSize(100, 40);
	 			this.stop.setLocation(500, 400);
	 			this.stop.setSize(100, 40);
	 			this.achievement.setLocation(500, 200);
	 			this.achievement.setSize(100, 40);
	 			this.statis.setSize(100,40);
	 			this.statis.setLocation(500, 300);
			}
		}
	   }
	@Override
	public void actionPerformed(ActionEvent e) {
		if("start".equals(e.getActionCommand())){
			this.remove(start);
			this.remove(stop);
			this.remove(achievement);
			this.remove(statis);
			index = 0;
			//start the game
		}
		else if("achievement".equals(e.getActionCommand())){
			achi.setLocation(100, 50);
			achi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			achi.setSize(1000, 500);
			achi.setVisible(true);
		}
		else if("statis".equals(e.getActionCommand())){
			// show the statis
		}
		else if("stop".equals(e.getActionCommand())){
			//exit game
			exit();
		}
	}
	
	public void setgs(GameStage g)
	{
		gs = g;
	}
		
}