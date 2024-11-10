package mazemaker;

import java.awt.Color;

import javax.swing.JFrame;

public class makemap extends JFrame {

	public mazedesignwin designWin;
	public mazedesignmenu dm;
   	makemap(){
    	designWin = new mazedesignwin(this); //創建設計端頁面
		dm = new mazedesignmenu(this); //創建設計端菜單&工具列


   	}
}