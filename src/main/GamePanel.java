package main;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import tile.TileManager;
import tile.Tile;
import tile.GTile;


public class GamePanel extends JPanel implements Runnable{
        final int originalTileSize = 16;
        final int scale = 3;
        final int gScale = 25;
        public final int tileSize = originalTileSize * scale;
        public final int gtSize = (originalTileSize * gScale) / 10;
        final public int maxScreenCol = 16;
        final public int maxScreenRow = 11;
        final int screenWidth = tileSize * maxScreenCol;
        final int screenHeight = tileSize * maxScreenRow;

        public int maxWorldCol;
        public int maxWorldRow;
        public int maxWorldWidth;
        public int maxWorldHeight;
        final int FPS = 60;
        public GTile[][] grid;
        public int[][] gridHolder;
        private int rx;
        private int ry;
         
        JTextField text = new JTextField();

        TileManager tileM = new TileManager(this);
        KeyHandler KeyH = new KeyHandler();
        GridChecker gridC = new GridChecker(this);
//      GTile gTile = new GTile(this);
        Thread gameThread;



        public GamePanel(){

                this.setPreferredSize(new Dimension(screenWidth, screenHeight));
                this.setBackground(Color.black);
                this.setDoubleBuffered(true);
                this.addKeyListener(KeyH);
                this.setFocusable(true);
                this.setLayout(null);
                grid = new GTile[9][9];
                text.setPreferredSize(new Dimension(200, 200));
                gameInit();

        }

        public void startGameThread(){
                
                gameThread = new Thread(this);
                gameThread.start();
        }

        @Override
        public void run(){

                double drawInterval = 1000000000 / FPS;
                double delta = 0;
                double delta2 = 0;
                long lastTime = System.nanoTime();
                long currentTime;
                long totalTime = 0;

                while(gameThread != null) {
                        
                        currentTime = System.nanoTime();
                        delta += (currentTime - lastTime) / drawInterval;
                        delta2 += (currentTime - lastTime) / drawInterval;
                        totalTime += (currentTime - lastTime);
                        lastTime = currentTime;

                        if (delta >= 1) {
//                          update();

            //              repaint();

                            delta--;
                                
                        }

                        if (delta2 >= 0.125) {
                            repaint();
                            delta2--;
                                
                        }

                }
        }

        public void gameInit() {
                gridHolder = gridC.getRow();
                Random rand = new Random();
//              System.out.println(list);
                for (int i = 0; i < 9 ; i++){
                     for (int j = 0; j < 9; j++){
                        int y = ((i +1) * tileSize - 24);
                        int x = ((j +1) * tileSize - 40);
                        grid[j][i] = new GTile (gridHolder[j][i], x, y);
                     }
                    }
                for (int l = 0; l < 32; l++){
                    rx = rand.nextInt(9); 
                    ry = rand.nextInt(9); 
                    if (grid[rx][ry].visible == true){
                        l--;
                    }
                    else
                        grid[rx][ry].visible = true;
//                  System.out.println(rx + " " + ry);
                }
            
        }

//      public void update() {

 //         player.update();
               
//      }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            tileM.draw(g2);
            gridC.draw(g2);
//          player.draw(g2);
            g2.dispose();
                            this.add(text);
                            this.setVisible(true);
        }
}
