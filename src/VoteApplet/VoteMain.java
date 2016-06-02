package VoteApplet;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class VoteMain extends JFrame{
	public static void main(String [] args){
		
		VoteApplet applet = new VoteApplet();
		applet.init();
		applet.start();
		applet.setFocusable(true);
		
		JFrame window = new JFrame("client");
		window.setContentPane(applet);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1000, 500);
		window.setVisible(true);
	}
}
