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
	
	//When the Character jump and fall down to the ground, set the coordinate y for Character
	public void setToGround(AbstractCharacter ch){
		if(ch.y>=infHeight-100)
			ch.y=infHeight-100;
	}


}
