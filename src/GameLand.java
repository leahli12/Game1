//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
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
        public Scoop[] toppings;
        public Image[] pics;
        public Image[] toppingPics;
        public Scoop testScoop;
        /** STEP 2: Declare an image for your object **/
        public Image startPic;
        public Image astroPic;
        public Image testScoopPic;
        public int attachedScoop = 10;
        public int attachedScoop2 = 10;
        public boolean anyAttached = false;
        public int[] correctAnswer = new int[5];
        Image[] correctAnswerPics = new Image[5];
        public int cone = (int)(Math.random()*3 + 1);
        public int scoop1 = (int)(Math.random()*9 + 1);
        public int scoop2 = (int)(Math.random()*13 + 1);
        public int sprinkles1 = (int)(Math.random()*4 + 1);
        public int sprinkles2 = (int)(Math.random()*4 + 1);
        public int[] submission = new int[5];
        public int score = 0;

        // Main method definition: PSVM
        // This is the code that runs first and automatically
        public static void main(String[] args) {
            GameLand ex = new GameLand();   //creates a new instance of the game and tells GameLand() method to run
            new Thread(ex).start();       //creates a thread & starts up the code in the run( ) method
            System.out.println("Arrow keys to move");
            System.out.println("Space to toggle cone");
            System.out.println("Enter/return to submit");
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
            astro = new Hero(50, 570, 1, 2);
            for (int i = 0; i<5; i++){
                correctAnswerPics[i] = null;
            }

            scoops = new Scoop[4];
            toppings = new Scoop[2];
            for (int i=0; i<4; i++){
                scoops[i] = new Scoop((int)(Math.random() * 9 + 1), (int)(Math.random() * 900 + 50));
            }
            toppings[0] = new Scoop(10, (int)(Math.random() * 900 + 50));
            toppings[1] = new Scoop(10, (int)(Math.random() * 900 + 50));

            pics = new Image[4];
            toppingPics = new Image[2];
//            testScoop = new Scoop((int)(Math.random() * 9 + 1), (int)(Math.random() * 900 + 50));
//            for (int x = 0; x < scoops.length; x++) {
//                scoops[x] = new Scoop((int)(Math.random()* 8 + 1), (int)(Math.random() * 900));
//            }
//        astro.printInfo();

            /** STEP 4: load in the image for your object **/
            astroPic = Toolkit.getDefaultToolkit().getImage("vanilla.png");
            startPic = Toolkit.getDefaultToolkit().getImage("start.png");
//            ob1Pic = Toolkit.getDefaultToolkit().getImage("rock.png");
//            scoopDecision(testScoop);
            for (int i=0; i<4; i++){
                scoopDecision(i);
            }
            toppingPics[0] = Toolkit.getDefaultToolkit().getImage("sprinkles.png");
            toppingPics[1] = Toolkit.getDefaultToolkit().getImage("sprinkles.png");

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
                g.setFont(new Font("Optima", Font.PLAIN, 17));
                g.setColor(Color.getHSBColor(223.64F, 91F, 88.24F));
                //draw the image of your objects below:
                // This is technically not the actual object! It's just a picture
                  // g.drawImage(pastel, 0,0, 1000, 700, null);
                // g.drawImage(astroPic, astro.xpos, astro.ypos, 200,200, null);
                startPic = Toolkit.getDefaultToolkit().getImage("gamebg.png");
                g.drawImage(startPic, 0, 0, WIDTH, HEIGHT, null);
                if (astro.cone == 1) {
                    astroPic = Toolkit.getDefaultToolkit().getImage("vanilla.png");
                    g.drawImage(astroPic, astro.xpos, astro.ypos, 165, 265, null);
                } else if (astro.cone == 2) {
                    astroPic = Toolkit.getDefaultToolkit().getImage("chocolate.png");
                    g.drawImage(astroPic, astro.xpos, astro.ypos, 165, 265, null);
                } else if (astro.cone == 3) {
                    astroPic = Toolkit.getDefaultToolkit().getImage("sakura.png");
                    g.drawImage(astroPic, astro.xpos, astro.ypos, 165, 265, null);
                }
                g.drawImage(Toolkit.getDefaultToolkit().getImage("order.png"), 850, 0, 150, 225, null);

//                scoopDecision(testScoop);
                for (int i=0; i<4; i++){
                    scoopDecision(i);
                }

//                g.drawImage(testScoopPic, testScoop.xpos, testScoop.ypos, 163, 163, null);
                for (int i=0; i<4; i++){
                    g.drawImage(pics[i], scoops[i].xpos, scoops[i].ypos, 163, 163, null);
                }
                for (int i = 0; i < 2; i++){
                    g.drawImage(toppingPics[i], toppings[i].xpos, toppings[i].ypos, 163, 163, null);
                }

                createOrder();
                // 910, 120, 35, 70; 910, 95, 35, 35; 910, 95, 35, 35;
                drawOrder();
                g.drawString("Score: " + score, 895, 210);
            }
//            for (int x = 0; x < scoops.length; x++) {
//                    g.drawImage(scoops[x].pic, scoops[x].xpos, , 230, 230, null);
//            }

            //dispose the images each time(this allows for the illusion of movement).
            g.dispose();
            bufferStrategy.show();
        }

        public void moveThings() {
            //call the move() method code from your object class
//        astro.bouncingMove();
//            ob1.bouncingMove();
            astro.move();
//            testScoop.move();
            for (int i=0; i<4; i++){
                scoops[i].move();
            }
            for (int i = 0; i < 2; i++){
                toppings[i].move();
            }
        }

        public void collisions() {
//                if(testScoop.rec.intersects(astro.rec) || !testScoop.notAttached){
//                    // Lisa made this ^ unsustainable but whatever (make cone and scoop the same width?)
//                    testScoop.notAttached = false;
//                    testScoop.xpos = astro.xpos;
//                    testScoop.ypos = astro.ypos - 125;
//                }

            for (int i = 0; i < 4; i++) {
                for (int h = 0; h < 4; h++) {
                    if (scoops[i].rec.intersects(scoops[h].rec) && (h != i) && scoops[i].notAttached && scoops[h].notAttached) {
                        scoops[i].xpos = (int) (Math.random() * 900 + 50);
                    }
//                    else if (scoops[i].rec.intersects(scoops[h].rec) && (h != i) && (!scoops[i].notAttached || !scoops[h].notAttached)){
//                        if (attachedScoop2 == 10 || attachedScoop2 == i){
//                            System.out.println("true" + i + h);
//                            attachedScoop2 = i;
//                            scoops[i].notAttached = false;
//                            scoops[i].xpos = astro.xpos;
//                            scoops[i].ypos = astro.ypos - 250;
//                        }
//                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                if (scoops[i].rec.intersects(astro.rec) || !scoops[i].notAttached) {
                    if (scoops[i].identity < 10) {
                        if (attachedScoop == 10 || attachedScoop == i) {
                            attachedScoop = i;
                            scoops[i].notAttached = false;
                            scoops[i].xpos = astro.xpos;
                            scoops[i].ypos = astro.ypos - 125;
                        } else if (attachedScoop2 == 10 || attachedScoop2 == i) {
                            attachedScoop2 = i;
                            scoops[i].notAttached = false;
                            scoops[i].xpos = astro.xpos;
                            scoops[i].ypos = astro.ypos - 250;
                        }
                    }
                }
            }


            for (int k = 0; k < 4; k++){
                if (!scoops[k].notAttached) {
                    anyAttached = true;
                    break;
                }
            }
                for (int i = 0; i < 2; i++) {
                    if ((anyAttached && toppings[i].rec.intersects(astro.rec) && attachedScoop2 == 10 && toppings[Math.abs(i - 1)].scoopOn != 1) || (toppings[i].scoopOn == 1)) {
                        toppings[i].scoopOn = 1;
                        toppings[i].notAttached = false;
                        toppings[i].xpos = astro.xpos;
                        toppings[i].ypos = astro.ypos - 125;
                    } else if ((attachedScoop2 != 10 && toppings[i].rec.intersects(astro.rec)) || !toppings[i].notAttached) {
                        toppings[i].scoopOn = 2;
                        toppings[i].notAttached = false;
                        toppings[i].xpos = astro.xpos;
                        toppings[i].ypos = astro.ypos - 250;
                    }
                }


        }

        public void generateOrder(){
            // Order is at x = 850, y = 0
            cone = (int)(Math.random()*3 + 1);
            scoop1 = (int)(Math.random()*9 + 1);
            scoop2 = (int)(Math.random()*13 + 1);
            sprinkles1 = (int)(Math.random()*4 + 1);
            sprinkles2 = (int)(Math.random()*4 + 1);
        }

        public void createOrder(){
            if (cone == 1){
                correctAnswerPics[0] = (Toolkit.getDefaultToolkit().getImage("vanilla.png"));
            } else if (cone == 2){
                correctAnswerPics[0] = (Toolkit.getDefaultToolkit().getImage("chocolate.png"));
            } else if (cone == 3){
                correctAnswerPics[0] = (Toolkit.getDefaultToolkit().getImage("sakura.png"));
            }

            correctAnswer[0] = (cone);
            correctAnswer[1] = (scoop1);
            if (scoop1 == 1){
                correctAnswerPics[1] = (Toolkit.getDefaultToolkit().getImage("s1.png"));
            } else if (scoop1 == 2){
                correctAnswerPics[1] = (Toolkit.getDefaultToolkit().getImage("s2.png"));
            } else if (scoop1 == 3){
                correctAnswerPics[1] = (Toolkit.getDefaultToolkit().getImage("s3.png"));
            } else if (scoop1 == 4){
                correctAnswerPics[1] = (Toolkit.getDefaultToolkit().getImage("s4.png"));
            } else if (scoop1 == 5){
                correctAnswerPics[1] = (Toolkit.getDefaultToolkit().getImage("s5.png"));
            } else if (scoop1 == 6){
                correctAnswerPics[1] = (Toolkit.getDefaultToolkit().getImage("s6.png"));
            } else if (scoop1 == 7){
                correctAnswerPics[1] = (Toolkit.getDefaultToolkit().getImage("s7.png"));
            } else if (scoop1 == 8){
                correctAnswerPics[1] = (Toolkit.getDefaultToolkit().getImage("s8.png"));
            } else if (scoop1 == 9){
                correctAnswerPics[1] = (Toolkit.getDefaultToolkit().getImage("s9.png"));
            }

            if(sprinkles1 == 1) {
                correctAnswer[2] = (10);
                correctAnswerPics[2] = (Toolkit.getDefaultToolkit().getImage("sprinkles.png"));
            } else {
                correctAnswer[2] = (0);
                // Make sure that correctAnswer[2] isn't 0 before rendering this one; you'll know it's wrong bc it's a lollipop lmao
                correctAnswerPics[2] = (Toolkit.getDefaultToolkit().getImage("lollipop.png"));
            }

            if (scoop2 < 10){
                correctAnswer[3] = scoop2;
                if (scoop2 == 1){
                    correctAnswerPics[3] = (Toolkit.getDefaultToolkit().getImage("s1.png"));
                } else if (scoop2 == 2){
                    correctAnswerPics[3] =(Toolkit.getDefaultToolkit().getImage("s2.png"));
                } else if (scoop2 == 3){
                    correctAnswerPics[3] = (Toolkit.getDefaultToolkit().getImage("s3.png"));
                } else if (scoop2 == 4){
                    correctAnswerPics[3] = (Toolkit.getDefaultToolkit().getImage("s4.png"));
                } else if (scoop2 == 5){
                    correctAnswerPics[3] = (Toolkit.getDefaultToolkit().getImage("s5.png"));
                } else if (scoop2 == 6){
                    correctAnswerPics[3] = (Toolkit.getDefaultToolkit().getImage("s6.png"));
                } else if (scoop2 == 7){
                    correctAnswerPics[3] = (Toolkit.getDefaultToolkit().getImage("s7.png"));
                } else if (scoop2 == 8){
                    correctAnswerPics[3] = (Toolkit.getDefaultToolkit().getImage("s8.png"));
                } else if (scoop2 == 9){
                    correctAnswerPics[3] = (Toolkit.getDefaultToolkit().getImage("s9.png"));
                }

                if (sprinkles2 == 1){
                    correctAnswer[4] = (10);
                    correctAnswerPics[4] = (Toolkit.getDefaultToolkit().getImage("sprinkles.png"));
                } else {
                    correctAnswer[4] = 0;
                }
            } else {
                correctAnswer[3] = 0;
                correctAnswer[4] = 0;
            }
        }

        public void drawOrder(){
            // 910, 120, 35, 70; 910, 95, 35, 35; 910, 95, 35, 35;
            Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
            g.drawImage(correctAnswerPics[0], 910, 120, 35, 70, null);
            g.drawImage(correctAnswerPics[1], 910, 95, 35, 35, null);
            if (correctAnswer[2] != 0){
                g.drawImage(correctAnswerPics[2], 910, 95, 35, 35, null);
            }
            if (correctAnswer[3] != 0){
                g.drawImage(correctAnswerPics[3], 910, 70, 35, 35, null);
            }
            if (correctAnswer[4] != 0){
                g.drawImage(correctAnswerPics[4], 910, 70, 35, 35, null);
            }
//            for (int i = 0; i<5; i++){
//                System.out.print(correctAnswer[i] + " ");
//            }
//            System.out.println();
        }

            public void scoopDecision(int i){
                if (scoops[i].identity == 1){
                    pics[i] = Toolkit.getDefaultToolkit().getImage("s1.png");
                }
                else if (scoops[i].identity == 2){
                    pics[i] = Toolkit.getDefaultToolkit().getImage("s2.png");
                } else if (scoops[i].identity == 3){
                    pics[i] = Toolkit.getDefaultToolkit().getImage("s3.png");
                }
                else if (scoops[i].identity == 4){
                    pics[i] = Toolkit.getDefaultToolkit().getImage("s4.png");
                }
                else if (scoops[i].identity == 5){
                    pics[i] = Toolkit.getDefaultToolkit().getImage("s5.png");
                }
                else if (scoops[i].identity == 6){
                    pics[i] = Toolkit.getDefaultToolkit().getImage("s6.png");
                }
                else if (scoops[i].identity == 7){
                    pics[i] = Toolkit.getDefaultToolkit().getImage("s7.png");
                }
                else if (scoops[i].identity == 8){
                    pics[i] = Toolkit.getDefaultToolkit().getImage("s8.png");
                }
                else if (scoops[i].identity == 9){
                    pics[i] = Toolkit.getDefaultToolkit().getImage("s9.png");
                } else {
                    pics[i] = Toolkit.getDefaultToolkit().getImage("sprinkles.png");
                }
            }

            public boolean evaluateCone(){
                submission[0] = astro.cone;
                if (attachedScoop != 10) { // checking it isn't empty
                    submission[1] = scoops[attachedScoop].identity;
                } else {
                    submission[1] = 0;
                }
                // sprinkles will either have 10 in the array or 0
                if (attachedScoop2 != 10) {
                    submission[3] = scoops[attachedScoop2].identity; // equals 10 when empty
                } else {
                    submission[3] = 0;
                }
                    // sprinkles have scoopOn toppings[x]
                for (int i = 0; i<2; i++){
                    if (toppings[i].scoopOn == 1){
                        submission[2] = 10;
//                        System.out.println(submission[2]);
                    } else if (toppings[i].scoopOn == 2){
                        submission[4] = 10;
                    }
                }
//                System.out.println("after for" + submission[2]);
                if (submission[2] != 10){
                    submission[2] = 0;
                }
                if (submission[4] != 10){
                    submission[4] = 0;
                }
                for (int i = 0; i<4; i++){
//                    System.out.println(submission[i] + " vs " + correctAnswer[i]);
                    if (submission[i] != correctAnswer[i]){
                        return false;
                    }
                }
                if (submission[3] == 0) {
                    score += 10;
                } else {
                    score += 20;
                }
                return true;
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
            if (keyCode == 39){ // d = 68
                astro.rightPressed = true; // name of whatever hero you end up using
            }
            if (keyCode == 37){ // a = 65
                astro.leftPressed = true; // empty name
            }
            if (keyCode == 87) { // w
                astro.upPressed = true;
                startScreen = false;
            }
            if (keyCode == 39) { // s
                astro.downPressed = true;
            }
            if (keyCode == 32) { // space
                astro.spacePressed = true;
            }
            if (keyCode == 10){ // enter
//                testScoop.reset = true;
                // Find the identity of attachedScoop/2 (if they exist)
                // Check the identity of astro.cone
                // Check to see if the sprinkles are on and which one (use scoopOn?)
                evaluateCone();
                for (int i = 0; i<4; i++){
                    scoops[i].reset = true;
                }
                for (int i = 0; i<4; i++){
                    submission[i] = 0;
                }
                for (int i = 0; i < 2; i++){
                    toppings[i].reset = true;
                }
                attachedScoop = 10;
                attachedScoop2 = 10;
                anyAttached = false;
                generateOrder();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            char key = e.getKeyChar();
            int keyCode = e.getKeyCode();
            if (keyCode == 39){ // d = 68
                astro.rightPressed = false; // name of whatever hero you end up using
            }
            if (keyCode == 37){ // a = 65
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


