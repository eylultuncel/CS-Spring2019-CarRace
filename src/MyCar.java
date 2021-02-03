public class MyCar {

    public Sprite addMyCar(String path,int x, int y){
        Sprite myCar = new Sprite();
        myCar.setImage(path);
        myCar.setPosition(x, y);
        return myCar;
    }
}
