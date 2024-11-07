package mazemaker;

import java.awt.Color;
import javax.swing.*;

public class title extends JFrame 
{
   public String title = "ezPaint";
   public int version = 1;

   title()
   {
	   MainWindow mainWin = new MainWindow();
	   
       // 設置視窗大小、位置、背景顏色等

       // 設置 JFrame 的背景色
	   mainWin.getContentPane().setBackground(Color.GRAY); // 設置內容面板背景色
 
       // 設置視窗最大化
 
       // 設置視窗可見
	   mainWin.setVisible(true);
 
       // 設定預設關閉行為
	   mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}