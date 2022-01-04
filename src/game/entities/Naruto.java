package game.entities;

import core.Constants;
import lib.RectangleCollider;
import lib.Transform;
import lib.Updatable;

public class Naruto implements Updatable {
    public final Transform transform;
    public final RectangleCollider feetCollider;
    public final NarutoAnimator animationManager;

    public int width;
    public int height;
    public float velocityX;
    public float velocityY;
    public boolean isGrounded;

    private int jumpEffectLeft;

    public Naruto(int x, int y) {
        this.transform = new Transform(x, y);
        this.animationManager = new NarutoAnimator(this);
        this.width = animationManager.width;
        this.height = animationManager.height;
        this.feetCollider = new RectangleCollider(transform, width + 5, 10, -2, height - 10);

        this.velocityX = 0;
        this.velocityY = 0;
        this.jumpEffectLeft = 0;
        this.isGrounded = false;
    }

    public void idle() {
        velocityX = 0;
    }

    public void moveLeft() {
        velocityX = -10;
    }

    public void moveRight() {
        velocityX = 10;
    }

    public void jump() {
        if (isGrounded && jumpEffectLeft <= 0) {
            jumpEffectLeft = 4;
        }
    }

    @Override
    public void update() {
        animationManager.update();

        // apply gravity
        if (isGrounded) {
            // do not apply gravity when player is touching ground
            velocityY = 0;
        } else if (velocityY < 20) {
            // maximum gravity is 20 units
            velocityY += 2f;
        }

        // apply jump effect
        if (jumpEffectLeft > 0) {
            velocityY -= 10;
            jumpEffectLeft--;
        }

        // apply velocity
        transform.x += velocityX;
        transform.y += velocityY;

        // Constrain out of bounds movement
        if (transform.x > Constants.MAX_WIDTH - width) {
            transform.x = Constants.MAX_WIDTH - width;
        }
        if (transform.x < 0) {
            transform.x = 0;
        }
    }
}
