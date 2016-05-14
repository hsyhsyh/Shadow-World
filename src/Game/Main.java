package Game;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame{
	
	private final static int windowWidth = 1000, windowHeight = 500;
	
	public static void main(String [] args){
		
		GameStage applet = new GameStage();
		applet.init();
		applet.start();
		applet.setFocusable(true);
		
		JFrame window = new JFrame("Shadow-World");
		window.setLocation(100, 50);
		window.setContentPane(applet);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(windowWidth, windowHeight);
		window.setVisible(true);
		window.setResizable(false);
	}
}
