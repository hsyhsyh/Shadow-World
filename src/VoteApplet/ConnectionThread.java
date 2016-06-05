package VoteApplet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import processing.data.JSONArray;
import processing.data.JSONObject;

class ConnectionThread extends Thread{
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	public int votevalue;
	public int clientAction;
	public String charactername;
	public String username;
	public int listnum;
	public JSONArray accounts;
	public JSONObject user_account;
	public ConnectionThread(Socket socket,JSONArray j){
		this.socket = socket;
		this.accounts=j;
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
					boolean flag=false;
					for(int i=0;i<accounts.size();i++){
						if(accounts.getJSONObject(i).getString("account").equals(operation[1])){
							if(accounts.getJSONObject(i).getString("passwd").equals(operation[2])){
								user_account=accounts.getJSONObject(i);
								flag=true;
								break;
							}
						}
					}
					
					if(flag){
						sendMessage("correct::");
					}else{
						sendMessage("incorrect::");
					}
					username=operation[1];
				}else if(operation[0].equals("ask")){
					//clientAction=1;
					//listnum=Integer.valueOf(operation[1]);
					String L=user_account.getString(operation[1]);
					String list[]=new String[10];
					list=L.split(",");
					sendMessage("load:"+list[0]+":");
					sendMessage("load:"+list[1]+":");
					sendMessage("load:"+list[2]+":");
					sendMessage("load:"+list[3]+":");
					sendMessage("load:"+list[4]+":");
					sendMessage("load:"+list[5]+":");
					
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
