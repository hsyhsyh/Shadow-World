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
	
	
	private static final long serialVersionUID = 1L;
	private ServerSocket serverSocket;
	public List<ConnectionThread> connections = new ArrayList<ConnectionThread>();
	private VoteSever parent;
	private JSONArray accounts;
	public ChatServer(int portNum,VoteSever p,JSONArray j){
		this.parent=p;
		this.accounts=j;
		try {
			this.serverSocket = new ServerSocket(portNum);
			System.out.printf("Server starts listening on port %d.\n", portNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Checker checker=new Checker(parent,connections);
		Thread th=new Thread(checker);
		th.start();
	}
	
	public void runForever() {
		// Create a loop to make server wait for client forever (unless you stop it)
		// Make sure you do create a connectionThread and add it into 'connections'
		while(true){
			
			
			try {
				Socket connectionToClient = this.serverSocket.accept();
				System.out.println("Get connection from client "
						 + connectionToClient.getInetAddress() + ":"
						 + connectionToClient.getPort());
				ConnectionThread connThread = new ConnectionThread(connectionToClient,accounts);
				connThread.start();
				connections.add(connThread);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		runForever();
		
	}
	
	// Define an inner class (class name should be ConnectionThread)
	private class Checker implements Runnable{
		public List<ConnectionThread> connections;
		public VoteSever server;
		public Checker(VoteSever v,List<ConnectionThread> c){
			connections=c;
			server=v;
		}
		@Override
		public void run() {
			while(true){
				for(ConnectionThread client:this.connections){
					if(client.clientAction==1){
						//sendList(client.username,client.listnum);
						client.listnum=0;
						client.clientAction=0;
					}else if(client.clientAction==2){
						this.server.voteAccess(client.charactername,client.votevalue);
						//System.out.println("chatServer "+client.charactername+client.votevalue);;
						client.clientAction=0;
					}
				}
			}
			
		}
		
	}

		
}
