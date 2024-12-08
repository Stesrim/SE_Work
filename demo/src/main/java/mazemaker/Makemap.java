package mazemaker;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class Makemap extends JFrame {

	public static Mazedesignwin designWin;
	public Mazedesignmenu dm;
	public DrawPanel draw;
	public WorkSpace ws;
	public Toolbar tb;

	public static State st;
	public static State sta;
	public static JTabbedPane attributes;
	public static int obstacletype;
	public static int jtype;
	public static boolean ispass;
   	Makemap(){
		sta = State.portalstate;
		designWin = new Mazedesignwin(this); //創建設計端頁面
    	ws = new WorkSpace();
		draw = new DrawPanel();
		dm = new Mazedesignmenu(this, ws); //創建設計端菜單&工具列
		tb = new Toolbar(this);
		designWin.add(ws, BorderLayout.CENTER);
		attributes = new JTabbedPane();
		attributes.setPreferredSize(new Dimension(300, designWin.getHeight())); // 增加寬度至 300 像素
		designWin.add(attributes, BorderLayout.WEST);
	}
}