import javax.swing.JFrame;

public class MyWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MyWindow() {
		
		setLayout(null);
		GameStage gs = new GameStage();
		this.setLocation(100, 50);
	    this.add(gs);
	}
}
