package core;

import game.FixedGameLoop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GameWindow extends JFrame {
    JPanel gameCanvas;
    FixedGameLoop gameLoop;

    public GameWindow() {
        gameCanvas = new GameCanvas();

        // setup game loop
        gameLoop = new FixedGameLoop(gameCanvas);
        gameLoop.run();

        // setup window
        this.setSize(Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
        this.setResizable(false);
        this.add(gameCanvas);

        // exit the program when the window is closed
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gameLoop.stop();
                System.exit(0);
            }
        });
    }


    private class GameCanvas extends JPanel {

        public GameCanvas() {
            this.setSize(Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
            this.setFocusable(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            gameLoop.drawFrame(g);
        }
    }
}


