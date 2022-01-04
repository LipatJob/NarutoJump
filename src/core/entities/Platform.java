package core.entities;

import lib.RectangleCollider;
import lib.SpriteRenderer;
import lib.Transform;

public class Platform {
    public final Transform transform;
    public final SpriteRenderer renderer;
    public final RectangleCollider collider;

    public int width;
    public int height;

    public Platform(int x, int y) {
        transform = new Transform(x, y);
        renderer = new SpriteRenderer(transform, "src/assets/platform/platform.png", 2);
        this.width = renderer.getWidth();
        this.height =renderer.getHeight();
        collider = new RectangleCollider(transform, width, 15);
    }
}
