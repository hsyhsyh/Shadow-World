package VoteApplet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

class ConnectionThread extends Thread{
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	public int votevalue;
	public int clientAction;
	public String charactername;
	public String username;
	public int listnum;
	
	public ConnectionThread(Socket socket){
		this.socket = socket;
		try{
			this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		}catch(IOException e){
			e.printStackTrace();
		}
		votevalue=0;
		clientAction=0;
		Thread th=new Thread(this);
		th.start();
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
					username=operation[1];
				}else if(operation[0].equals("load")){
					listnum=Integer.valueOf(operation[2]);
				}else if(operation[0].equals("vote")){
					sendMessage(line);
					clientAction=2;
					charactername=operation[1];
					votevalue=Integer.valueOf(operation[2]);
					System.out.println("connectionThread"+operation[0]+operation[1]+operation[2]);
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
