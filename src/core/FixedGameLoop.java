package core;

import core.entities.Lava;
import core.entities.Naruto;
import core.entities.Platform;
import core.ui.Background;
import core.ui.GameOverMessage;
import core.ui.Instructions;
import core.ui.Score;
import infrastructure.Constants;
import lib.AbstractGameLoop;
import lib.Transform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class FixedGameLoop extends AbstractGameLoop {
    private final JPanel canvas;

    private final Naruto naruto;
    private final Lava lava;
    private final ArrayList<Platform> platforms;

    private final InputManager inputManager;
    private final GameOverMessage gameOverMessage;
    private final Background background;
    private final Score score;
    private final Instructions instructions;
    private final EnvironmentManager environmentManager;

    private boolean isGameOver = false;
    private boolean isGameStarted = false;

    public FixedGameLoop(JPanel canvas) {
        super();
        this.canvas = canvas;
        final int platformCount = 12;
        inputManager = InputManager.getInstance();

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
        if (!isGameStarted && inputManager.isKeyPressed(KeyEvent.VK_SPACE)) {
            isGameStarted = true;
            naruto.jump();
        }

        // Handle Game Over State
        if (isGameOver || !isGameStarted) {
            return;
        }
        isGameOver = environmentManager.isGameOver();


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
        inputManager.addKeyCode(KeyEvent.VK_A);
        inputManager.addKeyCode(KeyEvent.VK_D);
        inputManager.addKeyCode(KeyEvent.VK_SPACE);

        canvas.addKeyListener(inputManager.getKeyAdapter());
    }

    private void restartGame() {
        environmentManager.initializeEnvironment();
        isGameOver = false;
    }
}
