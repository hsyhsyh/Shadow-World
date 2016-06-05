package Game;

import java.util.ArrayList;

import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PImage;

public class Character extends AbstractCharacter implements Runnable{
	
	private Bullet bullets[]=new Bullet[20];
	public int waitAttackTime=0;
	private int bulletNumber=0;
	public boolean isMonsterTouch = false;
	public boolean isOnLadder = false;
	public boolean isOnTopLadder = false;
	public boolean isCrouch = false;
	
	public Character(PApplet parent, PImage chaImage, String name, float x, float y , int HP, GameStage gs){
        Ani.init(parent);
		floors = new ArrayList<Floor>();
		ladders = new ArrayList<Ladder>();
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
		for(int i=0;i<20;i++){
			bullets[i]= new Bullet(gs.bullet,10000,10000,0);
		}
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
    		if( floor.IsCeiling(this)&&velocityForDirectionY>0 && !isOnLadder) 
    		{
    			velocityForDirectionY = 0;
    		}
    		else if(isGround && velocityForDirectionY<0 && !isOnTopLadder)
    		{
    			velocityForDirectionY=0;
    		}
    	}
       if(!isGround && !isOnLadder && velocityForDirectionY>=-15)
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
		gs.effect[0].loop();
		gs.effect[0].play();
		if(bulletDirection.equals("right")){
			bullets[bulletNumber%20].x=this.x+40;
			bullets[bulletNumber%20].y=this.y+15;
			bullets[bulletNumber%20].velocity=30;
		}
		else if(bulletDirection.equals("left")){
			bullets[bulletNumber%20].x=this.x-12;
			bullets[bulletNumber%20].y=this.y+15;
			bullets[bulletNumber%20].velocity=-30;
		}
		bulletNumber++;
	}
	

	public void beAttacked() {
		
		
	}
	
	public void crouch(){
		if(!isCrouch){
			isCrouch = true;
			this.y+=38;
		}
	}
	
	public void unCrouch(){
		isCrouch = false;
		chaImage = gs.getImage(gs.man);
		this.y-=38;
	}
    
	public Bullet[] getBullet(){
		return bullets;
	}
	
	private void levelUp(){
		System.out.println(this.experienceValue);
		if(this.level<3){
			if(this.experienceValue>=5*this.level){
				gs.effect[1].loop();
				gs.effect[1].play();
				this.MAX_HP+=10;
				this.level++;
				this.now_HP= this.MAX_HP;
				}
		}
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
				//System.out.println(i);
				i ++;
				
				if(this.now_HP <= 0 && gs.GameOver_y<250) {
					isOnLadder=false;
					gs.GameOver_y +=0.5;
					this.chaImage = gs.dead_man;
				}
				if(gs.GameOver_y == 250) {
					if(i%80 == 0) {
						gs.GameOver_color = 250;
						gs.GameOver_color2 = 250;
					}
					if(i%80 == 40) {
						gs.GameOver_color = 250;
						gs.GameOver_color2 = 0;
					}
				}
				levelUp();
				fallDown();
				for(Bullet bullet: bullets){
					bullet.move();
				}
				Thread.sleep(5);
				
				if(this.now_HP > 0) {
				if(direction.equals("left")||direction.equals("right")){
					int moving=1;
					for(Floor floor : floors){
						if(floor.IsFloor(this))
							moving=0;
						}
					if(moving==1)
						move();
				}
				
				if(isOnLadder){
					isOnLadder=false;
					for(Ladder ladder : ladders){
						if(ladder.isLadder(this))
						{
							isOnLadder=true;
							break;
						}
					}
				}
				if(isOnTopLadder){
					isOnTopLadder=false;
					for(Ladder ladder : ladders){
						if(ladder.isOnTopLadder(this))
						{
							isOnTopLadder=true;
							break;
						}
					}
				}
				if(waitAttackTime>0)
					waitAttackTime--;
				if(waitAttackTime==0){
					this.canAttack=true;
				}
				
				if(this.isCrouch){
					if(bulletDirection.equals("right")){
						chaImage = gs.getImage(gs.man_s1);
					}
					else if(bulletDirection.equals("left")){
						chaImage = gs.getImage(gs.man_s2);
					}
					
				}
				else if(this.isOnLadder){
					if(i%80 == 0)
						chaImage = gs.getImage(gs.man_c1);
					if(i%80 == 40)
						chaImage = gs.getImage(gs.man_c2);
				}
				else if(this.isAttack){
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
				}
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
	
	public void addLadder(ArrayList<Ladder> ladders)
	{
		this.ladders = ladders;
	}
	
	public void deleteLadder()
	{
		ladders.clear();
	}
	


}
