import java.awt.*;

public class Scoop {
    public Rectangle rec;
    public int identity;
    public int xpos;
    public int ypos;
    public boolean notAttached;
    public boolean reset;
    public int scoopOn;

    public Scoop(int xID, int pos){
        identity = xID;
        xpos = pos;
        ypos = -(int)(Math.random()*900 + 150);
        notAttached = true;
        reset = false;
        rec = new Rectangle(xpos, ypos, 163, 163);
    }

    public void move(){
        if(notAttached) {
            ypos = ypos + 10;
            if (ypos > 775){
                ypos = -125;
                reset = true;
            }
        }
        if (!notAttached) {
            if (xpos < -150){
                xpos = 940;
            }
            if(xpos > 940){
                xpos = -150;
            }
        }
        if (reset){
            ypos = -(int)(Math.random()*900 + 150);
            notAttached = true;
            identity = (int)(Math.random()*9 + 1);
            xpos = (int)(Math.random() * 900 + 50);
            reset = false;
            scoopOn = 0;
        }
        rec = new Rectangle(xpos, ypos, 150, 150);
    }

}
