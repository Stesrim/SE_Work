package mazemaker;

import java.awt.Color;
import javax.swing.*;

public class makemap extends JFrame 
{
   public String makemap = "ezPaint";
   public int version = 1;

   makemap()
   {
	   gamedesign makeWin = new gamedesign();
	   

	   makeWin.getContentPane().setBackground(Color.BLACK); // 設置內容面板背景色
 
 
	   makeWin.setVisible(true);
 
	   makeWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}