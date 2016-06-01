package Game;

import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.KeyListener;

import processing.core.PApplet;
import processing.core.PImage;


public class Gamestart extends PApplet implements KeyListener{
	private static final long serialVersionUID = 1L;
	private final static int width = 1000, height = 500;
	private boolean laidOut = false;
	private PImage startbg;
	Button start, stop, achievement, statis;
	public void setup() {
		this.startbg = loadImage("startbg.png");
		size(width, height);
		smooth();
		this.start = new Button("Start");
		this.stop = new Button("Exit");
		this.achievement = new Button("achievement");
		this.statis = new Button("statis");
		this.add(start);
		this.add(stop);
		this.add(achievement);
		this.add(statis);
		}
	public void draw() {
	    	this.setLayout(null);
	    	image(this.startbg, -300, -300);
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