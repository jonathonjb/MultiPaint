import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author Jonathon Brandt on 8/27/17.
 * @project MultiPaint
 */
public class Server {
    private static final int PORT_NUMBER = 41217;
    private static ArrayList<Socket> socketList = new ArrayList<>();

    public static void main(String[] args){
        ServerSocket serverSocket;
        Socket clientSocket;
        ClientListener listener;
        try{
            serverSocket = new ServerSocket(PORT_NUMBER);
            while(true){
                clientSocket = serverSocket.accept();
                System.out.println("Client found");
                listener = new ClientListener(clientSocket);
                listener.start();
            }
        }
        catch(IOException ex){
            System.err.print("Port number is being used\n");
            System.exit(0);
        }
    }

    public static class ClientListener extends Thread{
        Socket socket;
        PrintWriter writer;
        BufferedReader reader;

        public ClientListener(Socket _socket){
            socket = _socket;
            socketList.add(socket);
            System.out.println("Client connected\n");
        }

        public void run(){
            try{
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = null;
                while(true){
                    if((line = reader.readLine()) != null){
                        sendMessage(line);
                    }
                }
            }
            catch(IOException ex){
                System.exit(0);
            }
        }

        public void sendMessage(String _message){
            for(int i = 0; i < socketList.size(); i++){
                if(socketList.get(i) != socket) {
                    try {
                        writer = new PrintWriter(new OutputStreamWriter(socketList.get(i).getOutputStream()));
                        writer.println(_message);
                        writer.flush();
                    } catch (IOException ex) {
                        System.exit(0);
                    }
                }
            }
        }
    }
}
