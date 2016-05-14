package Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class GameStage extends AbstractGameStage implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage duck = null;
	
	public GameStage() {
		
		setLayout(null);
		//setBackground(Color.cyan);
		setVisible(false);
		setBounds(0, 0, 1000, 500);
		Thread GameStage = new Thread(this);
		GameStage.start();
		
		try {
			duck = ImageIO.read(new File("duck.png"));
			
		} catch (IOException e) {
		}
		setVisible(true);
		
	}

	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(this.duck, 50, 50, this	);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 50);
		g.fillRect(0, 420, 1000, 50);
		g.fillRect(0, 50, 50, 370);
		g.fillRect(950, 50, 50, 370);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			while(true){
				this.repaint();
		        Thread.sleep(40);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}