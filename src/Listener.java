import java.io.*;
import java.net.*;

/**
 * @author Jonathon Brandt on 8/27/17.
 * @project MultiPaint
 */
public class Listener {
    Canvas canvas;

    public Listener(Canvas _canvas){
        canvas = _canvas;
        setUpClientListener();
    }

    public void setUpClientListener(){
        String ipAddress = "localhost";
        int port = 41217;
        connectWithServer(ipAddress, port);

        ServerListener serverListener = new ServerListener();
        serverListener.start();
    }

    public void connectWithServer(String _ipAddress, int _port){
        try{
            canvas.socket = new Socket(_ipAddress, _port);
        }
        catch(IOException ex){
            System.err.print("Server not found\n");
            System.exit(0);
        }
    }

    public class ServerListener extends Thread{
        public void run(){
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(canvas.socket.getInputStream()));
                String line = null;
                while(true){
                    if((line = reader.readLine()) != null){
                        //line is a string which contains two numbers with a space between them. Ex: "465 234"
                        //the first number will be the x location and the 2nd number will be the y location
                        String[] splitLine = line.split(" ");
                        canvas.addPaintLocation(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[1]));
                        canvas.repaint();
                    }
                }
            }
            catch(IOException ex){
                System.exit(0);
            }
        }
    }
}