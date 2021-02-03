import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class Game {
        private int score=1;
        private int level=1;
        private int turns=0;
        static public boolean gameover=false;

    public AnimationTimer animation (Sprite myCar, ArrayList<Sprite> enemyCars, ArrayList<String> input, GraphicsContext gc, int myspeed,ImageView[] backgrounds){

        AnimationTimer animationTimer = new AnimationTimer()
        {
            long lastNanoTime =  System.nanoTime();
            private long lastUpdate ;
            private double speed =myspeed;

            @Override
            public void start() {
                lastUpdate = System.nanoTime();
                super.start();
            }
            public void handle(long currentNanoTime)
            {
                // calculate time
                long elapsedNanoSeconds = currentNanoTime - lastUpdate ;
                double elapsedSeconds = elapsedNanoSeconds / 1_000_000_000.0 ;
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime= currentNanoTime;

                ImageView background =backgrounds[0];
                ImageView background2 =backgrounds[1];
                if(gameover==false) {
                    if(background.getLayoutY()>=990){background.setLayoutY(-1000);}
                    if(background2.getLayoutY()>=990){background2.setLayoutY(-1000);}

                    background.setLayoutY(background.getLayoutY() + elapsedTime * (speed + (getLevel() * 50)));
                    background2.setLayoutY(background2.getLayoutY() + elapsedTime * (speed + (getLevel() * 50)));

                lastUpdate = currentNanoTime ;

                // game logic
                    myCar.setVelocity(0,0);
                    if (input.contains("LEFT"))
                        myCar.addVelocity(-400,0);
                    if (input.contains("RIGHT"))
                        myCar.addVelocity(400,0);
                    if (input.contains("UP"))
                        myCar.addVelocity(0,-300);
                    if (input.contains("DOWN"))
                        myCar.addVelocity(0,300);

                    myCar.update(elapsedTime);
                }


                Iterator<Sprite> enemyCar = enemyCars.iterator();

                while ( enemyCar.hasNext() )
                {
                    Sprite nextEnemyCar = enemyCar.next();
                    if(gameover==false) {
                        if(nextEnemyCar.getPositionY()>=myCar.getPositionY()){
                            nextEnemyCar.setImage("AAAcarrr.png");
                            nextEnemyCar.setPassed(true);
                        }
                        if(nextEnemyCar.getPositionY()>=1000){
                            nextEnemyCar.setImage("AAAcarr.png");
                            nextEnemyCar.setPositionY(-10000);
                        }
                        nextEnemyCar.setPositionY(nextEnemyCar.getPositionY() + elapsedTime * (speed + (getLevel() * 50)));
                    }

                    //collision detection
                    if ( nextEnemyCar.getPositionY()<myCar.getPositionY() && myCar.intersects(nextEnemyCar,1) )
                    {
                        myCar.setImage("crashCar.png");
                        nextEnemyCar.setImage("crashCar.png");
                        gameover=true;


                        /*
                        if(input.contains("ENTER")){

                            myCar.setImage("AAAcar.png");
                            myCar.setPosition(225, 800);
                            setScore(0);
                            setLevel(1);
                            turns=0;
                            gameover=false;
                        }

                         */
                    }

                    if(gameover== false) {
                        setLevel(((getScore()) / 5) + 1);

                        int carCount = 0;
                        for (Sprite enem : enemyCars) {
                            if (enem.isPassed() == true) {
                                carCount++;
                            }
                        }
                        if (carCount % 35 == 0 && carCount != 0) {
                            turns++;
                            for (Sprite enemy : enemyCars) {
                                enemy.setPassed(false);
                            }
                        }
                        setScore(carCount + (turns * 35));
                    }

                }

                gc.clearRect(0, 0, 600,1000);
                myCar.render( gc );

                for (Sprite enemy : enemyCars )
                    enemy.render( gc );


                    Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 30 );
                    gc.setFont( theFont );
                    gc.setFill(Color.DARKBLUE);
                    gc.setStroke(Color.BLACK);
                    gc.setLineWidth(1);
                    String pointsText = "Score: " + (getScore()*getLevel());
                    String levelText = "Level: " + getLevel() ;
                    gc.fillText(pointsText, 0, 36);
                    gc.strokeText(pointsText, 0, 36);
                    gc.fillText(levelText, 0, 72);
                    gc.strokeText(levelText, 0, 72);

                if(gameover==true){
                    Font theFont1 = Font.font( "Helvetica", FontWeight.BOLD, 50 );
                    gc.setFont( theFont1 );
                    gc.setFill( Color.PALEVIOLETRED);
                    gc.setStroke( Color.BLACK );
                    gc.setLineWidth(1);
                    String text = "Score: " + (getScore());
                    gc.fillText( "GAME OVER", 150, 200);
                    gc.strokeText( "GAME OVER", 150, 200 );
                    gc.fillText( text, 200, 250 );
                    gc.strokeText( text, 200, 250 );
                    gc.fillText("Press enter to\n start again!",150,400);
                    gc.strokeText("Press enter to\n start again!",150,400);
                    return;

                }

            }


        };

        return animationTimer;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
