import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class BackGround {

    public ImageView[] animateBackground(Group root){
        ImageView background = new ImageView("background.png");
        background.setLayoutY(0);background.setLayoutX(0);
        background.setFitWidth(600);background.setFitHeight(1000);

        ImageView background2 = new ImageView("background2.png");
        background2.setLayoutY(-1000);background2.setLayoutX(0);
        background2.setFitWidth(600);background2.setFitHeight(1000);

        ImageView background3 = new ImageView("background3.png");
        background3.setLayoutY(0);background3.setLayoutX(0);
        background3.setFitWidth(600);background3.setFitHeight(1000);

        root.getChildren().add(background3);
        root.getChildren().add(background);
        root.getChildren().add(background2);

        ImageView[] back = new ImageView[2];
        back[0]=background;
        back[1]=background2;
        return back;


    }

}
