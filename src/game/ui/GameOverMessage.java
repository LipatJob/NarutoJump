package game.ui;

import core.Constants;
import core.GraphicsHelper;
import lib.Renderable;
import lib.Transform;

import java.awt.*;

public class GameOverMessage implements Renderable {
    public final Transform transform;
    public long score;

    public GameOverMessage(int x, int y) {
        this.transform = new Transform(x, y);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0, 120));
        g.fillRect(0, 0, Constants.MAX_WIDTH, Constants.MAX_HEIGHT);

        Font font1 = new Font("Arial", Font.BOLD, 42);
        g.setFont(font1);
        g.setColor(Color.white);
        String gameOverMessage = "Game Over";
        GraphicsHelper.drawCenteredString(g, gameOverMessage, (int) transform.x, (int) transform.y-100);

        Font font2 = new Font("Arial", Font.BOLD, 32);
        g.setFont(font2);
        g.setColor(Color.orange);
        String scoreMessage = "Score: " + score;
        GraphicsHelper.drawCenteredString(g, scoreMessage, (int) transform.x, (int) transform.y-50);

        Font font3 = new Font("Arial", Font.BOLD, 26);
        g.setFont(font3);
        g.setColor(Color.white);
        String retryMessage = "Press Space to Play Again!";
        GraphicsHelper.drawCenteredString(g, retryMessage, (int) transform.x, (int) transform.y + 100);
    }
}
