package tcp;


import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static final int DEFAULT_SERVER_PORT = 4444;
    private static ExecutorService pool = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws IOException{
        ServerSocket listener = new ServerSocket(DEFAULT_SERVER_PORT);
        System.out.println("Oppened up a server socket on " + Inet4Address.getLocalHost());

        while(true) {

            System.out.println("[SERVER] Waiting for client connection...");
            Socket client = listener.accept();
            System.out.println("[SERVER] Connected to client!");
            System.out.println("A connection was established with a client on the address of " + client.getRemoteSocketAddress());
            ServerConnection clientThread = new ServerConnection(client);


            try {
                pool.execute(clientThread);
                //System.out.println("Thread Finished! ");


            }
            catch (Exception e)
            {

                System.err.println("Server Thread. Run. IO Error/ Client terminated abruptly");
                listener.close();
            }

        }



    }
}
