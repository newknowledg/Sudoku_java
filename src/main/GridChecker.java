package main;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.swing.JTextField;


public class GridChecker {
        
        GamePanel gp;
        List<Integer> list = new ArrayList<>();
        List<Integer> xList = new ArrayList<>();
        JTextField text = new JTextField();

        public GridChecker(GamePanel gp) {
                this.gp = gp;
                text.setPreferredSize(new Dimension(gp.gtSize, gp.gtSize));
        }
        public int[][] getRow(){
            Random rand = new Random();
            int val;
            int grid[][];
            int cPos;
            int rPos;
            grid = new int[9][9];
            for (int g = 0; g < 9; g++){
                list.add(1);
                list.add(2);
                list.add(3);
                list.add(4);
                list.add(5);
                list.add(6);
                list.add(7);
                list.add(8);
                list.add(9);
//              System.out.println(list);
                for (int i = 0; i < 9 ; i++){
                   for (int m = 0; m < list.size(); m++)
                       xList.add(list.get(m));

 //             System.out.println(xList);
                   if (g >= 1){
//                     System.out.println(g);
                      for (int h = 0; h < g; h++)
                          xList.remove(Integer.valueOf(grid[i][h]));
 //             System.out.println(xList);
                      rPos = -(g%3);
                      cPos = -(i%3);
    //                    System.out.println("Cpos is " + cPos + " rPos is " + rPos);
                      for (int l = 0; l < 3; l++) {
                              for (int k = 0; k < 3; k++){
                                      xList.remove(Integer.valueOf(grid[i + cPos][g + rPos]));
                                  cPos++;
                              }
                              rPos++;
                              cPos = -(i%3);
                          }
//              System.out.println(xList);
                      if (xList.size() < 1){
                            i = -1;
                            list.clear();
                            for (int j = 1; j < 10; j++){
                                list.add(j);
                                grid[j-1][g] = 0;
                            }
                                continue;
                      }
                      val = xList.get(rand.nextInt(xList.size()));
                   }
                   else
                   val =  list.get(rand.nextInt(list.size()));

                   grid[i][g] = val;
                   list.remove(Integer.valueOf(val));
                   xList.clear();
               }
               System.out.println(grid[0][g] + " " + grid[1][g] + " " + grid[2][g] + " " + grid[3][g] + " " + grid[4][g] + " " + grid[5][g] + " " + grid[6][g] + " " + grid[7][g] + " " + grid[8][g]);
           }
           return grid;
        }
        public void draw(Graphics2D g2) {
                
                int worldCol = 1;
                int worldRow = 1;
                int val;

                while (worldCol < 10 && worldRow < 10) {
                        int col = worldCol - 1;
                        int row = worldRow - 1;
                        int worldX = (worldCol * gp.tileSize) + 4;
                        int worldY = (worldRow * gp.tileSize) + 4;
                        val = gp.grid[col][row].val; 
//                      System.out.println(gp.grid[col][row]);
                        g2.setColor(Color.white);
                        g2.fillRect(worldX, worldY, gp.gtSize, gp.gtSize);
                        if (gp.grid[col][row].visible == true) {
                            g2.setColor(Color.black);
                            g2.drawString(String.valueOf(val), worldX + 20, worldY + 36);
                        }
                        else {
                            gp.add(text);
                            gp.setVisible(true);
                        }

                        worldCol++;

                        if (worldCol == 10) {
                                
                                worldCol = 1;
                                worldRow++;
                        }
                }

        }
}
