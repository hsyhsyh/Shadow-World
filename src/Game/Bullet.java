package Game;

import processing.core.PImage;

public class Bullet {
	

	private PImage image;
	private float velocity;
	public float x,y;
	
	Bullet(PImage image, float x, float y,float velocity){
		this.image=image;
		this.x=x;
		this.y=y;
		this.velocity=velocity;
	}
	
	public void move(){
		x+=velocity/20;
	}
	
    public PImage getImage(){
    	return this.image;
    }
    
	public boolean IsBoundary(Bullet bullet){
		if(bullet.x>1000||bullet.x<0||bullet.y<0||bullet.y>500)
			return false;
		else return true;
	}

}
