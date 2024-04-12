
    //Game Example
//Lockwood Version 2023-24
// Learning goals:
// make an object class to go with this main class
// the object class should have a printInfo()
//input picture for background
//input picture for object and paint the object on the screen at a given point
//create move method for the object, and use it
// create a wrapping move method and a bouncing move method
//create a second object class
//give objects rectangles
//start interactions/collisions

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.sql.SQLOutput;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

    public class GameLand implements Runnable, KeyListener {

        //Variable Declaration Section
        //Declare the variables used in the program
        //You can set their initial values here if you want

        //Sets the width and height of the program window
        final int WIDTH = 1000;
        final int HEIGHT = 700;

        //Declare the variables needed for the graphics
        public JFrame frame;
        public Canvas canvas;
        public JPanel panel;

        public BufferStrategy bufferStrategy;

        public boolean startScreen = true;
        public boolean level1;
        public boolean level2;
        public boolean level3;

        //Declare the objects used in the program below
        /** STEP 1: Declare your object and give it a name **/
        public Hero astro;
        public Scoop[] scoops;
        public Scoop testScoop;
        /** STEP 2: Declare an image for your object **/
        public Image startPic;
        public Image astroPic;
        public Image testScoopPic;

        // Main method definition: PSVM
        // This is the code that runs first and automatically
        public static void main(String[] args) {
            GameLand ex = new GameLand();   //creates a new instance of the game and tells GameLand() method to run
            new Thread(ex).start();       //creates a thread & starts up the code in the run( ) method
        }

        // Constructor Method
        // This has no return type and has the same name as the class
        // This section is the setup portion of the program
        // Initialize your variables and construct your program objects here.
        public GameLand() {

            setUpGraphics(); //this calls the setUpGraphics() method

            //create (construct) the objects needed for the game below
            //for each object that has a picture, load in images as well
            /** STEP 3: Construct a specific Hero object **/
            astro = new Hero(50, 425, 1, 2);
            scoops = new Scoop[6];
            testScoop = new Scoop((int)(Math.random() * 9 + 1), (int)(Math.random() * 900 + 50));
//            for (int x = 0; x < scoops.length; x++) {
//                scoops[x] = new Scoop((int)(Math.random()* 8 + 1), (int)(Math.random() * 900));
//            }
//        astro.printInfo();

            /** STEP 4: load in the image for your object **/
            astroPic = Toolkit.getDefaultToolkit().getImage("vanilla.png");
            startPic = Toolkit.getDefaultToolkit().getImage("start.png");
//            ob1Pic = Toolkit.getDefaultToolkit().getImage("rock.png");
            scoopDecision(testScoop);

        }// GameLand()

//*******************************************************************************
//User Method Section
//
// put your code to do things here.

        // main thread
        // this is the code that plays the game after you set things up
        public void run() {
            //for the moment we will loop things forever using a while loop
            while (true) {
                moveThings();  //move all the game objects
                collisions(); // checks for all intersections
                render();  // paint the graphics
                astro.chooseCone();
                pause(20); // sleep for 20 ms
            }
        }

        //paints things on the screen using bufferStrategy
        private void render() {
            Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
            g.clearRect(0, 0, WIDTH, HEIGHT);
            if (startScreen) {
                //g.drawString("Press w to start", 400, 300);
                g.drawImage(startPic, 0, 0, WIDTH, HEIGHT, null);
            }
            if (!startScreen) {
                //draw the image of your objects below:
                // This is technically not the actual object! It's just a picture
                  // g.drawImage(pastel, 0,0, 1000, 700, null);
                // g.drawImage(astroPic, astro.xpos, astro.ypos, 200,200, null);
                startPic = Toolkit.getDefaultToolkit().getImage("gamebg.png");
                g.drawImage(startPic, 0, 0, WIDTH, HEIGHT, null);
                if (astro.cone == 1) {
                    astroPic = Toolkit.getDefaultToolkit().getImage("vanilla.png");
                    g.drawImage(astroPic, astro.xpos, astro.ypos, 300, 300, null);
                } else if (astro.cone == 2) {
                    astroPic = Toolkit.getDefaultToolkit().getImage("chocolate.png");
                    g.drawImage(astroPic, astro.xpos, astro.ypos, 300, 300, null);
                } else if (astro.cone == 3) {
                    astroPic = Toolkit.getDefaultToolkit().getImage("sakura.png");
                    g.drawImage(astroPic, astro.xpos, astro.ypos, 300, 300, null);
                }
                g.drawImage(testScoopPic, testScoop.xpos, testScoop.ypos, 150, 150, null);
            }
//            for (int x = 0; x < scoops.length; x++) {
//                    g.drawImage(scoops[x].pic, scoops[x].xpos, , 230, 230, null);
//            }
            // Write the picture generation based off of cheese world... gl bro

            //dispose the images each time(this allows for the illusion of movement).
            g.dispose();
            bufferStrategy.show();
        }

        public void moveThings() {
            //call the move() method code from your object class
//        astro.bouncingMove();
//            ob1.bouncingMove();
            astro.move();
            testScoop.move();
        }

        public void collisions(){
            // makes them bounce off of each other
//            ob1.dx = -1 * ob1.dx;
//            ob1.dy = -1 * ob1.dy;
//            ob3.dx = -1 * ob3.dx;
//            ob3.dy = -1 * ob3.dy;
            // Stops moving the object and puts it far out of the screen to make it "disappear"
//            ob3.isAlive = false;
//            ob3.dy = 0;
//            ob3.dx = 0;
//            ob3.xpos = 2000;
            // Makes an object get bigger
//        if (ob2.rec.intersects(ob1.rec) && (!ob2IsIntersectingob1)){
//            ob2IsIntersectingob1 = true;
//            System.out.println("Ouch");
//            ob1.width++;
//            ob1.height++;
//        }
//        if (!(ob2.rec.intersects(ob1.rec))){
//            ob2IsIntersectingob1 = false;
//        }

//            if (ob1.rec.intersects(ob2.rec) && (!ob1IsIntersectingob2)){
//                ob1IsIntersectingob2 = true;
//                winner = faceOff(ob1, ob2);
//                if (winner == 1){
//                    ob2.identity = ob1.identity;
//                }
//                else if (winner == 2){
//                    ob1.identity = ob2.identity;
//                }
                if(testScoop.rec.intersects(astro.rec)){
                    testScoop.notAttached = false;
                    if (astro.dx > 0){
                        testScoop.xpos = astro.xpos + 80;
                    }
                    testScoop.xpos = astro.xpos;
                    testScoop.ypos = astro.ypos;
                }
            }

            public void scoopDecision(Scoop scoop){
                if (scoop.identity == 1){
                    testScoopPic = Toolkit.getDefaultToolkit().getImage("s1.png");
                }
                else if (scoop.identity == 2){
                    testScoopPic = Toolkit.getDefaultToolkit().getImage("s2.png");
                } else if (scoop.identity == 3){
                    testScoopPic = Toolkit.getDefaultToolkit().getImage("s3.png");
                }
                else if (scoop.identity == 4){
                    testScoopPic = Toolkit.getDefaultToolkit().getImage("s4.png");
                }
                else if (scoop.identity == 5){
                    testScoopPic = Toolkit.getDefaultToolkit().getImage("s5.png");
                }
                else if (scoop.identity == 6){
                    testScoopPic = Toolkit.getDefaultToolkit().getImage("s6.png");
                }
                else if (scoop.identity == 7){
                    testScoopPic = Toolkit.getDefaultToolkit().getImage("s7.png");
                }
                else if (scoop.identity == 8){
                    testScoopPic = Toolkit.getDefaultToolkit().getImage("s8.png");
                }
                else if (scoop.identity == 9){
                    testScoopPic = Toolkit.getDefaultToolkit().getImage("s9.png");
                }
            }

        //Pauses or sleeps the computer for the amount specified in milliseconds
        public void pause(int time) {
            //sleep
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {

            }
        }

        //Graphics setup method
        private void setUpGraphics() {
            frame = new JFrame("Game Land");   //Create the program window or frame.  Names it.

            panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
            panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
            panel.setLayout(null);   //set the layout

            // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
            // and trap input events (Mouse and Keyboard events)
            canvas = new Canvas();
            canvas.setBounds(0, 0, WIDTH, HEIGHT);
            canvas.setIgnoreRepaint(true);
            canvas.addKeyListener(this);
            panel.add(canvas);  // adds the canvas to the panel.

            // frame operations
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
            frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
            frame.setResizable(false);   //makes it so the frame cannot be resized
            frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

            // sets up things so the screen displays images nicely.
            canvas.createBufferStrategy(2);
            bufferStrategy = canvas.getBufferStrategy();
            canvas.requestFocus();
            System.out.println("DONE graphic setup");

            // setting up variables and methods for the astronaut, created in the class Hero w/
            // constructor class, moving in the move function, painting in setupGraphics
            // Where do I set up variables and methods for the astronaut? Where do I declare astro?
            // Where do I create astro? Where do I tell astro to move?
            // Where do  I paint onto the screen?
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // likely will stay empty
        }

        @Override
        public void keyPressed(KeyEvent e) {
            char key = e.getKeyChar();
            int keyCode = e.getKeyCode();
//            System.out.println("Key: " + key + ", KeyCode: " + keyCode);
            if (keyCode == 68){ // d = 68
                astro.rightPressed = true; // name of whatever hero you end up using
            }
            if (keyCode == 65){ // a = 65
                astro.leftPressed = true; // empty name
            }
            if (keyCode == 87) { // w
                astro.upPressed = true;
                startScreen = false;
            }
            if (keyCode == 83) { // s
                astro.downPressed = true;
            }
            if (keyCode == 32) { // space
                astro.spacePressed = true;
            }
            if (keyCode == 10){ // enter

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            char key = e.getKeyChar();
            int keyCode = e.getKeyCode();
            if (keyCode == 68){ // d = 68
                astro.rightPressed = false; // name of whatever hero you end up using
            }
            if (keyCode == 65){ // a = 65
                astro.leftPressed = false; // empty name
            }
            if (keyCode == 87) { // w
                astro.upPressed = false;
            }
            if (keyCode == 83) { // s
                astro.downPressed = false;
            }
            if (keyCode == 32) { // space
                astro.spacePressed = false;
            }
            if (keyCode == 10) { // enter

            }
        }
    }


