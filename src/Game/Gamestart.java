package Game;

import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;

import processing.core.PApplet;
import processing.core.PImage;


public class Gamestart extends PApplet implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final static int width = 1000, height = 500;
	private PImage startbg;
	JButton start, stop, achievement, statis;
	Achieve ach;
	int index=0;   
    PImage [] imgs=new PImage[4];
	public void setup() {
		ach = new Achieve(this);
		this.startbg = loadImage("startbg.png");
		for(int i=0;i<4;i++){  
        	this.imgs[i] = loadImage("start" + (i+1) + ".png"); 
        }
		size(width, height);
		smooth();
		this.start = new JButton("Start");
		this.stop = new JButton("Exit");
		this.achievement = new JButton("achievement");
		this.statis = new JButton("statis");
		this.add(start);
		this.add(stop);
		this.add(achievement);
		this.add(statis);
		}
	public void draw() {
		if(index<4){
    		image(imgs[index], 0, 0);
    		try{  
    			Thread.sleep(1500);  
            	}  
    		catch(Exception e){  
    			e.printStackTrace();  
            	}  
    		index=index+1;  
    	}
		else{
	    	this.setLayout(null);
	    	image(this.startbg, -300, -300);
	    	this.start.setLocation(500,100);
	    	this.start.setSize(100, 40);
	    	start.addActionListener(this);
	    	start.setActionCommand("start");
	 		this.stop.setLocation(500, 400);
	 		this.stop.setSize(100, 40);
	 		stop.addActionListener(this);
	 		stop.setActionCommand("stop");
	 		this.achievement.setLocation(500, 200);
	 		this.achievement.setSize(100, 40);
	 		achievement.addActionListener(this);
	 		achievement.setActionCommand("achievement");
	 		this.statis.setSize(100,40);
	 		this.statis.setLocation(500, 300);
	 		statis.addActionListener(this);
	 		statis.setActionCommand("statis");
		}
	   }
	@Override
	public void actionPerformed(ActionEvent e) {
		if("start".equals(e.getActionCommand())){
			//start the game
		}
		else if("achievement".equals(e.getActionCommand())){
			this.stop();
			this.add(ach);
		}
		else if("statis".equals(e.getActionCommand())){
			// show the statis
		}
		else if("stop".equals(e.getActionCommand())){
			//exit game
		}
	}
		
}