package lib;

import core.AssetsManager;

import java.awt.*;

public class SpriteRenderer implements Renderable {
    int width;
    int height;
    Transform transform;
    Image image;

    public SpriteRenderer(Transform transform, String path) {
        this.image = AssetsManager.getImageFromPath(path);
        this.height = image.getHeight(null);
        this.width = image.getWidth(null);;
        this.transform = transform;
    }

    public SpriteRenderer(Transform transform, String path, float scale) {
        this.image = AssetsManager.getImageFromPath(path);
        this.height = (int) (image.getHeight(null) * scale);
        this.width = (int) (image.getWidth(null) * scale);
        this.transform = transform;
    }

    public SpriteRenderer(Transform transform, String path, int width, int height) {
        this.image = AssetsManager.getImageFromPath(path);
        this.width = width;
        this.height = height;
        this.transform = transform;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Transform getTransform() {
        return transform;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) transform.x, (int) transform.y, width, height, null);
    }
}
