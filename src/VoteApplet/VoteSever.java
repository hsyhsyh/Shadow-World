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
	JSONArray nodes, accounts;
	private ArrayList<VoteCharacter> characters;
	private ArrayList<VoteCharacter> showlist;
	private PImage bg;
	private boolean done,reset,quit;
	public ChatServer server;
	public int shownum;
	
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
		this.server= new ChatServer(8000,this,accounts);
		Thread th=new Thread(server);
		th.start();
		shownum=1;
	}
	
public void draw(){
		
		background(255);
		image(bg,0,0);
		stroke(60, 119, 119);
		strokeWeight(4);
		
		for(VoteCharacter character: this.showlist){
			character.display_server();
		}
		
		/*if(done){
			this.fill(0,255,0);
		}else{
			this.fill(255,255,0);
		}
		this.rect(600, 700, 150, 50);
		
		if(reset){
			this.fill(0,255,0);
		}else{
			this.fill(255,255,0);
		}
		this.rect(800, 700, 150, 50);
		if(quit){
			this.fill(0,255,0);
		}else{
			this.fill(255,255,0);
		}
		this.rect(1000, 700, 150, 50);
		
		this.fill(0);
		this.textSize(20);;
		this.text("Done!",645,735);
		this.text("Reset!",845,735);
		this.text("Quit!",1050,735);*/
	
	}

private void loadData(){
	done=false;
	reset=false;
	quit=false;
	
	data = loadJSONObject(file);
	
	nodes = data.getJSONArray("nodes");
	accounts = data.getJSONArray("account");

	System.out.println("Number of nodes: " + nodes.size());
	System.out.println("Number of accounts: " + accounts.size());
	int total=0;
	for(int i=0; i<nodes.size(); i++){
		JSONObject node = nodes.getJSONObject(i);
		VoteCharacter character=new VoteCharacter(this, node.getString("name"), 100+400*(i/2),100+300*(i%2),node.getInt("v0"),node.getInt("v1"),node.getInt("v2"),node.getInt("v3"),node.getInt("v4"),node.getInt("total"),node.getString("subject"),node.getInt("studentnum"),node.getString("inf"));
		characters.add(character);
		character.pic[0] = new PImage();
	    character.pic[0] = loadImage(node.getString("pic0").toString());
	    total=total+character.total;
	}
	for(int i=shownum;i<=shownum+5;i++){
		characters.get(i).setPos(100+400*(i/2),100+300*(i%2));
		showlist.add(characters.get(i));
	}
}

public void btnTouch(float mx,float my){
	
	if(700<=my && my<750){
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
	
	if(700<=my && my<750){
		//senlist
		if(600<=mx && mx<=750){
			nextpage();
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


public void voteAccess(String name,int n){
	for(VoteCharacter character:characters){
		if(character.getName().equals(name)){
			if(0<n && n<=5){
				character.voteAccess(n-1);
				System.out.println("voteserver"+n);
			}
		}
	}
}

public void nextpage(){
	this.stop();
	shownum++;
	for(int i=shownum;i<=shownum+5;i++){
		characters.get(i).setPos(100+400*(i/2),100+300*(i%2));
		showlist.add(characters.get(i));
	}
	this.start();
}

	
	
}


