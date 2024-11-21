package mazemaker;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class makemap extends JFrame {

	public mazedesignwin designWin;
	public mazedesignmenu dm;
	public DrawPanel draw;
	public Page tabpane;
	public toolbar tb;
	public static State st;
	public static State sta;

   	makemap(){
		designWin = new mazedesignwin(this); //創建設計端頁面
    	tabpane = new Page();
		draw = new DrawPanel();
		dm = new mazedesignmenu(this, tabpane); //創建設計端菜單&工具列
		tb = new toolbar(this);
		designWin.add(tabpane, BorderLayout.CENTER);
   	}
}