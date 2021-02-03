import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.AnimationTimer;
import java.util.ArrayList;

public class Assignment4 extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage)
    {
        theStage.setTitle( "HUBBM-Racer" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        BackGround backGround = new BackGround();
        ImageView[] background = backGround.animateBackground(root);
        theStage.show();

        Canvas canvas = new Canvas( 600, 1000 );
        root.getChildren().add( canvas );

        KeyboardControl keyboard = new KeyboardControl();
        ArrayList<String> input = keyboard.keyboardInput(theScene);

        GraphicsContext gc = canvas.getGraphicsContext2D();


        MyCar car = new MyCar();
        Sprite myCar = car.addMyCar("AAAcar.png",225,800);

        EnemyCars enemyCarsList = new EnemyCars();
        ArrayList<Sprite> enemyCars = enemyCarsList.addEnemyCar();
        int speed = 450;
        Game game = new Game();
        AnimationTimer animation = game.animation(myCar,enemyCars,input,gc,speed,background);
        animation.start();




        theStage.show();

        gc.fillText( "GAME OVER", 0, 0);
        gc.strokeText( "GAME OVER", 0, 0 );
    }
}