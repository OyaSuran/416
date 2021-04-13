package sll_server;

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
    	 boolean username=false;
    	 boolean id=false;
    	 boolean day=false;
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
        	System.out.println("f");
        	line = is.readLine();
        	out.println(SERVER_REPLY);
        	out.flush();
        	System.out.println("b");
            //line = is.readLine();
            System.out.println("Client " + sslSocket.getRemoteSocketAddress() + " sent : " + line);
            
            while(true) {
            	out.println("press 1 or 2");
                out.flush();
                
                String request = is.readLine();
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
                    
                    request = is.readLine();
                    System.out.println(request);

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
                    request = is.readLine();
                    System.out.println(request);
                     username=false;
               	  	id=false;
               	  	day=false;
                }


            }
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

