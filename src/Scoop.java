import java.awt.*;

public class Scoop {
    public Rectangle rec;
    public int identity;
    public int xpos;
    public int ypos;
    public boolean notAttached;
    public boolean reset;

    public Scoop(int xID, int pos){
        identity = xID;
        xpos = pos;
        ypos = -(int)(Math.random()*400 + 125);
        notAttached = true;
        reset = false;
        rec = new Rectangle(xpos, ypos, 163, 163);
    }

    public void move(){
        if(notAttached) {
            ypos = ypos + 10;
            if (ypos > 775){
                ypos = -125;
            }
        }
        if (reset){
            ypos = -(int)(Math.random()*400 + 125);
            notAttached = true;
            identity = (int)(Math.random()*9 + 1);
            System.out.println(identity);
            xpos = (int)(Math.random() * 900 + 50);
            reset = false;
        }
        rec = new Rectangle(xpos, ypos, 150, 150);
    }

}
