import java.io.*;
import java.net.*;
import java.util.concurrent.*;


public class SimpleServer {
    public static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 5000;

    private static final int MAX_CONNECTIONS = 3;

    private static ExecutorService pool = Executors.newFixedThreadPool(MAX_CONNECTIONS);

    private static int activeConnections = 0;
    public static void main(String[]args) {
        try{
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(SERVER_IP, SERVER_PORT));
            System.out.println("Serveri startoi ne " + SERVER_IP + ":" + SERVER_PORT);

            while (true){
                Socket clientSocket = serverSocket.accept();
                synchronized (SimpleServer.class){
                    if(activeConnections >= MAX_CONNECTIONS){
                        System.out.println("Lidhja u refuzua(kapaciteti i plote)");

                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        out.println("Serveri eshte i zene. Provo me vone. ");
                        clientSocket.close();
                        continue;
                    }
                    activeConnections++;
                }  
                pool.execute(new ClientHandler(clientSocket));
            }
        } catch(IOException e){
            e.printStackTrace();
        }
                 System.out.println("Klienti i ri u lidh!");

                ClientHandler clientThread = new ClientHandler(clientSocket);
                clientThread.start();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread{
    private Socket socket;
}
public void run(){
    try{
        BufferedReader input = new BufferedReader{
            new InputStreamReader (socket.getInputStream());
            
            PrintWriter output = new PrintWriter(
            socket.getOutputStream(), true);

            String message;
            while((message = input.readLine())!=null){
                System.out.println("Mesazh:" + message);
                output.println("U pranua" + message);
            }
            socket.close();
        }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

   