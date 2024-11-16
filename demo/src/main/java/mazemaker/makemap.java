package mazemaker;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class makemap extends JFrame {

	public mazedesignwin designWin;
	public mazedesignmenu dm;
	public Page tabpane;
	public toolbar tb;
   	makemap(){
    	tabpane = new Page();
		designWin = new mazedesignwin(this); //創建設計端頁面
		dm = new mazedesignmenu(this, tabpane); //創建設計端菜單&工具列
		tb = new toolbar(this);
		designWin.add(tabpane, BorderLayout.CENTER);
   	}
}