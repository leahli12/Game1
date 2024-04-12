import java.awt.*;

public class Scoop {
    public Rectangle rec;
    public int identity;
    public int xpos;
    public int ypos;
    public boolean notAttached;

    public Scoop(int xID, int pos){
        identity = xID;
        xpos = pos;
        ypos = -125;
        notAttached = true;
        rec = new Rectangle(xpos, ypos, 150, 150);
    }

    public void move(){
        if(notAttached) {
            ypos = ypos + 10;
            if (ypos > 775){
                ypos = -125;
            }
        }
        rec = new Rectangle(xpos, ypos, 150, 150);
    }
}
