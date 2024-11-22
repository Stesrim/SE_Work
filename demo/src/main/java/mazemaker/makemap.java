package mazemaker;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class makemap extends JFrame {

	public mazedesignwin designWin;
	public mazedesignmenu dm;
	public DrawPanel draw;
	public Page tabpane;
	public toolbar tb;
	public static State st;
	public static State sta;
	public JTabbedPane attributes;
   	makemap(){
		sta = State.portalstate;
		designWin = new mazedesignwin(this); //創建設計端頁面
    	tabpane = new Page();
		draw = new DrawPanel();
		dm = new mazedesignmenu(this, tabpane); //創建設計端菜單&工具列
		tb = new toolbar(this);
		
		designWin.add(tabpane, BorderLayout.CENTER);
		attributes = new JTabbedPane();
		attributes.setPreferredSize(new Dimension(300, designWin.getHeight())); // 增加寬度至 300 像素
		designWin.add(attributes, BorderLayout.WEST);
		JPanel temp = new JPanel();
		attributes.addTab("介紹 ", temp);
	}
}