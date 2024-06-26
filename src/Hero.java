import java.awt.*;

public class Hero {
    // Variable declarations
    public String name;
    public int xpos;
    public int ypos;
    public int dx; // Speed in the x direction
    public int dy; // Speed in the y direction
    public int width;
    public int height;
    public boolean isAlive;
    // the rectangle is like a hitbox
    public Rectangle rec;
    // Movement booleans
    public boolean rightPressed;
    public boolean leftPressed;
    public boolean upPressed;
    public boolean downPressed;
    public boolean spacePressed;
    public int cone;

    public static void main(String[] args) {

    }

    public Hero(int pXpos, int pYpos, int pDx, int pDy){
        xpos = pXpos;
        ypos = pYpos;
        dx = pDx; // one pixel per millisecond or the pause time
        dy = pDy;
        width = 165;
        height = 265;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);
        cone = 1;
    }

    public void move() { // User control move method
        // Horizontal
        if(rightPressed){
            xpos = xpos + dx;
            dx = 15;
            // System.out.println("test right pressed");
        } else if (leftPressed){
            xpos = xpos + dx;
//            dy = 1;
            // System.out.println("left");
            dx = -15;
        }

//        else if (upPressed){
//            ypos = ypos - dy;
//            dy = 2;
//            dx = 1;
//        } else if (downPressed){
//            ypos = ypos + dy;
//            dy = 2;
//            dx = 1;
        // }
        else{
            dx = 0;
        }
//        xpos = xpos + dx;
//        ypos = ypos + dy;
        if (xpos > 1000){
            xpos = -200;
        }
        if (xpos < -200) {
            xpos = 1000;
        }
        rec = new Rectangle(xpos, ypos, width, height);
    }

    public void chooseCone(){
//        if (spacePressed){
//            if (cone == 3){
//                cone = 1;
//                System.out.println("hit" + cone);
//            }
//            else {
//                cone += 1;
//            }
//            System.out.println(cone);
//        }
        if (spacePressed){
            if (cone == 3){
                cone = 1;
            }
            else {
                cone += 1;
            }
        }
        pause(50);
    }

    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    public void wrappingMove(){
        if (xpos > 1040){
            xpos = 0;
        }
        if (xpos < 0) {
            xpos = 1040;
        }
        if (ypos > 700) {
            ypos = 0;
        }
        if (ypos < 0) {
            xpos = 700;
        }

        // Last two lines are to update the position, makes the object move
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, width, height);
    }

    public void bouncingMove(){
        if (xpos > 940 || xpos < 0) {
            dx = -dx;
        }

        if (ypos > 640 || ypos < 0){
            dy = -dy;
        }

        xpos = xpos + dx;
        ypos = ypos + dy;
        // Update rectangle location
        rec = new Rectangle(xpos, ypos, width, height);
    }



    public void printInfo(){
        System.out.println("This hero is at " + xpos + ", " + ypos + " || dx and dy = " + dx + " " + dy +
                " || width and height = " + width + " " + height + " || isAlive is " + isAlive);
    }
}

