package Game;

import java.util.ArrayList;

import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PImage;

public class Character extends AbstractCharacter implements Runnable{
	
	private ArrayList<Bullet> bullets;
	public int waitAttackTime=0;
	
	public Character(PApplet parent, PImage chaImage, String name, float x, float y , int HP, GameStage gs){
        Ani.init(parent);
		floors = new ArrayList<Floor>();
		this.gs = gs;
		Thread ch = new Thread(this);
		ch.start();
		this.x=x;
		this.y=y;
		this.name=name;
		this.parent=parent;
		this.MAX_HP=HP;
		this.now_HP=HP;
		this.chaImage=chaImage;
		this.bullets=new ArrayList<Bullet>();
	}
	
	//the effect of gravity that make character fall down
    public void fallDown(){
    	isGround=false;
    	for(Floor floor : floors){
    		if(floor.IsOnGround(this))
    		{
    			isGround=true;
    			break;
    		}
    	}
    	for(Floor floor : floors){
    		if( floor.IsCeiling(this)&&velocityForDirectionY>0 ) 
    		{
    			velocityForDirectionY = 0;
    		}
    		else if(floor.IsGround(this) && velocityForDirectionY<0)
    		{
    			velocityForDirectionY=0;
    		}
    	}
       if(!isGround && velocityForDirectionY>=-15)
    	   velocityForDirectionY-=gravity/20;
    		
    	//because the coordinate y become small when "move up", if velocityForDirectionY>0, y-velocityForDirectionY is "move up"

       y-=velocityForDirectionY/20;
    	
	}
	
    //always character move, stop when velocityForDirectionX=0, 
    @Override
    public void move(){
    	
    	x+=velocityForDirectionX/20;

	}
    
    @Override
    public void move(String direction){

		if(direction.equals("left")){
			velocityForDirectionX=-20;
		}
		else if(direction.equals("right")){
			velocityForDirectionX=20;
		}
		else if(direction.equals("stop")){
			velocityForDirectionX=0;
		}
	}
	
	public void jump(){
		if(velocityForDirectionY==0)
			velocityForDirectionY += 35;
	}
	
	@Override
    public void attack(){
		if(bulletDirection.equals("right"))
			bullets.add(new Bullet(gs.bullet,x+40,y+15,30));
		else if(bulletDirection.equals("left"))
			bullets.add(new Bullet(gs.bullet,x-12,y+15,-30));
	}
	

	public void beAttacked() {
		
		
	}
    
	public ArrayList<Bullet> getBullet(){
		return bullets;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 1;
		
		while(true) {
			
			try {
				if(this.velocityForDirectionY>0)
					UpDown="Up";
				else if(this.velocityForDirectionY<0)
					UpDown="Down";
				if(direction.equals("left")||direction.equals("right")){
					int moving=1;
					for(Floor floor : floors){
						if(floor.IsFloor(this))
							moving=0;
						}
					if(moving==1)
						move();
				}
				fallDown();
				if(!bullets.isEmpty())
					for(Bullet bullet: bullets){
							bullet.move();
					}
				System.out.println(bullets.size());
				Thread.sleep(5);
				if(waitAttackTime>0)
					waitAttackTime--;
				if(waitAttackTime==0){
					this.canAttack=true;
				}
				if(this.isAttack){
					if(this.canAttack){
						this.attack();
						this.waitAttackTime=100;
						this.canAttack = false;
					}
					if(bulletDirection.equals("right")){
						if(i%80 == 0)
							chaImage = gs.getImage(gs.man_a[1]);
						if(i%80 == 20)
							chaImage = gs.getImage(gs.man_a[2]);
						if(i%80 == 40)
							chaImage = gs.getImage(gs.man_a[3]);
						if(i%80 == 60)
							chaImage = gs.getImage(gs.man_a[4]);
					}
					else if(bulletDirection.equals("left")){
						if(i%80 == 0)
							chaImage = gs.getImage(gs.man_a[5]);
						if(i%80 == 20)
							chaImage = gs.getImage(gs.man_a[6]);
						if(i%80 == 40)
							chaImage = gs.getImage(gs.man_a[7]);
						if(i%80 == 60)
							chaImage = gs.getImage(gs.man_a[8]);
					}
					
				}
				else if(direction.equals("right") && this.isWalk) {
					if(i%80 == 0)
						chaImage = gs.getImage(gs.man1);
					if(i%80 == 20)
						chaImage = gs.getImage(gs.man2);
					if(i%80 == 40)
						chaImage = gs.getImage(gs.man3);
					if(i%80 == 60)
						chaImage = gs.getImage(gs.man4);
				}
				else if(direction.equals("left") && this.isWalk) {
					if(i%80 == 0)
						chaImage = gs.getImage(gs.man5);
					if(i%80 == 20)
						chaImage = gs.getImage(gs.man6);
					if(i%80 == 40)
						chaImage = gs.getImage(gs.man7);
					if(i%80 == 60)
						chaImage = gs.getImage(gs.man8);
				}
				else 
				{
					for(Floor floor : floors){
						if(floor.IsGround(this)){
							chaImage = gs.getImage(gs.man);
							break;
						}
					}
				}	
				//System.out.println(i);
				i ++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addFloor(ArrayList<Floor> floors)
	{
		this.floors = floors;
	}
	
	public void deleteFloor()
	{
		floors.clear();
	}



}
