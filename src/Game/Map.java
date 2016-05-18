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
	

}
