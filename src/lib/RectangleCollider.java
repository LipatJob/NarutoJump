package lib;

import java.awt.*;

public class RectangleCollider implements Renderable{
    Transform transform;
    int width;
    int height;
    int offsetX;
    int offsetY;

    public RectangleCollider(Transform transform, int width, int height) {
        this.transform = transform;
        this.width = width;
        this.height = height;
        this.offsetX = 0;
        this.offsetY = 0;
    }

    public RectangleCollider(Transform transform, int width, int height, int offsetX, int offsetY) {
        this.transform = transform;
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public boolean isCollidingWith(RectangleCollider other){
        Rectangle thisArea = new Rectangle((int) (this.transform.x + offsetX), (int) this.transform.y + offsetY, this.width, this.height);
        Rectangle otherArea = new Rectangle((int) other.transform.x, (int) other.transform.y, other.width, other.height);
        return thisArea.intersects(otherArea);
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g.setColor(Color.GREEN);
        g.drawRect((int) transform.x + offsetX, (int) transform.y + offsetY, width, height);
    }

}
