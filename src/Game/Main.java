package Game;
import javax.swing.JFrame;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class Main extends JFrame{
	
	private final static int windowWidth = 1000, windowHeight = 500;
//	private PApplet applet1, applet2;
	
	public static void main(String [] args){
		GameStage applet1 = new GameStage();
		Gamestart applet2 = new Gamestart();
//		applet2.init();
//		applet2.start();
//		applet2.setFocusable(true);
		
		applet1.init();
		applet1.start();
		applet1.setFocusable(true);
		
		
		JFrame window = new JFrame("Shadow-World");
		window.setLocation(100, 50);
		window.setContentPane(applet1);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(windowWidth, windowHeight);
		window.setVisible(true);
		window.setResizable(false);
//		window.add(applet2);
	}
}
