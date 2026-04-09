import java.io.*;
import java.net.*;



public class Server {
    public static final int PORT = 8080;
    public static void main(String[]args) {
        try{
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Serveri po degjon ne portin:" + PORT);

            while (true){
                Socket clientSocket = serverSocket.accept();  
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

   