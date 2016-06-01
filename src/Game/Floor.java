package Game;

public class Floor implements Runnable{
	
	private int x,y,width,height,leftBound, rightBound;
	String direction = "right";
	
	//不可移動
	public Floor(int x, int y,int width,int height){
		
		this.x=x;
		this.y=y;
		this.height=height;
		this.width=width;

	}
	
	//可移動
    public Floor(int x, int y,int width,int height,int leftBound,int rightBound){
		
		Thread fr = new Thread(this);
		fr.start();
		this.x=x;
		this.y=y;
		this.height=height;
		this.width=width;
		this.leftBound=leftBound;
		this.rightBound=rightBound;

	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public boolean IsFloor(AbstractCharacter ch){
		if(ch.direction.equals("left")){
			if(this.x+this.width-15<=ch.x && this.x+this.width+5>=ch.x && ch.y<=this.y+this.height && this.y<=ch.y+ch.chaImage.height)
				return true;
			else return false;
		}
		else if(ch.direction.equals("right")){
			if(ch.x+ch.chaImage.width<= this.x+15 && ch.x+ch.chaImage.width>= this.x-5 && ch.y<=this.y+this.height && this.y<=ch.y+ch.chaImage.height)
				return true;
			else return false;
		}
		else return false;
			
	}
	
	public boolean IsOnGround(AbstractCharacter ch){
		if(ch.y+ch.chaImage.height<=this.y+20 && ch.y+ch.chaImage.height>=this.y+3 && ch.x+15<=this.x+this.width && this.x+15<=ch.x+ch.chaImage.width)
			return true;
		else return false;
	}
	
	
	public boolean IsGround(AbstractCharacter ch){
		if(ch.UpDown.equals("Down")){
			if(ch.y+ch.chaImage.height<=this.y+20 && ch.y+ch.chaImage.height>=this.y+3 && ch.x+15<=this.x+this.width && this.x+15<=ch.x+ch.chaImage.width)
				return true;
			else return false;
		}
		else
			return false;
	}
	
	public boolean IsCeiling(AbstractCharacter ch){
		if(ch.UpDown.equals("Up")){
			if(ch.y>=this.y+this.height-20 && ch.y<=this.y+this.height-2 && ch.x+15<=this.x+this.width && this.x+15<=ch.x+ch.chaImage.width)
				return true;
			else return false;
		}
		else
			return false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				if(this.x>=this.rightBound)
					this.direction = "left";
				if(this.x<=this.leftBound)
					this.direction = "right";
				if(this.direction == "right")
				    this.x++;
				else
					this.x--;
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
