package infrastructure;

import java.awt.*;

public class GraphicsHelper {
    public static void drawCenteredString(Graphics g, String message, int x, int y){
        Font font = g.getFont();
        FontMetrics metrics = g.getFontMetrics(font);
        int width = metrics.stringWidth(message);
        int height = metrics.getHeight();
        g.drawString(message, x - (width / 2), y - (height / 2));
    }
}
