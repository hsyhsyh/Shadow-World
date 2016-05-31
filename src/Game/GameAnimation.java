package Game;

import java.awt.*;

import processing.core.PApplet;
import processing.core.PImage;

import java.applet.*;  
  
public class GameAnimation extends PApplet implements Runnable {  
	private static final long serialVersionUID = 1L;
    int index=0;   
    PImage [] imgs=new PImage[3];  
    public void init() {  
        for(int i=0;i<3;i++){  
        	this.imgs[i] = loadImage("start" + (i+1) + ".png"); 
            }  
        new Thread(this).start();  
    }  
    
  
    public void paint(Graphics g) {  
    	image(imgs[index], 80, 320);
        g.setFont(new Font(null,Font.ITALIC|Font.BOLD,30));  
        g.drawString(Integer.toString(index), 0, 60 );   
  
    }  
    public void run(){  
        while(true){  
            try{  
                Thread.sleep(100);  
                }  
            catch(Exception e){  
                e.printStackTrace();  
                }  
            index=(index+1)%3;   
            repaint();  
            }  
        }  
}  