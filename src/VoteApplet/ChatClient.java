package VoteApplet;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class ChatClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private String destinationIPAddr;
	private int destinationPortNum;
	private Socket socket;
	private PrintWriter writer;
	private VoteApplet parent;
	
	private String nickname;
	private JTextArea textArea;
	private JTextField textField;
	private String username;
	private String password;
	
	JTextField usernameTxt = new JTextField();
	JTextField passwordTxt = new JPasswordField();
	Object[] message = {
		    "Username:", usernameTxt,
		    "Password:", passwordTxt
		};
	
	public ChatClient(VoteApplet p) {
		this.parent=p;
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initialize textArea
		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
		this.textArea.setPreferredSize(new Dimension(500,550));
		JScrollPane scrollPane = new JScrollPane(this.textArea);
	    this.add(scrollPane);
	    
	    // Initialize textField
	    this.textField = new JTextField();
	    this.textField.setPreferredSize(new Dimension(500,40));
	    this.textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ChatClient.this.sendMessage(ChatClient.this.textField.getText());
			}
	    	
	    });
	    this.add(this.textField);
	    
	    this.pack();
	    // Ask for nickname before showing client window
	    int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
	    username=usernameTxt.getText();
	    password=passwordTxt.getText();
	    
	    
		
//		System.out.println(SwingUtilities.isEventDispatchThread());
		setIPAddress("127.0.0.1");
		setPort(8000);
		connect();
		this.setVisible(true);
	}
	
	public ChatClient(String IPAddress, int portNum) {
		this.destinationIPAddr = IPAddress;
		this.destinationPortNum = portNum;
	}
	
	private void welcome(final String s) {

		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
//				System.out.println(SwingUtilities.isEventDispatchThread());
				ChatClient.this.textArea.append(s);
			}
			
		});
	}
	
	public void sendMessage(String message) {
//		System.out.println(SwingUtilities.isEventDispatchThread());
		/*StringBuilder sBuilder = new StringBuilder(this.username);
		sBuilder.append(": ").append(message);
		*/
			this.writer.println(message);
			this.writer.flush();
			this.textField.setText("");
		
	}

	public ChatClient setIPAddress(String IPAddress) {
		this.destinationIPAddr = IPAddress;
		return this;
	}
	
	public ChatClient setPort(int portNum) {
		this.destinationPortNum = portNum;
		return this;
	}
	
	public void connect() {
		// Create socket & thread, remember to start your thread
		try {
			this.socket = new Socket(this.destinationIPAddr, this.destinationPortNum);
			this.writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			new CleintThread(reader,parent).start();
			sendMessage("login:"+this.username+":"+this.password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addLine(final String message) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				ChatClient.this.textArea.append(message+"\n"); 
				parent.receiveMessage(message);
			}
			
		});
	}
	
	// Define an inner class for handling message reading
	class CleintThread extends Thread{
		private BufferedReader reader;
		private VoteApplet parent;
		public CleintThread(BufferedReader reader,VoteApplet p){
			this.reader = reader;
			this.parent=p;
		}
		
		public void run(){
			while(true){
				try{
					String line = this.reader.readLine();
					addLine(line);
					this.parent.receiveMessage(line);
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
		
}
