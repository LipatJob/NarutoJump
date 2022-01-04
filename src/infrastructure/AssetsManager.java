package infrastructure;

import javax.swing.*;
import java.awt.*;

public class AssetsManager {

    public static Image getImageFromPath(String filePath){
        return new ImageIcon(filePath).getImage();
    }
}
