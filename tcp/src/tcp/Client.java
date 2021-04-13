package tcp;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class Client{
    private static final String Server_IP= "127.0.0.1";
    private static final int Server_Port= 4444;

    public static void main(String[] args) throws IOException{

        Socket socket = new Socket(Server_IP,Server_Port);
        // I have keyboard reader to make sure client does not type quit if so I close the socket
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader  keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        boolean closed=false;
        //String serverResponse = input.readLine();
        //System.out.println("Server says: " + serverResponse);
        // client is in active convoy with the user all times same thing is happening at the server side as well
        while(true) {
        	String serverResponse = input.readLine();
            System.out.println("Server says: " + serverResponse);
           if(closed) break;
            System.out.println("> ");
            String command = keyboard.readLine();
            long start = System.currentTimeMillis();
            if(command.equals("quit") || command.equals("okay")) break;
            out.println(command);
            
             serverResponse = input.readLine();
            if(serverResponse != null) {
                if (serverResponse.contains("string")) {

                    serverResponse = input.readLine();
                    
                    System.out.println(serverResponse);
                    long finish = System.currentTimeMillis();
	                long timeElapsed = finish - start;
                    System.out.println(timeElapsed+" milliseconds");
                    out.flush(); 
        
                }
                else if (serverResponse.contains("nonpersistent")) {
                	 //serverResponse = input.readLine();
                	String answer="";
                	int count =0;
              	   while(count !=3) { 
              		 //System.out.println(serverResponse); 
                	 Socket filesocket = new Socket(Server_IP, 9090);
                     BufferedReader innput = new BufferedReader(new InputStreamReader(filesocket.getInputStream()));
                     serverResponse = innput.readLine();
                     answer=answer+" "+serverResponse;
                     System.out.println(serverResponse);
                     filesocket.close();
                     innput.close();
                     serverResponse = input.readLine();
                     //System.out.println(serverResponse);
                     count++;
                  
                    }
              	   	long finish = System.currentTimeMillis();
	                long timeElapsed = finish - start;
	                System.out.println(answer);
	                out.flush(); 
                 System.out.println(timeElapsed+" milliseconds");

                }

            //System.out.println("Server says: " + serverResponse);
        }
  
        // to show the image in the client side
    }
        System.out.println("socket is going to be closed");
        socket.close();
    }
}


