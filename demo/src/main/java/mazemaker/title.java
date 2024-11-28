package mazemaker;

import java.awt.Color;

import javax.swing.JFrame;

public class Title extends JFrame {
   	public String title = "ezPaint";
   	public int version = 1;

   	Title(){
	   	MainWindow mainWin = new MainWindow();
	   	mainWin.getContentPane().setBackground(Color.GRAY); // 設置內容面板背景色
		mainWin.setVisible(true);
	   	mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}