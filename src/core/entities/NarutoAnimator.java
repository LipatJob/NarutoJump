package core.entities;

import lib.AnimatedSprite;
import lib.Renderable;
import lib.SpriteRenderer;
import lib.Updatable;

import java.awt.*;


public class NarutoAnimator implements Updatable, Renderable {
    private final AnimatedSprite walkingLeftAnimation;
    private final AnimatedSprite walkingRightAnimation;
    private final SpriteRenderer idleLeftAnimation;
    private final SpriteRenderer idleRightAnimation;
    private final SpriteRenderer jumpingLeftAnimation;
    private final SpriteRenderer jumpingRightAnimation;
    private final Naruto naruto;

    public int width;
    public int height;
    
    private Renderable currentSprite;
    private float previousVelocityX;

    public NarutoAnimator(Naruto naruto) {
        this.naruto = naruto;

        String[] walkingLeftImages = new String[]{
                "src/assets/naruto/movingleft/L1.png",
                "src/assets/naruto/movingleft/L2.png",
                "src/assets/naruto/movingleft/L3.png",
                "src/assets/naruto/movingleft/L4.png",
        };
        String[] walkingRightImages = new String[]{
                "src/assets/naruto/movingright/R1.png",
                "src/assets/naruto/movingright/R2.png",
                "src/assets/naruto/movingright/R3.png",
                "src/assets/naruto/movingright/R4.png",
        };

        String idleLeftImage = "src/assets/naruto/movingleft/L1.png";
        String idleRightImage = "src/assets/naruto/movingright/R1.png";
        String jumpingLeftImage = "src/assets/naruto/movingleft/L2.png";
        String jumpingRightImage = "src/assets/naruto/movingright/R2.png";

        walkingLeftAnimation = new AnimatedSprite(naruto.transform, walkingLeftImages, 4);
        walkingRightAnimation = new AnimatedSprite(naruto.transform, walkingRightImages, 4);
        idleLeftAnimation = new SpriteRenderer(naruto.transform, idleLeftImage);
        idleRightAnimation = new SpriteRenderer(naruto.transform, idleRightImage);
        jumpingLeftAnimation = new SpriteRenderer(naruto.transform, jumpingLeftImage);
        jumpingRightAnimation = new SpriteRenderer(naruto.transform, jumpingRightImage);
        currentSprite = idleRightAnimation;

        previousVelocityX = 0;
        width = idleRightAnimation.getWidth();
        height = idleRightAnimation.getHeight();
    }

    @Override
    public void update() {
        // jumping/falling
        if (naruto.velocityY != 0 && naruto.velocityX > 0) {
            currentSprite = jumpingRightAnimation;
        } else if (naruto.velocityY != 0 && naruto.velocityX < 0) {
            currentSprite = jumpingLeftAnimation;
        }
        // walking
        else if (naruto.velocityX > 0) {
            currentSprite = walkingRightAnimation;
        } else if (naruto.velocityX < 0) {
            currentSprite = walkingLeftAnimation;
        }
        // idle
        else if (previousVelocityX > 0) {
            currentSprite = idleRightAnimation;
        } else if (previousVelocityX < 0) {
            currentSprite = idleLeftAnimation;
        }
        previousVelocityX = naruto.velocityX;
    }


    @Override
    public void render(Graphics g) {
        currentSprite.render(g);
    }
}
