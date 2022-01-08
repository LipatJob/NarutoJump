package core;

import infrastructure.Constants;
import core.entities.Lava;
import core.entities.Naruto;
import core.entities.Platform;
import lib.RectangleCollider;
import lib.Updatable;

import java.util.ArrayList;
import java.util.Random;

public class EnvironmentManager implements Updatable {
    private final ArrayList<Platform> platforms;
    private final Lava lava;
    private final Naruto naruto;

    public int highestHeight = 0;
    private int heightToNextSpeedUp = 100;

    public EnvironmentManager(Naruto naruto, Lava lava, ArrayList<Platform> platforms) {
        this.naruto = naruto;
        this.lava = lava;
        this.platforms = platforms;

        initializeEnvironment();
    }

    public void initializeEnvironment() {
        naruto.transform.x = Constants.MAX_WIDTH / 2;
        naruto.transform.y = Constants.MAX_HEIGHT / 2;

        lava.transform.x = 0;
        lava.transform.y = Constants.MAX_HEIGHT + 100;

        highestHeight = 0;
        lava.restartSpeed();
        initializePlatforms();
    }

    private void initializePlatforms() {
        for (Platform currentPlatform : platforms) {
            currentPlatform.transform.setLocation(0, 999);
        }

        Platform platform = platforms.get(0);
        platform.transform.x = Constants.MAX_WIDTH / 2;
        platform.transform.y = Constants.MAX_HEIGHT / 2 + 100;
        for (int i = 1; i < platforms.size(); i++) {
            respawnPlatform(platforms.get(i));
        }
    }

    @Override
    public void update() {
        naruto.isGrounded = (naruto.velocityY >= 0) && isCollidingWithPlatforms(naruto.feetCollider);

        // Respawn out ouf bound platforms
        for (Platform platform : platforms) {
            if (isPlatformOutOfBounds(platform)) {
                respawnPlatform(platform);
            }
        }

        // Scroll environment
        if (naruto.transform.y < 250 && naruto.velocityY < 0) {
            naruto.transform.y -= naruto.velocityY;
            scrollEnvironment(-naruto.velocityY);
        }

        // Speed up lava
        if (highestHeight > heightToNextSpeedUp) {
            lava.speedUp();
            heightToNextSpeedUp += 100;
        }
    }

    private boolean isCollidingWithPlatforms(RectangleCollider collider) {
        for (Platform platform : platforms) {
            if (collider.isCollidingWith(platform.collider)) {
                return true;
            }
        }
        return false;
    }

    private void scrollEnvironment(float amount) {
        for (Platform platform : platforms) {
            platform.transform.y += amount;
        }
        highestHeight += amount / 10;
        lava.transform.y += amount;
    }

    private boolean isPlatformOutOfBounds(Platform platform) {
        return platform.transform.y > Constants.MAX_HEIGHT + 10;
    }

    private void respawnPlatform(Platform platform) {
        Random random = new Random();

        // Generate next position
        Platform highestPlatform = getHighestPlatform();
        float newX = random.nextFloat() * (Constants.MAX_WIDTH - platform.width);
        float newY = (highestPlatform.transform.y) -     // gap should start on top of the highest platform
                     (100) -                             // minimum gap is 100
                     (random.nextFloat() * 150);         // Add a random number from 0 to 100 to the gap

        // Platforms that are too far must be moved nearer
        int maxDistance = 400;
        float distance = newX - highestPlatform.transform.x;
        if (Math.abs(distance) > maxDistance) {
            int direction = distance > 0 ? 1 : -1;
            float offset = maxDistance + (random.nextFloat() * 50);
            newX = highestPlatform.transform.x + offset * direction;
            newY = highestPlatform.transform.y - 50 - (random.nextFloat() * 25);
        }

        platform.transform.y = newY;
        platform.transform.x = newX;
    }

    private Platform getHighestPlatform() {
        Platform highestPlatform = platforms.get(0);
        for (Platform currentPlatform : platforms) {
            if (currentPlatform.transform.y < highestPlatform.transform.y) {
                highestPlatform = currentPlatform;
            }
        }
        return highestPlatform;
    }

    public boolean isGameOver() {
        boolean isPlayerOutOfBounds = naruto.transform.y > Constants.MAX_HEIGHT + naruto.height,
                isPlayerTouchingLava = lava.collider.isCollidingWith(naruto.feetCollider);
        return isPlayerOutOfBounds || isPlayerTouchingLava;
    }

}
