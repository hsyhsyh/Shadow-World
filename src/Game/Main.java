package Game;
import java.util.Random;

import javax.swing.JFrame;

import VoteApplet.VoteApplet;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class Main extends JFrame{
	
	private final static int windowWidth = 1000, windowHeight = 500;
	private PApplet mainapplet;
	private boolean is_voted;
	public Gamestart gamestart;
	public GameStage gamestage;
	public PApplet applet1, applet2;
	public VoteApplet voteapplet;
	public Main()
	{
		is_voted = false;
		//Voteapplet
		voteapplet = new VoteApplet(this);
		voteapplet.init();
		voteapplet.setVoteVisible(false);
		
		gamestage = new GameStage(this);
		gamestart = new Gamestart(this);
		gamestart.setgs(gamestage);
		gamestage.setgs(gamestart);
		mainapplet = gamestart;
		mainapplet.init();
		mainapplet.start();
		add(mainapplet);
		
		
	}
	
	public void change_into_applet(PApplet tem)
	{
		mainapplet.stop();
		remove(mainapplet);
		mainapplet = tem;
		mainapplet.init();
		mainapplet.start();
		add(mainapplet);
		
	}
	//±b¸¹±K½X¬Ò¬O102021101
	public void addapplet()
	{
		mainapplet.stop();
		remove(mainapplet);
		if(!is_voted)
		{
			is_voted = true;
		}
		Random tems = new Random();
		int a = (tems.nextInt(3) + 1);
		voteapplet.loadList("L" + a );
		System.out.println("SDADADADS" + a);
		add(voteapplet);
		voteapplet.start();
		voteapplet.setVoteVisible(true);

	}
	public void removeapplet(PApplet tem)
	{
		mainapplet.start();
		tem.stop();
		remove(tem);
		add(mainapplet);
	}
	
	public static void main(String [] args){
		
		Main m = new Main();
		m.setTitle("Shadow-World");
		m.setLocation(100, 50);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setSize(windowWidth, windowHeight);
		m.setVisible(true);
		m.setResizable(false);

	}
}
