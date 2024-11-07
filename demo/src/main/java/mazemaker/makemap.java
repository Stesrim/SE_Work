package mazemaker;

import java.awt.Color;
import javax.swing.*;

public class makemap extends JFrame 
{
   public String makemap = "ezPaint";
   public int version = 1;

   makemap()
   {
	   secondwindow makeWin = new secondwindow();
	   
       // 設置視窗大小、位置、背景顏色等

       // 設置 JFrame 的背景色
	   makeWin.getContentPane().setBackground(Color.RED); // 設置內容面板背景色
 
       // 設置視窗最大化
 
       // 設置視窗可見
	   makeWin.setVisible(true);
 
       // 設定預設關閉行為
	   makeWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}