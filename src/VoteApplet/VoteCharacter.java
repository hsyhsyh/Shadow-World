
package VoteApplet;

import java.util.ArrayList;

import javax.swing.JRadioButton;

import controlP5.ControlP5;
import processing.core.PApplet;
import processing.core.PImage;


public class VoteCharacter {
	public float x, y, radius;
	private String name;
	private PApplet parent;
	public PImage[] pic;
	public JRadioButton[] option;
	private ControlP5 cp5;
	private int[] votevalue=new int[5];
	public int total;
	private int all_total;
	private String subject;
	public boolean[][] option_b;
	public boolean[] showinf_b;
	int pic_width=150;
	int bar_width=150;
	int bar_height=30;
	int word_x=10;
	int word_y=10;
	public VoteCharacter(PApplet parent, String name, float x, float y,int v0,int v1,int v2,int v3,int v4,int total,String subject){
		
		this.parent = parent;
		this.name = name;
		this.x = x;
		this.y = y;
		this.radius = 25;
		this.subject=subject;
		pic=new PImage[2];
		pic[0]= null;
		
		option_b=new boolean[2][5];
		option_b[0][0]=false;
		option_b[0][1]=false;
		option_b[0][2]=false;
		option_b[0][3]=false;
		option_b[0][4]=false;
		option_b[1][0]=false;
		option_b[1][1]=false;
		option_b[1][2]=false;
		option_b[1][3]=false;
		option_b[1][4]=false;
		
		this.total=total;
		this.votevalue[0]=v0;
		this.votevalue[1]=v1;
		this.votevalue[2]=v2;
		this.votevalue[3]=v3;
		this.votevalue[4]=v4;
		showinf_b=new boolean[3];
		this.showinf_b[0]=false;
		this.showinf_b[1]=true;
		this.showinf_b[2]=true;
		
		/*cp5=new ControlP5(this.parent);
		cp5.add("pieChart").setPosition(this.x+pic_width,this.y) 
		.setColorLabels(100)
		.setColorActive(175)
        .setSize(50,bar_height)
        .setColorForeground(75)
        .setColorActive(bar_width)
        .setColorLabel(bar_width)
        .setItemsPerRow(1)
        .setSpacingColumn(50)
        .addItem("1",1)
        .addItem("2",2)
        .addItem("3",3)
        .addItem("4",4)
        .addItem("5",5)
        ;*/
		// Initialize the target list
	}
	
	public void voteAccess(int n){
		this.votevalue[n]++;
		System.out.println("votevalue[0]="+votevalue[0]);
		System.out.println("votevalue[1]="+votevalue[1]);
		System.out.println("votevalue[2]="+votevalue[2]);
		System.out.println("votevalue[3]="+votevalue[3]);
		System.out.println("votevalue[4]="+votevalue[4]);
	}
	
	
	public void display_client0(){
		
		this.parent.fill(2, 247, 141);
		this.parent.rect(x-subject.length()*6+bar_width/2+10, y-50, subject.length()*15, 40, 12, 12, 12, 12);
		this.parent.fill(0,255,255);
		this.parent.rect(x-name.length()*5+pic_width/4, y+pic_width+10, name.length()*20, 40, 12, 12, 12, 12);
		
		this.parent.textSize(26);
		this.parent.fill(255);
		this.parent.text(subject, x-subject.length()*6+10+bar_width/2+10, y-20);
		this.parent.text(name, x-name.length()*5+10+pic_width/4,(int)(y+pic_width+40));
		this.parent.textSize(20);
		this.parent.image(pic[0], this.x, this.y, pic_width, pic_width);
			
		if(showinf_b[1]){
			if(showinf_b[0]){
				this.parent.fill(0,255,0);
				this.parent.rect(x+pic_width, y+pic_width+10, bar_width, 40, 12, 12, 12, 12);
			}else{
				this.parent.fill(0,128,255);
				this.parent.rect(x+pic_width, y+pic_width+10, bar_width, 40, 12, 12, 12, 12);
			}
			this.parent.fill(0);
			this.parent.text("Click To Vote",x+pic_width+10,(int)(y+pic_width+40));
		}else{
			
			if(showinf_b[0]){
				this.parent.fill(0,255,0);
				this.parent.rect(x+pic_width, y+pic_width+10, bar_width, 40, 12, 12, 12, 12);
			}else{
				this.parent.fill(255,128,0);
				this.parent.rect(x+pic_width, y+pic_width+10, bar_width, 40, 12, 12, 12, 12);
			}
			this.parent.fill(0);
			this.parent.text("Click To Inf",x+pic_width+20,(int)(y+pic_width+40));
			if(pic[0]!=null){
			if(option_b[1][0]){
				this.parent.fill(250,0,0);
			}else if(option_b[0][0]){
				this.parent.fill(0,250,0);
			}else{
				this.parent.fill(250,250,0);
			}
			this.parent.rect(x+pic_width, y, bar_width, bar_height);
			this.parent.fill(0);
			this.parent.text("Excellent",(int)(x+bar_width)+10,y+bar_height/2+5);
			
			if(option_b[1][1]){
				this.parent.fill(250,0,0);
			}else if(option_b[0][1]){
				this.parent.fill(0,250,0);
			}else{
				this.parent.fill(250,250,0);
			}
			this.parent.rect(x+pic_width, y+bar_height, bar_width, bar_height);
			this.parent.fill(0);
			this.parent.text("Good",(int)(x+bar_width)+10,y+bar_height*1+bar_height/2+5);
			
			if(option_b[1][2]){
				this.parent.fill(250,0,0);
			}else if(option_b[0][2]){
				this.parent.fill(0,250,0);
			}else{
				this.parent.fill(250,250,0);
			}
			this.parent.rect(x+pic_width, y+bar_height*2, bar_width, bar_height);
			this.parent.fill(0);
			this.parent.text("Normal",(int)(x+bar_width)+10,y+bar_height*2+bar_height/2+5);
		
			if(option_b[1][3]){
				this.parent.fill(250,0,0);
			}else if(option_b[0][3]){
				this.parent.fill(0,250,0);
			}else{
				this.parent.fill(250,250,0);
			}
			this.parent.rect(x+pic_width, y+bar_height*3, bar_width, bar_height);
			this.parent.fill(0);
			this.parent.text("Bad",(int)(x+bar_width)+10,y+bar_height*3+bar_height/2+5);
			
			if(option_b[1][4]){
				this.parent.fill(250,0,0);
			}else if(option_b[0][4]){
				this.parent.fill(0,250,0);
			}else{
				this.parent.fill(250,250,0);
			}
			this.parent.rect(x+pic_width, y+bar_height*4, bar_width, bar_height);
			this.parent.fill(0);
			this.parent.text("VeryBad",(int)(x+bar_width)+10,y+bar_height*4+bar_height/2+5);
			}
		}
	}
	
	
	public void display_server(){
		//this.parent.noStroke();
		this.parent.fill(2, 247, 141);
		this.parent.rect(x-subject.length()*6+bar_width/2+10, y-50, subject.length()*15, 40, 12, 12, 12, 12);
		this.parent.fill(0,255,255);
		this.parent.rect(x-name.length()*5+pic_width/4, y+pic_width+10, name.length()*20, 40, 12, 12, 12, 12);
		
		this.parent.textSize(26);
		this.parent.fill(255);
		this.parent.text(subject, x-subject.length()*6+10+bar_width/2+10, y-20);
		this.parent.text(name, x-name.length()*5+10+pic_width/4,(int)(y+pic_width+40));
		this.parent.textSize(20);
		this.parent.image(pic[0], this.x, this.y, pic_width, pic_width);
		
		if(showinf_b[1]){
			if(showinf_b[0]){
				this.parent.fill(0,255,0);
				this.parent.rect(x+pic_width, y+pic_width+10, bar_width, 40, 12, 12, 12, 12);
			}else{
				this.parent.fill(0,128,255);
				this.parent.rect(x+pic_width, y+pic_width+10, bar_width, 40, 12, 12, 12, 12);
			}
			this.parent.fill(0);
			this.parent.text("Click To Vote",x+pic_width+10,(int)(y+pic_width+40));
		}else{
			if(showinf_b[0]){
				this.parent.fill(0,255,0);
				this.parent.rect(x+pic_width, y+pic_width+10, bar_width, 40, 12, 12, 12, 12);
			}else{
				this.parent.fill(255,128,0);
				this.parent.rect(x+pic_width, y+pic_width+10, bar_width, 40, 12, 12, 12, 12);
			}
			this.parent.fill(0);
			this.parent.text("Click To Inf",x+pic_width+20,(int)(y+pic_width+40));
			
		if(pic[0]!= null){
		int largest=0;
		for(int i:votevalue){
			if(i>largest){
				largest=i;
			}
		}
		this.parent.fill(250,250,250);
		this.parent.rect(x+pic_width,y,bar_width+10,bar_height);
		this.parent.rect(x+pic_width,y+bar_height*1,bar_width+10,bar_height);
		this.parent.rect(x+pic_width,y+bar_height*2,bar_width+10,bar_height);
		this.parent.rect(x+pic_width,y+bar_height*3,bar_width+10,bar_height);
		this.parent.rect(x+pic_width,y+bar_height*4,bar_width+10,bar_height);
		this.parent.rect(x, y+pic_width+55,pic_width*2,bar_height);
		//==================
		if(total !=0){
			
		if(option_b[0][0]){
			this.parent.fill(0,250,0);
		}else{
			this.parent.fill(250,250,0);
		}
		this.parent.rect(x+pic_width, y,(int)(bar_width*((double)votevalue[0]/(double)largest)), bar_height);
		this.parent.fill(0);
		this.parent.text("Excellent",x+(int)(bar_width)+5,y+(int)(bar_height*0.5)+5);
		
		if(option_b[0][1]){
			this.parent.fill(0,250,0);
		}else{
			this.parent.fill(250,250,0);
		}
		this.parent.rect(x+pic_width, y+bar_height, (int)(bar_width*((double)votevalue[1]/(double)largest)), bar_height);
		this.parent.fill(0);
		this.parent.text("Good",x+(int)(bar_width)+5,y+(int)(bar_height*1.5)+5);
		
		if(option_b[0][2]){
			this.parent.fill(0,250,0);
		}else{
			this.parent.fill(250,250,0);
		}
		this.parent.rect(x+pic_width, y+bar_height*2,(int)(bar_width*((double)votevalue[2]/(double)largest)) , bar_height);
		this.parent.fill(0);
		this.parent.text("Normal",x+(int)(bar_width)+5,y+(int)(bar_height*2.5)+5);
	
		if(option_b[0][3]){
			this.parent.fill(0,250,0);
		}else{
			this.parent.fill(250,250,0);
		}
		this.parent.rect(x+pic_width, y+bar_height*3, (int)(bar_width*((double)votevalue[3]/(double)largest)), bar_height);
		this.parent.fill(0);
		this.parent.text("Bad",x+(int)(bar_width)+5,y+(int)(bar_height*3.5)+5);
		
		if(option_b[0][4]){
			this.parent.fill(0,250,0);
		}else{
			this.parent.fill(250,250,0);
		}
		this.parent.rect(x+pic_width, y+bar_height*4,(int)(bar_width*((double)votevalue[4]/(double)largest)) , bar_height);
		this.parent.fill(0);
		this.parent.text("VeryBad",x+(int)(bar_width)+5,y+(int)(bar_height*4.5)+5);
		}
		}
		
		if(all_total!=0){
			this.parent.rect(x,y+pic_width+55,(int)(pic_width*2*(double)total/(double)all_total),bar_height);
		}
		}
	}	
	
	public void boxClick(float mx,float my){
		boolean[] temp=new boolean[2];
		temp[0]=showinf_b[1];
		temp[1]=showinf_b[2];
		if(this.x+pic_width<=mx && mx <=x+pic_width+bar_width){
			if(this.y+pic_width+10<=my && my<=this.y+pic_width+50){
				showinf_b[2]=temp[0];
				showinf_b[1]=!temp[1];
				//System.out.println("btn_click_"+showinf_b[1]);
			}
		}
		
		
		if(this.x+pic_width<=mx && mx<=this.x+250){
			if(y<=my && my<=y+pic_width){
				option_b[1][0]=false;
				option_b[1][1]=false;
				option_b[1][2]=false;
				option_b[1][3]=false;
				option_b[1][4]=false;
			}
			
			
			if(y<=my && my<this.y+bar_height){
				option_b[1][0]=true;
			}else if(this.y+bar_height<=my && my<this.y+2*bar_height){
				option_b[1][1]=true;
			}else if(this.y+2*bar_height<=my && my<this.y+3*bar_height){
				option_b[1][2]=true;
			}else if(this.y+3*bar_height<=my && my<this.y+4*bar_height){
				option_b[1][3]=true;
			}else if(this.y+4*bar_height<=my && my<this.y+5*bar_height){
				option_b[1][4]=true;
			}
		}
	}
	
	public void boxTouch(float mx ,float my){
		option_b[0][0]=false;
		option_b[0][1]=false;
		option_b[0][2]=false;
		option_b[0][3]=false;
		option_b[0][4]=false;
		showinf_b[0]=false;
		if(this.x+pic_width<=mx && mx <=x+pic_width+bar_width){
			if(this.y+pic_width+10<=my && my<=this.y+pic_width+50){
				showinf_b[0]=true;
			}
		}
		
		if(this.x+pic_width<=mx && mx<=this.x+250){
			
			if(y<=my && my<this.y+bar_height){
				option_b[0][0]=true;
			}else if(this.y+bar_height<=my && my<this.y+2*bar_height){
				option_b[0][1]=true;
			}else if(this.y+2*bar_height<=my && my<this.y+3*bar_height){
				option_b[0][2]=true;
			}else if(this.y+3*bar_height<=my && my<this.y+4*bar_height){
				option_b[0][3]=true;
			}else if(this.y+4*bar_height<=my && my<this.y+5*bar_height){
				option_b[0][4]=true;
			}
		}
	}
	
	public int boxClickCheck(){
		for(int i=0;i<5;i++){
			if(option_b[0][i]){
				return i;
			}
		}
		return -1;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setPos(int x,int y){
		this.x=x;
		this.y=y;
	}
	
}
