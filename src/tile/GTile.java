package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import main.GamePanel;

public class GTile {
        
        public Color color;
        GamePanel gp;
        public int x;
        public int y;
        public int val;
        public boolean visible = false;

        public GTile (int val, int x, int y) {
            this.x = x;
            this.y = y;
            this.val = val;

        }

}
