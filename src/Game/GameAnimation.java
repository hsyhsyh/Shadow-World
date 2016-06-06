package Game;

import java.awt.*;

import javax.swing.JFrame;

import processing.core.PApplet;
import processing.core.PImage;

import java.applet.*;  
  
public class GameAnimation extends PApplet {  
	private static final long serialVersionUID = 1L;
    int index=0;   
    PImage [] imgs=new PImage[4];
    public GameAnimation(JFrame window){
    	window = new JFrame("Shadow-World");
    }
    public void setup() {
        for(int i=0;i<4;i++){  
        	this.imgs[i] = loadImage("start" + (i+1) + ".png"); 
            }
    }  
    
  
    public void draw() {
    	if(index<4){
    		image(imgs[index], 0, 0);
    		try{  
    			Thread.sleep(1000);  
            	}  
    		catch(Exception e){  
    			e.printStackTrace();  
            	}  
    		index=index+1;  
    	}
    	else if(index == 4){
    		Gamestart applet3 = new Gamestart(new Main());//?????????????
			applet3.init();
			applet3.start();
			applet3.setFocusable(true);
			index=index+1; 
    	}
    } 
} 