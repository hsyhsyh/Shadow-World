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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.prism.paint.Color;

import processing.core.PApplet;

public class Achieve extends JFrame{
	private static final long serialVersionUID = 1L;
	boolean achieve1, achieve2, achieve3, achieve4, achieve5, achieve6, achieve7, achieve8;
	JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9;
	BufferedImage image1 = null;
	BufferedImage image2 = null;
	BufferedImage image3 = null;
	BufferedImage image4 = null;
	BufferedImage image5 = null;
	BufferedImage image6 = null;
	BufferedImage image7 = null;
	BufferedImage image8 = null;
	BufferedImage image9 = null;
	JPanel panel;
	public Achieve(){
		achieve1 = true;
		achieve2 = true;
		achieve3 = true;
		achieve4 = true;
		achieve5 = true;
		achieve6 = true;
		achieve7 = true;
		achieve8 = true;
		File file1 = new File("res/achieve1.png");
		File file2 = new File("res/achieve2.png");
		File file3 = new File("res/achieve3.png");
		File file4 = new File("res/achieve4.png");
		File file5 = new File("res/achieve5.png");
		File file6 = new File("res/achieve6.png");
		File file7 = new File("res/achieve7.png");
		File file8 = new File("res/achieve8.png");
		File file9 = new File("res/startbg.png");
		try {
			this.image1 = ImageIO.read(file1);
			this.image2 = ImageIO.read(file2);
			this.image3 = ImageIO.read(file3);
			this.image4 = ImageIO.read(file4);
			this.image5 = ImageIO.read(file5);
			this.image6 = ImageIO.read(file6);
			this.image7 = ImageIO.read(file7);
			this.image8 = ImageIO.read(file8);
			this.image9 = ImageIO.read(file9);
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
		this.label9 = new JLabel(new ImageIcon(this.image9));
		if(achieve1){
			this.add(this.label1, 0);
			label1.setSize(100,100);
			label1.setLocation(100, 100);
		}
		if(achieve2){
			this.add(this.label2, 0);
			label2.setSize(100,100);
			label2.setLocation(300, 100);
		}
		if(achieve3){
			this.add(this.label3, 0);
			label3.setSize(100,100);
			label3.setLocation(500, 100);
		}
		if(achieve4){
			this.add(this.label4, 0);
			label4.setSize(100,100);
			label4.setLocation(700, 100);
		}
		if(achieve5){
			this.add(this.label5, 0);
			label5.setSize(100,100);
			label5.setLocation(100, 300);
		}
		if(achieve6){
			this.add(this.label6, 0);
			label6.setSize(100,100);
			label6.setLocation(300, 300);
		}
		if(achieve7){
			this.add(this.label7, 0);
			label7.setSize(100,100);
			label7.setLocation(500, 300);
		}
		if(achieve8){
			this.add(this.label8, 0);
			label8.setSize(100,100);
			label8.setLocation(700, 300);
		}
		if(achieve8){
			this.add(this.label9, -1);
			label9.setSize(100,100);
			label9.setLocation(700, 300);
		}
	}
	public void setachieve(int num){
		if(num == 1){
			achieve1 = true;
		}
		if(num == 2){
			achieve2 = true;
		}
		if(num == 3){
			achieve3 = true;
		}
		if(num == 4){
			achieve4 = true;
		}
		if(num == 5){
			achieve5 = true;
		}
		if(num == 6){
			achieve6 = true;
		}
		if(num == 7){
			achieve7 = true;
		}
		if(num == 8){
			achieve8 = true;
		}
	}
}