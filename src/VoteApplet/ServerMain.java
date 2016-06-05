package VoteApplet;

import javax.swing.JFrame;

public class ServerMain {
public static void main(String [] args){
		
		VoteSever applet = new VoteSever();
		applet.init();
		applet.start();
		applet.setFocusable(true);
		
		JFrame window = new JFrame("server");
		window.setContentPane(applet);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1000,500);
		window.setVisible(true);
		
	}
}
