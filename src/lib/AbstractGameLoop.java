package lib;

public abstract class AbstractGameLoop {
    private boolean isLoopRunning;
    private Thread gameThread;

    public AbstractGameLoop() {
        isLoopRunning = false;
        gameThread = new Thread();
    }

    public void run() {
        isLoopRunning = true;
        gameThread = new Thread(this::processGameLoop);
        gameThread.start();
    }

    public void stop() {
        isLoopRunning = false;
    }

    public boolean isLoopRunning() {
        return isLoopRunning;
    }

    void processGameLoop() {
        while (isLoopRunning()) {
            updateFrame();
            renderFrame();
            waitForNextFrame();
        }
    }

    void waitForNextFrame() {
        int framesPerSecond = 30;
        final long milisecondsPerFrame = (int) (1000.0/framesPerSecond);
        try {
            Thread.sleep(milisecondsPerFrame);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract void updateFrame();

    public abstract void renderFrame();
}
