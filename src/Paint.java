import javax.swing.*;

/**
 * @author Jonathon Brandt on 8/27/17.
 * @project MultiPaint
 */
public class Paint extends JFrame{
    private int xLength = 500;
    private int yLength = 500;

    private int xLocation = 100;
    private int yLocation = 100;

    Canvas canvas;

    public Paint(){
        setSize(xLength, yLength);
        setLocation(xLocation, yLocation);

        canvas = new Canvas();
        add(canvas);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        new Paint();
    }
}
