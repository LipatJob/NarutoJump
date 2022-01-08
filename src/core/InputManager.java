package core;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Dictionary;
import java.util.Hashtable;

public class InputManager {
    private final Dictionary<Integer, Boolean> keyMappings;
    private static InputManager instance = null;
    private final KeyAdapter keyAdapter;

    private InputManager() {
        keyMappings = new Hashtable<>();
        keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyMappings.put(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyMappings.put(e.getKeyCode(), false);
            }
        };
    }

    public static InputManager getInstance() {
        if (instance == null) {
            instance = new InputManager();
        }
        return instance;
    }

    public void listenToKeyCode(int keyCode) {
        keyMappings.put(keyCode, false);
    }

    public boolean isKeyHeldDown(int keyCode) {
        return keyMappings.get(keyCode);
    }

    public KeyAdapter getKeyAdapter() {
        return keyAdapter;
    }


}
