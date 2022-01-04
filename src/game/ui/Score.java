package game.ui;

import lib.Renderable;
import lib.Transform;

import java.awt.*;

public class Score implements Renderable {
    public final Transform transform;

    public long score;

    public Score() {
        transform = new Transform(50, 50);
        score = 0;
    }

    @Override
    public void render(Graphics g) {
        Font font = new Font("Arial", 1, 24);
        g.setFont(font);
        String message = String.format("Score: %s", score);
        FontMetrics metrics = g.getFontMetrics(font);
        int width = metrics.stringWidth(message);
        int height = metrics.getHeight();

        int padding = 20;
        g.setColor(new Color(0, 0, 0, 50));
        g.fillRect((int) transform.x - padding / 2, (int) transform.y - height - padding / 2 + 10, width + padding, height + padding - 10);

        g.setColor(Color.orange);
        g.drawString(message, (int) transform.x, (int) transform.y);
    }
}
