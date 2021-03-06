package server;

import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Copyright [2017] [Yahya Hassanzadeh-Nazarabadi]

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */


public class SSLServerThread extends Thread
{

    private final String SERVER_REPLY = "Hello Client";
    private SSLSocket sslSocket;
    private String line = new String();
    private BufferedReader is;
    //private BufferedWriter os;
    private PrintWriter out;
    public SSLServerThread(SSLSocket s)
    
    {
        sslSocket = s;
    }

    public void run()
    
    {
    	
        try
        {
            is = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            //os = new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
            out = new PrintWriter(sslSocket.getOutputStream(), true);
        }
        catch (IOException e)
        {
            System.out.println("Server Thread. Run. IO error in server thread");
        }

        try
        {	
        	
        	line = is.readLine();
        	out.println(SERVER_REPLY);
        	out.flush();
            System.out.println("Client " + sslSocket.getRemoteSocketAddress() + " sent : " + line);
            int count =0;
            while(true) {
            	System.out.println("oyafffffffffeoyoayao");
            	out.println("I will give my KUSIS ID In nonpersistent manner");
                out.flush();
                
                String request = is.readLine();
               
                System.out.println(request);
                
                if(request.equals("quit") ){

                    break;
                }
              
                 else  {
                    while(count !=5) {
                    	if(count==0) {
                    		 ServerSocket fileserver = new ServerSocket(9090);
     	                    System.out.println("Oppened up a server socket on " + Inet4Address.getLocalHost());
     	                    Socket sock = fileserver.accept();
     	                    PrintWriter outt = new PrintWriter(sock.getOutputStream(), true);
     	                    outt.println("6");
     	                    sock.close();
     	                	System.out.println("in  id digit 1");
     	                	out.println("id is done");
     	                    outt.close();
     	                    fileserver.close();
                    		count++;
                    		
	                    	}if(count==1) { 
	                    	ServerSocket fileserver = new ServerSocket(9090);
	 	                    System.out.println("Oppened up a server socket on " + Inet4Address.getLocalHost());
	 	                    Socket sock = fileserver.accept();
	 	                    PrintWriter outt = new PrintWriter(sock.getOutputStream(), true);
	 	                    outt.println("9");
	 	                    sock.close();
	 	                	System.out.println("in  id digit 2");
	 	                	out.println("id is done");
	 	                    outt.close();
	 	                    fileserver.close();
	                		count++;
	                	
                    	}if(count==2) {                    		
                    		ServerSocket fileserver = new ServerSocket(9090);
	 	                    System.out.println("Oppened up a server socket on " + Inet4Address.getLocalHost());
	 	                    Socket sock = fileserver.accept();
	 	                    PrintWriter outt = new PrintWriter(sock.getOutputStream(), true);
	 	                    outt.println("3");
	 	                    sock.close();
	 	                	System.out.println("in  id digit 3");
	 	                	out.println("id is done");
	 	                    outt.close();
	 	                    fileserver.close();
	                		count++;
	                		
	                		
                    	}if(count==3) {                    		
                    		ServerSocket fileserver = new ServerSocket(9090);
	 	                    System.out.println("Oppened up a server socket on " + Inet4Address.getLocalHost());
	 	                    Socket sock = fileserver.accept();
	 	                    PrintWriter outt = new PrintWriter(sock.getOutputStream(), true);
	 	                    outt.println("3");
	 	                    sock.close();
	 	                	System.out.println("in  id digit 4");
	 	                	out.println("id is done");
	 	                    outt.close();
	 	                    fileserver.close();
	                		count++;
	                		
                    	}if(count==4) {                   		
                    		ServerSocket fileserver = new ServerSocket(9090);
	 	                    System.out.println("Oppened up a server socket on " + Inet4Address.getLocalHost());
	 	                    Socket sock = fileserver.accept();
	 	                    PrintWriter outt = new PrintWriter(sock.getOutputStream(), true);
	 	                    outt.println("7");
	 	                    sock.close();
	 	                	System.out.println("in  id digit 5");
	 	                	out.println("id is done");
	 	                    outt.close();
	 	                    fileserver.close();
	                		count++;
                    	}
                    } 	
                    out.println("My id number is given");
                    break;     	
	                 
                 }
            }
            out.println("My id number is given");
        }
        catch (IOException e)
        {
            line = this.getClass().toString(); //reused String line for getting thread name
            System.out.println("Server Thread. Run. IO Error/ Client " + line + " terminated abruptly");
        }
        catch (NullPointerException e)
        {
            line = this.getClass().toString(); //reused String line for getting thread name
            System.out.println("Server Thread. Run.Client " + line + " Closed");
        } finally
        {
            try
            {
                System.out.println("Closing the connection");
                if (is != null)
                {
                    is.close();
                    System.out.println(" Socket Input Stream Closed");
                }

                if (out != null)
                {
                    out.close();
                    System.out.println("Socket Out Closed");
                }
                if (sslSocket != null)
                {
                    sslSocket.close();
                    System.out.println("Socket Closed");
                }

            }
            catch (IOException ie)
            {
                System.out.println("Socket Close Error");
            }
        }//end finally
    } 
}

