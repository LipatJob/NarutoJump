package core.ui;

import infrastructure.Constants;
import lib.Renderable;
import lib.SpriteRenderer;
import lib.Transform;

import java.awt.*;

public class Background implements Renderable {
    public final Transform transform;
    public final SpriteRenderer mountainsSprite;

    public Background() {
        transform = new Transform();
        mountainsSprite = new SpriteRenderer(new Transform(0, 0), "src/assets/environment/background.png", Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(135, 206, 235));
        g.fillRect(1, 1, Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
        mountainsSprite.render(g);
    }
}
