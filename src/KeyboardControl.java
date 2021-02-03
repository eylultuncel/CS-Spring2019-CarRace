import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;

public class KeyboardControl {
    ArrayList<String> input = new ArrayList<String>();

    public ArrayList<String> keyboardInput(Scene theScene){

        theScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e)
            {
                String code = e.getCode().toString();
                if ( !input.contains(code) )
                    input.add( code );
            }
        });

        theScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e)
            {
                String code = e.getCode().toString();
                input.remove( code );
            }
        });
        return input;
    }
}
