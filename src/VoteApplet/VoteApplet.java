package VoteApplet;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;

@SuppressWarnings("serial")
public class VoteApplet extends PApplet{

	private String file = "src/VoteApplet/resources/data.json";
	JSONObject data;
	JSONArray nodes, accounts;
	private ArrayList<VoteCharacter> characters;
	private ArrayList<VoteCharacter> showlist,showlist1,showlist2,showlist3;
	private PImage bg;
	private boolean done,reset,quit;
	private ChatClient client;
	private int listnum;
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
		
		this.setVisible(false);
		
		//socket
		this.client = new ChatClient(this);
		loadList("L1");
	}
	
	public void draw(){
		
		background(255);
		image(bg,0,0);
		stroke(60, 119, 119);
		strokeWeight(4);
		for(VoteCharacter character: this.showlist){
			character.display_client0();
		}
		
		if(done){
			this.fill(0,255,0);
		}else{
			this.fill(255,255,0);
		}
		this.rect(200, 350, 150, 50);
		
		if(reset){
			this.fill(0,255,0);
		}else{
			this.fill(255,255,0);
		}
		this.rect(400, 350, 150, 50);
		if(quit){
			this.fill(0,255,0);
		}else{
			this.fill(255,255,0);
		}
		this.rect(600, 350, 150, 50);
		
		this.fill(0);
		this.textSize(30);;
		this.text("Done!",230,382);
		this.text("Reset!",430,382);
		this.text("Quit!",630,382);
	}

	public void keyPressed(){
		if(keyCode==32)
			loadList("L2");
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

	private void loadData(){
		done=false;
		reset=false;
		quit=false;
		
		data = loadJSONObject(file);
		
		nodes = data.getJSONArray("nodes");
		accounts = data.getJSONArray("account");
		showlist=new ArrayList<VoteCharacter>();
		System.out.println("Number of nodes: " + nodes.size());
		System.out.println("Number of links: " + accounts.size());
	
		for(int i=0; i<nodes.size(); i++){
			JSONObject node = nodes.getJSONObject(i);
			VoteCharacter character=new VoteCharacter(this, node.getString("name"), 100+400*(i/2),100+300*(i%2),node.getInt("v0"),node.getInt("v1"),node.getInt("v2"),node.getInt("v3"),node.getInt("v4"),node.getInt("total"),node.getString("subject"),node.getInt("studentnum"),node.getString("inf"));
			characters.add(character);
			character.pic[0] = new PImage();
		    character.pic[0] = loadImage(node.getString("pic0").toString());
		    
		}
	}
	
	public void loadList(String s){
		listnum=0;
		showlist=new ArrayList<VoteCharacter>();
		client.sendMessage("ask:"+s+":");
		this.setVisible(true);
		/*showlist.add(characters.get(1));
		showlist.add(characters.get(2));
		showlist.add(characters.get(3));
		showlist.add(characters.get(4));*/
	}
	
	public void setVoteVisible(boolean b){
		this.setVisible(b);
	}
	
	public void sendList(){
		for(VoteCharacter character:showlist){
			boolean flag=true;
			if(character.option_b[1][0]){
				//System.out.println("vote:"+character.getName()+":1");
				client.sendMessage("vote:"+character.getName()+":"+"1");
				flag=false;
			}
			if(character.option_b[1][1]){
				//System.out.println("vote:"+character.getName()+":2");
				client.sendMessage("vote:"+character.getName()+":"+"2");
				flag=false;
			}
			if(character.option_b[1][2]){
				//System.out.println("vote:"+character.getName()+":3");
				client.sendMessage("vote:"+character.getName()+":"+"3");
				flag=false;
			}
			if(character.option_b[1][3]){
				//System.out.println("vote:"+character.getName()+":4");
				client.sendMessage("vote:"+character.getName()+":"+"4");
				flag=false;
			}
			if(character.option_b[1][4]){
				//System.out.println("vote:"+character.getName()+":5");
				client.sendMessage("vote:"+character.getName()+":"+"5");
				flag=false;
			}
			
			if(flag){
				//System.out.println("vote:"+character.getName()+":no vote");
				client.sendMessage("vote:"+character.getName()+":"+"0");
			}
		}
	}
	
	public void btnTouch(float mx,float my){
		
		if(350<=my && my<400){
			if(200<=mx && mx<=350){
				done=true;
			}else{
				done=false;
			}
			
			if(400<=mx && mx<=550){
				reset=true;
			}else{
				reset=false;
			}
			
			if(600<=mx && mx<=750){
				quit=true;
			}else{
				quit=false;
			}
		}
	}
	
public void btnClick(float mx,float my){
		
		if(350<=my && my<400){
			sendList();
			if(200<=mx && mx<=350){
				sendList();
				setVoteVisible(false);
			}
			//reset
			if(400<=mx && mx<=550){
				for(VoteCharacter character:showlist){
					for(int i=0;i<5;i++){
						character.option_b[0][i]=false;
						character.option_b[1][i]=false;
					}
				}
			}
			//quit
			if(600<=mx && mx<=750){
				setVisible(false);
			}
		}
	}

	public void receiveMessage(String line){
		String[] operation=new String[3];
		operation=line.split(":",3);
		if(operation[0].equals("load")){
			for(VoteCharacter character:characters){
				if(character.getName().toString().equals(operation[1])){
					if(!showlist.contains(character)){
					this.stop();
					character.setPos(10+320*listnum,100);
					showlist.add(character);
					listnum++;
					System.out.println("list get"+listnum);
					this.start();
					}
				}
			}
		}
	}
	
}