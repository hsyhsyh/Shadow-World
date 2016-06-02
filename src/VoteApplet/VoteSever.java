package VoteApplet;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;


public class VoteSever extends PApplet{
	private String file = "src/VoteApplet/resources/data.json";
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<VoteCharacter> characters;
	private ArrayList<VoteCharacter> showlist;
	private PImage bg;
	private boolean done,reset,quit;
	public ChatServer server;
	
	public void setup(){
		size(1000, 500);
		characters = new ArrayList<VoteCharacter>();
		showlist=new ArrayList<VoteCharacter>();
		loadData();
		smooth();
		addMouseListener(this);
		addMouseMotionListener( this);
		
		bg=new PImage();
		bg=loadImage("src/VoteApplet/resources/horror.jpg");
		
		this.setVisible(true);
		
		//socket
		this.server= new ChatServer(8000);
	}
	
public void draw(){
		
		background(255);
		image(bg,0,0);
		stroke(60, 119, 119);
		strokeWeight(4);
		
		for(VoteCharacter character: this.characters){
			character.display_server();
		}
		
		if(done){
			this.fill(0,255,0);
		}else{
			this.fill(255,255,0);
		}
		this.rect(600, 850, 150, 50);
		
		if(reset){
			this.fill(0,255,0);
		}else{
			this.fill(255,255,0);
		}
		this.rect(800, 850, 150, 50);
		if(quit){
			this.fill(0,255,0);
		}else{
			this.fill(255,255,0);
		}
		this.rect(1000, 850, 150, 50);
		
		this.fill(0);
		this.textSize(20);;
		this.text("Done!",645,885);
		this.text("Reset!",845,885);
		this.text("Quit!",1050,885);
	
	}

private void loadData(){
	done=false;
	reset=false;
	quit=false;
	
	data = loadJSONObject(file);
	
	nodes = data.getJSONArray("nodes");
	links = data.getJSONArray("links");

	System.out.println("Number of nodes: " + nodes.size());
	System.out.println("Number of links: " + links.size());
	int total=0;
	for(int i=0; i<nodes.size(); i++){
		JSONObject node = nodes.getJSONObject(i);
		VoteCharacter character=new VoteCharacter(this, node.getString("name"), 100+400*(i/2),100+300*(i%2),node.getInt("v0"),node.getInt("v1"),node.getInt("v2"),node.getInt("v3"),node.getInt("v4"),node.getInt("total"),node.getString("subject"));
		characters.add(character);
		character.pic[0] = new PImage();
	    character.pic[0] = loadImage(node.getString("pic0").toString());
	    total=total+character.total;
	}
	
	for(VoteCharacter character:characters){
		character.setAllTotal(total);
	}
}

public void btnTouch(float mx,float my){
	
	if(850<=my && my<900){
		if(600<=mx && mx<=750){
			done=true;
		}else{
			done=false;
		}
		
		if(800<=mx && mx<=950){
			reset=true;
		}else{
			reset=false;
		}
		
		if(1000<=mx && mx<=1150){
			quit=true;
		}else{
			quit=false;
		}
	}
}

public void receiveMessage(String line){
	String[] operation=new String[3];
	operation=line.split(":",3);
	
	if(operation[0].equals("load")){
		for(VoteCharacter character:characters){
			if(character.getName().toString().equals(operation[1])){
				showlist.add(character);
			}
		}
	}
}

public void keyPressed(){
	if(keyCode==32)
		sendList();
}

public void mouseMoved(MouseEvent e){
	for(VoteCharacter character: this.characters){
		 character.boxTouch(e.getX(),e.getY());
	}
	btnTouch(e.getX(),e.getY());
	//this.repaint();
}

public void mousePressed(MouseEvent e){
	for(VoteCharacter character: this.characters){
		 character.boxClick(e.getX(),e.getY());
	}
	btnClick(e.getX(),e.getY());
	//this.repaint();
}

public void btnClick(float mx,float my){
	
	if(850<=my && my<900){
		//senlist
		if(600<=mx && mx<=750){
			sendList();
		}
		//reset
		if(800<=mx && mx<=950){
			for(VoteCharacter character:showlist){
				for(int i=0;i<5;i++){
					character.option_b[0][i]=false;
					character.option_b[1][i]=false;
				}
			}
		}
		//quit
		if(1000<=mx && mx<=1150){
			
		}
	}
}

public void sendList(){
	
}

}
