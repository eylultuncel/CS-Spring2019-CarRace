import java.util.ArrayList;

public class EnemyCars {
    ArrayList<Sprite> enemyCars = new ArrayList<Sprite>();

    public ArrayList<Sprite> addEnemyCar(){

        for (int i = 0; i <35; i++)
        {
            Sprite enemycar = new Sprite();
            enemycar.setImage("AAAcarr.png");

            boolean check= false;
            while (check==false) {

                double random = 500 * Math.random();
                while (random > 365 || random < 155) {
                    random = 500 * Math.random();
                }
                double px = random;
                double py = 10000 * Math.random() - 11000;
                enemycar.setPosition(px, py);
                check = checkIntersection(enemycar);
            }

            enemyCars.add(enemycar );
        }
        return enemyCars;
    }

    public boolean checkIntersection (Sprite enemycar){
        for(Sprite ene: enemyCars){
            if(enemycar.intersects(ene,0)){
                return false;
            }
        }
        return true;
    }
}
