package Game;
import javax.swing.JFrame;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class Main extends JFrame{
	
	private final static int windowWidth = 1000, windowHeight = 500;
	private PApplet mainapplet;
	public PApplet applet1, applet2;
	
	public Main()
	{
		
		applet1 = new GameStage();
		applet2 = new Gamestart();
//		changeapplet(applet1);
		mainapplet = applet1;
		mainapplet.init();
		mainapplet.start();
		add(mainapplet);
		
	}
	
	public void change_into_applet(PApplet tem)
	{
		remove(mainapplet);
//		removeAll();
		mainapplet = tem;
		mainapplet.init();
		mainapplet.start();
		add(mainapplet);
		
	}
	
	public void applet(PApplet tem)
	{
		tem.init();
		tem.start();
		add(tem);
	}
	public static void main(String [] args){
//		GameStage applet1 = new GameStage();
//		Gamestart applet2 = new Gamestart();
//		applet2.init();
//		applet2.start();
//		applet2.setFocusable(true);
//		
////		applet1.init();
////		applet1.start();
////		applet1.setFocusable(true);
//		
//		
//		JFrame window = new JFrame("Shadow-World");
//		window.setLocation(100, 50);
////		window.setContentPane(applet1);
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.setSize(windowWidth, windowHeight);
//		window.setVisible(true);
//		window.setResizable(false);
//		window.add(applet2);
		
		Main m = new Main();
		m.setTitle("Shadow-World");
		m.setLocation(100, 50);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setSize(windowWidth, windowHeight);
		m.setVisible(true);
		m.setResizable(false);
//		Thread test = new Thread(new Runnable(){
//			public void run()
//			{
//				try {
//					Thread.sleep(2000);
//					System.out.println("AAA");
//					m.change_into_applet(m.applet2);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//		});
//		test.start();
	}
}
