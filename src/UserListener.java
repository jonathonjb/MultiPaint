import java.awt.event.*;
import java.io.*;

/**
 * @author Jonathon Brandt on 8/27/17.
 * @project MultiPaint
 */
public class UserListener {
    Canvas canvas;
    PrintWriter writer;

    public UserListener(Canvas _canvas){
        canvas = _canvas;
        try {
            writer = new PrintWriter(new OutputStreamWriter(canvas.socket.getOutputStream()));
        }
        catch(IOException ex){
            System.exit(0);
        }
        canvas.addMouseListener(new UserMouseListener());
        canvas.addMouseMotionListener(new UserMouseDraggedListener());
    }

    public class UserMouseListener implements MouseListener{
        public void mouseEntered(MouseEvent me){}
        public void mouseClicked(MouseEvent me){}
        public void mouseReleased(MouseEvent me){}
        public void mouseExited(MouseEvent me){}
        public void mousePressed(MouseEvent me){
            canvas.addPaintLocation(me.getX(), me.getY());
            canvas.repaint();
            sendLocation(me.getX(), me.getY());
        }
    }

    public class UserMouseDraggedListener implements MouseMotionListener{
        public void mouseMoved(MouseEvent me){}
        public void mouseDragged(MouseEvent me){
            canvas.addPaintLocation(me.getX(), me.getY());
            canvas.repaint();
            sendLocation(me.getX(), me.getY());
        }
    }

    public void sendLocation(int _x, int _y){
        writer.println(_x + " " + _y);
        writer.flush();
    }
}
