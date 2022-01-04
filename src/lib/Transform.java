package lib;

public class Transform {
    public float x;
    public float y;

    public Transform() {
        this.x = 0;
        this.y = 0;
    }

    public Transform(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setLocation(float x, float y){
        this.x = x;
        this.y = y;
    }
}
