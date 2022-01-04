package game;

import core.Constants;
import game.entities.Lava;
import game.entities.Naruto;
import game.entities.Platform;
import game.ui.Background;
import game.ui.GameOverMessage;
import game.ui.Instructions;
import game.ui.Score;
import lib.AbstractGameLoop;
import lib.Transform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class FixedGameLoop extends AbstractGameLoop {
    private final JPanel canvas;
    private final Naruto naruto;
    private final Lava lava;
    private final GameOverMessage gameOverMessage;
    private final ArrayList<Platform> platforms;
    private final Background background;
    private final Score score;
    private final Instructions instructions;
    private final EnvironmentManager environmentManager;

    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;
    private boolean isJumping = false;
    private boolean isGameOver = false;
    private boolean isGameStarted = false;

    public FixedGameLoop(JPanel canvas) {
        super();
        this.canvas = canvas;
        final int platformCount = 12;

        // Initialize Entities
        this.naruto = new Naruto(0, 0);
        this.lava = new Lava();
        this.platforms = new ArrayList<>();
        for (int i = 0; i < platformCount; i++) {
            platforms.add(new Platform(0, 999));
        }
        this.environmentManager = new EnvironmentManager(naruto, lava, platforms);

        // Initialize User Interface Elements
        this.gameOverMessage = new GameOverMessage(Constants.MAX_WIDTH / 2, Constants.MAX_HEIGHT / 2);
        this.background = new Background();
        this.score = new Score();
        instructions = new Instructions(new Transform(260, 260));

        setupInputHandler();
    }

    @Override
    public void updateFrame() {
        // Handle Game Over State
        if (isGameOver || !isGameStarted) {
            return;
        }
        isGameOver = environmentManager.isGameOver();

        // Handle Player Actions
        if (isMovingLeft) {
            naruto.moveLeft();
        } else if (isMovingRight) {
            naruto.moveRight();
        } else {
            naruto.idle();
        }
        if (isJumping) {
            isJumping = false;
        }

        // Update Entities
        environmentManager.update();
        naruto.update();
        lava.update();
        score.score = environmentManager.highestHeight;
    }

    @Override
    public void renderFrame() {
        canvas.repaint();
    }

    public void drawFrame(Graphics g) {
        background.render(g);
        naruto.animationManager.render(g);
        //naruto.feetCollider.render(g);
        for (Platform platform : platforms) {
            platform.renderer.render(g);
            //platform.collider.render(g);
        }
        lava.renderer.render(g);
        if (isGameOver) {
            gameOverMessage.score = score.score;
            gameOverMessage.render(g);
        }
        score.render(g);
        if (!isGameStarted) {
            instructions.render(g);
        }
    }

    void setupInputHandler() {
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                        isMovingLeft = true;
                        break;
                    case KeyEvent.VK_D:
                        isMovingRight = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        if (!isGameStarted) {
                            isGameStarted = true;
                            naruto.jump();
                        }
                        if (isGameOver) {
                            restartGame();
                        } else {
                            naruto.jump();
                        }
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                        isMovingLeft = false;
                        break;
                    case KeyEvent.VK_D:
                        isMovingRight = false;
                        break;
                }
            }
        });
    }

    private void restartGame() {
        environmentManager.initializeEnvironment();
        isGameOver = false;
    }
}
