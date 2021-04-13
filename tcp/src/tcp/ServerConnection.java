package tcp;

import java.awt.TexturePaint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class ServerConnection implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    public ServerConnection (Socket clientSocket) throws IOException {
        this.client=clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
    }

    
   

    public void run() {
    	 boolean closed=false;
    	 boolean username=false;
    	 boolean id=false;
    	 boolean day=false;
        /*
        String request = null; // for first instruction, this part was suppose to be the authentication stage but I did not connect them
        try {
            request = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }S
         */
        // My prootocols works as following I ask client to press 1 if they want APOD picture and press 2
        // if they want to connect to insight api and get the pressure value of a sol
        try {
           
            while(true) {
            	out.println("press 1 or 2");
            	  if(closed) break;
                String request = in.readLine();
                System.out.println(request);
                
                if(request.equals("quit") ){

                    break;
                }
                else if (request.contains("1")) {
                    out.println("I will send my KUSIS ID+ KUSIS USERNAM + Date in a single string");
                    out.flush();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
                    LocalDateTime now = LocalDateTime.now();  
                    String path = "69337 + oyasuran18 " +dtf.format(now)+".";
                    System.out.println(path);
            
                    out.println(path);
                    out.flush();
                   

                } else if (request.contains("2")) {
                    out.println("I will send my KUSIS ID+ KUSIS USERNAME + Date  in nonpersistent manner ");
                    while(id != true || username != true || day  !=true) {
                    	if(id == false) {
	                    ServerSocket fileserver = new ServerSocket(9090);
	                    System.out.println("Oppened up a server socket on " + Inet4Address.getLocalHost());
	                    Socket sock = fileserver.accept();
	                    PrintWriter outt = new PrintWriter(sock.getOutputStream(), true);
	                    OutputStream outing = sock.getOutputStream();
	                    outt.println("69337");
	                    sock.close();
	                	System.out.println("in 2 id");
	                	out.println("id is done");
	                    id = true;
	                    outt.close();
	                    fileserver.close();
                    }
                    else if(username == false ) {
                    	  ServerSocket fileserver = new ServerSocket(9090);
                          System.out.println("Oppened up a server socket on " + Inet4Address.getLocalHost());
                          Socket sock = fileserver.accept();
                          PrintWriter outt = new PrintWriter(sock.getOutputStream(), true);
                          OutputStream outing = sock.getOutputStream();
                          outt.println("oyasuran18");
                          sock.close();
                          System.out.println("in 2 user");
                          out.println("user is done");
                          username =true;
                          outt.close();
                          fileserver.close();
                    	
                    }
                    else if(day == false ) {
                  	  ServerSocket fileserver = new ServerSocket(9090);
                        System.out.println("Oppened up a server socket on " + Inet4Address.getLocalHost());
                        Socket sock = fileserver.accept();
                        PrintWriter outt = new PrintWriter(sock.getOutputStream(), true);
                        OutputStream outing = sock.getOutputStream();
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
                        LocalDateTime now = LocalDateTime.now();  
                        outt.println(dtf.format(now));
                        sock.close();
                        System.out.println("in 2 day");
                        out.println("day is done");
                        day= true;
                        outt.close();
                        fileserver.close();
                  	
                  }
                    
                   }
                    out.flush();
                     username=false;
               	  	id=false;
               	  	day=false;
                }
                /*
             if(!request.contains("1") || !request.contains("2") ) {
            	 out.println("closing the socket");
                    System.out.println("closing the socket!!!!");
                    client.close();
                    closed=true;
                    break;
                }
         
               else {
                    out.println("closing the socket");
                    System.out.println("closing the socket and fileserver");
                    client.close();
                    break;
                }
             */
            }
       
        } catch (IOException  e) {
            e.printStackTrace();
            System.err.println("Server Class.Connection establishment error inside listen and accept function");
        } finally {

            try {
                client.close();
                out.println("closing the socket");
                System.out.println("closing the socket");
                client.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Client Handler Error cannot close reader");
            }

        }
    }
    //}

}

