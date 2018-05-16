import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.net.*;

/**
 * @author Jonathon Brandt on 8/27/17.
 * @project MultiPaint
 */
public class Canvas extends JPanel{
    ArrayList<Integer> xLocations;
    ArrayList<Integer> yLocations;
    private int size;

    UserListener userListener;
    Listener listener;

    Socket socket;

    public Canvas(){
        xLocations = new ArrayList<>();
        yLocations = new ArrayList<>();
        size = 3;

        listener = new Listener(this);
        userListener = new UserListener(this);
    }

    public void paint(Graphics g){
        for(int i = 0; i < xLocations.size(); i++){
            g.drawRect(xLocations.get(i), yLocations.get(i), size, size);
            g.fillRect(xLocations.get(i), yLocations.get(i), size, size);
        }
    }

    public void addPaintLocation(int _xLocation, int _yLocation){
        xLocations.add(_xLocation);
        yLocations.add(_yLocation);
    }
}
