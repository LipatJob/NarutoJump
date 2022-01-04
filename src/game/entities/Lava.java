package game.entities;

import core.Constants;
import lib.RectangleCollider;
import lib.SpriteRenderer;
import lib.Transform;
import lib.Updatable;

public class Lava implements Updatable {
    public final Transform transform;
    public final RectangleCollider collider;
    public final SpriteRenderer renderer;

    public final int width = Constants.MAX_WIDTH;
    public final int height = 1000;
    public final int maxY = 1000;
    public final int maxSpeed = 10;
    public final int initialSpeed = 3;
    public final int speedIncrement = 1;

    public int speed;

    public Lava() {
        transform = new Transform();
        renderer = new SpriteRenderer(transform, "src/assets/environment/lava.png", width, height);
        collider = new RectangleCollider(transform, width, height);
        speed = initialSpeed;
    }

    @Override
    public void update() {
        if (transform.y > maxY) {
            transform.y = maxY;
        }
        transform.y -= speed;
    }

    public void speedUp() {
        if (speed < maxSpeed) {
            speed += speedIncrement;
        }
    }

    public void restartSpeed() {
        speed = initialSpeed;
    }
}
