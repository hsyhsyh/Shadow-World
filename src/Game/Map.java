package Game;

public class Map{
	
	private int supHeight,infHeight,supWidth,infWidth;

	
	public Map(int supHeight,int infHeight,int supWidth,int infWidth){

		this.supHeight=supHeight;
		this.infHeight=infHeight;
		this.supWidth=supWidth;
		this.infWidth=infWidth;
	}
	
	public int getSupHeight(){
		return this.supHeight;
	}
	
	public int getInfHeight(){
		return this.infHeight;
	}
	
	public int getSupWidth(){
		return this.supWidth;
	}
	
	public int getInfWidth(){
		return this.infWidth;
	}
	
	public boolean IsGround(AbstractCharacter ch){
		if(ch.y>=infHeight-100)
			return true;
		else
			return false;
	}
	
	//set the bound that character can not go out the boundary 
	public boolean BoundFor(String direction, AbstractCharacter ch) {
	// TODO Auto-generated method stub
		if(direction.equals("left") && ch.x<=supWidth-20 && ch.x>=infWidth+30)
			return true;
		else if(direction.equals("right") && ch.x<=supWidth-50 && ch.x>=infWidth)
			return true;
		else
			return false;
	}

	//When the Character jump and fall down to the ground, set the coordinate y for Character
	public void setToGround(AbstractCharacter ch){
		if(ch.y>=infHeight-100)
			ch.y=infHeight-100;
	}



}
