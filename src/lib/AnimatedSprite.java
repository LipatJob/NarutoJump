package lib;

import core.AssetsManager;

import java.awt.*;

public class AnimatedSprite implements Renderable {
    int width;
    int height;
    Transform transform;
    Image[] images;

    private int currentSpriteIndex = 0;
    private int delay;
    private int currentDelay;

    public AnimatedSprite(Transform transform, String[] spriteLocations) {
        this(transform, spriteLocations, 1);
    }

    public AnimatedSprite(Transform transform, String[] spriteLocations, int delay) {
        this.transform = transform;
        this.delay = delay;

        images = new Image[spriteLocations.length];
        for (int i = 0; i < spriteLocations.length; i++) {
            images[i] = AssetsManager.getImageFromPath(spriteLocations[i]);
        }

        width = images[0].getWidth(null);
        height = images[0].getHeight(null);
    }


    private Image getCurrentSprite() {
        return images[currentSpriteIndex];
    }

    private void selectNextSprite() {
        if (currentDelay <= 0) {
            currentSpriteIndex = (currentSpriteIndex + 1) % images.length;
            currentDelay = delay;
        } else {
            currentDelay--;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentSprite(), (int) transform.x, (int) transform.y, width, height, null);
        selectNextSprite();
    }
}
