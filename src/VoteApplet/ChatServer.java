package VoteApplet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

public class ChatServer implements Runnable{

	/**
	 * 
	 */
	private String file = "src/VoteApplet/resources/data.json";
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<VoteCharacter> characters;
	private ArrayList<VoteCharacter> showlist;
	
	private static final long serialVersionUID = 1L;
	private ServerSocket serverSocket;
	private List<ConnectionThread> connections = new ArrayList<ConnectionThread>();
	
	public ChatServer(int portNum){
		this.characters=new ArrayList<VoteCharacter>();
		this.showlist=new ArrayList<VoteCharacter>();
		
		try {
			this.serverSocket = new ServerSocket(portNum);
			Thread th=new Thread(this);
			th.start();
			System.out.printf("Server starts listening on port %d.\n", portNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void runForever() {
		System.out.println("Server starts waiting for client.");
		// Create a loop to make server wait for client forever (unless you stop it)
		// Make sure you do create a connectionThread and add it into 'connections'
		while(true){
			try {
				Socket connectionToClient = this.serverSocket.accept();
				System.out.println("Get connection from client "
						 + connectionToClient.getInetAddress() + ":"
						 + connectionToClient.getPort());
				ConnectionThread connThread = new ConnectionThread(connectionToClient);
				connThread.start();
				connections.add(connThread);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private void broadcast(String message) {
		for (ConnectionThread connection: connections) {
			connection.sendMessage(message);
		}
	}
	
	// Define an inner class (class name should be ConnectionThread)
	class ConnectionThread extends Thread{
		private Socket socket;
		private BufferedReader reader;
		private PrintWriter writer;
		public boolean[] votevalue=new boolean[5];
		
		
		public ConnectionThread(Socket socket){
			this.socket = socket;
			try{
				this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		public void run(){
			while(true){
				try{
					String line = this.reader.readLine();
					System.out.println(line);
					String[] operation=new String[3];
					operation=line.split(":",3);
					if(operation[0].equals("login")){
						boolean flag=true;
						
						if(operation[1].equals("andy") && operation[2].equals("123")){
							this.sendMessage("correct::");
						}else{
							this.sendMessage("incorrect::");
						}
					}else if(operation[0].equals("load")){
						
					}else if(operation[0].equals("vote")){
						sendMessage(line);
					}else if(operation[0].equals("finish")){
						
					}
					
				}catch(IOException e){
					//e.printStackTrace();
				}
			}
		}
		
		public void sendMessage(String message){
			this.writer.println(message);
			this.writer.flush();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		runForever();
	}

	/*public static void main(String[] args) {
		ChatServer server = new ChatServer(8000);
		server.runForever();
	}*/
	
	
}
