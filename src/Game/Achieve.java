package Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.prism.paint.Color;

import processing.core.PApplet;

public class Achieve extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	boolean achieve1, achieve2, achieve3, achieve4, achieve5, achieve6, achieve7, achieve8;
	JLabel label1, label2, label3, label4, label5, label6, label7, label8;
	BufferedImage image1 = null;
	BufferedImage image2 = null;
	BufferedImage image3 = null;
	BufferedImage image4 = null;
	BufferedImage image5 = null;
	BufferedImage image6 = null;
	BufferedImage image7 = null;
	BufferedImage image8 = null;
	Gamestart game;
	public Achieve(Gamestart ga){
		this.game = ga; 
		achieve1 = false;
		achieve2 = false;
		achieve3 = false;
		achieve4 = false;
		achieve5 = false;
		achieve6 = false;
		achieve7 = false;
		achieve8 = false;
		File file1 = new File("achieve1.png");
		File file2 = new File("achieve2.png");
		File file3 = new File("achieve3.png");
		File file4 = new File("achieve4.png");
		File file5 = new File("achieve5.png");
		File file6 = new File("achieve6.png");
		File file7 = new File("achieve7.png");
		File file8 = new File("achieve8.png");
		try {
			this.image1 = ImageIO.read(file1);
			this.image2 = ImageIO.read(file2);
			this.image3 = ImageIO.read(file3);
			this.image4 = ImageIO.read(file4);
			this.image5 = ImageIO.read(file5);
			this.image6 = ImageIO.read(file6);
			this.image7 = ImageIO.read(file7);
			this.image8 = ImageIO.read(file8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.label1 = new JLabel(new ImageIcon(this.image1));
		this.label2 = new JLabel(new ImageIcon(this.image2));
		this.label3 = new JLabel(new ImageIcon(this.image3));
		this.label4 = new JLabel(new ImageIcon(this.image4));
		this.label5 = new JLabel(new ImageIcon(this.image5));
		this.label6 = new JLabel(new ImageIcon(this.image6));
		this.label7 = new JLabel(new ImageIcon(this.image7));
		this.label8 = new JLabel(new ImageIcon(this.image8));
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setSize(800, 500);
		this.setLocation(300, 0);
		if(achieve1){
			this.add(this.label1);
			label1.setSize(50,50);
			label1.setLocation(100, 100);
		}
		else if(achieve2){
			this.add(this.label2);
			label2.setSize(50,50);
			label2.setLocation(150, 100);
		}
		else if(achieve3){
			this.add(this.label3);
			label3.setSize(50,50);
			label3.setLocation(200, 100);
		}
		else if(achieve4){
			this.add(this.label4);
			label4.setSize(50,50);
			label4.setLocation(250, 100);
		}
		else if(achieve5){
			this.add(this.label5);
			label5.setSize(50,50);
			label5.setLocation(100, 200);
		}
		else if(achieve6){
			this.add(this.label6);
			label6.setSize(50,50);
			label6.setLocation(150, 200);
		}
		else if(achieve7){
			this.add(this.label7);
			label7.setSize(50,50);
			label7.setLocation(200, 200);
		}
		else if(achieve8){
			this.add(this.label8);
			label8.setSize(50,50);
			label8.setLocation(250, 200);
		}
		JButton button = new JButton("Return");
		button.addActionListener(this);
		button.setActionCommand("return");
		this.add(button);
		button.setLayout(null);
		button.setLocation(300, 400);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if("return".equals(e.getActionCommand())){
			this.game.start();
		}
	}
}