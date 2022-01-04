package core.ui;

import infrastructure.Constants;
import infrastructure.GraphicsHelper;
import lib.Renderable;
import lib.SpriteRenderer;
import lib.Transform;

import java.awt.*;

public class Instructions implements Renderable {
    public final Transform origin;
    private final SpriteRenderer aKeySprite;
    private final SpriteRenderer dKeySprite;
    private final SpriteRenderer spaceKeySprite;

    public final int WIDTH = 380;
    public final int HEIGHT = 380;
    private final int keyHeight;


    public Instructions(Transform origin) {
        this.origin = origin;
        aKeySprite = new SpriteRenderer(new Transform(origin.x + 25, origin.y + 50), "src/assets/instructions/A-Key.png", 1.5f);
        dKeySprite = new SpriteRenderer(new Transform(origin.x + 25, origin.y + 100), "src/assets/instructions/D-Key.png", 1.5f);
        spaceKeySprite = new SpriteRenderer(new Transform(origin.x, origin.y + 150), "src/assets/instructions/Space-Key.png", 1.5f);
        keyHeight = aKeySprite.getHeight() * 2 / 3;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0, 120));
        g.fillRect((int) origin.x - 50, (int) origin.y - 60, WIDTH, HEIGHT);
        aKeySprite.render(g);
        dKeySprite.render(g);
        spaceKeySprite.render(g);
        g.setColor(Color.white);

        g.setFont(new Font("Arial", 1, 32));
        g.drawString("Controls", (int) origin.x + 80, (int) origin.y);

        g.setFont(new Font("Arial", 1, 24));
        g.drawString("Move Left", (int) origin.x + 120, (int) origin.y + 50 + keyHeight);
        g.drawString("Move Right", (int) origin.x + 120, (int) origin.y + 100 + keyHeight);
        g.drawString("Jump", (int) origin.x + 120, (int) origin.y + 150 + keyHeight);

        g.setFont(new Font("Arial", 1, 32));
        g.drawString("Jump to Start!", (int) origin.x + 40, (int) origin.y + 240 + keyHeight);

        g.setFont(new Font("Arial", 1, 12));
        GraphicsHelper.drawCenteredString(g, "Assets from https://essssam.itch.io/rocky-roads", Constants.MAX_WIDTH/2, Constants.MAX_HEIGHT - 50);
    }
}
